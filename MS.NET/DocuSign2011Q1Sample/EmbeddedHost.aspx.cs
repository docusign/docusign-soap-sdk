using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class EmbeddedHost : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            string token = null;
            if (Request["source"] == "SendDocument")
            {
                string envelopeID = Request["eid"];
                string username = Request["uname"];
                string email = Request["email"];
                string clientUserId = Request["cid"];

                DocuSignAPI.RequestRecipientTokenAuthenticationAssertion assertion = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion();
                assertion.AssertionID = new Guid().ToString();
                assertion.AuthenticationInstant = DateTime.Now;
                assertion.AuthenticationMethod = DocuSignAPI.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password;
                assertion.SecurityDomain = "DocuSign2011Q1Sample";

                DocuSignAPI.RequestRecipientTokenClientURLs urls = new DocuSignAPI.RequestRecipientTokenClientURLs();

                String urlBase = Request.Url.AbsoluteUri.Replace("EmbeddedHost.aspx", "pop.html") + "?source="+Request["source"];
                urls.OnSigningComplete = urlBase + "&event=SignComplete&uname=" + username;
                urls.OnViewingComplete = urlBase + "&event=ViewComplete&uname=" + username;
                urls.OnCancel = urlBase + "&event=Cancel&uname=" + username;
                urls.OnDecline = urlBase + "&event=Decline&uname=" + username;
                urls.OnSessionTimeout = urlBase + "&event=Timeout&uname=" + username;
                urls.OnTTLExpired = urlBase + "&event=TTLExpired&uname=" + username;
                urls.OnIdCheckFailed = urlBase + "&event=IDCheck&uname=" + username;
                urls.OnAccessCodeFailed = urlBase + "&event=AccessCode&uname=" + username;
                urls.OnException = urlBase + "&event=Exception&uname=" + username;
                try
                {
                    // Request the token for a specific recipient
                    token = client.RequestRecipientToken(envelopeID, clientUserId,
                                                                    username, email, assertion, urls);
                }
                catch (Exception ex)
                {
                    base.GoToErrorPage(ex.Message);
                }
            }
            else
            {
                string envelopeID = Request["envelopeID"];
                string accountID = Request["accountID"];
                
                try
                {
                    string retURL = Request.Url.AbsoluteUri.Replace("EmbeddedHost.aspx", "pop.html");
                    token = client.RequestSenderToken(envelopeID, accountID, retURL);
                }
                catch (Exception ex)
                {
                    base.GoToErrorPage(ex.Message);
                }
            }
            sendingFrame.Attributes["src"] = token;
        }
    }
}