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