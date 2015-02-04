DocuSign Web Services API 2011 SDK ReadMe 
===========================================================================

The DocuSign Web Services API provides methods for integrating the DocuSign 
API into an application to gather electronic signatures and data. 

To get started, go to our DevCenter (http://www.docusign.com/devcenter/)
and set up a Free Demo Account and credentials for your project.

Get help on the Community Forums at http://community.docusign.com

Wiki on GitHub at https://github.com/docusign/DocuSign-eSignature-SDK/wiki



Important Terms
----------

`Integrator Key`: Identifies a single integration. Every API 
request includes the Integrator Key and a 
username/password combination

`Envelope`: Just like a normal Postal Envelope.It contains 
things like Documents, Recipients, and Tabs

`Document`: The PDF, Doc, Image, or other file you want 
signed. If it is not a PDF, you must include the File 
Extension in the API call

`Tab`: Tied to a position on a Document and defines what 
happens there. For example, you have a SignHere Tab 
wherever you want a Recipient to sign

`Recipient`: The person you want to send the Envelope 
to. Requires a UserName and Email

`Captive (or Embedded) Recipient`: Recipient signs in an iframe on your 
website instead of receving an email 

`PowerForm`: A pre-created Envelope that you can launch
instead of writing server-side code


System Requirements
----------

System requirements for Microsoft .NET samples:
- Visual Studio 2008+ (with applicable system requirements)
- NUnit 2.5.7+ (from http://www.nunit.org/?p=download)
 
System requirements for Java samples:
- Java Development Kit 1.6
- Apache Ant 1.6

System requirements for PHP samples:
- PHP Version 5.3+
-  libcurl/7.19.4 OpenSSL/0.9.8k zlib/1.2.3 

System requirements for Ruby samples:
- Ruby 1.8.6+


Documentation
----------

Full Documentation can be downloaded or viewed online by going here:
http://www.docusign.com/developer-center/documentation

In order to help developers get started with the Web Services API, we have
developed sample code and documentation for .NET, PHP, Ruby and Java
environments.  

This SDK includes samples in Java, .NET, Ruby, PHP, and Apex (Salesforce).  
You will need to apply here http://www.docusign.com/devcenter in order to get 
a development account which is required to run the samples.
 
WSDL files can be downloaded directly from our webservice end point.

https://demo.docusign.net/api/3.0/api.asmx?WSDL - 
main web service description 

https://demo.docusign.net/api/3.0/credential.asmx?WSDL -
credential web service that has a subset of functions and doesn't require
WS-Security SOAP headers


Rate Limits
----------

Please note: Applications are not allowed to poll for envelope status more
than once every 15 minutes and we discourage integrators from continuously
retrieving status on envelopes that are in a terminal state (Completed, 
Declined, and Voided).  Excessive polling will result in your API access 
being revoked.  
If you need immediate notification of envelope events we encourage you to 
review envelope events or use our Connect Publisher technology, DocuSign 
Connect as an alternative.


More Information
----------

Professional Services is also available to help define and implement your
project fast.  We also encourage you to use the `DocuSignApi` tag on Stack
Overflow to search for answered questions or ask new ones:  
[DocuSignAPI](http://stackoverflow.com/questions/tagged/docusignapi)


API Possibilities
----------

The DocuSign API helps developers build solutions that can: 

     - Send documents for signature one at a time or in bulk
     - Require authentication before signing
     - Define signature locations using static or relative signature location descriptions on any document page
     - Define optional/required data field locations, masks, and pre-population
     - Assign individuals specific form fields
     - Include embedded signing where the developer hosts an iframe that renders the DocuSign experience
     - Support remote signing where the Recipient receives an email with a link and completes signing from a branded page
     - Route signing to multiple recipients in parallel, serial, or a combination of the two
     - Submit partially specified envelopes for later completion by the customer.
     - Void an envelope that has been submitted but not yet completed.
     - Retrieve the status of an envelope including sent, signed, voided, forwarded, etc.
     - Retrieve the digitally signed completed PDF of every document in an envelope.
     - Retrieve the completed PDF for each separate document in an envelope.
     - Retrieve the e-sign status of a recipient.
     - Retrieve the certificate and audit log of the transaction
     - Transfer an envelope to another DocuSign user or account.
     - Correct recipient information for an existing envelope.
     - Resend a notification email to an existing recipient.
     - Correct the workflow for envelopes have already been submitted
     - Retrieve the Member level permissions for the optional features.
     - Turn on or off the envelope ID stamp
     - Retrieve data entered by signers filling in the data fields. 
     - Submit documents for signature without any signing tabs at all, which invokes a freeform signing process that the recipient will follow to place signatures
     - Specify electronic vaulting for Envelopes.
     - Extract and remove original copies of documents from the DocuSign systems.


DocuSign DevCon 2015
----------

![Alt text](DevCon.jpg "DocuSign DevCon 2015")

Looking to fast-track your integration?  Join us at DocuSign DevCon 2015 to learn from the leading business platforms, attend hands-on coding sessions, and network directly with DocuSign engineers and architects.  We'll be taking over Union Square in San Francisco for this 2-day developer conference starting with an opening keynote from the legendary Geoffrey Moore.  [LEARN MORE](http://momentum.docusign.com/san-francisco/dev-con/?mc=devcon-github-soapsdk)
