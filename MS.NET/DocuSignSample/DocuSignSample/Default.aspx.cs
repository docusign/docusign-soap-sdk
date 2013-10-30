/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
 
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