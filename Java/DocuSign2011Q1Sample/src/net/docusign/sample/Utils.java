package net.docusign.sample;

import java.util.Date;

public class Utils {
	
	// System strings 
	public static final String CONFIG_PROPERTIES_CLASSPATH_LOCATION = "/config.properties";
	public static final String DOCUSIGN_WEBSERVICE_ENDPOINT = "docusign.webservice.endpoint";
	public static final String DOCUSIGN_CREDENTIAL_ENDPOINT = "docusign.credential.endpoint";

	// session attributes
	public static final String SESSION_INTEGRATORS_KEY = "docusign.integrators.key";
	public static final String SESSION_ACCOUNT_ID = "docusign.account.id";
	public static final String SESSION_USER_ID = "docusign.user.id";
	public static final String SESSION_PASSWORD = "docusign.password";
	public static final String SESSION_EMAIL = "email";
	public static final String SESSION_LOGGEDIN = "loggedin";
	public static final String SESSION_ERROR_MSG = "errmsg";
	
	// Web pages
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String PAGE_SENDDOCUMENT = "senddocument.jsp";
	public static final String PAGE_EMBED = "embeddocusign.jsp";
	public static final String PAGE_EMBEDSEND = "embedsending.jsp";
	public static final String PAGE_ERROR = "error.jsp";
	public static final String PAGE_GETSTATUS = "getstatusanddocs.jsp";
	public static final String PAGE_POP = "pop.html";
	public static final String PAGE_POP2 = "pop2.html";
	public static final String PAGE_SENDTEMPLATE = "sendatemplate.jsp";
	public static final String PAGE_SUCCESS = "sendsuccess.jsp";
	
	// Controller classes
	public static final String CONTROLLER_LOGIN = "Login";
	public static final String CONTROLLER_SENDDOCUMENT = "SendDocument";
	public static final String CONTROLLER_EMBEDDOCUSIGN = "EmbedDocusign";
	public static final String CONTROLLER_GETSTATUS = "GetStatusAndDocs";
	public static final String CONTROLLER_SENDTEMPLATE = "SendATemplate";
	
	// Form parameter names
	//	PAGE_LOGIN
	public static final String NAME_EMAIL = "DevCenterEmail";
	public static final String NAME_PASSWORD = "DevCenterPassword";
	public static final String NAME_IKEY = "DevCenterIKey";
	public static final String NAME_SUBMIT = "submit";
	public static final String NAME_RESET ="reset";
	
	//	PAGE SENDDOCUMENT
	public static final String NAME_SENDNOW = "SendNow";
	public static final String NAME_SUBJECT = "subject";
	public static final String NAME_EMAILBLURB = "emailBlurb";
	public static final String NAME_RECIPIENTNAME = "RecipientName";
	public static final String NAME_RECIPIENTEMAIL = "RecipientEmail";
	public static final String NAME_RECIPIENTSECURITY = "RecipientSecurity";
	public static final String NAME_ACCESSCODE = "AccessCode";
	public static final String NAME_RECIPIENTSECURITYSETTING = "RecipientSecuritySetting";
	public static final String NAME_STOCKDOC = "stockdoc";
	public static final String NAME_SIGNERATTACHMENT = "signerattachment";
	public static final String NAME_MARKUP = "markup";
	public static final String NAME_ENABLEPAPER = "enablepaper";
	public static final String NAME_REMINDERS = "reminders";
	public static final String NAME_EXPIRATION = "expiration";	
	public static final String NAME_ADDSIGS = "addsigs";
	public static final String NAME_FORMFIELDS = "formfields";
	public static final String NAME_CONDITIONALFIELDS = "conditionalfields";
	public static final String NAME_COLLABFIELDS = "collabfields";
	
	// Error messages
	public static final String ERROR_CONFIG = "Could not load client.properties from the root of the test classpath.";
	public static final String ERROR_SUBJECT = "You must have a subject";
	public static final String ERROR_EMAILBLURB = "You must have an email blurb";
	
	// resources
	public static final String RESOURCE_STOCKDOC = "resources/Docusign_Demo_11.pdf";
	public static final String RESOURCE_BLANKDOC = "resources/BlankPDF.pdf";
	
	// helper functions
	public static long daysBetween(Date max, Date min) {
		return (max.getTime() - min.getTime())/86400000;
	}
}
