using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class EmbedSending : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            string envelopeID = Request["envelopeID"];
            string accountID = Request["accountID"];
            string token = null;
            try
            {
                string retURL = Request.Url.AbsoluteUri.Replace("EmbedSending.aspx", "pop.html");
                token = client.RequestSenderToken(envelopeID, accountID, retURL);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
            sendingFrame.Attributes["src"] = token;
        }
    }
}