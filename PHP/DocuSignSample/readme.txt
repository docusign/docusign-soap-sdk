DocuSign Web Services API 3.5 PHP Sample
la5
===========================================================================

System requirements for PHP samples:
-   PHP Version 5.3.5+

OverView:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

This sample shows how to implement many of the typical ways to integrate the 
DocuSign Web Service with a product.

This source code is intended only as a supplement to DocuSign SDK and/or
on-line documentation. This sample is designed to demonstrate DocuSign
features and is not intended for production use. Code and policy for a
production application must be developed to meet the specific data and
security requirements of the application.

Installation and Use
---------------------------------------------------------------------------

Deployment is just copying the entire CodeSnippts directory into your
htdocs (or equivalent) directory on your server.

Optionally add your credentials to include/account_creds.php by modifying the
following lines:

// TODO: Use Integrator's Key from Docusign DevCenter Account Preferences API
$IntegratorsKey = "";
// TODO: Use your Docusign DevCenter Account email
$UserID = "";
// TODO: Use your Docusign DevCenter Account password
$Password = "";

You can later change or fill in these values from the login page in index.php.

Recursively copy all files to your server and execute the index.php file to 
begin.