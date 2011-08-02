DocuSign Web Services API 3.5 PHP Code Snippet Samples
===========================================================================

System requirements for PHP samples:
-	PHP Version 5.3+
- 	mcrypt/2.5.7 libcurl/7.19.4 OpenSSL/0.9.8k zlib/1.2.3 

OverView:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

This sample includes the executable source code for the PHP code snippets
that are in our API documentation. 

Installation and Use
---------------------------------------------------------------------------

Deployment is just copying the entire CodeSnippts directory into your
htdocs (or equivalent) directory on your server.

Add your credentials to include/creds.php. Modify the following four lines. 
Valid values for these lines can be found from your DevCenter account member
console at Preferences->API:

// TODO: Use Integrator's Key from Docusign DevCenter Account Preferences API
$IntegratorsKey = "integrators key";
// TODO: Use your Docusign DevCenter Account email
$UserID = "your docusign devcenter email";
// TODO: Use your Docusign DevCenter Account password
$Password = "your docusign devcenter password";
// TODO: Use API Account ID from Docusign DevCenter Account Preferences API
$AccountID = "your docusign devcenter account id";

For trying out methods from the APIService class, uncomment lines in
index_of_api_sample_functions.php. Some methods require some setup in the 
ApiServiceSnippets.php file at the following lines:

// TODO: put in a test recipient email
$_apiRecipient1Email = "test email account";
// TODO: put in users name
$_apiUserName = "test user name";


For trying out methods from the AccountManagement class, uncomment lines in 
index_of_ams_sample_functions.php. Some methods require the following to be set
in AccountManagementSnippets.php:

// TODO: email used to create and check accounts
$_amEmail = "test email account";

The following should be set ininclude/utils.php for your time zone:

// TODO: put in your timezone or make it null
$TimeZone = 'America/Los_Angeles';

Happy Coding!
