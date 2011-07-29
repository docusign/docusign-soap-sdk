DocuSign Web Services API 3.5 Java Sample
===========================================================================

System Requirements:
-   Java Development Kit 1.5 or greater
-   Apache Ant 1.7.1 or greater
-   Tomcat 6 (or equivalent)
-   apache CFX 2.3.2 or greater (install from http://cxf.apache.org/download.html)

Overview:
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
To build:
1. change line in build.properties file to point to apache cxf lib directory.
   On Linux, Unix or Mac OS X it would look like this:
            cxf.dir=/opt/apache-cxf/lib
   On Windows it could be:
            cxf.dir=c:\dev\apache-cxf\lib

2. Optionally set elements in src/config.properties file by modifying the
   following lines eliminating the square brackets:
   
   docusign.integrators.key=[Add Integrator Key here]
   docusign.user.id=[Add userid GUID here]
   email=[add your docusign demo email here]
   docusign.password=[Add your docusign member console password here]
   
   You can later change or fill in these values from the login page.

3. Execute 'ant' while in same directory as build.xml file

4. Use tomcat manager to deploy WAR file that is placed in dist directory.  For
   more information about the Tomcat manager see this page:
   http://tomcat.apache.org/tomcat-7.0-doc/manager-howto.html

   For versions other than 7.0 please navigate tomcat.apache.org for a similar
   page.

5. Run the app by going to the following URL (for example):
   http://localhost:8080/DocuSignSample

The net.docusign.api_3_0 package was generated from the WSDL located at 
https://demo.docusign.net/api/3.0/api.asmx?WSDL using the wsdl2java tool that's 
shipped with apache CFX. It was unchanged for this project.

The net.docusign.credential package was generated from the WSDL located at
https://demo.docusign.net/api/3.0/credential.asmx?WSDL, also unchanged from
the output of the tool.

The net.docusign.sample package contains all of the controller servlets for the
jsp pages. 