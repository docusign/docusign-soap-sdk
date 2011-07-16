package net.docusign.sample;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.docusign.credential.*;
import net.docusign.credential.LoginResponse.LoginResult;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		if (session.getAttribute(Utils.SESSION_LOGGEDIN) != null &&
				session.getAttribute(Utils.SESSION_LOGGEDIN).equals(true)) {
				response.sendRedirect(Utils.CONTROLLER_SENDDOCUMENT);
		}
		else {
			session.setAttribute(Utils.SESSION_ENVELOPEIDS, null);
			session.setAttribute(Utils.SESSION_ERROR_MSG, null);
			session.setAttribute(Utils.SESSION_LOGGEDIN, false);
			Properties creds = new Properties();
			try {
				creds.load(getClass().getResourceAsStream(
						Utils.CONFIG_PROPERTIES_CLASSPATH_LOCATION));
				session.setAttribute(Utils.SESSION_EMAIL, creds.getProperty(Utils.SESSION_EMAIL));
				session.setAttribute(Utils.SESSION_PASSWORD, creds.getProperty(Utils.SESSION_PASSWORD));
				session.setAttribute(Utils.SESSION_INTEGRATORS_KEY, creds.getProperty(Utils.SESSION_INTEGRATORS_KEY));
				session.setAttribute(Utils.DOCUSIGN_CREDENTIAL_ENDPOINT, creds.getProperty(Utils.DOCUSIGN_CREDENTIAL_ENDPOINT));
				session.setAttribute(Utils.DOCUSIGN_WEBSERVICE_ENDPOINT, creds.getProperty(Utils.DOCUSIGN_WEBSERVICE_ENDPOINT));
			} catch (IOException ioexception) {
				System.err.println(Utils.ERROR_CONFIG);
			}
	
	    	response.sendRedirect(Utils.PAGE_LOGIN);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Handle Submit button
		if (request.getParameterValues(Utils.NAME_SUBMIT) != null) {
			session.setAttribute(Utils.SESSION_EMAIL, request.getParameterValues(Utils.NAME_EMAIL)[0]);
			session.setAttribute(Utils.SESSION_PASSWORD, request.getParameterValues(Utils.NAME_PASSWORD)[0]);
			session.setAttribute(Utils.SESSION_INTEGRATORS_KEY, request.getParameterValues(Utils.NAME_IKEY)[0]);
			if (login(request, response)) {
				session.setAttribute(Utils.SESSION_LOGGEDIN, true);
				response.sendRedirect(Utils.CONTROLLER_SENDDOCUMENT);
			}
			else {
				session.setAttribute(Utils.SESSION_LOGGEDIN, false);
				response.sendRedirect(Utils.PAGE_ERROR);
			}
		}
		
		// Handle Reset button
		if (request.getParameterValues(Utils.NAME_RESET) != null) {
			doGet(request, response);
		}
	}


	private boolean login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		boolean ret = false;
		LoginResult result = null;
		HttpSession session = request.getSession();
		session.setAttribute(Utils.SESSION_ERROR_MSG, null);
		
		CredentialSoap credApi = new CredentialFactory().getCredential(
				session.getAttribute(Utils.DOCUSIGN_CREDENTIAL_ENDPOINT).toString());
		
		try {
			// add integrator key TODO: remove if this is changed
			result = credApi.login(
					"[" + session.getAttribute(Utils.SESSION_INTEGRATORS_KEY).toString() + "]" +
					session.getAttribute(Utils.SESSION_EMAIL).toString(), 
					session.getAttribute(Utils.SESSION_PASSWORD).toString());
		} catch (Exception e) {
			// TODO: handle exception
			ret = false;
		}
		
		if (result != null && result.isSuccess()) {
			session.setAttribute(Utils.SESSION_ACCOUNT_ID, 
					result.getAccounts().getAccount().get(0).getAccountID());
			session.setAttribute(Utils.SESSION_USER_ID, 
					result.getAccounts().getAccount().get(0).getUserID());
			ret = true;
		}
		else {
			session.setAttribute(Utils.SESSION_ERROR_MSG, result.getErrorCode());
			ret = false;
		}
		
		return ret;
	}
		
}
