using DocuSignSample.DocuSignAPI;
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

    protected string logoutCtrlName = "ctl00$logoutBtn";

    public BasePage()
    {

    }

    protected override void OnLoad(EventArgs e)
    {
        base.OnLoad(e);
    }

    public APIServiceSoapClient CreateAPIProxy()
    {
        DocuSignSample.AccountCredentials creds = GetAPICredentials();
        APIServiceSoapClient apiClient = new APIServiceSoapClient("APIServiceSoap", creds.ApiUrl);
        apiClient.ClientCredentials.UserName.UserName = creds.UserName;
        apiClient.ClientCredentials.UserName.Password = creds.Password;

        return apiClient;
    }

    public DocuSignSample.AccountCredentials GetAPICredentials()
    {
        DocuSignSample.AccountCredentials credentials = new DocuSignSample.AccountCredentials();
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

    public void AddEnvelopeID(string id)
    {
        if (Session["EnvelopeIDs"] == null)
        {
            Session["EnvelopeIDs"] = id;
        }
        else
        {
            Session["EnvelopeIDs"] += "," + id;
        }
    }

    public string[] GetEnvelopeIDs()
    {
        if (Session["EnvelopeIDs"] == null)
        {
            return new string[0];
        }
        string ids = Session["EnvelopeIDs"].ToString();
        return ids.Split(',');
    }
}