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
 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace DocuSignSample
{
    /// <summary>
    /// This just has some methods that create some common api objects to save typing and avoid cluttering up the main
    /// process code. Many of the values here are defaulted for the demo.
    /// </summary>
    public class ApiHelper
    {

    }

    public struct AccountCredentials
    {
        public string ApiUrl; // url endpoint of the api
        public string AccountId; // billing account for sending envelopes
        public string UserName; // email address used for DocuSign login, prefixed with integrator key in square brackets
        public string Password; // password for DocuSign login
    }

}