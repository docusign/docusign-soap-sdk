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
using System.Collections.Generic;

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
                if (null != Session[Keys.ApiAccounts] &&
                    null != Session[Keys.ApiEmail] &&
                    null != Session[Keys.ApiPassword] &&
                    null != Session[Keys.ApiIkey])
                {
                    names = ((IDictionary<String, CredentialAPI.Account>)Session[Keys.ApiAccounts]).Keys;
                    accounts = ((IDictionary<String, CredentialAPI.Account>)Session[Keys.ApiAccounts]);
                    email = Session[Keys.ApiEmail].ToString();
                    password = Session[Keys.ApiPassword].ToString();
                    key = Session[Keys.ApiIkey].ToString();
                }
                else
                {
                    GoToErrorPage("Could not log you in. Please check your credentials.");
                }
            }
        }

        private void OnSelect()
        {
            String selectedName = Request.Form[Keys.DevCenterName];
            accounts = (IDictionary<String, CredentialAPI.Account>)Session[Keys.ApiAccounts];
            if(accounts.ContainsKey(selectedName)) {
                Session[Keys.ApiAccountId] = accounts[selectedName].AccountID;
                Session[Keys.ApiUserId] = accounts[selectedName].UserID;
                Session[Keys.ApiUsername] = accounts[selectedName].UserName;
                Response.Redirect("SendDocument.aspx", true);
            } else {
                GoToErrorPage("Could not log you in. Please check your credentials.");
            }
        }
    }
}