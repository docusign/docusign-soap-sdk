Readme.txt for DocuSign SDK Salesforce samples
===========================================================================

System Requirements:
-	Salesforce Developer Edition account

Overview:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

Each sample includes the source code for the services required to execute
the applications. All of the samples run as Salesforce applications. Refer 
to the Readme.pdf in the root directory of each sample for
further details.


----- Rate Limits -----

Please note: Applications are not allowed to poll for envelope status more
than once every 15 minutes and we discourage integrators from continuously
retrieving status on envelopes that are in a terminal state (Completed, 
Declined, and Voided).  Excessive polling will result in your API access 
being revoked.  
If you need immediate notification of envelope events we encourage you to 
review envelope events or use our Connect Publisher technology, DocuSign 
Connect as an alternative.