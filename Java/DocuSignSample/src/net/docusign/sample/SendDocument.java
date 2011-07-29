package net.docusign.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.docusign.api_3_0.*;

/**
 * Servlet implementation class SendDocument
 */
public class SendDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDocument() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		// Make sure we're logged in
		if (session.getAttribute(Utils.SESSION_LOGGEDIN) == null ||
			session.getAttribute(Utils.SESSION_LOGGEDIN).equals(false)) {
			response.sendRedirect(Utils.CONTROLLER_LOGIN);
		}
		else {
			response.sendRedirect(Utils.PAGE_SENDDOCUMENT);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// using org.apache.commons.fileupload to deal with multipart form. Not
		// needed with tomcat 7+ or glassfish 3+
		// a parser is required to get at the parameters of a multipart request
		boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultiPart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			try {
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						request.setAttribute(item.getFieldName(), item.getString());
					}
					else {
						// Stick the whole FileItem in the attribute if it's a file
						request.setAttribute(item.getFieldName(), item);
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Envelope envelope;
		try {
			envelope = buildEnvelope(request, response);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		if (request.getAttribute(Utils.NAME_SENDNOW) != null) {
			try {
				sendNow(envelope, request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute(Utils.SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Utils.PAGE_ERROR);				
				return;
			}
		}
		else {
			try {
				embedSending(envelope, request, response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getSession().setAttribute(Utils.SESSION_ERROR_MSG, e.getMessage());
				response.sendRedirect(Utils.PAGE_ERROR);				
				return;
			}
		}
	}

	private void sendNow(Envelope envelope, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		APIServiceSoap api = Utils.getAPI(request);
		EnvelopeStatus status = api.createAndSendEnvelope(envelope);
		if (status.getStatus() == EnvelopeStatusCode.SENT) {
			Utils.addEnvelopeID(request, status.getEnvelopeID());
			response.sendRedirect(Utils.CONTROLLER_GETSTATUS);
		}
	}

	private void embedSending(Envelope envelope, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		APIServiceSoap api = Utils.getAPI(request);
		HttpSession session = request.getSession();
		EnvelopeStatus status = api.createEnvelope(envelope);
		if (status.getStatus() == EnvelopeStatusCode.CREATED) {
			Utils.addEnvelopeID(request, status.getEnvelopeID());
			String returnURL = Utils.getCallbackURL(request, Utils.PAGE_POP);
			String embedToken = api.requestSenderToken(status.getEnvelopeID(), 
					session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString(), returnURL);
			session.setAttribute(Utils.SESSION_EMBEDTOKEN, embedToken);
			response.sendRedirect(Utils.PAGE_EMBEDSEND + 
					"?envelopeid=" + status.getEnvelopeID() + 
					"$accountID=" + envelope.getAccountId() +
					"&source=Document");
		}
		
	}

	private Envelope buildEnvelope(HttpServletRequest request, HttpServletResponse response) 
			throws FileUploadException, IOException, ParseException {
		HttpSession session = request.getSession();
		Envelope envelope = new Envelope();
	    envelope.setSubject(request.getAttribute(Utils.NAME_SUBJECT).toString());
		envelope.setEmailBlurb(request.getAttribute(Utils.NAME_EMAILBLURB).toString());
		ArrayOfRecipient recipients = constructRecipients(request);
		envelope.setRecipients(recipients);
		
		envelope.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());		
		envelope.setTabs(addTabs(request, envelope.getRecipients().getRecipient().size()));
		envelope = processOptions(request, envelope);
		envelope.setDocuments(getDocuments(request));
		
		return envelope;
	}

	private ArrayOfDocument getDocuments(HttpServletRequest request) throws FileUploadException, IOException {
		ArrayOfDocument docs = new ArrayOfDocument();
		int id = 1;
		
		if (request.getAttribute(Utils.NAME_STOCKDOC) != null) {
			Document doc = new Document();
			
			// get stock document as byte stream without fancy libs
			String filePath = getServletContext().getRealPath(Utils.RESOURCE_STOCKDOC);
			File f = new File(filePath);
			FileInputStream fs = new FileInputStream(f);
			byte[] PDFBytes = new byte[(int) f.length()];
			fs.read(PDFBytes);
			fs.close();
			doc.setPDFBytes(PDFBytes);
			doc.setName("Demo Document");
			doc.setID(new BigInteger(Integer.toString(id++)));
			doc.setFileExtension("pdf");
			docs.getDocument().add(doc);
		}
		else {
			FileItem fItem = (FileItem) request.getAttribute(Utils.NAME_FILE1);
			if (fItem.getName().length() > 0) {
				Document doc = new Document();
				doc.setPDFBytes(fItem.get());
				doc.setName(fItem.getName());
				doc.setID(new BigInteger(Integer.toString(id++)));
				String ct = fItem.getContentType();
				doc.setFileExtension(ct.substring(ct.indexOf("/") +1));
				docs.getDocument().add(doc);
			}
			fItem = (FileItem) request.getAttribute(Utils.NAME_FILE2);
			if (fItem.getName().length() > 0) {
				Document doc = new Document();
				doc.setPDFBytes(fItem.get());
				doc.setName(fItem.getName());
				doc.setID(new BigInteger(Integer.toString(id++)));
				String ct = fItem.getContentType();
				doc.setFileExtension(ct.substring(ct.indexOf("/") +1));
				docs.getDocument().add(doc);
			}
		}
		
		if (request.getAttribute(Utils.NAME_SIGNERATTACHMENT) != null) {
			Document doc = new Document();
			String filePath = getServletContext().getRealPath(Utils.RESOURCE_STOCKDOC);
			File f = new File(filePath);
			FileInputStream fs = new FileInputStream(f);
			byte[] PDFBytes = new byte[(int) f.length()];
			fs.read(PDFBytes);
			fs.close();
			doc.setPDFBytes(PDFBytes);
			doc.setName("Signer Attachment");
			doc.setID(new BigInteger(Integer.toString(id++)));
			doc.setFileExtension("pdf");
			doc.setAttachmentDescription("Please attach your document here");
			docs.getDocument().add(doc);
		}
		return docs;
	}

	private Envelope processOptions(HttpServletRequest request, Envelope envelope) throws ParseException {
		if (request.getAttribute(Utils.NAME_MARKUP) != null) {
			envelope.setAllowMarkup(true);
		}
		if (request.getAttribute(Utils.NAME_ENABLEPAPER) != null) {
			envelope.setEnableWetSign(true);
		}
		if (request.getAttribute(Utils.NAME_REMINDERS).toString().length() > 0) {
			if (envelope.getNotification() == null) {
				envelope.setNotification(new Notification());
			}
			envelope.getNotification().setReminders(new Reminders());
			envelope.getNotification().getReminders().setReminderEnabled(true);
			String reminder = request.getAttribute(Utils.NAME_REMINDERS).toString();
			long days = Utils.daysBetween(new SimpleDateFormat("M/d/y").parse(reminder), new Date());
			envelope.getNotification().getReminders().setReminderDelay(
					new BigInteger(Long.toString(days)));
			envelope.getNotification().getReminders().setReminderFrequency(new BigInteger("2"));
		}
		if (request.getAttribute(Utils.NAME_EXPIRATION).toString().length() > 0) {
			if (envelope.getNotification() == null) {
				envelope.setNotification(new Notification());
			}
			envelope.getNotification().setExpirations(new Expirations());
			envelope.getNotification().getExpirations().setExpireEnabled(true);
			String expiration = request.getAttribute(Utils.NAME_EXPIRATION).toString();
			long days = Utils.daysBetween(new SimpleDateFormat("M/d/y").parse(expiration), new Date());
			envelope.getNotification().getExpirations().setExpireAfter(
					new BigInteger(Long.toString(days)));
			envelope.getNotification().getExpirations().setExpireWarn(
					new BigInteger(Long.toString(days - 2)));
		}
		return envelope;
	}

	private ArrayOfTab addTabs(HttpServletRequest request, int size) {
        ArrayOfTab tabs = new ArrayOfTab();
        String pageTwo = (request.getAttribute(Utils.NAME_STOCKDOC) != null) ? "2" : "1";
        String pageThree = (request.getAttribute(Utils.NAME_STOCKDOC) != null) ? "3" : "1";
        if (request.getAttribute(Utils.NAME_ADDSIGS) != null)
        {
            // Create a tab to reflect the company of the recipient
            Tab company = new Tab();
            company.setType(TabTypeCode.COMPANY);
            company.setDocumentID(new BigInteger("1"));
            company.setPageNumber(new BigInteger(pageTwo));
            company.setRecipientID(new BigInteger("1"));
            company.setXPosition(new BigInteger("342"));
            company.setYPosition(new BigInteger("303"));

            tabs.getTab().add(company);

            // Create a tab to ask the recipient to initial
            Tab init1 = new Tab();
            init1.setType(TabTypeCode.INITIAL_HERE);
            init1.setDocumentID(new BigInteger("1"));
            init1.setPageNumber(new BigInteger(pageThree));
            init1.setRecipientID(new BigInteger("1"));
            init1.setXPosition(new BigInteger("454"));
            init1.setYPosition(new BigInteger("281"));

            tabs.getTab().add(init1);
            
            // Create a tab to ask the recipient to sign
            Tab sign1 = new Tab();
            sign1.setType(TabTypeCode.SIGN_HERE);
            sign1.setDocumentID(new BigInteger("1"));
            sign1.setPageNumber(new BigInteger(pageTwo));
            sign1.setRecipientID(new BigInteger("1"));
            sign1.setXPosition(new BigInteger("338"));
            sign1.setYPosition(new BigInteger("330"));

            tabs.getTab().add(sign1);    

            // Create a tab to reflect the full name of the recipient at each location of "(printed)"
            Tab fullAnchor = new Tab();
            fullAnchor.setType(TabTypeCode.FULL_NAME);
            fullAnchor.setAnchorTabItem(new AnchorTab());
            fullAnchor.getAnchorTabItem().setAnchorTabString("(printed)");
            fullAnchor.getAnchorTabItem().setXOffset((double) -90);
            fullAnchor.getAnchorTabItem().setYOffset((double) -70);
            fullAnchor.getAnchorTabItem().setUnit(UnitTypeCode.PIXELS);
            fullAnchor.getAnchorTabItem().setIgnoreIfNotPresent(true);
            fullAnchor.setDocumentID(new BigInteger("1"));
            fullAnchor.setPageNumber(new BigInteger(pageTwo));
            fullAnchor.setRecipientID(new BigInteger("1"));

            tabs.getTab().add(fullAnchor);

            // Create a tab to reflect the date that the recipient signs the envelope
            Tab date1 = new Tab();
            date1.setType(TabTypeCode.DATE_SIGNED);
            date1.setDocumentID(new BigInteger("1"));
            date1.setPageNumber(new BigInteger(pageTwo));
            date1.setRecipientID(new BigInteger("1"));
            date1.setXPosition(new BigInteger("343"));
            date1.setYPosition(new BigInteger("492"));

            tabs.getTab().add(date1);
            
            // Create a tab to ask the recipient to initial, but make it small
            Tab init2 = new Tab();
            init2.setType(TabTypeCode.INITIAL_HERE);
            init2.setDocumentID(new BigInteger("1"));
            init2.setPageNumber(new BigInteger(pageThree));
            init2.setRecipientID(new BigInteger("1"));
            init2.setXPosition(new BigInteger("179"));
            init2.setYPosition(new BigInteger("583"));
            init2.setScaleValue(new BigDecimal("0.6"));

            tabs.getTab().add(init2);

            if (size > 1)
            {
                // Create a tab to ask the recipient to sign
                Tab sign2 = new Tab();
                sign2.setType(TabTypeCode.SIGN_HERE);
                sign2.setDocumentID(new BigInteger("1"));
                sign2.setPageNumber(new BigInteger(pageThree));
                sign2.setRecipientID(new BigInteger("2"));
                sign2.setXPosition(new BigInteger("339"));
                sign2.setYPosition(new BigInteger("97"));

                tabs.getTab().add(sign2);

                // Create a tab to reflect the date that the recipient signs the envelope
                Tab date2 = new Tab();
                date2.setType(TabTypeCode.DATE_SIGNED);
                date2.setDocumentID(new BigInteger("1"));
                date2.setPageNumber(new BigInteger(pageThree));
                date2.setRecipientID(new BigInteger("2"));
                date2.setXPosition(new BigInteger("343"));
                date2.setYPosition(new BigInteger("197"));

                tabs.getTab().add(date2);
            }
        }

        if (request.getAttribute(Utils.NAME_FORMFIELDS) != null)
        {
            // Create a data field to capture the recipient's favorite color
            Tab favColor = new Tab();
            favColor.setType(TabTypeCode.CUSTOM);
            favColor.setCustomTabType(CustomTabType.TEXT);
            favColor.setDocumentID(new BigInteger("1"));
            favColor.setPageNumber(new BigInteger(pageThree));
            favColor.setRecipientID(new BigInteger("1"));
            favColor.setXPosition(new BigInteger("301"));
            favColor.setYPosition(new BigInteger("416"));
            
            if (request.getAttribute(Utils.NAME_COLLABFIELDS) != null) {
            	favColor.setSharedTab(true);
            	favColor.setRequireInitialOnSharedTabChange(true);
            }

            tabs.getTab().add(favColor);
        }

        if (request.getAttribute(Utils.NAME_CONDITIONALFIELDS) != null)
        {
            // Create two radio buttons in the same group to see if the recipient likes fruit
            Tab fruitNo = new Tab();
            fruitNo.setType(TabTypeCode.CUSTOM);
            fruitNo.setCustomTabType(CustomTabType.RADIO);
            fruitNo.setCustomTabRadioGroupName("fruit");
            fruitNo.setTabLabel("No");
            fruitNo.setName("No");
            fruitNo.setDocumentID(new BigInteger("1"));
            fruitNo.setPageNumber(new BigInteger(pageThree));
            fruitNo.setRecipientID(new BigInteger("1"));
            fruitNo.setXPosition(new BigInteger("269"));
            fruitNo.setYPosition(new BigInteger("508"));

            tabs.getTab().add(fruitNo);

            Tab fruitYes = new Tab();
            fruitYes.setType(TabTypeCode.CUSTOM);
            fruitYes.setCustomTabType(CustomTabType.RADIO);
            fruitYes.setCustomTabRadioGroupName("fruit");
            fruitYes.setTabLabel("Yes");
            fruitYes.setName("Yes");
            fruitYes.setValue ("Yes");
            fruitYes.setDocumentID(new BigInteger("1"));
            fruitYes.setPageNumber(new BigInteger(pageThree));
            fruitYes.setRecipientID(new BigInteger("1"));
            fruitYes.setXPosition(new BigInteger("202"));
            fruitYes.setYPosition(new BigInteger("509"));

            tabs.getTab().add(fruitYes);
        
            // Create a data field that will display depending upon the selection of the radio buttons
            Tab data1 = new Tab();
            data1.setType(TabTypeCode.CUSTOM);
            data1.setCustomTabType(CustomTabType.TEXT);
            data1.setConditionalParentLabel("fruit");
            data1.setConditionalParentValue("Yes");
            data1.setName("Fruit");
            data1.setTabLabel("Preferred Fruit");
            data1.setDocumentID(new BigInteger("1"));
            data1.setPageNumber(new BigInteger(pageThree));
            data1.setRecipientID(new BigInteger("1"));
            data1.setXPosition(new BigInteger("202"));
            data1.setXPosition(new BigInteger("265"));
            data1.setYPosition(new BigInteger("547"));

            tabs.getTab().add(data1);
        }

        if (request.getAttribute(Utils.NAME_COLLABFIELDS) != null)
        {
        	// TODO implement collaberative fields
        }

        if (request.getAttribute(Utils.NAME_SIGNERATTACHMENT) != null)
        {
            // Create a signer attachment tab that will prompt the recipient to contribute a file
            Tab attach = new Tab();
            attach.setType(TabTypeCode.SIGNER_ATTACHMENT);
            attach.setTabLabel("Signer Attachment");
            attach.setName("Signer Attachment");
            attach.setDocumentID(new BigInteger("2"));
            attach.setPageNumber(new BigInteger("1"));
            attach.setRecipientID(new BigInteger("1"));
            attach.setXPosition(new BigInteger("20"));
            attach.setYPosition(new BigInteger("20"));

            tabs.getTab().add(attach);
        }

        return tabs;
    }

	private ArrayOfRecipient constructRecipients(HttpServletRequest request) {
		ArrayOfRecipient recipients = new ArrayOfRecipient();
		int index = 1;
		if (request.getAttribute(Utils.NAME_RECIPIENTNAME + index) != null) {
			while (request.getAttribute(Utils.NAME_RECIPIENTNAME + index) != null) {
				Recipient r = new Recipient();
				
				// Create a recipient with the information from the form
				r.setUserName(request.getAttribute(Utils.NAME_RECIPIENTNAME + index).toString());
				r.setEmail(request.getAttribute(Utils.NAME_RECIPIENTEMAIL + index).toString());
				r.setRequireIDLookup(false);
				
				// Set security options if indicated
				if (request.getAttribute(Utils.NAME_RECIPIENTSECURITY + index).toString().
					equals(Utils.NAME_ACCESSCODE)) {
					r.setAccessCode(request.getAttribute(Utils.NAME_RECIPIENTSECURITYSETTING + index).toString());
				}
				else if (request.getAttribute(Utils.NAME_RECIPIENTSECURITY + index).toString().
					equals(Utils.NAME_IDCHECK)) {
					r.setRequireIDLookup(true);
				}
				else if (request.getAttribute(Utils.NAME_RECIPIENTSECURITY + index).toString().
						equals(Utils.NAME_PHONEAUTHENTICATION)) {
					r.setRequireIDLookup(true);
					r.setIDCheckConfigurationName("Phone Auth $");
					RecipientPhoneAuthentication pa = new RecipientPhoneAuthentication();
					pa.setRecipMayProvideNumber(true);
					r.setPhoneAuthentication(pa);
				}
				
				r.setID(new BigInteger(Integer.toString(index)));
				r.setType(RecipientTypeCode.SIGNER);
				
				// Create the recipient as captive if we asked it to
				if (request.getAttribute(Utils.NAME_EMAILTOGGLE + index) == null) {
				    RecipientCaptiveInfo captive = new RecipientCaptiveInfo();
				    captive.setClientUserId(Integer.toString(index)); 
				    r.setCaptiveInfo(captive);  
				}
				
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
