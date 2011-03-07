package net.docusign.sample;

public class Utils {
	
	// System strings 
	public static final String CONFIG_PROPERTIES_CLASSPATH_LOCATION = "/config.properties";
	public static final String DOCUSIGN_WEBSERVICE_ENDPOINT = "docusign.webservice.endpoint";

	// session attributes
	public static final String SESSION_INTEGRATORS_KEY = "docusign.integrators.key";
	public static final String SESSION_ACCOUNT_ID = "docusign.account.id";
	public static final String SESSION_USER_ID = "docusign.user.id";
	public static final String SESSION_PASSWORD = "docusign.password";
	public static final String SESSION_EMAIL = "email";
	public static final String SESSION_LOGGEDIN = "loggedin";
	
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
	
	// Form parameter names
	//	PAGE_LOGIN
	public static final String NAME_EMAIL = "DevCenterEmail";
	public static final String NAME_PASSWORD = "DevCenterPassword";
	public static final String NAME_IKEY = "DevCenterIKey";
	public static final String NAME_SUBMIT = "submit";
	public static final String NAME_RESET ="reset";
	
	// Error messages
	public static final String ERROR_CONFIG = "Could not load client.properties from the root of the test classpath.";
}
