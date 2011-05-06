using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSignSample
{
    public partial class SendTemplate : BasePage
    {
        protected override void OnInit(EventArgs e)
        {
            selectTemplateButton.ServerClick += new EventHandler(OnTemplateSelect);
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Form["__EVENTTARGET"] != logoutCtrlName)
            {
                if (!Page.IsPostBack)
                {
                    LoadTemplates();
                }
                else
                {
                    if (Request.Form["SendNow"] != null || Request.Form["EditFirst"] != null)
                    {
                        CreateEnvelope();
                    }
                }
            }
        }

        protected void CreateEnvelope()
        {
            // Create the envelope information
            DocuSignAPI.EnvelopeInformation envelopeInfo = new DocuSignAPI.EnvelopeInformation();
            envelopeInfo.Subject = Request.Form["subject"];
            envelopeInfo.EmailBlurb = Request.Form["emailBlurb"];
            envelopeInfo.AccountId = Session["APIAccountId"].ToString();

            // Add any reminders
            if (!String.IsNullOrEmpty(Request.Form["reminders"]))
            {
                DateTime remind = Convert.ToDateTime(Request.Form["reminders"]);
                int difference = (remind - DateTime.Today).Days;

                if (envelopeInfo.Notification == null)
                {
                    envelopeInfo.Notification = new DocuSignAPI.Notification();
                }
                envelopeInfo.Notification.Reminders = new DocuSignAPI.Reminders();
                envelopeInfo.Notification.Reminders.ReminderEnabled = true;
                envelopeInfo.Notification.Reminders.ReminderDelay = difference.ToString();
                envelopeInfo.Notification.Reminders.ReminderFrequency = "2";
            }

            // Add any expirations
            if (!String.IsNullOrEmpty(Request.Form["expiration"]))
            {
                DateTime expire = Convert.ToDateTime(Request.Form["expiration"]);
                int difference = (expire - DateTime.Today).Days;

                if (envelopeInfo.Notification == null)
                {
                    envelopeInfo.Notification = new DocuSignAPI.Notification();
                }

                envelopeInfo.Notification.Expirations = new DocuSignAPI.Expirations();
                envelopeInfo.Notification.Expirations.ExpireEnabled = true;
                envelopeInfo.Notification.Expirations.ExpireAfter = difference.ToString();
                envelopeInfo.Notification.Expirations.ExpireWarn = (difference - 2).ToString();
            }

            // Get all the recipients
            DocuSignAPI.Recipient[] recipients = ConstructRecipients();

            // Construct the template reference
            DocuSignAPI.TemplateReference templateReference = new DocuSignAPI.TemplateReference();
            templateReference.TemplateLocation = DocuSignAPI.TemplateLocationCode.Server;
            templateReference.Template = TemplateTable.Value;
            templateReference.RoleAssignments = CreateFinalRoleAssignments(recipients);

            if (Request.Form["SendNow"] != null)
            {
                SendNow(templateReference, envelopeInfo, recipients);
            }
            else
            {
                EmbedSending(templateReference, envelopeInfo, recipients);
            }
        }

        protected void SendNow(DocuSignAPI.TemplateReference templateReference, DocuSignAPI.EnvelopeInformation envelopeInfo,
            DocuSignAPI.Recipient[] recipients)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Create the envelope using the specified template, and send it (note the last parameter)
                DocuSignAPI.EnvelopeStatus status = client.CreateEnvelopeFromTemplates(new DocuSignAPI.TemplateReference[] { templateReference },
                recipients, envelopeInfo, true);

                base.AddEnvelopeID(status.EnvelopeID);
                if (status.SentSpecified)
                {
                    Response.Redirect("GetStatusAndDocs.aspx", false);
                }

            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        protected void EmbedSending(DocuSignAPI.TemplateReference templateReference, DocuSignAPI.EnvelopeInformation envelopeInfo,
            DocuSignAPI.Recipient[] recipients)
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            try
            {
                // Create the envelope using the specified template, but don't send it (note the last parameter)
                DocuSignAPI.EnvelopeStatus status = client.CreateEnvelopeFromTemplates(new DocuSignAPI.TemplateReference[] { templateReference },
                recipients, envelopeInfo, false);
                base.AddEnvelopeID(status.EnvelopeID);

                // If it created successfully, redirect to the embedded host
                if (status.Status == DocuSignAPI.EnvelopeStatusCode.Created)
                {
                    string navURL = String.Format("{0}?envelopeID={1}&accountID={2}&source=Template", "EmbeddedHost.aspx", status.EnvelopeID,
                        envelopeInfo.AccountId);

                    Response.Redirect(navURL, false);
                }
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }
        }

        protected DocuSignAPI.Recipient[] ConstructRecipients()
        {
            List<DocuSignAPI.Recipient> runningList = new List<DocuSignAPI.Recipient>();

            // Create all the recipients on the template's envelope
            for (int i = 1; i <= Request.Form.Count; i++)
            {
                if (Request.Form["RecipientRole" + i] != null)
                {
                    DocuSignAPI.Recipient r = new DocuSignAPI.Recipient();
                    r.UserName = Request.Form["RecipientName" + i].ToString();
                    r.Email = Request.Form["RecipientEmail" + i].ToString();
                    r.ID = i.ToString();
                    r.RoleName = Request.Form["RecipientRole" + i];
                    r.Type = DocuSignAPI.RecipientTypeCode.Signer;
                    runningList.Add(r);
                }
                else
                {
                    break;
                }
            }

            return runningList.ToArray();
        }

        protected DocuSignAPI.TemplateReferenceRoleAssignment[] CreateFinalRoleAssignments(DocuSignAPI.Recipient[] recipients)
        {
            List<DocuSignAPI.TemplateReferenceRoleAssignment> runningList = new List<DocuSignAPI.TemplateReferenceRoleAssignment>();

            // Match up all the recipients to the roles on the template
            foreach (DocuSignAPI.Recipient recipient in recipients)
            {
                DocuSignAPI.TemplateReferenceRoleAssignment assign = new DocuSignAPI.TemplateReferenceRoleAssignment();
                assign.RecipientID = recipient.ID;
                assign.RoleName = recipient.RoleName;

                runningList.Add(assign);
            }
            return runningList.ToArray();
        }

        protected void LoadTemplates()
        {
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            DocuSignAPI.EnvelopeTemplateDefinition[] templates = null;

            // Load all the template the logged in user has on their account
            try
            {
                templates = client.RequestTemplates(Session["APIAccountId"].ToString(), false);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Add them to the drop-down select
            foreach (DocuSignAPI.EnvelopeTemplateDefinition template in templates)
            {
                TemplateTable.Items.Add(new ListItem("Template " + template.TemplateID + ": " + template.Name, template.TemplateID));
            }

        }

        protected void OnTemplateSelect(object src, EventArgs e)
        {
            // Request the template and populate the recipient table
            DocuSignAPI.APIServiceSoapClient client = CreateAPIProxy();
            DocuSignAPI.EnvelopeTemplate template = null;
            try
            {
                template = client.RequestTemplate(TemplateTable.Value, false);
            }
            catch (Exception ex)
            {
                base.GoToErrorPage(ex.Message);
            }

            // Populate the recipient UI
            AddRecipients(template.Envelope.Recipients);

        }

        // Add the recipients to the UI
        protected void AddRecipients(DocuSignAPI.Recipient[] recipients)
        {
            int i = 1;
            foreach (DocuSignAPI.Recipient recipient in recipients)
            {
                System.Web.UI.HtmlControls.HtmlTableRow row = new System.Web.UI.HtmlControls.HtmlTableRow();
                System.Web.UI.HtmlControls.HtmlTableCell roleCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                System.Web.UI.HtmlControls.HtmlTableCell nameCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                System.Web.UI.HtmlControls.HtmlTableCell emailCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                System.Web.UI.HtmlControls.HtmlTableCell securityCell = new System.Web.UI.HtmlControls.HtmlTableCell();
                System.Web.UI.HtmlControls.HtmlTableCell inviteCell = new System.Web.UI.HtmlControls.HtmlTableCell();

                roleCell.InnerHtml = "<input id=\"RecipientRole\" type=\"text\" readonly=\"true\" name=\"RecipientRole" + i.ToString() + "\" value=\"" + recipient.RoleName + "\"/>";

                nameCell.InnerHtml = "<input id=\"RecipientName\" type=\"text\" name=\"RecipientName" + i.ToString() + "\" value=\"" + recipient.UserName + "\"/>";

                emailCell.InnerHtml = "<input id=\"RecipientEmail\" type=\"text\" name=\"RecipientEmail" + i.ToString() + "\" value=\"" + recipient.Email + "\"/>";

                string security = "";
                if (!String.IsNullOrEmpty(recipient.AccessCode))
                {
                    security = "Access code: " + recipient.AccessCode;
                }
                else if (recipient.PhoneAuthentication != null)
                {
                    security = "Phone Authentication";
                }
                else if (recipient.RequireIDLookup)
                {
                    security = "ID Check";
                }
                else
                {
                    security = "None";
                }

                securityCell.InnerHtml = "<input id=\"RecipientSecurity\" type=\"text\" readonly=\"true\" name=\"RecipientSecurity" + i.ToString() + "\" value=\"" + security + "\"/>";

                inviteCell.InnerHtml = "<ul class=\"switcher\" name=\"RecipientInvite1\" ><li class=\"active\"><a href=\"#\" title=\"On\">ON</a></li><li><a href=\"#\" title=\"OFF\">OFF</a></li></ul>";
                inviteCell.Attributes["ID"] = String.Format("RecipientInvite{0}", recipient.ID);
                inviteCell.Attributes["name"] = String.Format("RecipientInvite{0}", recipient.ID);

                row.Cells.Add(roleCell);
                row.Cells.Add(nameCell);
                row.Cells.Add(emailCell);
                row.Cells.Add(securityCell);
                row.Cells.Add(inviteCell);
                RecipientTable.Rows.Add(row);
                i++;
            }
        }
    }
}
