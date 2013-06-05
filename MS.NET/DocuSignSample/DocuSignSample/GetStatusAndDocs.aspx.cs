using System.Linq;
using DocuSignSample.resources;
using System;

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
            var assertion = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion
                {
                    AssertionID = new Guid().ToString(),
                    AuthenticationInstant = DateTime.Now,
                    AuthenticationMethod =
                        DocuSignAPI.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password,
                    SecurityDomain = "DocuSignSample"
                };

            // Construct the URLs to which the iframe will redirect upon every event
            String urlBase = Request.Url.AbsoluteUri.Replace("GetStatusAndDocs.aspx", "pop.html");
            var urls = new DocuSignAPI.RequestRecipientTokenClientURLs
                {
                    OnSigningComplete = urlBase + "?event=SignComplete",
                    OnViewingComplete = urlBase + "?event=ViewComplete",
                    OnCancel = urlBase + "?event=Cancel",
                    OnDecline = urlBase + "?event=Decline",
                    OnSessionTimeout = urlBase + "?event=Timeout",
                    OnTTLExpired = urlBase + "?event=TTLExpired",
                    OnIdCheckFailed = urlBase + "?event=IDCheck",
                    OnAccessCodeFailed = urlBase + "?event=AccessCode",
                    OnException = urlBase + "?event=Exception"
                };

            var client = CreateAPIProxy();
            String token = null;

            try
            {
                // Request the token for a specific recipient
                token = client.RequestRecipientToken(envelopeID, clientID,
                                                                userName, email, assertion, urls);
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }

            // Set the source of the iframe to the token
            hostiframe.Visible = true;
            hostiframe.Attributes[Keys.Source] = token;
        }

        protected void DownloadItem(string id)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();

            try
            {
                // Download all documents as one pdf
                DocuSignAPI.EnvelopePDF pdf = client.RequestPDF(id);
                // Write the output to the browser
                Response.ContentType = "Application/pdf";
                Response.BinaryWrite(pdf.PDFBytes);
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }

        }

        protected void GetStatuses()
        {
            var client = CreateAPIProxy();
            try
            {
                // Get all the envelope IDs which we have created in this session
                var filter = new DocuSignAPI.EnvelopeStatusFilter
                    {
                        AccountId = Session[Keys.ApiAccountId].ToString(),
                        EnvelopeIds = GetEnvelopeIDs()
                    };

                if (filter.EnvelopeIds.Length > 0)
                {
                    // Request all the envelope statuses based on that filter
                    DocuSignAPI.FilteredEnvelopeStatuses statuses = client.RequestStatusesEx(filter);
                    CreateStatusTable(statuses);
                }
            }
            catch (Exception ex)
            {
                GoToErrorPage(ex.Message);
            }

        }

        // The next two functions simply create the html for the status tables. You can safely ignore these.
        protected void CreateStatusTable(DocuSignAPI.FilteredEnvelopeStatuses statuses)
        {
            foreach (DocuSignAPI.EnvelopeStatus status in statuses.EnvelopeStatuses)
            {
                var containerDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                var info = new System.Web.UI.HtmlControls.HtmlGenericControl("p")
                    {
                        InnerHtml =
                            "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail" +
                            "');\"><img src=\"images/plus.png\"></a> " + status.Subject + " (" + status.Status.ToString() +
                            ") - " + status.EnvelopeID
                    };
                containerDiv.Controls.Add(info);
                System.Web.UI.HtmlControls.HtmlGenericControl envelopeDetail = CreateEnvelopeTable(status);
                envelopeDetail.Attributes[Keys.Class] = "detail";
                envelopeDetail.Attributes[Keys.Id] = status.EnvelopeID + "_Detail";

                containerDiv.Controls.Add(envelopeDetail);
                var tr = new System.Web.UI.HtmlControls.HtmlTableRow();
                var tc = new System.Web.UI.HtmlControls.HtmlTableCell();
                tc.Controls.Add(containerDiv);
                tr.Cells.Add(tc);
                statusTable.Rows.Add(tr);
            }

        }

        protected System.Web.UI.HtmlControls.HtmlGenericControl CreateEnvelopeTable(DocuSignAPI.EnvelopeStatus status)
        {
            var envelopeDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");

            int recipIndex = 0;

            foreach (DocuSignAPI.RecipientStatus recipient in status.RecipientStatuses)
            {
                var info = new System.Web.UI.HtmlControls.HtmlGenericControl("p");

                String recipId = "Recipient_Detail_" + status.EnvelopeID + "_" + recipient.RoutingOrder + "_" + recipient.UserName + "_" + recipient.Email + "_" + recipIndex++;

                info.InnerHtml = "<a href=\"javascript:toggle('" + recipId + "');\"><img src=\"images/plus.png\"></a> Recipient - " +
                    recipient.UserName + ": " + recipient.Status.ToString();
                if (recipient.Status != DocuSignAPI.RecipientStatusCode.Completed && null != recipient.ClientUserId)
                {
                    info.InnerHtml += " <input type=\"submit\" id=\"" + status.EnvelopeID + "\" value=\"Start Signing\" name=\"DocEnvelope+" + status.EnvelopeID + "&Email+" + recipient.Email + "&UserName+" +
                        recipient.UserName + "&CID+" + recipient.ClientUserId + "\">";
                }

                if (null != recipient.TabStatuses)
                {
                    var tabs = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                    foreach (var t in recipient.TabStatuses.Select(tab => new System.Web.UI.HtmlControls.HtmlGenericControl("p")
                        {
                            InnerHtml = tab.TabName + ": " + tab.TabValue
                        }))
                    {
                        tabs.Controls.Add(t);
                    }
                    tabs.Attributes[Keys.Id] = recipId;
                    tabs.Attributes[Keys.Class] = "detail";
                    info.Controls.Add(tabs);
                }
                envelopeDiv.Controls.Add(info);
            }

            var documents = new System.Web.UI.HtmlControls.HtmlGenericControl("p")
                {
                    InnerHtml =
                        "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail_Documents" +
                        "');\"><img src=\"images/plus.png\"></a> Documents"
                };
            if (status.Status == DocuSignAPI.EnvelopeStatusCode.Completed)
            {
                documents.InnerHtml += " <input type=\"submit\" id=\"" + status.EnvelopeID + "\" value=\"Download\" name=\"" + status.EnvelopeID + "\";>";
            }
            envelopeDiv.Controls.Add(documents);
            if (null != status.DocumentStatuses)
            {
                var documentDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                foreach (var info in status.DocumentStatuses.Select(document => new System.Web.UI.HtmlControls.HtmlGenericControl("p") {InnerHtml = document.Name}))
                {
                    documentDiv.Controls.Add(info);
                }
                documentDiv.Attributes[Keys.Id] = status.EnvelopeID + "_Detail_Documents";
                documentDiv.Attributes[Keys.Class] = "detail";
                envelopeDiv.Controls.Add(documentDiv);
            }
            return envelopeDiv;
        }
    }
}