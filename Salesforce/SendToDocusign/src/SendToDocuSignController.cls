public with sharing class SendToDocuSignController {
	private final Contract contract;
	
	public String envelopeId {get;set;}
	private String accountId = 'TODO - get from https://demo.docusign.net';
	private String userId = 'TODO - get from https://demo.docusign.net';
	private String password = 'TODO - get from https://demo.docusign.net';
	private String integratorsKey = 'TODO - get from https://demo.docusign.net';
	private String webServiceUrl = 'https://demo.docusign.net/api/3.0/dsapi.asmx';
	
	public SendToDocuSignController(ApexPages.StandardController controller)
	{
		this.contract = [select Id,CustomerSignedId,AccountId,ContractNumber from Contract where id = :controller.getRecord().Id];
		envelopeId = 'Not sent yet';
		
		SendNow();
	}

	public void SendNow()
	{
        DocuSignAPI.APIServiceSoap dsApiSend = new DocuSignAPI.APIServiceSoap();
        dsApiSend.endpoint_x = webServiceUrl;

        //Set Authentication
        String auth = '<DocuSignCredentials><Username>'+ userId 
            +'</Username><Password>' + password 
            + '</Password><IntegratorKey>' + integratorsKey 
            + '</IntegratorKey></DocuSignCredentials>';
        System.debug('Setting authentication to: ' + auth);
            
        dsApiSend.inputHttpHeaders_x = new Map<String, String>();
        dsApiSend.inputHttpHeaders_x.put('X-DocuSign-Authentication', 
            auth);
 
        DocuSignAPI.Envelope envelope = new DocuSignAPI.Envelope();
        envelope.Subject    = 'Please Sign this Contract: ' + contract.ContractNumber;
        envelope.EmailBlurb = 'This is my new eSignature service, it allows me to get your signoff without having to fax, scan, retype, refile and wait forever';
        envelope.AccountId  = accountId; 
        

        // Render the contract
        System.debug('Rendering the contract');
        PageReference pageRef = new PageReference('/apex/RenderContract');
		pageRef.getParameters().put('id',contract.Id);
     	Blob pdfBlob = pageRef.getContent();     

        // Document
        DocuSignAPI.Document document = new DocuSignAPI.Document();
        document.ID = 1;
        document.pdfBytes = EncodingUtil.base64Encode(pdfBlob);
        document.Name = 'Contract';
        document.FileExtension = 'pdf';
        envelope.Documents = new DocuSignAPI.ArrayOfDocument();
        envelope.Documents.Document = new DocuSignAPI.Document[1];
        envelope.Documents.Document[0] = document;
        
        // Recipient
        System.debug('getting the contact');
        Contact contact = [SELECT email, FirstName, LastName from Contact where id = :contract.CustomerSignedId];
        System.debug('got ' + contact.email + ' ' + contact.FirstName + ' ' + contact.LastName);
        
        System.debug('Building up the recipient');
        DocuSignAPI.Recipient recipient = new DocuSignAPI.Recipient();
        recipient.ID = 1;
        recipient.Type_x = 'Signer';
        recipient.RoutingOrder = 1;
        recipient.Email = contact.Email;
        recipient.UserName = contact.FirstName + ' ' + contact.LastName;
            
        // This setting seems required or you see the error:
        // "The string '' is not a valid Boolean value. at System.Xml.XmlConvert.ToBoolean(String s)" 
        recipient.RequireIDLookup = false;      
        
        envelope.Recipients = new DocuSignAPI.ArrayOfRecipient();
        envelope.Recipients.Recipient = new DocuSignAPI.Recipient[1];
        envelope.Recipients.Recipient[0] = recipient;
        
        // Tab
        DocuSignAPI.Tab tab1 = new DocuSignAPI.Tab();
        tab1.Type_x = 'SignHere';
        tab1.RecipientID = 1;
        tab1.DocumentID = 1;
        tab1.AnchorTabItem = new DocuSignAPI.AnchorTab();
        tab1.AnchorTabItem.AnchorTabString = 'By:';
        tab1.AnchorTabItem.XOffset = 100;

        
        DocuSignAPI.Tab tab2 = new DocuSignAPI.Tab();
        tab2.Type_x = 'DateSigned';
        tab2.RecipientID = 1;
        tab2.DocumentID = 1;
        tab2.AnchorTabItem = new DocuSignAPI.AnchorTab();
        tab2.AnchorTabItem.AnchorTabString = 'Date Signed:';
        
        envelope.Tabs = new DocuSignAPI.ArrayOfTab();
        envelope.Tabs.Tab = new DocuSignAPI.Tab[2];
        envelope.Tabs.Tab[0] = tab1;        
        envelope.Tabs.Tab[1] = tab2;        
        
        System.debug('Calling the API');
        try {
        	DocuSignAPI.EnvelopeStatus es = dsApiSend.CreateAndSendEnvelope(envelope);
        	envelopeId = es.EnvelopeID;
        	System.debug('Returned successfully, envelope id = ' + envelopeId );
      	} catch ( CalloutException e) {
			System.debug('Exception - ' + e );
      		envelopeId = 'Exception - ' + e;
		}
      
	}
}