package net.docusign.sample;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.docusign.api_3_0.APIServiceSoap;
import net.docusign.api_3_0.ArrayOfRecipient;
import net.docusign.api_3_0.ArrayOfRecipient1;
import net.docusign.api_3_0.ArrayOfTemplateReference;
import net.docusign.api_3_0.ArrayOfTemplateReferenceRoleAssignment;
import net.docusign.api_3_0.EnvelopeInformation;
import net.docusign.api_3_0.EnvelopeStatus;
import net.docusign.api_3_0.EnvelopeStatusCode;
import net.docusign.api_3_0.EnvelopeTemplates;
import net.docusign.api_3_0.Expirations;
import net.docusign.api_3_0.Notification;
import net.docusign.api_3_0.Recipient;
import net.docusign.api_3_0.RecipientTypeCode;
import net.docusign.api_3_0.Reminders;
import net.docusign.api_3_0.RequestSenderToken;
import net.docusign.api_3_0.TemplateLocationCode;
import net.docusign.api_3_0.TemplateReference;
import net.docusign.api_3_0.TemplateReferenceRoleAssignment;

/**
 * Servlet implementation class SendATemplate
 */
public class SendATemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendATemplate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute(Utils.SESSION_LOGGEDIN) == null ||
			session.getAttribute(Utils.SESSION_LOGGEDIN).equals(false)) {
			response.sendRedirect(Utils.CONTROLLER_LOGIN);
		}
		else {
			// stick the list of templates into a session attribute that the
			// sendatemplate.jsp page can display
			APIServiceSoap api = Utils.getAPI(request);
			try {
				EnvelopeTemplates templates = api.requestTemplates(
						session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString(), 
						false);
				session.setAttribute(Utils.SESSION_TEMPLATES, templates.getEnvelopeTemplateDefinition());
				response.sendRedirect(Utils.PAGE_SENDTEMPLATE);
			} catch (Exception e) {
				session.setAttribute(Utils.SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Utils.PAGE_ERROR);
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			createSampleEnvelope(request, response);
		} catch (Exception e) {
			request.getSession().setAttribute(Utils.SESSION_ERROR_MSG, e.getMessage());
			response.sendRedirect(Utils.PAGE_ERROR);
		}
	}

	private void createSampleEnvelope(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		HttpSession session = request.getSession();
		EnvelopeInformation envInfo = new EnvelopeInformation();
		envInfo.setSubject(request.getParameter(Utils.NAME_SUBJECT));
		envInfo.setEmailBlurb(request.getParameter(Utils.NAME_EMAILBLURB));
		envInfo.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
		
		if (request.getParameter(Utils.NAME_REMINDERS).length() > 0) {
			envInfo.setNotification(new Notification());
			envInfo.getNotification().setReminders(new Reminders());
			long days = Utils.daysBetween(new SimpleDateFormat("mm/ss/yyyy").parse(request.getParameter(Utils.NAME_REMINDERS)), new Date());
			envInfo.getNotification().getReminders().setReminderDelay(new BigInteger(Long.toString(days)));
			envInfo.getNotification().getReminders().setReminderFrequency(new BigInteger("2"));
		}
		
		if (request.getParameter(Utils.NAME_EXPIRATION).length() > 0) {
			envInfo.setNotification(new Notification());
			envInfo.getNotification().setExpirations(new Expirations());
			envInfo.getNotification().getExpirations().setExpireEnabled(true);
			long days = Utils.daysBetween(new SimpleDateFormat("mm/ss/yyyy").parse(request.getParameter(Utils.NAME_EXPIRATION)), new Date());
			envInfo.getNotification().getExpirations().setExpireAfter(new BigInteger(Long.toString(days)));
			envInfo.getNotification().getExpirations().setExpireWarn(new BigInteger(Long.toString(days - 2)));
		}
		
		// get all recipients
		ArrayOfRecipient recipients = constructRecipients(request);
		
		// Construct the template reference
		TemplateReference tref = new TemplateReference();
		tref.setTemplateLocation(TemplateLocationCode.SERVER);
		tref.setTemplate(request.getParameter(Utils.NAME_TEMPLATETABLE));
		tref.setRoleAssignments(createFinalRoleAssignments(recipients));
		ArrayOfTemplateReference trefs = new ArrayOfTemplateReference();
		trefs.getTemplateReference().add(tref);
		
		if (request.getParameterValues(Utils.NAME_SENDNOW) != null) {
			sendNow(trefs, envInfo, recipients, request, response);
		}
		else {
			embedSending(trefs, envInfo, recipients, request, response);
		}
	}

	private ArrayOfTemplateReferenceRoleAssignment createFinalRoleAssignments(ArrayOfRecipient recipients) {
		ArrayOfTemplateReferenceRoleAssignment tras = new ArrayOfTemplateReferenceRoleAssignment();
		for (Recipient r : recipients.getRecipient()) {
			TemplateReferenceRoleAssignment assign = new TemplateReferenceRoleAssignment();
			assign.setRoleName(r.getRoleName());
			assign.setRecipientID(r.getID());
			tras.getRoleAssignment().add(assign);
		}
		return tras;
	}

	private void embedSending(ArrayOfTemplateReference trefs,
			EnvelopeInformation envInfo, ArrayOfRecipient recipients,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		APIServiceSoap api = Utils.getAPI(request);
		
		ArrayOfRecipient1 recipients1 = new ArrayOfRecipient1();
		recipients1.getRecipient().addAll(recipients.getRecipient());
		
		EnvelopeStatus status = api.createEnvelopeFromTemplates(trefs, recipients1, envInfo, false);
		if (status.getStatus() == EnvelopeStatusCode.CREATED) {
			Utils.addEnvelopeID(request, status.getEnvelopeID());
			String token = api.requestSenderToken(status.getEnvelopeID(), envInfo.getAccountId(), 
					Utils.getCallbackURL(request, Utils.PAGE_POP));
			request.getSession().setAttribute(Utils.SESSION_EMBEDTOKEN, token);
			response.sendRedirect(Utils.PAGE_EMBEDSEND + 
					"?envelopeid=" + status.getEnvelopeID() + 
					"$accountID=" + envInfo.getAccountId() +
					"&source=Document");
		}
	}

	private void sendNow(ArrayOfTemplateReference trefs,
			EnvelopeInformation envInfo, ArrayOfRecipient recipients, HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		APIServiceSoap api = Utils.getAPI(request);
		
		ArrayOfRecipient1 recipients1 = new ArrayOfRecipient1();
		recipients1.getRecipient().addAll(recipients.getRecipient());
		
		EnvelopeStatus status = api.createEnvelopeFromTemplates(trefs, recipients1, envInfo, true);
		if (status.getStatus() == EnvelopeStatusCode.SENT) {
			Utils.addEnvelopeID(request, status.getEnvelopeID());
			response.sendRedirect(Utils.CONTROLLER_GETSTATUS);
		}
	}

	private ArrayOfRecipient constructRecipients(HttpServletRequest request) {
		ArrayOfRecipient recipients = new ArrayOfRecipient();
		int index = 1;
		if (request.getParameter(Utils.NAME_ROLENAME + index) != null) {
			while (request.getParameter(Utils.NAME_ROLENAME + index) != null) {
				Recipient r = new Recipient();
				
				r.setUserName(request.getParameter(Utils.NAME_NAME + index));
				r.setEmail(request.getParameter(Utils.NAME_ROLEEMAIL + index));
				r.setRequireIDLookup(false);
				r.setRoleName(request.getParameter(Utils.NAME_ROLENAME + index));
				
				// TODO add handling for Phone authentication
				
				r.setID(new BigInteger(Integer.toString(index)));
				r.setType(RecipientTypeCode.SIGNER);
				r.setRoutingOrder(index);
				recipients.getRecipient().add(r);
				index++;
			}
		}
		else {
			recipients = null;
		}
		return recipients;
	}
}
