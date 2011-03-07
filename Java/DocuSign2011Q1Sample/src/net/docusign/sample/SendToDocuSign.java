package net.docusign.sample;


import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.docusign.api_3_0.*;

/**
 * Servlet implementation class SendToDocuSign
 */
@WebServlet("/SendToDocuSign")
public class SendToDocuSign extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONFIG_PROPERTIES_CLASSPATH_LOCATION = "/config.properties";
	private static final String DOCUSIGN_INTEGRATORS_KEY = "docusign.integrators.key";
	private static final String DOCUSIGN_ACCOUNT_ID = "docusign.account.id";
	private static final String DOCUSIGN_USER_ID = "docusign.user.id";
	private static final String DOCUSIGN_PASSWORD = "docusign.password";
	private static final String EMAIL = "email";
	private static final String DOCUSIGN_WEBSERVICE_ENDPOINT = "docusign.webservice.endpoint";
	private static final String CONFIG_ERROR_MESSAGE = "Could not load client.properties from the root of the test classpath.";
	protected String accountId;
	protected APIServiceSoap api;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendToDocuSign() {
		super();
		Properties testProperties = new Properties();
		try {
			testProperties.load(getClass().getResourceAsStream(
					CONFIG_PROPERTIES_CLASSPATH_LOCATION));
		} catch (IOException ioexception) {
			System.err.println(CONFIG_ERROR_MESSAGE);
		}

		DocusignAPICredentials apiCredentials = new DocusignAPICredentials();
		apiCredentials.setAccountId(testProperties
				.getProperty(DOCUSIGN_ACCOUNT_ID));
		apiCredentials.setUserId(testProperties.getProperty(DOCUSIGN_USER_ID));
		apiCredentials.setIntegratorsKey(testProperties
				.getProperty(DOCUSIGN_INTEGRATORS_KEY));
		apiCredentials.setPassword(testProperties
				.getProperty(DOCUSIGN_PASSWORD));
		apiCredentials.setUserEmail(testProperties.getProperty(EMAIL));
		apiCredentials.setDocusignWebserviceEndpoint(testProperties
				.getProperty(DOCUSIGN_WEBSERVICE_ENDPOINT));

		DocusignWebserviceFactory docusignWebserviceFactory = new DocusignWebserviceFactory();
		docusignWebserviceFactory.setEmail(apiCredentials.getUserEmail());
		docusignWebserviceFactory.setIntegratorsId(apiCredentials
				.getIntegratorsKey());
		docusignWebserviceFactory.setUserId(apiCredentials.getUserId());

		accountId = testProperties.getProperty(DOCUSIGN_ACCOUNT_ID);
		api = docusignWebserviceFactory.setupClient(
				apiCredentials.getDocusignWebserviceEndpoint()).authorizeAPI(
				apiCredentials);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//
		// get the variables from the JSP
		String userName = request.getParameter("userName").toString();
		String email = request.getParameter("email").toString();
		String templateId = request.getParameter("templateId").toString();
		String roleName = request.getParameter("roleName").toString();

		//
		// get the template
		TemplateReference ref = new TemplateReference();
		ref.setTemplate(templateId);
		ref.setTemplateLocation(TemplateLocationCode.SERVER);

		ArrayOfTemplateReference templateRefs = new ArrayOfTemplateReference();
		templateRefs.getTemplateReference().add(ref);

		//
		// add one recipient
		Recipient recipient = new Recipient();
		recipient.setUserName(userName);
		recipient.setEmail(email);
		recipient.setRoleName(roleName);
		recipient.setType(RecipientTypeCode.SIGNER);
		recipient.setID(new BigInteger("1"));

		ArrayOfRecipient1 recipients = new ArrayOfRecipient1();
		recipients.getRecipient().add(recipient);

		//
		// set up the envelope information
		EnvelopeInformation envelopeInformation = new EnvelopeInformation();
		envelopeInformation.setAccountId(accountId);
		envelopeInformation.setSubject("Sending from a servlet");

		EnvelopeStatus status = api.createEnvelopeFromTemplates(templateRefs,
				recipients, envelopeInformation, true);

		response.sendRedirect("sendingComplete.jsp?envelopeId="
				+ status.getEnvelopeID());
	}

}
