using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class EmbedResult : System.Web.UI.Page
    {
        protected string _stat = "";

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request["source"] == "SendDocument")
            {
                string s = "<script type=\"text/javascript\">self.close();</script>";
                ClientScript.RegisterStartupScript(Page.GetType(), "closescript", s);
            }
            if(Request["event"] != null)
            {
                string signingEvent = Request["event"];
                switch (signingEvent)
                {

                    case "SignComplete":
                        _stat = "The user has completed the signing.  The legally binding document with signatures is stored on the DocuSign, Inc. server.";
                        break;
                    case "ViewComplete":
                        _stat = "The user has viewed the document without signing it.";
                        break;
                    case "Cancel":
                        _stat = "The user has cancelled out of the signign experience";
                        break;
                    case "Decline":
                        _stat = "The user has declined to sign the document.";
                        break;
                    case "Timeout":
                        _stat = "The user did not sign the document in time.  The timeout is set to 20 minutes.";
                        break;
                    case "TTLExpired":
                        _stat = "Trusted connection has expired.  The server communication might be a problem.";
                        break;
                    case "IDCheck":
                        _stat = "The ID Check has failed.  The user was denied an opportunity to view or sign the document.";
                        break;
                    case "AccessCode":
                        _stat = "The access code verification has failed.  The user was denied an opportunity to view or sign the document.";
                        break;
                    case "Exception":
                        _stat = "An exception has occurred on the server.  Please check the parameters passed to the Web Service Methods.";
                        break;
                    case "Send":
                        _stat = "The Envelope was Sent!";
                        break;
                    case "Save":
                        _stat = "The Envelope was Saved as a Draft";
                        break;

                    default:
                        _stat = signingEvent;
                        break;
                }
            }
        }
    }
}