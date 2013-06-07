using DocuSignSample.DocuSignAPI;
using DocuSignSample.resources;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class EmbedDocuSign : BasePage
    {
        protected bool _oneSigner = true;
        protected string signerMessage;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!LoggedIn())
            {
                Response.Redirect("LogIn.aspx");
            }
            if (!Page.IsPostBack && Request.Form["__EVENTTARGET"] != logoutCtrlName)
            {
                // Check to see if we're coming back after signing as the first signer
                EnvelopeStatus status = (EnvelopeStatus)Session[Keys.EnvelopeStatus];
                if (null == status || null == Request[Keys.Event])
                {
                    hostiframe.Visible = false;
                }

                // If we are, start the second signer
                else if (Request[Keys.Event].ToString() == "SignComplete1" && status.RecipientStatuses.Length > 1)
                {
                    SignSecond(status);
                }

                // If we're finished altogether, or if one of the signers exited without completed, go to the status page
                else if ((Request[Keys.Event].ToString() == "SignComplete1" && status.RecipientStatuses.Length == 1) ||
                    Request[Keys.Event].ToString() == "SignComplete2" || null != Request["event"].ToString())
                {
                    Session[Keys.EnvelopeStatus] = null;
                    Response.Redirect("GetStatusAndDocs.aspx", false);
                }
            }
            else
            {
                // Create and send the envelope using the two different options.
                if (null != Request.Form[Keys.OneSigner])
                {
                    _oneSigner = true;
                    CreateAndSend();
                }
                else if (null != Request.Form[Keys.TwoSigners])
                {
                    _oneSigner = false;
                    CreateAndSend();
                }
            }
        }

        protected void CreateAndSend()
        {
            buttonTable.Visible = false;

            // Construct the envelope basics
            var envelope = new Envelope
                {
                    Subject = "DocuSign API SDK Example",
                    EmailBlurb = "This envelope demonstrates embedded signing",
                    AccountId = Session[Keys.ApiAccountId].ToString(),
                    Recipients = ConstructRecipients()
                };

            // Create the recipient(s)

            // Add the document to the envelope
            var stockDocument = new Document
                {
                    PDFBytes = Resources.DocuSign_Demo__111_PDF,
                    Name = "Demo Document",
                    ID = "1",
                    FileExtension = "pdf"
                };

            envelope.Documents = new Document[] { stockDocument };

            // Add the tabs to the envelope
            envelope.Tabs = AddTabs(envelope.Recipients.Length);

            APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Send the envelope and temporarily store the status in the session
                EnvelopeStatus status = client.CreateAndSendEnvelope(envelope);
                if (status.SentSpecified)
                {
                    Session[Keys.EnvelopeStatus] = status;
                    AddEnvelopeID(status.EnvelopeID);

                    // Start the first signer
                    SignFirst(status);
                }
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }
        }

        protected void SignFirst(EnvelopeStatus status)
        {
            // Create the assertion using the current time, password and demo information
            var assertion = new RequestRecipientTokenAuthenticationAssertion
                {
                    AssertionID = new Guid().ToString(),
                    AuthenticationInstant = DateTime.Now,
                    AuthenticationMethod =
                        RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password,
                    SecurityDomain = "DocuSignSample"
                };

            var recipient = status.RecipientStatuses[0];

            // Construct the URLs to which the iframe will redirect upon every event
            var urlBase = Request.Url.AbsoluteUri.Replace("EmbedDocuSign.aspx", "pop.html") + "?source=embed";
            var urls = new RequestRecipientTokenClientURLs
                {
                    OnSigningComplete = urlBase + "&event=SignComplete1",
                    OnViewingComplete = urlBase + "&event=ViewComplete1",
                    OnCancel = urlBase + "&event=Cancel1",
                    OnDecline = urlBase + "&event=Decline1",
                    OnSessionTimeout = urlBase + "&event=Timeout1",
                    OnTTLExpired = urlBase + "&event=TTLExpired1",
                    OnIdCheckFailed = urlBase + "&event=IDCheck1",
                    OnAccessCodeFailed = urlBase + "&event=AccessCode1",
                    OnException = urlBase + "&event=Exception1"
                };

            var client = CreateAPIProxy();
            String token = null;
            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(status.EnvelopeID, recipient.ClientUserId,
                                                                recipient.UserName, recipient.Email, assertion, urls);
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }

            // Set the signer message
            signerMessage = _oneSigner ? "Have the signer fill out the Envelope" : "Have the first signer fill out the Envelope";
            messagediv.Visible = true;

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes[Keys.Source] = token;
        }

        protected void SignSecond(EnvelopeStatus status)
        {
            buttonTable.Visible = false;

            // Create the assertion using the current time, password and demo information
            var assertion = new RequestRecipientTokenAuthenticationAssertion
                {
                    AssertionID = new Guid().ToString(),
                    AuthenticationInstant = DateTime.Now,
                    AuthenticationMethod =
                        RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password,
                    SecurityDomain = "DocuSignSample"
                };

            var recipient = status.RecipientStatuses[1];

            // Construct the URLs to which the iframe will redirect upon every event
            // TODO: replace urlBase with your own test url
            var urlBase = Request.Url.AbsoluteUri.Replace("EmbedDocuSign.aspx", "pop.html") + "?source=embed";
            var urls = new RequestRecipientTokenClientURLs
                {
                    OnSigningComplete = urlBase + "&event=SignComplete2",
                    OnViewingComplete = urlBase + "&event=ViewComplete2",
                    OnCancel = urlBase + "&event=Cancel2",
                    OnDecline = urlBase + "&event=Decline2",
                    OnSessionTimeout = urlBase + "&event=Timeout2",
                    OnTTLExpired = urlBase + "&event=TTLExpired2",
                    OnIdCheckFailed = urlBase + "&event=IDCheck2",
                    OnAccessCodeFailed = urlBase + "&event=AccessCode2",
                    OnException = urlBase + "&event=Exception2"
                };


            var client = CreateAPIProxy();
            String token = null;
            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(status.EnvelopeID, recipient.ClientUserId,
                                                                recipient.UserName, recipient.Email, assertion, urls);
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }

            // Set the signer message
            signerMessage = "The first signer has completed the Envelope. Now the second signer will be asked to fill out details in the Envelope.";
            messagediv.Visible = true;

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes[Keys.Source] = token;
        }

        protected Recipient[] ConstructRecipients()
        {
            // Construct the recipients
            var runningList = new List<Recipient>();
            var r1 = new Recipient
                {
                    UserName = Session[Keys.ApiUsername].ToString(),
                    Email = Session[Keys.ApiEmail].ToString(),
                    ID = "1",
                    Type = RecipientTypeCode.Signer,
                    CaptiveInfo = new RecipientCaptiveInfo {ClientUserId = "1"}
                };
            runningList.Add(r1);

            // If we're creating an envelop with two signers,
            // add the second signer with dummy credentials
            if (!_oneSigner)
            {
                var r2 = new Recipient
                    {
                        UserName = "DocuSign Recipient2",
                        Email = "DocuSignRecipient2@mailinator.com",
                        ID = "2",
                        Type = RecipientTypeCode.Signer,
                        CaptiveInfo = new RecipientCaptiveInfo {ClientUserId = "2"}
                    };
                runningList.Add(r2);
            }

            return runningList.ToArray();
        }

        private Tab[] AddTabs(int recipientCount)
        {
            var runningList = new List<Tab>();

            // Basic Company Tab
            var company = new Tab
                {
                    Type = TabTypeCode.Company,
                    DocumentID = "1",
                    PageNumber = "2",
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
                    PageNumber = "3",
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
                    PageNumber = "2",
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
                                XOffset = -123,
                                YOffset = 31,
                                Unit = UnitTypeCode.Pixels,
                                UnitSpecified = true,
                                IgnoreIfNotPresent = true,
                                IgnoreIfNotPresentSpecified = true
                            }
                };
            fullAnchor.DocumentID = "1";
            fullAnchor.PageNumber = "2";
            fullAnchor.RecipientID = "1";

            runningList.Add(fullAnchor);

            // Basic DateSigned tab
            var date1 = new Tab
                {
                    Type = TabTypeCode.DateSigned,
                    DocumentID = "1",
                    PageNumber = "2",
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
                    PageNumber = "3",
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
                        PageNumber = "3",
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
                        PageNumber = "3",
                        RecipientID = "2",
                        XPosition = "343",
                        YPosition = "197"
                    };

                runningList.Add(date2);
            }

            //Custom text tab
            var favColor = new Tab
                {
                    Type = TabTypeCode.Custom,
                    CustomTabType = CustomTabType.Text,
                    CustomTabTypeSpecified = true,
                    DocumentID = "1",
                    PageNumber = "3",
                    RecipientID = "1",
                    XPosition = "301",
                    YPosition = "416"
                };

            runningList.Add(favColor);

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
                    PageNumber = "3",
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
                    PageNumber = "3",
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
                    PageNumber = "3",
                    RecipientID = "1",
                    XPosition = "202"
                };
            data1.XPosition = "265";
            data1.YPosition = "547";

            runningList.Add(data1);

            return runningList.ToArray();
        }


    }
}