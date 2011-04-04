using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class Default : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            // If we have all the settings already, jump into the demo
            if (Settings_In_Config())
            {
                Response.Redirect("SendDocument.aspx");
            }
            // Otherwise, force a log in
            else
            {
                Response.Redirect("LogIn.aspx");
            }
        }

        protected bool Settings_In_Config()
        {

            if (SettingIsSet("APIUserEmail"))
            {
                Session["APIEmail"] = ConfigurationManager.AppSettings["APIUserEmail"];
            }
            if (SettingIsSet("Password"))
            {
                Session["APIPassword"] = ConfigurationManager.AppSettings["Password"];
            }
            if (SettingIsSet("IntegratorsKey"))
            {
                Session["APIIKey"] = ConfigurationManager.AppSettings["IntegratorsKey"];
            }
            if (SettingIsSet("APIAccountId"))
            {
                Session["APIAccountId"] = ConfigurationManager.AppSettings["APIAccountId"];
            }

            return (SettingIsSet("APIUserEmail") && SettingIsSet("Password") &&
                SettingIsSet("IntegratorsKey") && SettingIsSet("APIAccountId"));
        }
    }
}