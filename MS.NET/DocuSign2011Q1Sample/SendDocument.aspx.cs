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
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Page.IsPostBack)
            {
                // Construct the envelope basics
                DocuSignAPI.Envelope envelope = new DocuSignAPI.Envelope();
                envelope.Subject = Request.Form["subject"];
                envelope.EmailBlurb = Request.Form["emailBlurb"];
                envelope.AccountId = Session["APIAccountId"].ToString();

                // Construct the recipients, documents and tabs
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
            }
        }

        protected void SendNow(DocuSignAPI.Envelope envelope)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Create and send the envelope in one step
                DocuSignAPI.EnvelopeStatus status = client.CreateAndSendEnvelope(envelope);

                // If we succeeded, go to the status
                if (status.SentSpecified)
                {
                    base.AddEnvelopeID(status.EnvelopeID);
                    Response.Redirect("GetStatusAndDocs.aspx", false);
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
                // Create the envelope (but don't send it!)
                DocuSignAPI.EnvelopeStatus status = client.CreateEnvelope(envelope);
                base.AddEnvelopeID(status.EnvelopeID);

                // If it created successfully, redirect to the embedded host
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

                    // Get and set the security settings
                    string security = Request.Form["RecipientSecurity" + i].ToString();
                    if (security != null)
                    {
                        if (security == "AccessCode")
                        {
                            r.AccessCode = Request.Form["RecipientSecuritySetting" + i].ToString();
                        }
                        else if (security == "PhoneAuthentication")
                        {
                            r.PhoneAuthentication = new DocuSignAPI.RecipientPhoneAuthentication();
                            r.PhoneAuthentication.RecipMayProvideNumber = true;
                            r.PhoneAuthentication.RecipMayProvideNumberSpecified = true;
                            r.PhoneAuthentication.RecordVoicePrint = true;
                            r.PhoneAuthentication.RecordVoicePrintSpecified = true;
                            r.IDCheckConfigurationName = "Phone Auth $";
                        }
                        else if (security == "IDCheck")
                        {
                            r.RequireIDLookup = true;
                            r.RequireIDLookupSpecified = true;
                            r.IDCheckConfigurationName = "ID Check $";
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

            if (Request.Form["stockdoc"] != null)
            {
                // Use the document that came with this sample
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
                // Upload and use any file uploads
                HttpFileCollection uploadedFiles = Request.Files;
                for (int i = 0; i < uploadedFiles.Count; i++)
                {
                    HttpPostedFile file = uploadedFiles[i];
                    DocuSignAPI.Document uploadedDocument = new DocuSignAPI.Document();
                    System.IO.Stream inStream = file.InputStream;
                    byte[] fileData = new byte[file.ContentLength];
                    inStream.Read(fileData, 0, file.ContentLength);
                    uploadedDocument.PDFBytes = fileData;
                    uploadedDocument.Name = file.FileName;
                    uploadedDocument.ID = (i + 1).ToString();
                    uploadedDocument.FileExtension = Path.GetExtension(file.FileName).ToLower();

                    Debug.Assert(uploadedDocument.PDFBytes != null);
                    runningList.Add(uploadedDocument);
                }
            }

            if (Request.Form["signerattachment"] != null)
            {
                // Add a document for signer attachments
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
                // Basic Company Tab
                DocuSignAPI.Tab company = new DocuSignAPI.Tab();
                company.Type = DocuSignAPI.TabTypeCode.Company;
                company.DocumentID = "1";
                company.PageNumber = pageTwo;
                company.RecipientID = "1";
                company.XPosition = "342";
                company.YPosition = "303";

                runningList.Add(company);

                // Basic InitialHere tab
                DocuSignAPI.Tab init1 = new DocuSignAPI.Tab();
                init1.Type = DocuSignAPI.TabTypeCode.InitialHere;
                init1.DocumentID = "1";
                init1.PageNumber = pageThree;
                init1.RecipientID = "1";
                init1.XPosition = "454";
                init1.YPosition = "281";

                runningList.Add(init1);

                // Basic SignHere tab
                DocuSignAPI.Tab sign1 = new DocuSignAPI.Tab();
                sign1.Type = DocuSignAPI.TabTypeCode.SignHere;
                sign1.DocumentID = "1";
                sign1.PageNumber = pageTwo;
                sign1.RecipientID = "1";
                sign1.XPosition = "338";
                sign1.YPosition = "330";

                runningList.Add(sign1);

                // Basic FullName Anchor tab
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

                // Basic DateSigned tab
                DocuSignAPI.Tab date1 = new DocuSignAPI.Tab();
                date1.Type = DocuSignAPI.TabTypeCode.DateSigned;
                date1.DocumentID = "1";
                date1.PageNumber = pageTwo;
                date1.RecipientID = "1";
                date1.XPosition = "343";
                date1.YPosition = "492";

                runningList.Add(date1);

                // Scaled InitialHere tab
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
                    // Basic SignHere tab
                    DocuSignAPI.Tab sign2 = new DocuSignAPI.Tab();
                    sign2.Type = DocuSignAPI.TabTypeCode.SignHere;
                    sign2.DocumentID = "1";
                    sign2.PageNumber = pageThree;
                    sign2.RecipientID = "2";
                    sign2.XPosition = "339";
                    sign2.YPosition = "97";

                    runningList.Add(sign2);

                    // Basic DateSigned tab
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
                //Custom text tab
                DocuSignAPI.Tab favColor = new DocuSignAPI.Tab();
                favColor.Type = DocuSignAPI.TabTypeCode.Custom;
                favColor.CustomTabType = DocuSignAPI.CustomTabType.Text;
                favColor.CustomTabTypeSpecified = true;
                favColor.DocumentID = "1";
                favColor.PageNumber = pageThree;
                favColor.RecipientID = "1";
                favColor.XPosition = "301";
                favColor.YPosition = "416";

                if (Request.Form["collabfields"] != null)
                {
                    favColor.SharedTab = true;
                    favColor.SharedTabSpecified = true;
                    favColor.RequireInitialOnSharedTabChange = true;
                    favColor.RequireInitialOnSharedTabChangeSpecified = true;
                }

                runningList.Add(favColor);
            }

            if (Request.Form["conditionalfields"] != null)
            {
                // Custom radio button tab
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

                // Custom radio button tab
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

                // Custom conditional text tab
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

            if (Request.Form["signerattachment"] != null)
            {
                //Basic SignerAttachment tab
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
                // Allow recipients to mark up the envelope
                envelope.AllowMarkup = true;
                envelope.AllowMarkupSpecified = true;
            }

            if (Request.Form["enablepaper"] != null)
            {
                // Allow recipients to sign on paper (called wet signing)
                envelope.EnableWetSign = true;
                envelope.EnableWetSignSpecified = true;
            }

            if (!String.IsNullOrEmpty(Request.Form["reminders"]))
            {
                // Set any reminders
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
                // Set any expirations
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
    }
}
