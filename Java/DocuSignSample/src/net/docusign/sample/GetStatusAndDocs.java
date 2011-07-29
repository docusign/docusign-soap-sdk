package net.docusign.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import net.docusign.api_3_0.APIServiceSoap;
import net.docusign.api_3_0.ArrayOfString2;
import net.docusign.api_3_0.EnvelopePDF;
import net.docusign.api_3_0.EnvelopeStatusFilter;
import net.docusign.api_3_0.FilteredEnvelopeStatuses;
import net.docusign.api_3_0.RequestRecipientTokenAuthenticationAssertion;
import net.docusign.api_3_0.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod;
import net.docusign.api_3_0.RequestRecipientTokenClientURLs;

/**
 * Servlet implementation class GetStatusAndDocs
 */
public class GetStatusAndDocs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStatusAndDocs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(Utils.SESSION_EMBEDTOKEN, "");

		HttpSession session = request.getSession();

        // Make sure we're logged in
		if (session.getAttribute(Utils.SESSION_LOGGEDIN) == null ||
				session.getAttribute(Utils.SESSION_LOGGEDIN).equals(false)) {
				response.sendRedirect(Utils.CONTROLLER_LOGIN);
		}
		else {
		
		    // Do we have envelope IDs in this session?
			if (session.getAttribute(Utils.SESSION_ENVELOPEIDS) != null) {
				APIServiceSoap api = Utils.getAPI(request);
				
				// Grab all the envelope IDs in this session
				ArrayOfString2 envIDs = new ArrayOfString2();
				envIDs.getEnvelopeId().addAll((List<String>) session.getAttribute(Utils.SESSION_ENVELOPEIDS));
				
				// Create a filter so we only retrieve these envelope statuses
				EnvelopeStatusFilter filter = new EnvelopeStatusFilter();
				filter.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
				filter.setEnvelopeIds(envIDs);
				try {
				
				    // Call requestStatusesEx on these envelopes
					FilteredEnvelopeStatuses statuses = api.requestStatusesEx(filter);
					session.setAttribute(Utils.SESSION_STATUSES, 
							statuses.getEnvelopeStatuses().getEnvelopeStatus());
				} catch (Exception e) {
					
				}
			}
			response.sendRedirect(Utils.PAGE_GETSTATUS);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the parameter names
		Enumeration paramNames = request.getParameterNames();
		
		// Loop through the parameter names
		while (paramNames.hasMoreElements()) {
		
			String paramName = (String)paramNames.nextElement();
			if (paramName.startsWith(Utils.NAME_STARTSIGNING)) {
			
				// We want to start this user signing
				startSigning(paramName, request);
				response.sendRedirect(Utils.PAGE_GETSTATUS);
			} else if (paramName.startsWith(Utils.NAME_DOWNLOAD)) {
			
			    // We want to download the specified envelope
			    downloadEnvelope(paramName, request, response);
			}
		}		
	}
	
	protected void downloadEnvelope(String param, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = param.split("\\+")[1];
				
		// Request the PDF of the envelope
		APIServiceSoap api = Utils.getAPI(request);     
		EnvelopePDF pdf = api.requestPDF(eid);
		        
		// Start download of the resulting PDF
		byte[] documentBytes = pdf.getPDFBytes();
		response.setHeader("Content-Disposition", "attachment;filename=Envelope.pdf");
        response.setContentLength(documentBytes.length);
        response.setContentType("application/pdf");
        response.getOutputStream().write(documentBytes);
        
        return;
	}
	
	protected void startSigning(String param, HttpServletRequest request) throws ServletException, IOException {
	
				// Parse out envelope id, email, username, client user id
				String[] params = param.split("\\&");

				String eid = "", cid = "", uname = "", email = "";
				for (int i = 0; i < params.length; i++) {
				    String[] pair = params[i].split("\\+");

				    if(pair[0].equals("SignDocEnvelope")) {
				    	eid = pair[1];
				    } else if (pair[0].equals("Email")) {
				    	email = pair[1];
				    } else if (pair[0].equals("UserName")) {
				    	uname = pair[1];
				    } else if (pair[0].equals("CID")) {
				    	cid = pair[1];
				    }
				}
				
		        // Request the token
		        try {
					getToken(request, eid, email, uname, cid);
				} catch (DatatypeConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	protected void getToken(HttpServletRequest request, String eid, String email, String username, String CID) throws DatatypeConfigurationException {
		String token = null;
		
		// Create the assertion
		RequestRecipientTokenAuthenticationAssertion assertion = new RequestRecipientTokenAuthenticationAssertion();
		assertion.setAssertionID(UUID.randomUUID().toString());
		
		// wsdl2java translates this to XMLGregorianCalendar
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(new Date());
		assertion.setAuthenticationInstant(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal));
		
		assertion.setAuthenticationMethod(RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.PASSWORD);
		assertion.setSecurityDomain("DocuSign2010Q1Sample");
				
		// Create the URLs that DocuSign will redirect the iframe to after different events
		RequestRecipientTokenClientURLs urls = new RequestRecipientTokenClientURLs();
		String urlbase = Utils.getCallbackURL(request, Utils.PAGE_POP);
		
	    urls.setOnAccessCodeFailed(urlbase + "?event=AccessCodeFailed&uname=" + username);
	    urls.setOnCancel(urlbase + "?event=Cancel&uname=" + username);
	    urls.setOnDecline(urlbase + "?event=Decline&uname=" + username);
	    urls.setOnException(urlbase + "?event=Exception&uname=" + username);
	    urls.setOnFaxPending(urlbase + "?event=FaxPending&uname=" + username);
	    urls.setOnIdCheckFailed(urlbase + "?event=IdCheckFailed&uname=" + username);
	    urls.setOnSessionTimeout(urlbase + "?event=SessionTimeout&uname=" + username);
	    urls.setOnTTLExpired(urlbase + "?event=TTLExpired&uname=" + username);
	    urls.setOnViewingComplete(urlbase + "?event=ViewingComplete&uname=" + username);
	    urls.setOnSigningComplete(urlbase + "?event=SigningComplete&uname=" + username);

	    // Get the API service and call RequestRecipientToken for this recipient
	    APIServiceSoap api = Utils.getAPI(request);
	    token = api.requestRecipientToken(eid, 
	    		CID, 
	    		username, 
	    		email, 
	    		assertion, 
	    		urls);
	    
	    // Set the iframe to the token
	    request.getSession().setAttribute(Utils.SESSION_EMBEDTOKEN, token);
	}
}
