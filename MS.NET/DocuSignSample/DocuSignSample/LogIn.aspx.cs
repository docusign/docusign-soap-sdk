using DocuSignSample.resources;
using System;
using System.Linq;

namespace DocuSignSample
{
    public partial class Login : BasePage
    {
        protected string email;
        protected string password;
        protected string key;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Page.IsPostBack)
            {
                On_Login();
            }
            else
            {
                // Prepopulate any of the fields we can from the web.config
                if (null != Session[Keys.ApiEmail])
                {
                    email = Session[Keys.ApiEmail].ToString();
                }
                if (null != Session[Keys.ApiPassword])
                {
                    password = Session[Keys.ApiPassword].ToString();
                }
                if (null != Session[Keys.ApiIkey])
                {
                    key = Session[Keys.ApiIkey].ToString();
                }
            }
        }

        public void On_Login()
        {
            // Log in with Credential API
            var login = String.Format("[{0}]{1}", Request.Form[Keys.DevCenterKey], Request.Form[Keys.DevCenterEmail]);
            var credential = new CredentialAPI.CredentialSoapClient();
            var result = credential.Login(login, Request.Form[Keys.DevCenterPassword].ToString(), false);

            // If we could log the user in, go to the main page
            if (result.Success)
            {
                // Grab the info from the form, even if it is already stored in the Session
                Session[Keys.ApiEmail] = Request.Form[Keys.DevCenterEmail];
                Session[Keys.ApiPassword] = Request.Form[Keys.DevCenterPassword];
                Session[Keys.ApiIkey] = Request.Form[Keys.DevCenterKey];

                // Get the account ID first
                if (result.Accounts.Length == 1)
                {
                    Session[Keys.ApiAccountId] = result.Accounts[0].AccountID;
                    Session[Keys.ApiUserId] = result.Accounts[0].UserID;
                    Session[Keys.ApiUsername] = result.Accounts[0].UserName;
                    Response.Redirect("SendDocument.aspx", true);
                }
                else
                {
                    Session[Keys.ApiAccounts] = result.Accounts.ToDictionary(x => x.AccountID);
                    Response.Redirect("SelectUser.aspx", true);
                }                
            }
            // Otherwise send the user to the error page
            else
            {
                GoToErrorPage("Could not log you in. Please check your credentials.");
            }
        }
    }
}