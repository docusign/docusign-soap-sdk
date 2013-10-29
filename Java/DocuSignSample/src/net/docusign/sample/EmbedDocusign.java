/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package net.docusign.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import net.docusign.api_3_0.APIServiceSoap;
import net.docusign.api_3_0.AnchorTab;
import net.docusign.api_3_0.ArrayOfDocument;
import net.docusign.api_3_0.ArrayOfRecipient;
import net.docusign.api_3_0.ArrayOfTab;
import net.docusign.api_3_0.CustomTabType;
import net.docusign.api_3_0.Document;
import net.docusign.api_3_0.Envelope;
import net.docusign.api_3_0.EnvelopeStatus;
import net.docusign.api_3_0.Recipient;
import net.docusign.api_3_0.RecipientCaptiveInfo;
import net.docusign.api_3_0.RecipientStatus;
import net.docusign.api_3_0.RecipientTypeCode;
import net.docusign.api_3_0.RequestRecipientTokenAuthenticationAssertion;
import net.docusign.api_3_0.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod;
import net.docusign.api_3_0.RequestRecipientTokenClientURLs;
import net.docusign.api_3_0.Tab;
import net.docusign.api_3_0.TabTypeCode;
import net.docusign.api_3_0.UnitTypeCode;

/**
 * Servlet implementation class EmbedDocusign
 */
