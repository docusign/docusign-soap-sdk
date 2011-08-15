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
                DocuSignAPI.EnvelopeStatus status = (DocuSignAPI.EnvelopeStatus)Session["EnvelopeStatus"];
                if (status == null || Request["event"] == null)
                {
                    hostiframe.Visible = false;
                }

                // If we are, start the second signer
                else if (Request["event"].ToString() == "SignComplete1" && status.RecipientStatuses.Length > 1)
                {
                    SignSecond(status);
                }

                // If we're finished altogether, or if one of the signers exited without completed, go to the status page
                else if ((Request["event"].ToString() == "SignComplete1" && status.RecipientStatuses.Length == 1) ||
                    Request["event"].ToString() == "SignComplete2" || !(Request["event"].ToString() == null))
                {
                    Session["EnvelopeStatus"] = null;
                    Response.Redirect("GetStatusAndDocs.aspx", false);
                }
            }
            else
            {
                // Create and send the envelope using the two different options.
                if (Request.Form["OneSigner"] != null)
                {
                    _oneSigner = true;
                    CreateAndSend();
                }
                else if (Request.Form["TwoSigners"] != null)
                {
                    _oneSigner = false;
                    CreateAndSend();
                }
            }
        }

        protected void CreateAndSend()
        {
            DocuSignAPI.EnvelopeStatus status = null;
            buttonTable.Visible = false;

            // Construct the envelope basics
            DocuSignAPI.Envelope envelope = new DocuSignAPI.Envelope();
            envelope.Subject = "DocuSign API SDK Example";
            envelope.EmailBlurb = "This envelope demonstrates embedded signing";
            envelope.AccountId = Session["APIAccountId"].ToString();

            // Create the recipient(s)
            envelope.Recipients = ConstructRecipients();

            // Add the document to the envelope
            DocuSignAPI.Document stockDocument = new DocuSignAPI.Document();
            stockDocument.PDFBytes = Resources.DocuSign_Demo__111_PDF;
            stockDocument.Name = "Demo Document";
            stockDocument.ID = "1";
            stockDocument.FileExtension = "pdf";

            envelope.Documents = new DocuSignAPI.Document[] { stockDocument };

            // Add the tabs to the envelope
            envelope.Tabs = AddTabs(envelope.Recipients.Length);

            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Send the envelope and temporarily store the status in the session
                status = client.CreateAndSendEnvelope(envelope);
                if (status.SentSpecified)
                {
                    Session["EnvelopeStatus"] = status;
                    base.AddEnvelopeID(status.EnvelopeID);

                    // Start the first signer
                    SignFirst(status);
                }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        protected void SignFirst(DocuSignAPI.EnvelopeStatus status)
        {
            // Create the assertion using the current time, password and demo information
            DocuSignAPI.RequestRecipientTokenAuthenticationAssertion assertion = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion();
            assertion.AssertionID = new Guid().ToString();
            assertion.AuthenticationInstant = DateTime.Now;
            assertion.AuthenticationMethod = DocuSignAPI.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password;
            assertion.SecurityDomain = "DocuSignSample";
            
            DocuSignAPI.RecipientStatus recipient = status.RecipientStatuses[0];

            // Construct the URLs to which the iframe will redirect upon every event
            DocuSignAPI.RequestRecipientTokenClientURLs urls = new DocuSignAPI.RequestRecipientTokenClientURLs();

            String urlBase = Request.Url.AbsoluteUri.Replace("EmbedDocuSign.aspx", "pop.html") + "?source=embed";
            urls.OnSigningComplete = urlBase + "&event=SignComplete1";
            urls.OnViewingComplete = urlBase + "&event=ViewComplete1";
            urls.OnCancel = urlBase + "&event=Cancel1";
            urls.OnDecline = urlBase + "&event=Decline1";
            urls.OnSessionTimeout = urlBase + "&event=Timeout1";
            urls.OnTTLExpired = urlBase + "&event=TTLExpired1";
            urls.OnIdCheckFailed = urlBase + "&event=IDCheck1";
            urls.OnAccessCodeFailed = urlBase + "&event=AccessCode1";
            urls.OnException = urlBase + "&event=Exception1";

            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            String token = null;
            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(status.EnvelopeID, recipient.ClientUserId,
                                                                recipient.UserName, recipient.Email, assertion, urls);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Set the signer message
            signerMessage = "Have the first signer fill out the Envelope";
            messagediv.Visible = true;

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes["src"] = token;
        }

        protected void SignSecond(DocuSignAPI.EnvelopeStatus status)
        {
            // Create the assertion using the current time, password and demo information
            DocuSignAPI.RequestRecipientTokenAuthenticationAssertion assertion = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion();
            assertion.AssertionID = new Guid().ToString();
            assertion.AuthenticationInstant = DateTime.Now;
            assertion.AuthenticationMethod = DocuSignAPI.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password;
            assertion.SecurityDomain = "DocuSignSample";

            DocuSignAPI.RecipientStatus recipient = status.RecipientStatuses[1];

            // Construct the URLs to which the iframe will redirect upon every event
            DocuSignAPI.RequestRecipientTokenClientURLs urls = new DocuSignAPI.RequestRecipientTokenClientURLs();

            // TODO: replace urlBase with your own test url
            String urlBase = Request.Url.AbsoluteUri.Replace("EmbedDocuSign.aspx", "pop.html") + "?source=embed";
            urls.OnSigningComplete = urlBase + "&event=SignComplete2";
            urls.OnViewingComplete = urlBase + "&event=ViewComplete2";
            urls.OnCancel = urlBase + "&event=Cancel2";
            urls.OnDecline = urlBase + "&event=Decline2";
            urls.OnSessionTimeout = urlBase + "&event=Timeout2";
            urls.OnTTLExpired = urlBase + "&event=TTLExpired2";
            urls.OnIdCheckFailed = urlBase + "&event=IDCheck2";
            urls.OnAccessCodeFailed = urlBase + "&event=AccessCode2";
            urls.OnException = urlBase + "&event=Exception2";

            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            String token = null;
            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(status.EnvelopeID, recipient.ClientUserId,
                                                                recipient.UserName, recipient.Email, assertion, urls);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Set the signer message
            signerMessage = "The first signer has completed the Envelope. Now the second signer will be asked to fill out details in the Envelope.";
            messagediv.Visible = true;

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes["src"] = token;
        }

        protected DocuSignAPI.Recipient[] ConstructRecipients()
        {
            // Construct the recipients
            List<DocuSignAPI.Recipient> runningList = new List<DocuSignAPI.Recipient>();
            DocuSignAPI.Recipient r1 = new DocuSignAPI.Recipient();
            r1.UserName = Session["APIUserName"].ToString();
            r1.Email = Session["APIEmail"].ToString();
            r1.ID = "1";
            r1.Type = DocuSignAPI.RecipientTypeCode.Signer;
            r1.CaptiveInfo = new DocuSignAPI.RecipientCaptiveInfo();
            r1.CaptiveInfo.ClientUserId = "1";
            runningList.Add(r1);

            // If we're creating an envelop with two signers,
            // add the second signer with dummy credentials
            if (!_oneSigner)
            {
                DocuSignAPI.Recipient r2 = new DocuSignAPI.Recipient();
                r2.UserName = "DocuSign Recipient2";
                r2.Email = "DocuSignRecipient2@mailinator.com";
                r2.ID = "2";
                r2.Type = DocuSignAPI.RecipientTypeCode.Signer;
                r2.CaptiveInfo = new DocuSignAPI.RecipientCaptiveInfo();
                r2.CaptiveInfo.ClientUserId = "2";
                runningList.Add(r2);
            }

            return runningList.ToArray();
        }

        private DocuSignAPI.Tab[] AddTabs(int recipientCount)
        {
            List<DocuSignAPI.Tab> runningList = new List<DocuSignAPI.Tab>();

            // Basic Company Tab
            DocuSignAPI.Tab company = new DocuSignAPI.Tab();
            company.Type = DocuSignAPI.TabTypeCode.Company;
            company.DocumentID = "1";
            company.PageNumber = "2";
            company.RecipientID = "1";
            company.XPosition = "342";
            company.YPosition = "303";

            runningList.Add(company);

            // Basic InitialHere tab
            DocuSignAPI.Tab init1 = new DocuSignAPI.Tab();
            init1.Type = DocuSignAPI.TabTypeCode.InitialHere;
            init1.DocumentID = "1";
            init1.PageNumber = "3";
            init1.RecipientID = "1";
            init1.XPosition = "454";
            init1.YPosition = "281";

            runningList.Add(init1);

            // Basic SignHere tab
            DocuSignAPI.Tab sign1 = new DocuSignAPI.Tab();
            sign1.Type = DocuSignAPI.TabTypeCode.SignHere;
            sign1.DocumentID = "1";
            sign1.PageNumber = "2";
            sign1.RecipientID = "1";
            sign1.XPosition = "338";
            sign1.YPosition = "330";

            runningList.Add(sign1);

            // Basic FullName Anchor tab
            DocuSignAPI.Tab fullAnchor = new DocuSignAPI.Tab();
            fullAnchor.Type = DocuSignAPI.TabTypeCode.FullName;
            fullAnchor.AnchorTabItem = new DocuSignAPI.AnchorTab();
            fullAnchor.AnchorTabItem.AnchorTabString = "(printed)";
            fullAnchor.AnchorTabItem.XOffset = -123;
            fullAnchor.AnchorTabItem.YOffset = 31;
            fullAnchor.AnchorTabItem.Unit = DocuSignAPI.UnitTypeCode.Pixels;
            fullAnchor.AnchorTabItem.UnitSpecified = true;
            fullAnchor.AnchorTabItem.IgnoreIfNotPresent = true;
            fullAnchor.AnchorTabItem.IgnoreIfNotPresentSpecified = true;
            fullAnchor.DocumentID = "1";
            fullAnchor.PageNumber = "2";
            fullAnchor.RecipientID = "1";

            runningList.Add(fullAnchor);

            // Basic DateSigned tab
            DocuSignAPI.Tab date1 = new DocuSignAPI.Tab();
            date1.Type = DocuSignAPI.TabTypeCode.DateSigned;
            date1.DocumentID = "1";
            date1.PageNumber = "2";
            date1.RecipientID = "1";
            date1.XPosition = "343";
            date1.YPosition = "492";

            runningList.Add(date1);

            // Scaled InitialHere tab
            DocuSignAPI.Tab init2 = new DocuSignAPI.Tab();
            init2.Type = DocuSignAPI.TabTypeCode.InitialHere;
            init2.DocumentID = "1";
            init2.PageNumber = "3";
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
                sign2.PageNumber = "3";
                sign2.RecipientID = "2";
                sign2.XPosition = "339";
                sign2.YPosition = "97";

                runningList.Add(sign2);

                // Basic DateSigned tab
                DocuSignAPI.Tab date2 = new DocuSignAPI.Tab();
                date2.Type = DocuSignAPI.TabTypeCode.DateSigned;
                date2.DocumentID = "1";
                date2.PageNumber = "3";
                date2.RecipientID = "2";
                date2.XPosition = "343";
                date2.YPosition = "197";

                runningList.Add(date2);
            }

            //Custom text tab
            DocuSignAPI.Tab favColor = new DocuSignAPI.Tab();
            favColor.Type = DocuSignAPI.TabTypeCode.Custom;
            favColor.CustomTabType = DocuSignAPI.CustomTabType.Text;
            favColor.CustomTabTypeSpecified = true;
            favColor.DocumentID = "1";
            favColor.PageNumber = "3";
            favColor.RecipientID = "1";
            favColor.XPosition = "301";
            favColor.YPosition = "416";

            runningList.Add(favColor);

            // Custom radio button tab
            DocuSignAPI.Tab fruitNo = new DocuSignAPI.Tab();
            fruitNo.Type = DocuSignAPI.TabTypeCode.Custom;
            fruitNo.CustomTabType = DocuSignAPI.CustomTabType.Radio;
            fruitNo.CustomTabTypeSpecified = true;
            fruitNo.CustomTabRadioGroupName = "fruit";
            fruitNo.TabLabel = "No";
            fruitNo.Name = "No";
            fruitNo.DocumentID = "1";
            fruitNo.PageNumber = "3";
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
            fruitYes.PageNumber = "3";
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
            data1.PageNumber = "3";
            data1.RecipientID = "1";
            data1.XPosition = "202";
            data1.XPosition = "265";
            data1.YPosition = "547";

            runningList.Add(data1);

            return runningList.ToArray();
        }


    }
}