using DocuSign2011Q1Sample.DocuSignAPI;
using System;
using System.Data;
using System.Collections;
using System.Collections.Generic;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;

public class BasePage : System.Web.UI.Page
{
    public BasePage()
    {

    }

    protected override void OnLoad(EventArgs e)
    {
        base.OnLoad(e);
    }

    public APIServiceSoapClient CreateAPIProxy()
    {
        DocuSign2011Q1Sample.AccountCredentials creds = GetAPICredentials();
        APIServiceSoapClient apiClient = new APIServiceSoapClient("APIServiceSoap", creds.ApiUrl);
        apiClient.ClientCredentials.UserName.UserName = creds.UserName;
        apiClient.ClientCredentials.UserName.Password = creds.Password;

        return apiClient;
    }

    public void clearSessionVars()
    {
        Session["LoggedIn"] = false;
        Session["Email"] = null;
        Session["Password"] = null;
        Session["AccountID"] = null;
        Session["AccountName"] = null;
        Session["UserName"] = null;
        Session["UserID"] = null;
        Session["TemplateID"] = null;
        Session["Accounts"] = null;
    }

    public DocuSign2011Q1Sample.AccountCredentials GetAPICredentials()
    {
        DocuSign2011Q1Sample.AccountCredentials credentials = new DocuSign2011Q1Sample.AccountCredentials();
        if (SettingIsSet("APIUrl") && Session["APIAccountID"] != null && Session["APIEmail"] != null && Session["APIPassword"] != null)
        {
            credentials.AccountId = (string)Session["APIAccountID"];
            credentials.UserName = "[" + (string)Session["APIIKey"] + "]";
            credentials.UserName += (string)Session["APIEmail"];
            credentials.Password = (string)Session["APIPassword"];
            credentials.ApiUrl = ConfigurationManager.AppSettings["APIUrl"];

        }
        else
        {
            this.GoToErrorPage("Please make sure your credentials are entered in web.config");
        }
        return credentials;
    }

    public void GoToErrorPage(string errorMessage)
    {
        Session["errorMessage"] = errorMessage;
        Response.Redirect("error.aspx", true);
    }

    public bool SettingIsSet(string settingName)
    {
        // check if a value is specified in the config file
        return (ConfigurationManager.AppSettings[settingName] != null && ConfigurationManager.AppSettings[settingName].Length > 0);
    }

    public void RequireOrDie(string[] args)
    {
        // check form post for required values 
        // if not found redirect to errorpage
        ArrayList missingFields = new ArrayList();
        foreach (string s in args)
        {
            if (Request.Form[s] == null)
            {
                missingFields.Add(s);
            }
            break;
        }
        if (missingFields.Count > 0)
        {
            GoToErrorPage("Required fields missing: " + String.Join(", ", (string[])missingFields.ToArray(typeof(string))));
        }
    }

}