Readme.txt for Docusign SDK MS.NET samples
===========================================================================

System requirements for Microsoft .NET samples:
-	Visual Studio 2008+ (with applicable system requirements)
-	NUnit 2.5.7+ (from http://www.nunit.org/?p=download)

Overview:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

Each sample includes the source code for the services required to execute
the applications. All of the appications include a Microsoft Visual Studio
solution file that can perform building, deployment, and debugging

Unit Test Setup
---------------------------------------------------------------------------

To run these tests, you need to:
	1. Install the NUnit framework and configure for use with Visual Studio
	2. Replace the placeholder credentials in the app.config appsettings section with your demo credentials
	3. Replace any placeholder strings (usually for email IDs or template IDs, especially within CreateOneSigner) within CodeSnippets.cs