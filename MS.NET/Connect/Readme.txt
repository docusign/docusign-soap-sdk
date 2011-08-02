Readme.txt for Docusign SDK MS.NET Connect sample.
===========================================================================

System Requirements:
-	Visual Studio 2008+ (with applicable system requirements)

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

Overview:
---------------------------------------------------------------------------

The Connect sample shows a simple ASP.NET servlet to send and receive events.

Installation
---------------------------------------------------------------------------

1.  Open hcc_poc.sln in project root and build.

2.  Publish to your IIS server.

Connect solution instructions
---------------------------------------------------------------------------

In order to start receiving events from your Docusign account, please
follow the configuration instructions in the DocuSign Connect Service
Guide. Note that the Demo site does not require an HTTPS URL.

The URL that you can post to is the same that can be used to browse the
files that were POSTed. It is http://<your_server>/HCC/Receiver.aspx
