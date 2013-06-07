using DocuSignSample.resources;
using System;

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
            Session[Keys.ApiAccountId] = null;
            Session[Keys.ApiEmail] = null;
            Session[Keys.ApiIkey] = null;
            Session[Keys.ApiPassword] = null;
            Session[Keys.EnvelopeIds] = null;
            Response.Redirect("Login.aspx", true);

        }
    }
}