using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class Tabs : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            logoutBtn.ServerClick += new EventHandler(Logout);
            String referrer = Request.Url.ToString();
            if (referrer.EndsWith("SendDocument.aspx"))
            {
                SendDocTab.Attributes.Add("class", "current");
            }
            else if (referrer.EndsWith("SendTemplate.aspx"))
            {
                SendTemplateTab.Attributes.Add("class", "current");
            }
            else if (referrer.EndsWith("EmbedDocuSign.aspx"))
            {
                EmbedTab.Attributes.Add("class", "current");
            }
            else if (referrer.EndsWith("GetStatusAndDocs.aspx"))
            {
                GetStatusTab.Attributes.Add("class", "current");
            }
            else if (referrer.Contains("Document"))
            {
                SendDocTab.Attributes.Add("class", "current");
            }
            else if (referrer.Contains("Template"))
            {
                SendTemplateTab.Attributes.Add("class", "current");
            }
            else if (referrer.Contains("Embedded"))
            {
                EmbedTab.Attributes.Add("class", "current");
            }
        }

        public void Logout(object sender, EventArgs e)
        {

            Session["APIAccountID"] = null;
            Session["APIEmail"] = null;
            Session["APIKey"] = null;
            Session["APIPassword"] = null;
            Session["EnvelopeIDs"] = null;
            Response.Redirect("Login.aspx", true);

        }
    }
}