public class EmbedDocusign extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Boolean _oneSigner;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmbedDocusign() {
        super();
        // TODO Auto-generated constructor stub
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
        	// It's time to start signing
            if(request.getQueryString() != null && request.getQueryString().contains(Utils.PARAM_ENVELOPEID)) {
            
            	// Get the envelope ID from the QueryString
                String eid = request.getQueryString().split("\\=")[1];
                EnvelopeStatus status = getStatus(request, eid);
                try {
                	// Got the envelope ID, now get the token
                    getToken(request, status, 1);
                } catch (DatatypeConfigurationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else {
                request.getSession().setAttribute(Utils.SESSION_EMBEDTOKEN, "");
            }
            response.sendRedirect(Utils.PAGE_EMBEDDOCUSIGN);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Are we embedding one or two signers?
        _oneSigner = (request.getParameter(Utils.NAME_ONESIGNER) != null);
        try {
        	// Create the embedded sample envelope
            createAndSend(request);
            
            // Redirect back here to start the signing process
            response.sendRedirect(Utils.PAGE_EMBEDDOCUSIGN);
        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createAndSend(HttpServletRequest request) throws IOException, DatatypeConfigurationException {
        HttpSession session = request.getSession();
        
        // Set the envelope information
        Envelope env = new Envelope();
        env.setSubject("DocuSign API SDK Sample");
        env.setEmailBlurb("This envelope demonstrates embedded signing");
        env.setAccountId(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString());
        env.setRecipients(constructRecipients(request));
        
        // Add the sample document to the envelope
        Document doc = new Document();
        String filePath = getServletContext().getRealPath(Utils.RESOURCE_STOCKDOC);
        File f = new File(filePath);
        FileInputStream fs = new FileInputStream(f);
        byte[] pdfBytes = new byte[(int) f.length()];
        fs.read(pdfBytes);
        fs.close();
        doc.setPDFBytes(pdfBytes);
        doc.setName("Demo Document");
        doc.setID(new BigInteger("1"));
        doc.setFileExtension("pdf");
        ArrayOfDocument docs = new ArrayOfDocument();
        docs.getDocument().add(doc);
        env.setDocuments(docs);
        
        // Add the tabs to the envelope
        env.setTabs(addTabs(request, env.getRecipients().getRecipient().size()));
        
        // Get the API service and call CreateAndSendEnvelope
        APIServiceSoap api = Utils.getAPI(request);
        EnvelopeStatus status = api.createAndSendEnvelope(env);
        
        // Add the envelope ID to the session
        Utils.addEnvelopeID(request, status.getEnvelopeID());
        
        // Get the token for the first signer
        getToken(request, status, 0);
    }

    private EnvelopeStatus getStatus(HttpServletRequest request, String envelopeID) {
    
    	// Get the API service and call request status to retrieve envelope information
        APIServiceSoap api = Utils.getAPI(request);        
        return api.requestStatus(envelopeID);
    }

    private ArrayOfTab addTabs(HttpServletRequest request, int count) {
        ArrayOfTab tabs = new ArrayOfTab();
        
        // Create a tab to reflect the company of the recipient
        Tab company = new Tab();
        company.setType(TabTypeCode.COMPANY);
        company.setDocumentID(new BigInteger("1"));
        company.setPageNumber(new BigInteger("2"));
        company.setRecipientID(new BigInteger("1"));
        company.setXPosition(new BigInteger("342"));
        company.setYPosition(new BigInteger("303"));

        tabs.getTab().add(company);

		// Create a tab to ask the recipient to initial
        Tab init1 = new Tab();
        init1.setType(TabTypeCode.INITIAL_HERE);
        init1.setDocumentID(new BigInteger("1"));
        init1.setPageNumber(new BigInteger("3"));
        init1.setRecipientID(new BigInteger("1"));
        init1.setXPosition(new BigInteger("454"));
        init1.setYPosition(new BigInteger("281"));

        tabs.getTab().add(init1);

		// Create a tab to ask the recipient to sign
        Tab sign1 = new Tab();
        sign1.setType(TabTypeCode.SIGN_HERE);
        sign1.setDocumentID(new BigInteger("1"));
        sign1.setPageNumber(new BigInteger("2"));
        sign1.setRecipientID(new BigInteger("1"));
        sign1.setXPosition(new BigInteger("338"));
        sign1.setYPosition(new BigInteger("330"));

        tabs.getTab().add(sign1);    

		// Create a tab to reflect the full name of the recipient at each location of "(printed)"
        Tab fullAnchor = new Tab();
        fullAnchor.setType(TabTypeCode.FULL_NAME);
        fullAnchor.setAnchorTabItem(new AnchorTab());
        fullAnchor.getAnchorTabItem().setAnchorTabString("(printed)");
        fullAnchor.getAnchorTabItem().setXOffset((double) -123);
        fullAnchor.getAnchorTabItem().setYOffset((double) 31);
        fullAnchor.getAnchorTabItem().setUnit(UnitTypeCode.PIXELS);
        fullAnchor.getAnchorTabItem().setIgnoreIfNotPresent(true);
        fullAnchor.setDocumentID(new BigInteger("1"));
        fullAnchor.setPageNumber(new BigInteger("2"));
        fullAnchor.setRecipientID(new BigInteger("1"));

        tabs.getTab().add(fullAnchor);

		// Create a tab to reflect the date that the recipient signs the envelope
        Tab date1 = new Tab();
        date1.setType(TabTypeCode.DATE_SIGNED);
        date1.setDocumentID(new BigInteger("1"));
        date1.setPageNumber(new BigInteger("2"));
        date1.setRecipientID(new BigInteger("1"));
        date1.setXPosition(new BigInteger("343"));
        date1.setYPosition(new BigInteger("492"));

        tabs.getTab().add(date1);

		// Create a tab to ask the recipient to initial, but make it small
        Tab init2 = new Tab();
        init2.setType(TabTypeCode.INITIAL_HERE);
        init2.setDocumentID(new BigInteger("1"));
        init2.setPageNumber(new BigInteger("3"));
        init2.setRecipientID(new BigInteger("1"));
        init2.setXPosition(new BigInteger("179"));
        init2.setYPosition(new BigInteger("583"));
        init2.setScaleValue(new BigDecimal("0.6"));

        tabs.getTab().add(init2);

        if (count > 1)
        {
        	// Create a tab to ask the recipient to sign
            Tab sign2 = new Tab();
            sign2.setType(TabTypeCode.SIGN_HERE);
            sign2.setDocumentID(new BigInteger("1"));
            sign2.setPageNumber(new BigInteger("3"));
            sign2.setRecipientID(new BigInteger("2"));
            sign2.setXPosition(new BigInteger("339"));
            sign2.setYPosition(new BigInteger("97"));

            tabs.getTab().add(sign2);

			// Create a tab to reflect the date that the recipient signs the envelope
            Tab date2 = new Tab();
            date2.setType(TabTypeCode.DATE_SIGNED);
            date2.setDocumentID(new BigInteger("1"));
            date2.setPageNumber(new BigInteger("3"));
            date2.setRecipientID(new BigInteger("2"));
            date2.setXPosition(new BigInteger("343"));
            date2.setYPosition(new BigInteger("97"));

            tabs.getTab().add(date2);
        }

		// Create a data field to capture the recipient's favorite color
        Tab favColor = new Tab();
        favColor.setType(TabTypeCode.CUSTOM);
        favColor.setCustomTabType(CustomTabType.TEXT);
        favColor.setDocumentID(new BigInteger("1"));
        favColor.setPageNumber(new BigInteger("3"));
        favColor.setRecipientID(new BigInteger("1"));
        favColor.setXPosition(new BigInteger("301"));
        favColor.setYPosition(new BigInteger("416"));

        tabs.getTab().add(favColor);

		// Create two radio buttons in the same group to see if the recipient likes fruit
        Tab fruitNo = new Tab();
        fruitNo.setType(TabTypeCode.CUSTOM);
        fruitNo.setCustomTabType(CustomTabType.RADIO);
        fruitNo.setCustomTabRadioGroupName("fruit");
        fruitNo.setTabLabel("No");
        fruitNo.setName("No");
        fruitNo.setDocumentID(new BigInteger("1"));
        fruitNo.setPageNumber(new BigInteger("3"));
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
        fruitYes.setPageNumber(new BigInteger("3"));
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
        data1.setPageNumber(new BigInteger("3"));
        data1.setRecipientID(new BigInteger("1"));
        data1.setXPosition(new BigInteger("202"));
        data1.setXPosition(new BigInteger("265"));
        data1.setYPosition(new BigInteger("547"));

        tabs.getTab().add(data1);

        return tabs;
    }

    private ArrayOfRecipient constructRecipients(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayOfRecipient recipients = new ArrayOfRecipient();

		// Create the first recipient as a captive signer
        Recipient r1 = new Recipient();
        r1.setUserName("DocuSign Recipient1");
        r1.setEmail(session.getAttribute(Utils.SESSION_EMAIL).toString());
        r1.setID(new BigInteger("1"));
        r1.setType(RecipientTypeCode.SIGNER);
        r1.setCaptiveInfo(new RecipientCaptiveInfo());
        r1.getCaptiveInfo().setClientUserId("1");
        recipients.getRecipient().add(r1);
        
        // If we wanted two captive signers, create the second recipient as a captive signer
        if (_oneSigner != true) {
            Recipient r2 = new Recipient();
            r2.setUserName("DocuSign Recipient2");
            r2.setEmail("DocuSignRecipient2@mailinator.com");
            r2.setID(new BigInteger("2"));
            r2.setType(RecipientTypeCode.SIGNER);
            r2.setCaptiveInfo(new RecipientCaptiveInfo());
            r2.getCaptiveInfo().setClientUserId("2");
            recipients.getRecipient().add(r2);
        }
        
        return recipients;
    }

    private void getToken(HttpServletRequest request, EnvelopeStatus status, int index) 
            throws DatatypeConfigurationException {
        String token = null;
        
        // Set the messaging
        if(index == 0) {
            request.getSession().setAttribute(Utils.MESSAGE_SIGNING, Utils.MESSAGE_FIRSTSIGNER);
        } else {
            request.getSession().setAttribute(Utils.MESSAGE_SIGNING, Utils.MESSAGE_SECONDSIGNER);
        }
        
        // Create the assertion
        RequestRecipientTokenAuthenticationAssertion assertion = new RequestRecipientTokenAuthenticationAssertion();
        assertion.setAssertionID(UUID.randomUUID().toString());
        
        // wsdl2java translates this to XMLGregorianCalendar
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(new Date());
        assertion.setAuthenticationInstant(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal));
        
        assertion.setAuthenticationMethod(RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.PASSWORD);
        assertion.setSecurityDomain("DocuSign2010Q1Sample");
        
        // Get the recipient to sign
        RecipientStatus recipient = status.getRecipientStatuses().getRecipientStatus().get(index);
        
        // Create the URLs that DocuSign will redirect the iframe to after different events
        RequestRecipientTokenClientURLs urls = new RequestRecipientTokenClientURLs();
        String urlbase = Utils.getCallbackURL(request, Utils.PAGE_POP) + "?source=Embedded";
        
        urls.setOnAccessCodeFailed(urlbase + "&event=AccessCodeFailed&uname=" + recipient.getUserName());
        urls.setOnCancel(urlbase + "&event=Cancel&uname=" + recipient.getUserName());
        urls.setOnDecline(urlbase + "&event=Decline&uname=" + recipient.getUserName());
        urls.setOnException(urlbase + "&event=Exception&uname=" + recipient.getUserName());
        urls.setOnFaxPending(urlbase + "&event=FaxPending&uname=" + recipient.getUserName());
        urls.setOnIdCheckFailed(urlbase + "&event=IdCheckFailed&uname=" + recipient.getUserName());
        urls.setOnSessionTimeout(urlbase + "&event=SessionTimeout&uname=" + recipient.getUserName());
        urls.setOnTTLExpired(urlbase + "&event=TTLExpired&uname=" + recipient.getUserName());
        urls.setOnViewingComplete(urlbase + "&event=ViewingComplete&uname=" + recipient.getUserName());
        if (_oneSigner || index == 1) {
            urls.setOnSigningComplete(urlbase + "&event=SigningComplete&uname=" + recipient.getUserName());
        }
        else {
            urls.setOnSigningComplete(Utils.getCallbackURL(request, Utils.PAGE_POP) + "?envelopeID=" + status.getEnvelopeID());
        }
        
        // Get the API service and call RequestRecipientToken for this recipient
        APIServiceSoap api = Utils.getAPI(request);
        token = api.requestRecipientToken(status.getEnvelopeID(), 
                recipient.getClientUserId(), 
                recipient.getUserName(), 
                recipient.getEmail(), 
                assertion, 
                urls);
        
        // Set the iframe to the token
        request.getSession().setAttribute(Utils.SESSION_EMBEDTOKEN, token);
    }
}
