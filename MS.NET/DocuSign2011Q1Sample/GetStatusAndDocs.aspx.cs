using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class GetStatusAndDocs : BasePage
    {

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Form.Keys.Count >= 1)
            {
                DownloadItem();
            }
            else
            {
                GetStatuses();
            }
        }

        protected void DownloadItem()
        {
            foreach (string key in Request.Form.Keys)
            {
                if (Request.Form[key].ToString() == "Download")
                {
                    DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
                    DocuSignAPI.EnvelopePDF pdf = null;

                    try
                    {
                        pdf = client.RequestPDF(key.ToString());
                    }
                    catch (Exception ex)
                    {
                        base.GoToErrorPage(ex.Message);
                    }
                    Response.ContentType = "Application/pdf";
                    Response.BinaryWrite(pdf.PDFBytes);
                }
            }
        }

        protected void GetStatuses()
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            DocuSignAPI.FilteredEnvelopeStatuses statuses = null;
            try
            {
                    DocuSignAPI.EnvelopeStatusFilter filter = new DocuSignAPI.EnvelopeStatusFilter();
                    filter.AccountId = Session["APIAccountID"].ToString();
                    filter.EnvelopeIds = base.GetEnvelopeIDs();

                    if (filter.EnvelopeIds.Length > 0)
                    {
                        statuses = client.RequestStatusesEx(filter);
                        CreateStatusTable(statuses);
                    }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
            
        }

        protected void CreateStatusTable(DocuSignAPI.FilteredEnvelopeStatuses statuses)
        {
            foreach (DocuSignAPI.EnvelopeStatus status in statuses.EnvelopeStatuses)
            {
                System.Web.UI.HtmlControls.HtmlGenericControl containerDiv = new System.Web.UI.HtmlControls.HtmlGenericControl("div");
                System.Web.UI.HtmlControls.HtmlGenericControl info = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
                info.InnerHtml = "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail" + "');\">img</a> " + status.Subject + " (" + status.Status.ToString() + ") - " + status.EnvelopeID;
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
                info.InnerHtml = "<img src=\"\"></img> Recipient - " + recipient.UserName + ": " + recipient.Status.ToString();
                envelopeDiv.Controls.Add(info);
            }

            System.Web.UI.HtmlControls.HtmlGenericControl documents = new System.Web.UI.HtmlControls.HtmlGenericControl("p");
            documents.InnerHtml = "<a href=\"javascript:toggle('" + status.EnvelopeID + "_Detail_Documents" + "');\">img</a> Documents";
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
                    info.InnerHtml = "<img src=\"\"></img> " + document.Name;
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