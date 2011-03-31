package net.docusign.sample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.docusign.api_3_0.APIServiceSoap;
import net.docusign.api_3_0.ArrayOfString;

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
	public static final String SESSION_ENVELOPEIDS = "envelopeids";
	public static final String SESSION_EMBEDTOKEN = "embedtoken";
	public static final String SESSION_STATUSES = "envelopestatuses";
	public static final String SESSION_TEMPLATES = "Templates";
	
	// Web pages
	public static final String PAGE_WELCOME = "index.jsp";
	public static final String PAGE_LOGIN = "login.jsp";
	public static final String PAGE_SENDDOCUMENT = "senddocument.jsp";
	public static final String PAGE_EMBEDDOCUSIGN = "embeddocusign.jsp";
	public static final String PAGE_EMBEDSEND = "embedsending.jsp";
	public static final String PAGE_ERROR = "error.jsp";
	public static final String PAGE_GETSTATUS = "getstatusanddocs.jsp";
	public static final String PAGE_POP = "pop.jsp";
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
	public static final Object NAME_IDCHECK = "IDCheck";
	public static final Object NAME_PHONEAUTHENTICATION = "PhoneAuthentication";
	public static final String NAME_FILE1 = "file1";
	public static final String NAME_FILE2 = "file2";
	
	// PAGE SENDTEMPLATE
	public static final String NAME_TEMPLATETABLE = "TemplateTable";
	public static final String NAME_ROLENAME = "RoleName";
	public static final String NAME_ROLE = "Role";
	public static final String NAME_NAME = "Name";
	public static final String NAME_ROLEEMAIL = "RoleEmail";
	public static final String NAME_ROLESECURITY = "RoleSecurity";
	public static final String NAME_NONE = "None";
	public static final String NAME_ROLESECURITYSETTING = "RoleSecuritySetting";
	
	// PAGE EMBEDDOCUSIGN
	public static final String NAME_ONESIGNER = "OneSigner";
	public static final String NAME_TWOSIGNERS = "TwoSigners";
	
	// URL Parameters
	public static final String PARAM_ENVELOPEID = "envelopeID";
	
	// Error messages
	public static final String ERROR_CONFIG = "Could not load client.properties from the root of the test classpath.";
	public static final String ERROR_SUBJECT = "You must have a subject";
	public static final String ERROR_EMAILBLURB = "You must have an email blurb";
	public static final String ERROR_RECIPIENTS = "Must have at least one recipient";
	public static final String ERROR_SEND = "Error sending envelope";
	public static final String ERROR_EMBED = "Error embedding envelope";
	
	// resources
	public static final String RESOURCE_STOCKDOC = "/resources/Docusign_Demo_11.pdf";
	public static final String RESOURCE_BLANKDOC = "/resources/BlankPDF.pdf";
	
	// helper functions
	public static long daysBetween(Date max, Date min) {
		return (max.getTime() - min.getTime())/86400000;
	}
	
	@SuppressWarnings("unchecked")
	public static void addEnvelopeID(HttpServletRequest request, String envelopeID) {
		HttpSession session = request.getSession();
		List<String> envIDs;
		if (session.getAttribute(Utils.SESSION_ENVELOPEIDS ) == null) {
			envIDs = new ArrayList<String>();
		}
		else {
			envIDs = (List<String>) session.getAttribute(Utils.SESSION_ENVELOPEIDS);
		}
		envIDs.add(envelopeID);
		session.setAttribute(Utils.SESSION_ENVELOPEIDS, envIDs);
	}

	public static String getCallbackURL(HttpServletRequest request, String pageName) {
		String urlbase = request.getRequestURL().toString();

		return urlbase.substring(0, urlbase.lastIndexOf('/')) + "/" + pageName;
	}
	
	public static APIServiceSoap getAPI(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DocusignAPICredentials apiCreds = new DocusignAPICredentials();
		apiCreds.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
		apiCreds.setUserId(session.getAttribute(Utils.SESSION_USER_ID).toString());
		apiCreds.setIntegratorsKey(session.getAttribute(Utils.SESSION_INTEGRATORS_KEY).toString());
		apiCreds.setPassword(session.getAttribute(Utils.SESSION_PASSWORD).toString());
		apiCreds.setUserEmail(session.getAttribute(Utils.SESSION_EMAIL).toString());
		apiCreds.setDocusignWebserviceEndpoint(
				session.getAttribute(Utils.DOCUSIGN_WEBSERVICE_ENDPOINT).toString());
		
		DocusignWebserviceFactory wsFactory = new DocusignWebserviceFactory();
		wsFactory.setEmail(apiCreds.getUserEmail());
		wsFactory.setIntegratorsId(apiCreds.getIntegratorsKey());
		wsFactory.setUserId(apiCreds.getUserId());
		
		return wsFactory.setupClient(apiCreds.getDocusignWebserviceEndpoint()).authorizeAPI(apiCreds);
	}
}
