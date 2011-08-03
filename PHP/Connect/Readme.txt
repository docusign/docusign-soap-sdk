DocuSign PHP Connect 2011 Readme
===========================================================================

The Connect sample show a simple implementation to receive DocuSign events. 

Installation
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

System requirements for PHP samples:
-	PHP Version 5.3+
- 	libcurl/7.19.4 OpenSSL/0.9.8k zlib/1.2.3 

Sample PHP Connect solution instructions 
---------------------------------------------------------------------------
 
In order to start receiving events from your DocuSign account please follow
the configuration in the HTTPS Connect Guide included in the DocuSign SDK.  
 
PHP Connect solution contains just one project.  The project consists of
one PHP file and one sample data file.  The data file can be used to test
out the post processing. 
 
Just like with a lot of PHP solutions deployment of the PHP sample is as
simple as just copying the files into a directory on your server. 
 
The url that you post the index.php can be used for both sending
https://<your_secure_server>/<somefolder>/index.php 

