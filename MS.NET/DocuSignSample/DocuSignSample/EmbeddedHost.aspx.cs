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

namespace DocuSignSample
{
    public partial class EmbeddedHost : BasePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!LoggedIn())
            {
                Response.Redirect("LogIn.aspx");
            }
            if (Request.Form["__EVENTTARGET"] != logoutCtrlName)
            {
                DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
                string token = null;

                // Get the information we need from the query string
                string envelopeID = Request[Keys.EnvelopeId];
                string accountID = Request[Keys.AccountId];

                // Request the token to edit the envelope
                try
                {
                    string retURL = Request.Url.AbsoluteUri.Replace("EmbeddedHost.aspx", "pop.html?source=document");
                    token = client.RequestSenderToken(envelopeID, accountID, retURL);
                }
                catch (Exception ex)
                {
                    GoToErrorPage(ex.Message);
                }

                // Set the source of the iframe to point to DocuSign
                sendingFrame.Attributes[Keys.Source] = token;
            }
        }
    }
}