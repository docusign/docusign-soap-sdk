DocuSign Web Services API 3.5 MS.NET Sample

===========================================================================

System Requirements:
   - Visual Studio 2010+ (with applicable system requirements)

Overview:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

This sample shows how to implement many of the typical ways to integrate
the DocuSign Web Service with a product.

This source code is intended only as a supplement to DocuSign SDK and/or
on-line documentation. This sample is designed to demonstrate DocuSign
features and is not intended for production use. Code and policy for a
production application must be developed to meet the specific data and
security requirements of the application.

Installation and Use
---------------------------------------------------------------------------
1. Ensure that you have the required system requirements

2. Open up the solution file in Visual Studio

3. Optionally enter your account credentials in the web.config file by
   modifying the following lines:
   
    <add key="APIAccountId" value="" />
    <add key="APIUserEmail" value="" />
    <add key="Password" value="" />
    <add key="IntegratorsKey" value="" />
	
4. Build the solution by clicking Build->Build Solution

5. Run the solution by clicking Debug->Start Without Debugging



----- Rate Limits -----

Please note: Applications are not allowed to poll for envelope status more
than once every 15 minutes and we discourage integrators from continuously
retrieving status on envelopes that are in a terminal state (Completed, 
Declined, and Voided).  Excessive polling will result in your API access 
being revoked.  
If you need immediate notification of envelope events we encourage you to 
review envelope events or use our Connect Publisher technology, DocuSign 
Connect as an alternative.
