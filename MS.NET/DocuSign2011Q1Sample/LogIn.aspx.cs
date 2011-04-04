using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
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
                if (Session["APIUserEmail"] != null)
                {
                    email = Session["APIUserEmail"].ToString();
                }
                if (Session["APIPassword"] != null)
                {
                    password = Session["APIPassword"].ToString();
                }
                if (Session["APIIKey"] != null)
                {
                    key = Session["APIIKey"].ToString();
                }
            }
        }

        public void On_Login()
        {
            // Grab the info from the form, even if it is already stored in the Session
            Session["APIEmail"] = Request.Form["DevCenterEmail"];
            Session["APIPassword"] = Request.Form["DevCenterPassword"];
            Session["APIIKey"] = Request.Form["DevCenterIKey"];

            // Log in with Cred API
            String login = String.Format("[{0}]{1}", Session["APIIKey"], Session["APIEmail"]);
            CredentialAPI.CredentialSoapClient credential = new CredentialAPI.CredentialSoapClient();
            CredentialAPI.LoginResult result = credential.Login(login, Session["APIPassword"].ToString());

            // If we could log the user in, go to the main page
            if (result.Success)
            {
                // Get the account ID first
                // TODO: deal with multiple accounts
                Session["APIAccountId"] = result.Accounts[0].AccountID;
                Response.Redirect("SendDocument.aspx", true);
            }
            // Otherwise send the user to the error page
            else
            {
                GoToErrorPage("Could not log you in. Please check your credentials.");
            }
        }
    }
}