using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class SelectUser : BasePage
    {
        protected IEnumerable<String> names;
        protected IDictionary<String, CredentialAPI.Account> accounts;

        protected string email;
        protected string password;
        protected string key;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Page.IsPostBack)
            {
                OnSelect();
            }
            else
            {
                if (Session["APIAccounts"] != null &&
                    Session["APIEmail"] != null &&
                    Session["APIPassword"] != null &&
                    Session["APIIKey"] != null)
                {
                    names = ((IDictionary<String, CredentialAPI.Account>)Session["APIAccounts"]).Keys;
                    accounts = ((IDictionary<String, CredentialAPI.Account>)Session["APIAccounts"]);
                    email = Session["APIEmail"].ToString();
                    password = Session["APIPassword"].ToString();
                    key = Session["APIIKey"].ToString();
                }
                else
                {
                    GoToErrorPage("Could not log you in. Please check your credentials.");
                }
            }
        }

        private void OnSelect()
        {
            String selectedName = Request.Form["DevCenterName"];
            IDictionary<String, CredentialAPI.Account> accounts = (IDictionary<String, CredentialAPI.Account>)Session["APIAccounts"];
            if(accounts.ContainsKey(selectedName)) {
                Session["APIAccountId"] = accounts[selectedName].AccountID;
                Session["APIUserID"] = accounts[selectedName].UserID;
                Session["APIUserName"] = accounts[selectedName].UserName;
                Response.Redirect("SendDocument.aspx", true);
            } else {
                GoToErrorPage("Could not log you in. Please check your credentials.");
            }
        }
    }
}