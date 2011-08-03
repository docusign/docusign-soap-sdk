public with sharing class NDAKioskController {

    public NDA_Signer__c signer {get; set;}
    public string errmsg {get; set;}
    public string selected_name {get; set;}
    public string tokenURL {get; set;}
    public string validateType {get; set;}
    public string reasonVisit {get; set;}
    public string signOutEmail {get; set;}
    
    private string id;    // id of current signer object
    private String auth;
    private String envelopeId;
    private string[] recipients;

    //=============================
    //===== Strings to modify =====
    private String accountId = ''; // TODO: Put DocuSign API Account ID Here
    private String userId = ''; // TODO: Put your DocuSign member email here
    private String password = ''; // TODO: Put your DocuSign member password here
    private String integratorsKey = ''; // TODO: Put your DocuSign API Integrators Key here
    private String templateId = ''; // TODO: Put your Template ID here
    private String devHost = 'https://na10.salesforce.com/'; // TODO: Salesforce host URL
    //=============================

    private String webServiceUrl = 'https://demo.docusign.net/api/3.0/dsapi.asmx';

    // to force asking for the pages
    private PageReference ndaDonePage = Page.ndaDone;
    private PageReference ndapopPage = Page.ndaPop;

    //===== Ctor =====            
    public NDAKioskController(ApexPages.StandardController controller) {
        // init signer
        id = ApexPages.currentPage().getParameters().get('id');
        if (id == null) {
            signer = new NDA_Signer__c();
        }
        else {
            signer = [SELECT id, name, 
                    Email__c, 
                    validate_method__c, 
                    selected_name__c,
                    Here_to_see__c,
                    Purpose_of_visit__c,
                    Company__c,
                    Sign_In__c,
                    Sign_Out__c
                FROM NDA_Signer__c
                WHERE id = :id];
        }

        //Set Authentication
        auth = '<DocuSignCredentials><Username>'+ userId 
            +'</Username><Password>' + password 
            + '</Password><IntegratorKey>' + integratorsKey 
            + '</IntegratorKey></DocuSignCredentials>';
        System.debug('Setting authentication to: ' + auth);

        errmsg = '';
        tokenURL = '';
    }

//===== Private methods =====
    // Send the envelope as an embedded signing    
    private string SendNDANow() {
        envelopeId = '';
        string token = '';
        DocuSignAPI.ApiServiceSOAP dsApiSend = dsapi_send();
        
        // create envelope info
        DocuSignAPI.EnvelopeInformation envelope = new DocuSignAPI.EnvelopeInformation();
        envelope.Subject = 'Please Sign this NDA: ' + signer.selected_name__c ;
        envelope.EmailBlurb = 'This is my new eSignature service,'+ 
        ' it allows me to get your signoff without having to fax, ' +
        'scan, retype, refile and wait forever';
        envelope.AccountId  = accountId; 

        // Recipient Information
        DocuSignAPI.Recipient recipient = new DocuSignAPI.Recipient();
        recipient.ID = 1;
        recipient.Type_x = 'Signer';
        recipient.RoutingOrder = 1;
        recipient.Email = signer.Email__c;
        recipient.UserName = signer.selected_name__c;
        recipient.RoleName = 'Signer';
        System.Debug('authentication method = ' + signer.validate_method__c);
        if (signer.validate_method__c == 'RSAID') {
            recipient.RequireIDLookup = true;
        }
        else if (signer.validate_method__c == 'PHONE') {
            recipient.RequireIDLookup = true;
            recipient.IDCheckConfigurationName = 'Phone Auth $';
            DocuSignAPI.RecipientPhoneAuthentication phoneAuth = new DocuSignAPI.RecipientPhoneAuthentication();
            phoneAuth.RecipMayProvideNumber = true;
            recipient.PhoneAuthentication = phoneAuth;
        }
        else {
            recipient.RequireIDLookup = false;
        }
        // make recipient captive for embedded experience
        recipient.CaptiveInfo = new DocuSignAPI.RecipientCaptiveInfo();
        recipient.CaptiveInfo.ClientUserId = '1';
        // Role assignments
        DocuSignAPI.TemplateReferenceRoleAssignment roleAssignment = new DocuSignAPI.TemplateReferenceRoleAssignment();
        roleAssignment.RoleName = recipient.RoleName;
        roleAssignment.RecipientID = recipient.ID;
        
        //
        // create a carbon copy of the recipient to ensure that they get a 
        // pdf of the signed NDA
        // giving a signer a copy of the document is important to be compliant
        // with the ESIGN legislature
        DocuSignAPI.Recipient recipient2 = new DocuSignAPI.Recipient();
        recipient2.ID = 2;
        recipient2.Type_x = 'CarbonCopy';
        recipient2.RoutingOrder = 2;
        recipient2.Email = signer.Email__c;
        recipient2.UserName = signer.selected_name__c;
        
        // Create array of recipients
        DocuSignAPI.ArrayOfRecipient1 signers = new DocuSignAPI.ArrayOfRecipient1();
        signers.Recipient = new DocuSignAPI.Recipient[2];
        signers.Recipient[0] = recipient;
        signers.Recipient[1] = recipient2;
        
        // Create object for the NDA server-side template
        DocuSignAPI.TemplateReference ndaTemplate = new DocuSignAPI.TemplateReference();
        ndaTemplate.Template = templateId;
        ndaTemplate.TemplateLocation = 'Server';
        
        // Add role assignment
        ndaTemplate.RoleAssignments = new DocuSignAPI.ArrayOfTemplateReferenceRoleAssignment();
        ndaTemplate.RoleAssignments.RoleAssignment = new DocuSignAPI.TemplateReferenceRoleAssignment[1];
        ndaTemplate.RoleAssignments.RoleAssignment[0] = roleAssignment;
        
        // Add data for fields
        DocuSignAPI.TemplateReferenceFieldDataDataValue fd1 = new DocuSignAPI.TemplateReferenceFieldDataDataValue();
        fd1.TabLabel = 'Full Name 1';
        fd1.Value = recipient.UserName;
        
        DocuSignAPI.TemplateReferenceFieldDataDataValue fd2 = new DocuSignAPI.TemplateReferenceFieldDataDataValue();
        fd2.TabLabel = 'Company 3';
        fd2.Value = signer.Company__c;
        
        ndaTemplate.FieldData = new DocuSignAPI.TemplateReferenceFieldData();
        ndaTemplate.FieldData.DataValues = new DocuSignAPI.ArrayOfTemplateReferenceFieldDataDataValue();
        ndaTemplate.FieldData.DataValues.DataValue = new DocuSignAPI.TemplateReferenceFieldDataDataValue[2];
        ndaTemplate.FieldData.DataValues.DataValue[0] = fd1;
        ndaTemplate.FieldData.DataValues.DataValue[1] = fd2;

        // create array of template references
        DocuSignAPI.ArrayOfTemplateReference templateReferences = new DocuSignAPI.ArrayOfTemplateReference();
        templateReferences.TemplateReference = new DocuSignAPI.TemplateReference[1];
        templateReferences.TemplateReference[0] = ndaTemplate;
                
        System.debug('Calling CreateEnvelopeFromTemplates');
        try {
            DocuSignAPI.EnvelopeStatus es = dsApiSend.CreateEnvelopeFromTemplates(templateReferences, signers, envelope, true);
            envelopeId = es.EnvelopeID;
        } catch ( CalloutException e) {
            System.debug('Exception - ' + e );
            errMsg = 'Exception - ' + e;
            return '';    //TODO: send to error landing place
        }
        
        DocuSignAPI.RequestRecipientTokenAuthenticationAssertion assert = new DocuSignAPI.RequestRecipientTokenAuthenticationAssertion();
        assert.AssertionID = 'Your AssertionID Here';
        assert.AuthenticationInstant = system.now();
        assert.AuthenticationMethod = 'Password';
        assert.SecurityDomain = 'YourApp.com';
        
        DocuSignAPI.RequestRecipientTokenClientURLs clientURLs = new DocuSignAPI.RequestRecipientTokenClientURLs();
        
        clientURLs.OnAccessCodeFailed = getPopURL() + '?Id=' + signer.id + '&event=OnAccessCodeFailed&envelopeid=' + envelopeID;
        clientURLs.OnCancel = getPopURL() + '?Id=' + signer.id + '&event=OnCancel&envelopeid=' + envelopeID;
        clientURLs.OnDecline = getPopURL() + '?Id=' + signer.id + '&event=OnDecline&envelopeid=' + envelopeID;
        clientURLs.OnException = getPopURL() + '?Id=' + signer.id + '&event=OnException&envelopeid=' + envelopeID;
        clientURLs.OnFaxPending = getPopURL() + '?Id=' + signer.id + '&event=OnFaxPending&envelopeid=' + envelopeID;
        clientURLs.OnIdCheckFailed = getPopURL() + '?Id=' + signer.id + '&event=OnIdCheckFailed&envelopeid=' + envelopeID;
        clientURLs.OnSessionTimeout = getPopURL() + '?Id=' + signer.id + '&event=OnSessionTimeout&envelopeid=' + envelopeID;
        clientURLs.OnSigningComplete = getPopURL() + '?Id=' + signer.id + '&event=OnSigningComplete&envelopeid=' + envelopeID;
        clientURLs.OnTTLExpired = getPopURL() + '?Id=' + signer.id + '&event=OnTTLExpired&envelopeid=' + envelopeID;
        clientURLs.OnViewingComplete = getPopURL() + '?Id=' + signer.id + '&event=OnViewingComplete&envelopeid=' + envelopeID;
        
        // assumes apiService = preconfigured api proxy
        try {
            token = dsApiSend.RequestRecipientToken(envelopeId, recipient.captiveinfo.ClientUserId, recipient.UserName, recipient.Email, assert,clientURLs);
        }  catch ( CalloutException e) {
            System.debug('Exception - ' + e );
            errMsg = 'Exception - ' + e;
            return '';    //TODO: send to error landing place
        }
        return token;
    }

    // Get the proxy for the dsapi-send wsdl
    private DocuSignAPI.APIServiceSoap dsapi_send() {
        // Get DSapi and authenticate
        DocuSignAPI.APIServiceSoap dsApiSend = new DocuSignAPI.APIServiceSoap();
        dsApiSend.endpoint_x = webServiceUrl;
        dsApiSend.inputHttpHeaders_x = new Map<String, String>();
        dsApiSend.inputHttpHeaders_x.put('X-DocuSign-Authentication', auth);
        return dsApiSend;
    }
    
    // Get the proxy for the dsapi-account wsdl
    private DocuSignAPI_Account.APIServiceSoap dsapi_account() {
        // Get DSapi and authenticate
        DocuSignAPI_Account.APIServiceSoap dsApiAccount = new DocuSignAPI_Account.APIServiceSoap();
        dsApiAccount.endpoint_x = webServiceUrl;
        dsApiAccount.inputHttpHeaders_x = new Map<String, String>();
        dsApiAccount.inputHttpHeaders_x.put('X-DocuSign-Authentication', auth);
        return dsApiAccount;
    }
    
    // Get the proxy for the dsapi-document wsdl
    private DocuSignAPI_document.APIServiceSoap dsapi_document() {
        // Get DSapi and authenticate
        DocuSignAPI_document.APIServiceSoap dsApiDoc = new DocuSignAPI_document.APIServiceSoap();
        dsApiDoc.endpoint_x = webServiceUrl;
        dsApiDoc.inputHttpHeaders_x = new Map<String, String>();
        dsApiDoc.inputHttpHeaders_x.put('X-DocuSign-Authentication', auth);
        return dsApiDoc;
    }
    
    // Return an array of recipient names if more than one
    // otherwise returns empty string
    private String[] recipientNames() {
        DocuSignAPI_Account.ApiServiceSOAP dsApiAccount = dsapi_account();
        
        DocuSignAPI_Account.RecipientList rlist = dsApiAccount.GetRecipientList(accountId, signer.Email__c);
        
        if (rlist.RecipientName != null) {
            return rlist.RecipientName.RecipientName;
        } else {
            return null;
        }
    }
    
    // returns URL of POP page that redirects callbacks to correct URL
    // Strips off end of path and replaces with ndaPOP
    private String getPopURL() {
        String popURL = Site.getCurrentSiteUrl();
        if (popURL == null) {
            popURL = devHost + 'apex/';
        } 
        return popURL + 'ndaPop';
    }
    
    // Checks for existence of 'hear to see' person
    // Returns email address if found, otherwise null
    private string findPersonToVisit() {
        string dsEmail = null;
        try {
            Contact dsPerson = 
                [SELECT Email 
                FROM Contact 
                WHERE name = :signer.Here_to_see__c];
            dsEmail = dsPerson.Email;
        } catch (Exception ex) {
            dsEmail = null;
            System.Debug('Error finding contact: ' + ex.getMessage());
        }
        return dsEmail;
    }
    
    // send an outbound email
    private void emailNow(string dsEmail) {
        Messaging.SingleEmailMessage mail = new Messaging.SingleEmailMessage();
        
        string[] toAddresses = new string[] {dsEmail};
        mail.setToAddresses(toAddresses);
        mail.setSenderDisplayName('DocuSign Reception');
        mail.setUseSignature(false);
        
        string emailSubject = signer.name + ' is here to see you ' + datetime.now().format() + '.';
        mail.setSubject(emailSubject);
        
        string emailBody = signer.here_to_see__c + '\n\n';
        emailBody += signer.name +' is here for ';
        if (signer.Purpose_of_visit__c == 'Business') {
            emailBody += 'a business meeting.';
        }
        else if (signer.Purpose_of_visit__c == 'Interview') {
            emailBody += 'a job interview.';
        }
        else {
            emailBody += ' a personal visit.';
        }
        emailBody += ' Please go to the front desk to meet them. Please remember to sign all guests out when they leave.';
        mail.setPlainTextBody(emailBody);
        
        Messaging.sendEmail(new Messaging.SingleEmailMessage[] { mail });
    }

//===== ndaWelcome Control Methods =====
    // actions to perform on page open
    public void ndaWelcomePageOpen () {
        errmsg = '';
        if (id == null) {
            validateType = 'SHOWID';
            selected_name = '';
            reasonVisit = 'Business';
        }
        else {
            validateType = signer.validate_method__c;
            reasonVisit = signer.purpose_of_visit__c;
            selected_name = signer.selected_name__c;
        }
    }
    
    // initialize radio buttons for ID check
    // TODO: make enum
    public List<SelectOption> getValidateItems() {
        List<SelectOption> options = new List<SelectOption>{};
        options.add(new SelectOption('SHOWID', 'Show ID to Receptionist'));
        options.add(new SelectOption('PHONE', 'Phone Authentication'));
        options.add(new SelectOption('RSAID', 'Use RSA ID Check'));
        
        return options;
    }
    
    // initialize radio buttons for reason for visit
    // TODO: make enum
    public List<SelectOption> getReasonItems() {
        List<SelectOption> options = new List<SelectOption>{};
        options.add(new SelectOption('Business', 'I am here for a business meeting'));
        options.add(new SelectOption('Interview', 'I am here for a job interview'));
        options.add(new SelectOption('Personal', 'I am here for personal reasons'));
        
        return options;
    }

    // populates the database with a record and goes to the next page
    public PageReference eSign() {
        PageReference nextWindow = Page.NDANameRes;
        errmsg = null;
        
        // set picklists
        signer.validate_method__c = validateType;
        signer.purpose_of_visit__c = reasonVisit;
        
        // set sign-in time and clear sign-out time
        signer.Sign_In__c = datetime.now();
        signer.Sign_Out__c = null;
        
        
        signer.selected_name__c = signer.name;
        try {
            if (id == null){
                insert signer;
            }
            else {
                update signer;
            }
        }
        catch (DMLException e) {
            errmsg = e.getDmlMessage(0);
        }
                
        if (errmsg == null) {
            // check for email of person to see and send if found
            // NOTE: This must be after all web service calls (Do not know why)
            string dsEmail = findPersonToVisit();
            if (dsEmail == null) {
                errmsg = 'Cannot locate Person to Visit. Please notify receptionist.';
                nextWindow = page.ndaNotifyReception;
            }
            else {
                emailNow(dsEmail);
            }
        }
        
        nextWindow.getParameters().put('id', signer.id);
        nextWindow.setRedirect(true);            
        System.debug('errmsg: ' + errmsg);
        return nextWindow;
    }

    // clears values on welcome page
    public void clearWelcomeFields() {
        signer.Name = '';
        signer.Email__c = '';
        signer.Here_to_see__c = '';
        signer.Company__c = '';
    }
    
    // sign out
    public PageReference signOutFromWelcome() {
        PageReference nextWindow = page.ndasignout;
        nextWindow.setRedirect(true);
        return nextWindow;
    }

//===== ndaNotifyReception Control Methods =====
    public void ndaNotifyReceptionPageOpen() {
    }
    
    public PageReference ndaNotifyContinue() {
        PageReference nextWindow = page.ndanameres;
        nextWindow.getParameters().put('id', signer.id);
        nextWindow.setRedirect(true);  
        return nextWindow;
    }          
        
    
//===== ndaNameRes Control Methods =====
    // actions to perform on page open
    public PageReference ndaNameResPageOpen() {
        PageReference nextWindow = null;
        // check if signer has more than one name
        //    Skip ndsnameres page if signer has more than one
        recipients = recipientNames();
        if (recipients == null) {
            nextWindow = Page.NDAeSign;
            nextWindow.getParameters().put('id', signer.id);
            nextWindow.setRedirect(true);            
        }
        else {
            selected_name = recipients[0];
        }
        return nextWindow;
    }
    
    // Continue button on Name resolution page
    public PageReference ndaContinue() {
        PageReference ndaEsign;
        if (selected_name == ''){
            errMsg = 'Please select a name';
            ndaEsign = null;
        }
        else {
            signer.selected_name__c = selected_name;
            update signer;
            System.debug('Selected Name: ' + selected_name);
            ndaEsign = Page.NDAeSign;
            ndaEsign.getParameters().put('id', signer.id);
            ndaEsign.setRedirect(true);
        }
        return ndaEsign;
    }
    
    // restart link on name resolution page
    public PageReference ndaRestart() {
        PageReference ndaWelcome;
        ndaWelcome = Page.NDAWelcome;
        ndaWelcome.getParameters().put('id', signer.id);
        ndaWelcome.setRedirect(true);
        return ndaWelcome;
    }
    
    // initialize from list gotten from DocuSign
    public List<SelectOption> getLegalNames() {
        List<SelectOption> options = new List<SelectOption>();
        // start with name in object
        options.add(new SelectOption(signer.Name, signer.Name));
        if (recipients != null) {
            for (String name : recipients) {
                options.add(new SelectOption(name, name));
            }
        }
        return options;
    }

//===== ndaEsign Control Methods =====
    // Perform actions on page open
    public void ndaEsignPageOpen() {
         // create token URL
        tokenURL = SendNDANow();
   }

//===== ndaPop Control Methods =====
    // Perform actions on page open
    public void ndaPopPageOpen() {
    }

//===== ndaDone Control Methods =====
    // Perform actions on page open
    public void ndaDonePageOpen() {
        envelopeId = ApexPages.currentPage().getParameters().get('envelopeid');
    }
    
//===== ndaSignOut Control Methods =====
    // Perform actions on page open
    public void ndaSignOutPageOpen() {
        string email = ApexPages.currentPage().getParameters().get('email');
        if (email != null) {
            errmsg = 'The email you entered: ' + email + ' cannot be found. Please try again or notify the receptionist.';
        }
    }
        
    // sign out
    public PageReference signOut() {
        List <NDA_signer__c> outSigners;
        PageReference nextPage = page.ndadonesignout;

        try {
            outSigners = [SELECT id, name, 
                    Sign_Out__c
                FROM NDA_Signer__c
                WHERE Email__c = :signOutEmail];
        }
        catch (Exception e) {
            nextPage = page.ndasignout;
        }
        
        if (! outSigners.isEmpty()) {
            for (NDA_signer__c outSigner : outSigners) {
                if (outSigner.sign_out__c == null) {
                    outSigner.sign_out__c = datetime.now();
                    update outSigner;
                }
            }
        }
    
        nextPage.getParameters().put('email', signOutEmail);
        nextPage.setRedirect(true);            
        return nextPage;
    }
 
    // return to ndawelcome
    public PageReference returnFromSignOut() {
        PageReference welcomePage = page.ndawelcome;
        welcomePage.setRedirect(true);
        return welcomePage;
    }
}