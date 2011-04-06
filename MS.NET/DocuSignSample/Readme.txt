Readme.txt for Docusign SDK MS.NET C# DocuSignSample sample.
===========================================================================

System Requirements:
-	Visual Studio 2008+ (with applicable system requirements)

Overview:
---------------------------------------------------------------------------

This sample code requires a DocuSign DevCenter account. If you do not
already have a DevCenter account please go to
http://www.docusign.com/devcenter/ and sign up for one. This sample will
not function without a valid DevCenter account.

This project an example of using the DocuSign webservice / DocuSign Credential webservice
in a web application. This example demonstrates the following:
•	account login
•	different types of tab creation
•	template retrieving
•	envelope creation / sending from scratch and templates
•	signing / sending envelopes via DocuSign web interface
•	confirmation of sign status
•	retrieval of signed PDF

The solution file for loading, building, and executing this project is at 
./DocuSignSample.sln.

Building and Running the Sample:
---------------------------------------------------------------------------
1 Open the solution in Visual Studio
2 Open the Web.config file
	2.1 Under the appSettings tag, add your credentials to the API AccountId, APIUserEmail,
		Password and IntegratorKey. You will need a DocuSign DevCenter account for this.
3 Compile the project
4 Run with or without debugging
	