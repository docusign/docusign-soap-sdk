Readme.txt for Docusign SDK Java Connect sample.
===========================================================================

System Requirements:
-	Java Development Kit 1.6
-	Apache Ant 1.6
-	Tomcat 6 (or equivalent)

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

Overview:
---------------------------------------------------------------------------

The Connect sample shows a simple Java servlet to send and receive events.

Installation
---------------------------------------------------------------------------

1.  Run Apache ANT in the project root.

2.  Deploy the resulting dist/Connect.war file to your Tomcat or equivalent
server.

Connect solution instructions
---------------------------------------------------------------------------

In order to start receiving events from your Docusign account, please
follow the configuration instructions in the DocuSign Connect Service
Guide. Note that the Demo site does not require an HTTPS URL.

The URL that you can post to is the same that can be used to browse the
files that were POSTed. It is http://<your_server>/Connect/Receiver
