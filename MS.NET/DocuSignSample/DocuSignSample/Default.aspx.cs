using DocuSignSample.resources;
using System;
using System.Configuration;

namespace DocuSignSample
{
    public partial class Default : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Settings_In_Config();
            Response.Redirect("LogIn.aspx");
        }

        protected bool Settings_In_Config()
        {

            if (SettingIsSet(Keys.ApiUserEmail))
            {
                Session[Keys.ApiEmail] = ConfigurationManager.AppSettings[Keys.ApiUserEmail];
            }
            if (SettingIsSet(Keys.Password))
            {
                Session[Keys.ApiPassword] = ConfigurationManager.AppSettings[Keys.Password];
            }
            if (SettingIsSet(Keys.IntegratorsKey))
            {
                Session[Keys.ApiIkey] = ConfigurationManager.AppSettings[Keys.IntegratorsKey];
            }
            if (SettingIsSet(Keys.ApiAccountId))
            {
                Session[Keys.ApiAccountId] = ConfigurationManager.AppSettings[Keys.ApiAccountId];
            }

            return (SettingIsSet(Keys.ApiUserEmail) && SettingIsSet(Keys.Password) &&
                SettingIsSet(Keys.IntegratorsKey) && SettingIsSet(Keys.ApiAccountId));
        }
    }
}