using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class EmbeddedHost : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!LoggedIn())
            {
                Response.Redirect("LogIn.aspx");
            }
            if (Request.Form["__EVENTTARGET"] != logoutCtrlName)
            {
                DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
                string token = null;

                // Get the information we need from the query string
                string envelopeID = Request["envelopeID"];
                string accountID = Request["accountID"];

                // Request the token to edit the envelope
                try
                {
                    string retURL = Request.Url.AbsoluteUri.Replace("EmbeddedHost.aspx", "pop.html?source=document");
                    token = client.RequestSenderToken(envelopeID, accountID, retURL);
                }
                catch (Exception ex)
                {
                    base.GoToErrorPage(ex.Message);
                }

                // Set the source of the iframe to point to DocuSign
                sendingFrame.Attributes["src"] = token;
            }
        }
    }
}