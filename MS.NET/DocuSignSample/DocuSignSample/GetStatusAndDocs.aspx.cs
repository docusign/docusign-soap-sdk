using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class GetStatusAndDocs : BasePage
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!LoggedIn())
            {
                Response.Redirect("LogIn.aspx");
            }
            if (Request.Form["__EVENTTARGET"] != logoutCtrlName)
            {
                if (Request.Form.Keys.Count >= 1)
                {
                    foreach (string key in Request.Form.Keys)
                    {
                        // Start the download of an envelope's documents
                        if (Request.Form[key] == "Download")
                        {
                            DownloadItem(key);
                            hostiframe.Visible = false;
                        }

                        // Start signing as a particular recipient
                        else if (Request.Form[key] == "Start Signing")
                        {
                            string[] signing_params = key.Split('&');
                            string uname = signing_params[2].Split('+')[1];
                            string cid = signing_params[3].Split('+')[1];
                            string email = signing_params[1].Split('+')[1];
                            string eid = signing_params[0].Split('+')[1];
                            StartSigning(uname, cid, email, eid);
                        }
                    }
                }
                else
                {
                    // Get the statuses of envelopes
                    GetStatuses();
                    hostiframe.Visible = false;
                }
            }
        }

        protected void StartSigning(string userName, string clientID, string email, string envelopeID)
        {
            // Create the assertion using the current time, password and demo information
            DocuSignAPI.RequestRecipientTokenAuthenticationAssertion assertion = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion();
            assertion.AssertionID = new Guid().ToString();
            assertion.AuthenticationInstant = DateTime.Now;
            assertion.AuthenticationMethod = DocuSignAPI.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password;
            assertion.SecurityDomain = "DocuSignSample";

            // Construct the URLs to which the iframe will redirect upon every event
            DocuSignAPI.RequestRecipientTokenClientURLs urls = new DocuSignAPI.RequestRecipientTokenClientURLs();

            String urlBase = Request.Url.AbsoluteUri.Replace("GetStatusAndDocs.aspx", "pop.html");
            urls.OnSigningComplete = urlBase + "?event=SignComplete";
            urls.OnViewingComplete = urlBase + "?event=ViewComplete";
            urls.OnCancel = urlBase + "?event=Cancel";
            urls.OnDecline = urlBase + "?event=Decline";
            urls.OnSessionTimeout = urlBase + "?event=Timeout";
            urls.OnTTLExpired = urlBase + "?event=TTLExpired";
            urls.OnIdCheckFailed = urlBase + "?event=IDCheck";
            urls.OnAccessCodeFailed = urlBase + "?event=AccessCode";
            urls.OnException = urlBase + "?event=Exception";

            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            String token = null;

            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(envelopeID, clientID,
                                                                userName, email, assertion, urls);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes["src"] = token;
        }

        protected void DownloadItem(string id)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            DocuSignAPI.EnvelopePDF pdf = null;

            try
            {
                // Download all documents as one pdf
                pdf = client.RequestPDF(id);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Write the output to the browser
            Response.ContentType = "Application/pdf";
            Response.BinaryWrite(pdf.PDFBytes);
        }

        protected void GetStatuses()
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            DocuSignAPI.FilteredEnvelopeStatuses statuses = null;
            try
            {
                // Get all the envelope IDs which we have created in this session
                DocuSignAPI.EnvelopeStatusFilter filter = new DocuSignAPI.EnvelopeStatusFilter();
                filter.AccountId = Session["APIAccountID"].ToString();
                filter.EnvelopeIds = base.GetEnvelopeIDs();

                if (filter.EnvelopeIds.Length > 0)
                {
                    // Request all the envelope statuses based on that filter
                    statuses = client.RequestStatusesEx(filter);
                    CreateStatusTable(statuses);
                }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

        }

        // The next two functions simply create the html for the status tables. You can safely ignore these.
        protected void CreateStatusTable(DocuSignAPI.FilteredEnvelopeStatuses statuses)
        {
            foreach (DocuSignAPI.EnvelopeStatus status in statuses.EnvelopeStatuses)
            {
                System.Web.UI.HtmlControls.HtmlGenericControl containerDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                System.Web.UI.HtmlControls.HtmlGenericControl info = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
                info.InnerHtml = "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail" + "');\"><img src=\"images/plus.png\"></a> " + status.Subject + " (" + status.Status.ToString() + ") - " + status.EnvelopeID;
                containerDiv.Controls.Add(info);
                System.Web.UI.HtmlControls.HtmlGenericControl envelopeDetail = CreateEnvelopeTable(status);
                envelopeDetail.Attributes["class"] = "detail";
                envelopeDetail.Attributes["id"] = status.EnvelopeID + "_Detail";

                containerDiv.Controls.Add(envelopeDetail);
                System.Web.UI.HtmlControls.HtmlTableRow tr = new System.Web.UI.HtmlControls.HtmlTableRow();
                System.Web.UI.HtmlControls.HtmlTableCell tc = new System.Web.UI.HtmlControls.HtmlTableCell();
                tc.Controls.Add(containerDiv);
                tr.Cells.Add(tc);
                statusTable.Rows.Add(tr);
            }

        }

        protected System.Web.UI.HtmlControls.HtmlGenericControl CreateEnvelopeTable(DocuSignAPI.EnvelopeStatus status)
        {
            System.Web.UI.HtmlControls.HtmlTable envelopeTable = new System.Web.UI.HtmlControls.HtmlTable();
            System.Web.UI.HtmlControls.HtmlGenericControl envelopeDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");

            foreach (DocuSignAPI.RecipientStatus recipient in status.RecipientStatuses)
            {
                System.Web.UI.HtmlControls.HtmlGenericControl info = new System.Web.UI.HtmlControls.HtmlGenericControl("p");

                info.InnerHtml = "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail_Recipient_" + recipient.UserName + "');\"><img src=\"images/plus.png\"></a> Recipient - " +
                    recipient.UserName + ": " + recipient.Status.ToString();
                if (recipient.Status != DocuSignAPI.RecipientStatusCode.Completed && recipient.ClientUserId != null)
                {
                    info.InnerHtml += " <input type=\"submit\" id=\"" + status.EnvelopeID + "\" value=\"Start Signing\" name=\"DocEnvelope+" + status.EnvelopeID + "&Email+" + recipient.Email + "&UserName+" +
                        recipient.UserName + "&CID+" + recipient.ClientUserId + "\">";
                }

                if (recipient.TabStatuses != null)
                {
                    System.Web.UI.HtmlControls.HtmlGenericControl tabs = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                    foreach (DocuSignAPI.TabStatus tab in recipient.TabStatuses)
                    {
                        System.Web.UI.HtmlControls.HtmlGenericControl t = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
                        t.InnerHtml = tab.TabName + ": " + tab.TabValue;
                        tabs.Controls.Add(t);
                    }
                    tabs.Attributes["id"] = status.EnvelopeID + "_Detail_Recipient_" + recipient.UserName;
                    tabs.Attributes["class"] = "detail";
                    info.Controls.Add(tabs);
                }
                envelopeDiv.Controls.Add(info);
            }

            System.Web.UI.HtmlControls.HtmlGenericControl documents = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
            documents.InnerHtml = "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail_Documents" + "');\"><img src=\"images/plus.png\"></a> Documents";
            if (status.Status == DocuSignAPI.EnvelopeStatusCode.Completed)
            {
                documents.InnerHtml += " <input type=\"submit\" id=\"" + status.EnvelopeID + "\" value=\"Download\" name=\"" + status.EnvelopeID + "\";>";
            }
            envelopeDiv.Controls.Add(documents);
            if (status.DocumentStatuses != null)
            {
                System.Web.UI.HtmlControls.HtmlGenericControl documentDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                foreach (DocuSignAPI.DocumentStatus document in status.DocumentStatuses)
                {
                    System.Web.UI.HtmlControls.HtmlGenericControl info = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
                    info.InnerHtml = document.Name;
                    documentDiv.Controls.Add(info);
                }
                documentDiv.Attributes["id"] = status.EnvelopeID + "_Detail_Documents";
                documentDiv.Attributes["class"] = "detail";
                envelopeDiv.Controls.Add(documentDiv);
            }
            return envelopeDiv;
        }
    }
}