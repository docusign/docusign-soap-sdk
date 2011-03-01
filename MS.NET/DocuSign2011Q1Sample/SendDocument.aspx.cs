using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class SendDocument : BasePage
    {
        // Need to instantiate it to avoid runtime error
        protected DocuSignAPI.EnvelopeStatus _status = new DocuSignAPI.EnvelopeStatus();

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Page.IsPostBack)
            {
                //if something do this
                // Construct the envelope basics
                DocuSignAPI.Envelope envelope = new DocuSignAPI.Envelope();
                envelope.Subject = Request.Form["subject"];
                envelope.EmailBlurb = Request.Form["emailBlurb"];
                envelope.AccountId = Session["APIAccountId"].ToString();

                envelope.Recipients = ConstructRecipients();
                envelope.Documents = GetDocuments();
                envelope.Tabs = AddTabs(envelope.Recipients.Length);
                envelope = ProcessOptions(envelope);

                if (Request.Form["SendNow"] != null)
                {
                    //we want to send the form ASAP
                    SendNow(envelope);
                }

                else
                {
                    //edit before sending -- embedded sending
                    EmbedSending(envelope);
                }
                //else
            }
            else
            {
                if (Request["void"] != null)
                {
                    VoidEnvelope(Request["id"]);
                }
            }
        }

        protected void VoidEnvelope(string id)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                DocuSignAPI.VoidEnvelopeStatus status = client.VoidEnvelope(id, "Envelope voided by sender");
                if (status.VoidSuccess)
                {
                    Response.Redirect("SendDocument.aspx", false);
                }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        protected void SendNow(DocuSignAPI.Envelope envelope)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                DocuSignAPI.EnvelopeStatus status = client.CreateAndSendEnvelope(envelope);
                if (status.SentSpecified)
                {
                    _status = status;
                    base.AddEnvelopeID(status.EnvelopeID);
                    CreateStatusTable();
                    string s = "<script type=\"text/javascript\">dialogOpen()</script>";
                    ClientScript.RegisterStartupScript(Page.GetType(), "openscript", s);
                }

            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        protected void EmbedSending(DocuSignAPI.Envelope envelope)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                DocuSignAPI.EnvelopeStatus status = client.CreateEnvelope(envelope);
                base.AddEnvelopeID(status.EnvelopeID);
                if (status.Status == DocuSignAPI.EnvelopeStatusCode.Created)
                {
                    string navURL = String.Format("{0}?envelopeID={1}&accountID={2}&source=Document", "EmbeddedHost.aspx", status.EnvelopeID,
                        envelope.AccountId);
                    Response.Redirect(navURL, false);
                }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        private DocuSignAPI.Recipient[] ConstructRecipients()
        {
            // Construct the recipients
            List<DocuSignAPI.Recipient> runningList = new List<DocuSignAPI.Recipient>();

            for (int i = 1; i <= Request.Form.Count; i++)
            {
                if (Request.Form["RecipientName" + i] != null)
                {
                    DocuSignAPI.Recipient r = new DocuSignAPI.Recipient();
                    r.UserName = Request.Form["RecipientName" + i].ToString();
                    r.Email = Request.Form["RecipientEmail" + i].ToString();
                    string security = Request.Form["RecipientSecurity"+i].ToString();
                    if (security != null)
                    {
                            if (security == "AccessCode")
                        {
                            r.AccessCode = Request.Form["RecipientSecuritySetting" + i].ToString();
                        }
                        else if (security == "PhoneAuthentication")
                        {
                            r.RequireIDLookup = true;
                            r.RequireIDLookupSpecified = true;
                            r.PhoneAuthentication = new DocuSignAPI.RecipientPhoneAuthentication();
                            r.PhoneAuthentication.RecipMayProvideNumber = true;
                            r.PhoneAuthentication.RecipMayProvideNumberSpecified = true;
                            r.PhoneAuthentication.RecordVoicePrint = true;
                            r.PhoneAuthentication.RecordVoicePrintSpecified = true;
                        }
                    }
                    r.ID = i.ToString();
                    r.Type = DocuSignAPI.RecipientTypeCode.Signer;

                    if (Request.Form["RecipientInviteToggle" + i] == null)
                    {
                        // we want an embedded signer
                        r.CaptiveInfo = new DocuSignAPI.RecipientCaptiveInfo();
                        r.CaptiveInfo.ClientUserId = i.ToString();
                    }
                    runningList.Add(r);
                }
                else
                {
                    break;
                }
            }
            return runningList.ToArray();
        }

        private DocuSignAPI.Document[] GetDocuments()
        {
            List<DocuSignAPI.Document> runningList = new List<DocuSignAPI.Document>();

            // For now, assume that we are using the stock document
            if (Request.Form["stockdoc"] != null)
            {
                DocuSignAPI.Document stockDocument = new DocuSignAPI.Document();
                stockDocument.PDFBytes = Resources.DocuSign_Demo__111_PDF;
                stockDocument.Name = "Demo Document";
                stockDocument.ID = "1";
                stockDocument.FileExtension = "pdf";

                Debug.Assert(stockDocument.PDFBytes != null);
                runningList.Add(stockDocument);

            }
            else
            {
                HttpFileCollection uploadedFiles = Request.Files;
                for(int i = 0; i< uploadedFiles.Count; i++)
                {
                    HttpPostedFile file = uploadedFiles[i];
                    DocuSignAPI.Document uploadedDocument = new DocuSignAPI.Document();
                    System.IO.Stream inStream = file.InputStream;
                    byte[] fileData = new byte[file.ContentLength];
                    inStream.Read(fileData, 0, file.ContentLength);
                    uploadedDocument.PDFBytes = fileData;
                    uploadedDocument.Name = file.FileName;
                    uploadedDocument.ID = (i+1).ToString();
                    uploadedDocument.FileExtension = Path.GetExtension(file.FileName).ToLower();

                    Debug.Assert(uploadedDocument.PDFBytes != null);
                    runningList.Add(uploadedDocument);
                }
            }

            if (Request.Form["signerattachment"] != null)
            {
                DocuSignAPI.Document signerAttachment = new DocuSignAPI.Document();
                signerAttachment.PDFBytes = Resources.BlankPDF;
                signerAttachment.Name = "Signer Attachment";
                signerAttachment.ID = "2";
                signerAttachment.FileExtension = "pdf";
                signerAttachment.AttachmentDescription = "Please attach your document here";

                Debug.Assert(signerAttachment.PDFBytes != null);
                runningList.Add(signerAttachment);
            }

            return runningList.ToArray();
        }

        private DocuSignAPI.Tab[] AddTabs(int recipientCount)
        {
            List<DocuSignAPI.Tab> runningList = new List<DocuSignAPI.Tab>();
            string pageTwo = (Request.Form["stockdoc"] != null) ? "2" : "1";
            string pageThree = (Request.Form["stockdoc"] != null) ? "3" : "1";
            if (Request.Form["addsigs"] != null)
            {
                DocuSignAPI.Tab company = new DocuSignAPI.Tab();
                company.Type = DocuSignAPI.TabTypeCode.Company;
                company.DocumentID = "1";
                company.PageNumber = pageTwo;
                company.RecipientID = "1";
                company.XPosition = "342";
                company.YPosition = "303";

                runningList.Add(company);

                DocuSignAPI.Tab init1 = new DocuSignAPI.Tab();
                init1.Type = DocuSignAPI.TabTypeCode.InitialHere;
                init1.DocumentID = "1";
                init1.PageNumber = pageThree;
                init1.RecipientID = "1";
                init1.XPosition = "454";
                init1.YPosition = "281";

                runningList.Add(init1);

                DocuSignAPI.Tab sign1 = new DocuSignAPI.Tab();
                sign1.Type = DocuSignAPI.TabTypeCode.SignHere;
                sign1.DocumentID = "1";
                sign1.PageNumber = pageTwo;
                sign1.RecipientID = "1";
                sign1.XPosition = "338";
                sign1.YPosition = "330";

                runningList.Add(sign1);    

                DocuSignAPI.Tab fullAnchor = new DocuSignAPI.Tab();
                fullAnchor.Type = DocuSignAPI.TabTypeCode.FullName;
                fullAnchor.AnchorTabItem = new DocuSignAPI.AnchorTab();
                fullAnchor.AnchorTabItem.AnchorTabString = "(printed)";
                fullAnchor.AnchorTabItem.XOffset = -90;
                fullAnchor.AnchorTabItem.YOffset = -70;
                fullAnchor.AnchorTabItem.Unit = DocuSignAPI.UnitTypeCode.Pixels;
                fullAnchor.AnchorTabItem.UnitSpecified = true;
                fullAnchor.AnchorTabItem.IgnoreIfNotPresent = true;
                fullAnchor.AnchorTabItem.IgnoreIfNotPresentSpecified = true;
                fullAnchor.DocumentID = "1";
                fullAnchor.PageNumber = pageTwo;
                fullAnchor.RecipientID = "1";

                runningList.Add(fullAnchor);

                DocuSignAPI.Tab date1 = new DocuSignAPI.Tab();
                date1.Type = DocuSignAPI.TabTypeCode.DateSigned;
                date1.DocumentID = "1";
                date1.PageNumber = pageTwo;
                date1.RecipientID = "1";
                date1.XPosition = "343";
                date1.YPosition = "492";

                runningList.Add(date1);

                DocuSignAPI.Tab init2 = new DocuSignAPI.Tab();
                init2.Type = DocuSignAPI.TabTypeCode.InitialHere;
                init2.DocumentID = "1";
                init2.PageNumber = pageThree;
                init2.RecipientID = "1";
                init2.XPosition = "179";
                init2.YPosition = "583";
                init2.ScaleValue = 0.6M;
                init2.ScaleValueSpecified = true;

                runningList.Add(init2);

                if (recipientCount > 1)
                {
                    DocuSignAPI.Tab sign2 = new DocuSignAPI.Tab();
                    sign2.Type = DocuSignAPI.TabTypeCode.SignHere;
                    sign2.DocumentID = "1";
                    sign2.PageNumber = pageThree;
                    sign2.RecipientID = "2";
                    sign2.XPosition = "339";
                    sign2.YPosition = "97";

                    runningList.Add(sign2);

                    DocuSignAPI.Tab date2 = new DocuSignAPI.Tab();
                    date2.Type = DocuSignAPI.TabTypeCode.DateSigned;
                    date2.DocumentID = "1";
                    date2.PageNumber = pageThree;
                    date2.RecipientID = "2";
                    date2.XPosition = "343";
                    date2.YPosition = "197";

                    runningList.Add(date2);
                }
            }

            if (Request.Form["formfields"] != null)
            {
                DocuSignAPI.Tab favColor = new DocuSignAPI.Tab();
                favColor.Type = DocuSignAPI.TabTypeCode.Custom;
                favColor.CustomTabType = DocuSignAPI.CustomTabType.Text;
                favColor.CustomTabTypeSpecified = true;
                favColor.DocumentID = "1";
                favColor.PageNumber = pageThree;
                favColor.RecipientID = "1";
                favColor.XPosition = "301";
                favColor.YPosition = "416";

                runningList.Add(favColor);
            }

            if (Request.Form["conditionalfields"] != null)
            {
                DocuSignAPI.Tab fruitNo = new DocuSignAPI.Tab();
                fruitNo.Type = DocuSignAPI.TabTypeCode.Custom;
                fruitNo.CustomTabType = DocuSignAPI.CustomTabType.Radio;
                fruitNo.CustomTabTypeSpecified = true;
                fruitNo.CustomTabRadioGroupName = "fruit";
                fruitNo.TabLabel = "No";
                fruitNo.Name = "No";
                fruitNo.DocumentID = "1";
                fruitNo.PageNumber = pageThree;
                fruitNo.RecipientID = "1";
                fruitNo.XPosition = "269";
                fruitNo.YPosition = "508";

                runningList.Add(fruitNo);

                DocuSignAPI.Tab fruitYes = new DocuSignAPI.Tab();
                fruitYes.Type = DocuSignAPI.TabTypeCode.Custom;
                fruitYes.CustomTabType = DocuSignAPI.CustomTabType.Radio;
                fruitYes.CustomTabTypeSpecified = true;
                fruitYes.CustomTabRadioGroupName = "fruit";
                fruitYes.TabLabel = "Yes";
                fruitYes.Name = "Yes";
                fruitYes.Value = "Yes";
                fruitYes.DocumentID = "1";
                fruitYes.PageNumber = pageThree;
                fruitYes.RecipientID = "1";
                fruitYes.XPosition = "202";
                fruitYes.YPosition = "509";

                runningList.Add(fruitYes);

                DocuSignAPI.Tab data1 = new DocuSignAPI.Tab();
                data1.Type = DocuSignAPI.TabTypeCode.Custom;
                data1.CustomTabType = DocuSignAPI.CustomTabType.Text;
                data1.CustomTabTypeSpecified = true;
                data1.ConditionalParentLabel = "fruit";
                data1.ConditionalParentValue = "Yes";
                data1.Name = "Fruit";
                data1.TabLabel = "Preferred Fruit";
                data1.DocumentID = "1";
                data1.PageNumber = pageThree;
                data1.RecipientID = "1";
                data1.XPosition = "202";
                data1.XPosition = "265";
                data1.YPosition = "547";

                runningList.Add(data1);
            }

            if (Request.Form["collabfields"] != null)
            {

            }

            if (Request.Form["signerattachment"] != null)
            {
                DocuSignAPI.Tab attach = new DocuSignAPI.Tab();
                attach.Type = DocuSignAPI.TabTypeCode.SignerAttachment;
                attach.TabLabel = "Signer Attachment";
                attach.Name = "Signer Attachment";
                attach.DocumentID = "2";
                attach.PageNumber = "1";
                attach.RecipientID = "1";
                attach.XPosition = "20";
                attach.YPosition = "20";

                runningList.Add(attach);
            }

            return runningList.ToArray();
        }

        private DocuSignAPI.Envelope ProcessOptions(DocuSignAPI.Envelope envelope)
        {
            if (Request.Form["markup"] != null)
            {
                envelope.AllowMarkup = true;
                envelope.AllowMarkupSpecified = true;
            }

            if (Request.Form["enablepaper"] != null)
            {
                envelope.EnableWetSign = true;
                envelope.EnableWetSignSpecified = true;
            }

            if (!String.IsNullOrEmpty(Request.Form["reminders"]))
            {
                DateTime remind = Convert.ToDateTime(Request.Form["reminders"]);
                int difference = (remind - DateTime.Today).Days;

                if (envelope.Notification == null)
                {
                    envelope.Notification = new DocuSignAPI.Notification();
                }
                envelope.Notification.Reminders = new DocuSignAPI.Reminders();
                envelope.Notification.Reminders.ReminderEnabled = true;
                envelope.Notification.Reminders.ReminderDelay = difference.ToString();
                envelope.Notification.Reminders.ReminderFrequency = "2";
            }

            if (!String.IsNullOrEmpty(Request.Form["expiration"]))
            {
                DateTime expire = Convert.ToDateTime(Request.Form["expiration"]);
                int difference = (expire - DateTime.Today).Days;

                if (envelope.Notification == null)
                {
                    envelope.Notification = new DocuSignAPI.Notification();
                }

                envelope.Notification.Expirations = new DocuSignAPI.Expirations();
                envelope.Notification.Expirations.ExpireEnabled = true;
                envelope.Notification.Expirations.ExpireAfter = difference.ToString();
                envelope.Notification.Expirations.ExpireWarn = (difference - 2).ToString();
            }

            return envelope;
        }

        protected void CreateStatusTable()
        {
            foreach (DocuSignAPI.RecipientStatus recipient in _status.RecipientStatuses)
            {
                System.Web.UI.HtmlControls.HtmlTableRow row = new System.Web.UI.HtmlControls.HtmlTableRow();
                System.Web.UI.HtmlControls.HtmlTableCell ctrl = new System.Web.UI.HtmlControls.HtmlTableCell();

                //TODO add image control
                System.Web.UI.HtmlControls.HtmlButton button = new System.Web.UI.HtmlControls.HtmlButton();
                button.Attributes["onclick"] = "toggle(\"" + recipient.Email + "\");";
                ctrl.Controls.Add(button);
                System.Web.UI.HtmlControls.HtmlTableCell name = new System.Web.UI.HtmlControls.HtmlTableCell();
                name.InnerText = recipient.UserName + " (" + recipient.Type.ToString() + ")";

                row.Cells.Add(ctrl);
                row.Cells.Add(name);

                // If the recipient was an embedded signer, add the "Start Signing" button
                if (recipient.ClientUserId != null)
                {
                    System.Web.UI.HtmlControls.HtmlButton signButton = new System.Web.UI.HtmlControls.HtmlButton();
                    signButton.InnerText = "Start Signing";
                    signButton.Attributes.Add("recipientID", recipient.RecipientId);
                    signButton.Attributes.Add("envelopeID", _status.EnvelopeID);
                    signButton.Attributes.Add("clientID", recipient.ClientUserId);
                    string url = String.Format("EmbeddedHost.aspx?source=SendDocument&eid={0}&uname={1}&email={2}&cid={3}", _status.EnvelopeID, recipient.UserName, recipient.Email, recipient.ClientUserId);
                    signButton.Attributes.Add("onclick", "window.open('"+ url + "');");
                    //signButton.ServerClick += new EventHandler(this.OnStartSigning);
                    System.Web.UI.HtmlControls.HtmlTableCell sign = new System.Web.UI.HtmlControls.HtmlTableCell();
                    sign.Controls.Add(signButton);
                    row.Cells.Add(sign);
                }

                statusTable.Rows.Add(row);
                System.Web.UI.HtmlControls.HtmlTableRow tableRow = new System.Web.UI.HtmlControls.HtmlTableRow();
                tableRow.Attributes["id"] = recipient.Email;
                tableRow.Attributes["style"] = "display:none;";
                System.Web.UI.HtmlControls.HtmlTableCell spacing = new System.Web.UI.HtmlControls.HtmlTableCell();
                spacing.Attributes["class"] = "space";
                tableRow.Cells.Add(spacing);
                System.Web.UI.HtmlControls.HtmlTableCell tableCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                System.Web.UI.HtmlControls.HtmlTable tabTable = new System.Web.UI.HtmlControls.HtmlTable();

                foreach (DocuSignAPI.TabStatus tab in recipient.TabStatuses)
                {
                    System.Web.UI.HtmlControls.HtmlTableRow tabRow = new System.Web.UI.HtmlControls.HtmlTableRow();
                    System.Web.UI.HtmlControls.HtmlTableCell tabCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                    tabCell.InnerText = tab.TabType.ToString() + tab.TabName + ": " + tab.TabValue;
                    tabRow.Cells.Add(tabCell);
                    tabTable.Rows.Add(tabRow);
                }
                tableCell.Controls.Add(tabTable);
                tableCell.Attributes["class"] = "indent";
                tableRow.Cells.Add(tableCell);
                statusTable.Rows.Add(tableRow);
            }
        }

        protected void OnStartSigning(object sender, EventArgs e)
        {
            int i = 0;
        }
    }
}
