package net.docusign.sample;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.docusign.credential.*;
import net.docusign.sample.*;

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
    	
		Properties creds = new Properties();
		try {
			creds.load(getClass().getResourceAsStream(
					Utils.CONFIG_PROPERTIES_CLASSPATH_LOCATION));
			session.setAttribute(Utils.SESSION_EMAIL, creds.getProperty(Utils.SESSION_EMAIL));
			session.setAttribute(Utils.SESSION_PASSWORD, creds.getProperty(Utils.SESSION_PASSWORD));
			session.setAttribute(Utils.SESSION_INTEGRATORS_KEY, creds.getProperty(Utils.SESSION_INTEGRATORS_KEY));
		} catch (IOException ioexception) {
			System.err.println(Utils.ERROR_CONFIG);
		}

    	response.sendRedirect(Utils.PAGE_LOGIN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// TODO Login
		if (request.getParameterValues("submit") != null) {
			session.setAttribute(Utils.SESSION_EMAIL, request.getParameterValues(Utils.NAME_EMAIL)[0]);
			session.setAttribute(Utils.SESSION_PASSWORD, request.getParameterValues(Utils.NAME_PASSWORD)[0]);
			session.setAttribute(Utils.SESSION_INTEGRATORS_KEY, request.getParameterValues(Utils.NAME_IKEY)[0]);
			if (login()) {
				session.setAttribute(Utils.SESSION_LOGGEDIN, true);
				response.sendRedirect(Utils.PAGE_SENDDOCUMENT);
			}
			else {
				session.setAttribute(Utils.SESSION_LOGGEDIN, false);
				response.sendRedirect(Utils.PAGE_ERROR);
			}
		}
		
		// TODO Reset
		if (request.getParameterValues("reset") != null) {
			doGet(request, response);
		}
	}


	private boolean login() {
		// TODO Auto-generated method stub
		return false;
	}
		
}
