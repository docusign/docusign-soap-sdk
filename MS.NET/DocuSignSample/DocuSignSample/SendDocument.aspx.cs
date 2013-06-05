using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Web;
using DocuSignSample.DocuSignAPI;
using DocuSignSample.resources;

namespace DocuSignSample
{
    public partial class SendDocument : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!LoggedIn())
            {
                Response.Redirect("LogIn.aspx");
            }
                if (Page.IsPostBack && Request.Form["__EVENTTARGET"] != logoutCtrlName)
                {
                    // Construct the envelope basics
                    var envelope = new Envelope
                        {
                            Subject = Request.Form[Keys.Subject],
                            EmailBlurb = Request.Form[Keys.EmailBlurb],
                            AccountId = Session[Keys.ApiAccountId].ToString(),
                            Recipients = ConstructRecipients(),
                            Documents = GetDocuments()
                        };

                    // Construct the recipients, documents and tabs
                    envelope.Tabs = AddTabs(envelope.Recipients.Length);
                    envelope = ProcessOptions(envelope);

                    if (null != Request.Form[Keys.SendNow])
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

        protected void SendNow(Envelope envelope)
        {
            APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Create and send the envelope in one step
                EnvelopeStatus status = client.CreateAndSendEnvelope(envelope);

                // If we succeeded, go to the status
                if (status.SentSpecified)
                {
                    AddEnvelopeID(status.EnvelopeID);
                    Response.Redirect("GetStatusAndDocs.aspx", false);
                }

            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }
        }

        protected void EmbedSending(Envelope envelope)
        {
            APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Create the envelope (but don't send it!)
                EnvelopeStatus status = client.CreateEnvelope(envelope);
                AddEnvelopeID(status.EnvelopeID);

                // If it created successfully, redirect to the embedded host
                if (status.Status == EnvelopeStatusCode.Created)
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

        private Recipient[] ConstructRecipients()
        {
            // Construct the recipients
            var runningList = new List<Recipient>();

            for (int i = 1; i <= Request.Form.Count; i++)
            {
                if (null !=Request.Form[Keys.RecipientName + i])
                {
                    var r = new Recipient
                        {
                            UserName = Request.Form[Keys.RecipientName + i],
                            Email = Request.Form[Keys.RecipientEmail + i]
                        };

                    // Get and set the security settings
                    string security = Request.Form[Keys.RecipientSecurity + i];
                    if (null != security)
                    {
                        switch (security)
                        {
                            case "AccessCode":
                                r.AccessCode = Request.Form[Keys.RecipientSecuritySetting + i].ToString();
                                break;
                            case "PhoneAuthentication":
                                r.PhoneAuthentication = new RecipientPhoneAuthentication
                                    {
                                        RecipMayProvideNumber = true,
                                        RecipMayProvideNumberSpecified = true,
                                        RecordVoicePrint = true,
                                        RecordVoicePrintSpecified = true
                                    };
                                r.IDCheckConfigurationName = "Phone Auth $";
                                break;
                            case "IDCheck":
                                r.RequireIDLookup = true;
                                r.RequireIDLookupSpecified = true;
                                r.IDCheckConfigurationName = "ID Check $";
                                break;
                        }
                    }
                    r.ID = i.ToString();
                    r.Type = RecipientTypeCode.Signer;

                    if (null == Request.Form[Keys.RecipientInviteToggle + i])
                    {
                        // we want an embedded signer
                        r.CaptiveInfo = new RecipientCaptiveInfo {ClientUserId = i.ToString()};
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

        private Document[] GetDocuments()
        {
            var runningList = new List<Document>();

            if (null != Request.Form[Keys.StockDoc])
            {
                // Use the document that came with this sample
                var stockDocument = new Document
                    {
                        PDFBytes = Resources.DocuSign_Demo__111_PDF,
                        Name = "Demo Document",
                        ID = "1",
                        FileExtension = "pdf"
                    };

                Debug.Assert(null != stockDocument.PDFBytes);
                runningList.Add(stockDocument);

            }
            else
            {
                // Upload and use any file uploads
                HttpFileCollection uploadedFiles = Request.Files;
                for (int i = 0; i < uploadedFiles.Count; i++)
                {
                    HttpPostedFile file = uploadedFiles[i];
                    if (file.ContentLength > 0)
                    {
                        Stream inStream = file.InputStream;
                        var fileData = new byte[file.ContentLength];
                        inStream.Read(fileData, 0, file.ContentLength);
                        var uploadedDocument = new Document
                            {
                                PDFBytes = fileData,
                                Name = file.FileName,
                                ID = (i + 1).ToString(),
                                FileExtension = Path.GetExtension(file.FileName).ToLower()
                            };

                        Debug.Assert(null != uploadedDocument.PDFBytes);
                        runningList.Add(uploadedDocument);
                    }
                }
            }

            if (null != Request.Form[Keys.SignerAttachment])
            {
                // Add a document for signer attachments
                var signerAttachment = new Document
                    {
                        PDFBytes = Resources.BlankPDF,
                        Name = "Signer Attachment",
                        ID = "2",
                        FileExtension = "pdf",
                        AttachmentDescription = "Please attach your document here"
                    };

                Debug.Assert(null != signerAttachment.PDFBytes);
                runningList.Add(signerAttachment);
            }

            return runningList.ToArray();
        }

        private Tab[] AddTabs(int recipientCount)
        {
            var runningList = new List<Tab>();
            string pageTwo = (null != Request.Form[Keys.StockDoc]) ? "2" : "1";
            string pageThree = (null != Request.Form[Keys.StockDoc]) ? "3" : "1";
            if (null != Request.Form[Keys.AddSigs])
            {
                AddCompanyTab(recipientCount, pageTwo, runningList, pageThree);
            }

            if (null != Request.Form[Keys.FormFields])
            {
                AddCustomTextTab(pageThree, runningList);
            }

            if (null != Request.Form[Keys.ConditionalFields])
            {
                AddCustomRadioButtonTab(pageThree, runningList);
            }

            if (null != Request.Form[Keys.SignerAttachment])
            {
                AddSignerAttachment(runningList);
            }

            return runningList.ToArray();
        }

        private static void AddCompanyTab(int recipientCount, string pageTwo, List<Tab> runningList, string pageThree)
        {
            // Basic Company Tab
            var company = new Tab
                {
                    Type = TabTypeCode.Company,
                    DocumentID = "1",
                    PageNumber = pageTwo,
                    RecipientID = "1",
                    XPosition = "342",
                    YPosition = "303"
                };

            runningList.Add(company);

            // Basic InitialHere tab
            var init1 = new Tab
                {
                    Type = TabTypeCode.InitialHere,
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "454",
                    YPosition = "281"
                };

            runningList.Add(init1);

            // Basic SignHere tab
            var sign1 = new Tab
                {
                    Type = TabTypeCode.SignHere,
                    DocumentID = "1",
                    PageNumber = pageTwo,
                    RecipientID = "1",
                    XPosition = "338",
                    YPosition = "330"
                };

            runningList.Add(sign1);

            // Basic FullName Anchor tab
            var fullAnchor = new Tab
                {
                    Type = TabTypeCode.FullName,
                    AnchorTabItem =
                        new AnchorTab
                            {
                                AnchorTabString = "(printed)",
                                XOffset = -90,
                                YOffset = -70,
                                Unit = UnitTypeCode.Pixels,
                                UnitSpecified = true,
                                IgnoreIfNotPresent = true,
                                IgnoreIfNotPresentSpecified = true
                            },
                    DocumentID = "1",
                    PageNumber = pageTwo,
                    RecipientID = "1"
                };

            runningList.Add(fullAnchor);

            // Basic DateSigned tab
            var date1 = new Tab
                {
                    Type = TabTypeCode.DateSigned,
                    DocumentID = "1",
                    PageNumber = pageTwo,
                    RecipientID = "1",
                    XPosition = "343",
                    YPosition = "492"
                };

            runningList.Add(date1);

            // Scaled InitialHere tab
            var init2 = new Tab
                {
                    Type = TabTypeCode.InitialHere,
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "179",
                    YPosition = "583",
                    ScaleValue = 0.6M,
                    ScaleValueSpecified = true
                };

            runningList.Add(init2);

            if (recipientCount > 1)
            {
                // Basic SignHere tab
                var sign2 = new Tab
                    {
                        Type = TabTypeCode.SignHere,
                        DocumentID = "1",
                        PageNumber = pageThree,
                        RecipientID = "2",
                        XPosition = "339",
                        YPosition = "97"
                    };

                runningList.Add(sign2);

                // Basic DateSigned tab
                var date2 = new Tab
                    {
                        Type = TabTypeCode.DateSigned,
                        DocumentID = "1",
                        PageNumber = pageThree,
                        RecipientID = "2",
                        XPosition = "343",
                        YPosition = "197"
                    };

                runningList.Add(date2);
            }
        }

        private void AddCustomTextTab(string pageThree, List<Tab> runningList)
        {
            //Custom text tab
            var favColor = new Tab
                {
                    Type = TabTypeCode.Custom,
                    CustomTabType = CustomTabType.Text,
                    CustomTabTypeSpecified = true,
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "301",
                    YPosition = "416"
                };

            if (null != Request.Form[Keys.CollabFields])
            {
                favColor.SharedTab = true;
                favColor.SharedTabSpecified = true;
                favColor.RequireInitialOnSharedTabChange = true;
                favColor.RequireInitialOnSharedTabChangeSpecified = true;
            }

            runningList.Add(favColor);
        }

        private static void AddCustomRadioButtonTab(string pageThree, List<Tab> runningList)
        {
            // Custom radio button tab
            var fruitNo = new Tab
                {
                    Type = TabTypeCode.Custom,
                    CustomTabType = CustomTabType.Radio,
                    CustomTabTypeSpecified = true,
                    CustomTabRadioGroupName = "fruit",
                    TabLabel = "No",
                    Name = "No",
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "269",
                    YPosition = "508"
                };

            runningList.Add(fruitNo);

            // Custom radio button tab
            var fruitYes = new Tab
                {
                    Type = TabTypeCode.Custom,
                    CustomTabType = CustomTabType.Radio,
                    CustomTabTypeSpecified = true,
                    CustomTabRadioGroupName = "fruit",
                    TabLabel = "Yes",
                    Name = "Yes",
                    Value = "Yes",
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "202",
                    YPosition = "509"
                };

            runningList.Add(fruitYes);

            // Custom conditional text tab
            var data1 = new Tab
                {
                    Type = TabTypeCode.Custom,
                    CustomTabType = CustomTabType.Text,
                    CustomTabTypeSpecified = true,
                    ConditionalParentLabel = "fruit",
                    ConditionalParentValue = "Yes",
                    Name = "Fruit",
                    TabLabel = "Preferred Fruit",
                    DocumentID = "1",
                    PageNumber = pageThree,
                    RecipientID = "1",
                    XPosition = "265",
                    YPosition = "547",
                };

            runningList.Add(data1);
        }

        private static void AddSignerAttachment(List<Tab> runningList)
        {
            //Basic SignerAttachment tab
            var attach = new Tab
                {
                    Type = TabTypeCode.SignerAttachment,
                    TabLabel = "Signer Attachment",
                    Name = "Signer Attachment",
                    DocumentID = "2",
                    PageNumber = "1",
                    RecipientID = "1",
                    XPosition = "20",
                    YPosition = "20"
                };

            runningList.Add(attach);
        }

        private Envelope ProcessOptions(Envelope envelope)
        {
            if (null != Request.Form[Keys.Markup])
            {
                // Allow recipients to mark up the envelope
                envelope.AllowMarkup = true;
                envelope.AllowMarkupSpecified = true;
            }

            if (null != Request.Form[Keys.EnablePaper])
            {
                // Allow recipients to sign on paper (called wet signing)
                envelope.EnableWetSign = true;
                envelope.EnableWetSignSpecified = true;
            }

            if (!String.IsNullOrEmpty(Request.Form[Keys.Reminders]))
            {
                // Set any reminders
                DateTime remind = Convert.ToDateTime(Request.Form[Keys.Reminders]);
                int difference = (remind - DateTime.Today).Days;

                if (null == envelope.Notification)
                {
                    envelope.Notification = new Notification();
                }
                envelope.Notification.Reminders = new Reminders
                    {
                        ReminderEnabled = true,
                        ReminderDelay = difference.ToString(),
                        ReminderFrequency = "2"
                    };
            }

            if (!String.IsNullOrEmpty(Request.Form[Keys.Expiration]))
            {
                // Set any expirations
                DateTime expire = Convert.ToDateTime(Request.Form[Keys.Expiration]);
                int difference = (expire - DateTime.Today).Days;

                if (null == envelope.Notification)
                {
                    envelope.Notification = new Notification();
                }

                envelope.Notification.Expirations = new Expirations
                    {
                        ExpireEnabled = true,
                        ExpireAfter = difference.ToString(),
                        ExpireWarn = (difference - 2).ToString()
                    };
            }

            return envelope;
        }
    }
}
