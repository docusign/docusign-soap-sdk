/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
 
using System;
using System.IO;
using System.Collections.Generic;
using System.Globalization;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using NUnit.Framework;
using System.Diagnostics.CodeAnalysis;
using System.Configuration;
using System.Collections.Specialized;
using System.Xml;

namespace CodeSnippets
{
    [TestFixture]
    public class HeartbeatTests
    {
        DocuSignWeb.APIServiceSoapClient _apiClient;
        String _userName;
        String _apiUrl;
        String _accountId;
        String _password;
        String _email;
        List<String> envelopeIDs;

        [TestFixtureSetUp]
        public void FixtureSetup()
        {

            // Keep a list of envelope IDs so we can cleanup later
            envelopeIDs = new List<string>();

            // Grab all of the account information
            _apiUrl = ConfigurationManager.AppSettings["APIUrl"];
            _accountId = ConfigurationManager.AppSettings["APIAccountId"];
            _email = ConfigurationManager.AppSettings["APIUserEmail"];
            
            if (ConfigurationManager.AppSettings["IntegratorsKey"] != null && ConfigurationManager.AppSettings["IntegratorsKey"].Length > 0)
            {
                _userName = "[" + ConfigurationManager.AppSettings["IntegratorsKey"] + "]";
            }
            else
            {
                Assert.Fail("You must use an integrator's key!");
            }
            _userName += _email;
            _password = ConfigurationManager.AppSettings["Password"];
            _apiClient = new DocuSignWeb.APIServiceSoapClient("APIServiceSoap", _apiUrl);
            _apiClient.ClientCredentials.UserName.UserName = _userName;
            _apiClient.ClientCredentials.UserName.Password = _password;

            // Sanity checking
            Console.WriteLine("Created SOAP client with ApiUrl {0}, UserName {1} and Password {2}", _apiClient.Endpoint.Address,
                               _apiClient.ClientCredentials.UserName.UserName, _apiClient.ClientCredentials.UserName.Password);
        }

        [Test]
        public void CorrectAndResendEnvelopeTest()
        {

            // Create a basic envelope to correct and resend later
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "correct and resend envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            Console.WriteLine("Envelope Id is {0} and status is {1}", status.EnvelopeID, status.Status);
            envelopeIDs.Add(status.EnvelopeID);

            // Create a new correction, and make it a recipient correction
            DocuSignWeb.Correction correction = new DocuSignWeb.Correction();
            correction.EnvelopeID = status.EnvelopeID;
            correction.RecipientCorrections = new DocuSignWeb.RecipientCorrection[1];

            // We're just copying recipient data into the corrected data
            // We could also change it entirely
            for (int i = 0; i < correction.RecipientCorrections.Length; i++)
            {
                DocuSignWeb.RecipientCorrection recipient = correction.RecipientCorrections[i];
                correction.RecipientCorrections[i] = new DocuSignWeb.RecipientCorrection();
                correction.RecipientCorrections[i].PreviousEmail = correction.RecipientCorrections[i].CorrectedEmail
                                                                    = envelope.Recipients[i].Email;
                correction.RecipientCorrections[i].PreviousUserName = correction.RecipientCorrections[i].CorrectedUserName
                                                                    = envelope.Recipients[i].UserName;
                correction.RecipientCorrections[i].PreviousRoutingOrder = correction.RecipientCorrections[i].CorrectedRoutingOrder
                                                                        = envelope.Recipients[i].RoutingOrder;
            }

            // Go ahead and make the correction
            DocuSignWeb.CorrectionStatus correctionStatus = _apiClient.CorrectAndResendEnvelope(correction);
            
            // Confirm that the call succeeded
            Assert.IsNotNull(correctionStatus);
            Console.WriteLine("Correction status succeeded? {0}", correctionStatus.RecipientCorrectionStatuses[0].CorrectionSucceeded);
        }

        [Test]
        public void CreateAndSendEnvelopeTest()
        {
            // Create and send the envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "create and send envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Did we get a sent status back?
            Assert.IsNotNull(status.SentSpecified);
            Console.WriteLine("Envelope status is {0}", status.Status);
        }

        [Test]
        public void CreateEnvelopeFromTemplatesAndFormsTest()
        {
            // Configure the envelope information
            DocuSignWeb.EnvelopeInformation envelopeInfo = new DocuSignWeb.EnvelopeInformation();
            envelopeInfo.AccountId = _accountId;
            envelopeInfo.EmailBlurb = "testing docusign creation services";
            envelopeInfo.Subject = "create envelope from templates and forms test";

            DocuSignWeb.CompositeTemplate template = new DocuSignWeb.CompositeTemplate();

            DocuSignWeb.Recipient recipient1 = new DocuSignWeb.Recipient();
            recipient1.UserName = "SignerOne";
            // TODO: replace the email string with an actual email
            recipient1.Email = "test email one";
            recipient1.Type = DocuSignWeb.RecipientTypeCode.Signer;
            recipient1.RequireIDLookup = false;
            recipient1.RequireIDLookupSpecified = true;
            recipient1.RoutingOrder = 1;
            recipient1.RoutingOrderSpecified = true;
            recipient1.RoleName = "One";
            recipient1.ID = "1";

            DocuSignWeb.Recipient recipient2 = new DocuSignWeb.Recipient();
            recipient2.UserName = "Signer2";
            // TODO: replace the email string with an actual email
            recipient2.Email = "test email two";
            recipient2.Type = DocuSignWeb.RecipientTypeCode.Signer;
            recipient2.RequireIDLookup = false;
            recipient2.RequireIDLookupSpecified = true;
            recipient2.RoutingOrder = 2;
            recipient2.RoutingOrderSpecified = true;
            recipient2.RoleName = "Two";
            recipient2.ID = "2";

            DocuSignWeb.Recipient[] signers = { recipient1, recipient2 };

            // Configure the inline templates
            DocuSignWeb.InlineTemplate inlineTemplate = new DocuSignWeb.InlineTemplate();
            inlineTemplate.Sequence = "1";
            inlineTemplate.Envelope = new DocuSignWeb.Envelope();
            inlineTemplate.Envelope.Recipients = signers;
            inlineTemplate.Envelope.AccountId = _accountId;

            // This tab matches the DateSigned tab assigned to recipient one
            DocuSignWeb.Tab tab1 = new DocuSignWeb.Tab();
            tab1.RecipientID = "1";
            tab1.TabLabel = "DocuSignDateSignedOne";
            tab1.Type = DocuSignWeb.TabTypeCode.DateSigned;

            // This tab matches the SignHere tabs assigned to recipient two
            DocuSignWeb.Tab tab2 = new DocuSignWeb.Tab();
            tab2.RecipientID = "2";
            tab2.TabLabel = "SignTwo\\*";
            tab2.Type = DocuSignWeb.TabTypeCode.SignHere;

            // This tab matches the SignHere tabs assigned to recipient one
            DocuSignWeb.Tab tab3 = new DocuSignWeb.Tab();
            tab3.RecipientID = "1";
            tab3.TabLabel = "SignOne\\*";
            tab3.Type = DocuSignWeb.TabTypeCode.SignHere;

            // This tab matches the DateSigned tab assigned to recipient two
            DocuSignWeb.Tab tab4 = new DocuSignWeb.Tab();
            tab4.RecipientID = "2";
            tab4.TabLabel = "DocuSignDateSignedTwo";
            tab4.Type = DocuSignWeb.TabTypeCode.DateSigned;

            // This tab matches nothing -- but that's okay!
            // It will just get discarded
            DocuSignWeb.Tab tab5 = new DocuSignWeb.Tab();
            tab5.RecipientID = "1";
            tab5.TabLabel = "asdf";
            tab5.Type = DocuSignWeb.TabTypeCode.FullName;

            inlineTemplate.Envelope.Tabs = new DocuSignWeb.Tab[] { tab1, tab2, tab3, tab4, tab5 };

            template.InlineTemplates = new DocuSignWeb.InlineTemplate[] { inlineTemplate };

            // Configure the document
            template.Document = new DocuSignWeb.Document();
            template.Document.ID = "1";
            template.Document.Name = "Form Document";
            template.Document.PDFBytes = Resource.LoremIpsum;
            template.Document.TransformPdfFields = true;
            template.Document.FileExtension = "pdf";

            // Create draft with all the composite template information
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateEnvelopeFromTemplatesAndForms(envelopeInfo, new DocuSignWeb.CompositeTemplate[] { template/*, template2*/ }, false);

            // Confirm that the envelope has been assigned an ID
            Assert.IsNotNullOrEmpty(status.EnvelopeID);

            // Confirm that we have the correct number of tabs for each recipient
            Assert.AreEqual(3, status.RecipientStatuses[0].TabStatuses.Length);
            Assert.AreEqual(3, status.RecipientStatuses[1].TabStatuses.Length);

            Console.WriteLine("Envelope Id is {0}", status.EnvelopeID);
        }

        [Test]
        public void CreateEnvelopeFromTemplatesTest()
        {
            // Construct all the recipient information
            DocuSignWeb.Recipient[] recipients = HeartbeatTests.CreateOneSigner();
            DocuSignWeb.TemplateReferenceRoleAssignment[] finalRoleAssignments = new DocuSignWeb.TemplateReferenceRoleAssignment[1];
            finalRoleAssignments[0] = new DocuSignWeb.TemplateReferenceRoleAssignment();
            finalRoleAssignments[0].RoleName = recipients[0].RoleName;
            finalRoleAssignments[0].RecipientID = recipients[0].ID;

            // Use a server-side template -- you could make more than one of these
            DocuSignWeb.TemplateReference templateReference = new DocuSignWeb.TemplateReference();
            templateReference.TemplateLocation = DocuSignWeb.TemplateLocationCode.Server;
            // TODO: replace with template ID from your account
            templateReference.Template = "server template ID";
            templateReference.RoleAssignments = finalRoleAssignments;

            // Construct the envelope information
            DocuSignWeb.EnvelopeInformation envelopeInfo = new DocuSignWeb.EnvelopeInformation();
            envelopeInfo.AccountId = _accountId;
            envelopeInfo.Subject = "create envelope from templates test";
            envelopeInfo.EmailBlurb = "testing docusign creation services";

            // Create draft with all the template information
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateEnvelopeFromTemplates(new DocuSignWeb.TemplateReference[] { templateReference },
                recipients, envelopeInfo, false);

            // Confirm that the envelope has been assigned an ID
            Assert.IsNotNullOrEmpty(status.EnvelopeID);
            Console.WriteLine("Status for envelope {0} is {1}", status.EnvelopeID, status.Status);

        }

        [Test]
        public void CreateEnvelopeTest()
        {
            // Construct a basic envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "create envelope test");
            
            // Go ahead and create it on your account -- it will be a draft
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateEnvelope(envelope);

            // An envelope ID indicates that it succeeded!
            Assert.IsNotNull(status.EnvelopeID);
            Console.WriteLine("Envelope Id is {0}", status.EnvelopeID);
        }

        [Test]
        public void EnvelopeAuditEventsTest()
        {
            // Construct, create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "envelope audit events test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Request the audit history
            XmlNode result = _apiClient.EnvelopeAuditEvents(status.EnvelopeID);

            // Confirm that we got history back
            Assert.IsNotNullOrEmpty(result.OuterXml);
            StringWriter stringWriter = new StringWriter();
            XmlTextWriter xmlTextWriter = new XmlTextWriter(stringWriter);
            xmlTextWriter.Formatting = Formatting.Indented;
            result.WriteTo(xmlTextWriter);
            xmlTextWriter.Flush();
            Console.Write(stringWriter.ToString());
        }

        [Test]
        public void GetAccountMemebershipFeaturesList()
        {
            // This is simple -- just input your account ID
            DocuSignWeb.AccountMembershipFeaturesList list = _apiClient.GetAccountMembershipFeaturesList(_accountId);
            
            // Confirm that we got anything in the list
            Assert.NotNull(list);
            Console.WriteLine("Features list retrieved for account: {0}", list.UserName);
        }

        [Test]
        public void GetAccountSettingsListTest()
        {
            // This is simple -- just input your account ID
            DocuSignWeb.AccountSetting[] settings = _apiClient.GetAccountSettingsList(_accountId);
            
            // Confirm that we got any settings
            Assert.Greater(settings.Length, 0);
            Console.WriteLine("We have {0} account settings", settings.Length);
        }

        [Test]
        public void GetAddressBookItemsTest()
        {
            // This is simple -- just input your account ID
            DocuSignWeb.AddressBookItem[] items = _apiClient.GetAddressBookItems(_accountId);

            // Confirm that we received results
            if (items.Length == 0)
            {
                Assert.Inconclusive("We did not get any address book items, but the user may not have contacts.");
            }
            else
            {
                Assert.Greater(items.Length, 0);
                Console.WriteLine("There there are {0} items in the address book", items.Length);

                foreach (DocuSignWeb.AddressBookItem item in items)
                {
                    Console.WriteLine("\t{0}: {1}", item.UserName, item.Email);
                }
            }
        }

        [Test]
        public void GetAuthenticationTokenTest()
        {
            // To take advantage of the optional navigation to an envelope, create the envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "get authentication token test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Now, call the function with the envelope ID of the envelope
            // to which you wish to navigate
            String token = _apiClient.GetAuthenticationToken(status.EnvelopeID);

            // Confirm that we got a return token
            Assert.IsNotNullOrEmpty(token);
            Console.WriteLine("Token retrieved is {0}", token);
        }

        [Test]
        public void GetFolderItemsTest()
        {
            // Create the folder filter to specify the scope of your search
            // Here, we are limiting the item search to the inbox
            // You can also limit by owner, date, status and position
            DocuSignWeb.FolderFilter filter = new DocuSignWeb.FolderFilter();
            filter.AccountId = _accountId;
            filter.FolderTypeInfo = new DocuSignWeb.FolderTypeInfo();
            filter.FolderTypeInfo.FolderType = DocuSignWeb.FolderType.Inbox;

            // Now, call the method with the filter we created
            DocuSignWeb.FolderResults results = _apiClient.GetFolderItems(filter);

            // This test isn't pass fail, because your filter may not match
            // anything
            if (results.ResultSetSize > 0)
            {
                Assert.Greater(results.ResultSetSize, 0);

                // Loop through and print out some information about the results
                Console.WriteLine("Envelopes in the inbox are:");
                foreach (DocuSignWeb.FolderItem item in results.FolderItems)
                {
                    Console.WriteLine("\tEnvelope {0} has status of: {1}", item.EnvelopeId, item.Status);
                }
            }

            // If we didn't get anything, it isn't necessarily indicative of failure
            else
            {
                Assert.Inconclusive();
            }
        }

        [Test]
        public void GetFolderListTest()
        {
            // Create the folders filter with an account ID
            DocuSignWeb.FoldersFilter filter = new DocuSignWeb.FoldersFilter();
            filter.AccountId = _accountId;

            // Now, call the method to get a list of the folders on the
            // specified account
            DocuSignWeb.AvailableFolders folders = _apiClient.GetFolderList(filter);

            // Everyone will at least have the folders:
            // Draft, Sent Items, Inbox and Deleted Items
            Assert.GreaterOrEqual(folders.Folders.Length, 4);

            // Print out some information about the returned folders
            Console.WriteLine("The folders available on account {0} are:", _accountId);
            foreach (DocuSignWeb.Folder folder in folders.Folders)
            {
                Console.WriteLine("\t{0} owns folder {1}", folder.FolderOwner.UserName, folder.FolderTypeInfo.FolderName);
            }
        }

        [Test]
        public void GetRecipientEsignListTest()
        {
            // Enter account holder's username, email and accountID,
            // and the email of the user that you wish to determine if
            // an esign agreement exists
            DocuSignWeb.RecipientEsignList recipients = _apiClient.GetRecipientEsignList(_userName, _email, _accountId, "test email");
            
            // Confirm that the call succeeded
            Assert.IsNotNull(recipients);
            Console.WriteLine("There are {0} recipients", recipients.RecipientEsign.Length);

            // Examine each record -- does the esign agreement exist?
            foreach (DocuSignWeb.RecipientEsign esign in recipients.RecipientEsign)
            {
                Console.WriteLine("\t{0}: {1}", esign.Email, esign.Esign);

            }
        }

        [Test]
        public void GetRecipientListTest()
        {
            // Enter the account holder's ID and the target email
            // to find all recipients at the target email address
            DocuSignWeb.RecipientList recipients = _apiClient.GetRecipientList(_accountId, "test email");
            
            // Confirm that the call succeeded
            Assert.IsNotNull(recipients);
            Console.WriteLine("There are {0} recipients", recipients.RecipientName.Length);

            // Examine each record -- does the esign agreement exist?
            foreach (String recipient in recipients.RecipientName)
            {
                Console.WriteLine("\t{0}", recipient);
            }
        }

        [Test]
        public void PingTest()
        {
            // Use this function to validate that the Credential API is alive
            bool retval = _apiClient.Ping();
            
            // Confirm that we got an appropriate reply
            Assert.IsTrue(retval);

            if (retval)
            {
                Console.WriteLine("Ping succeeded.");
            }
        }

        [Test]
        public void PurgeDocumentsTest()
        {
            // Create, send and void an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "purge documents test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            VoidEnvelope(status.EnvelopeID, _apiClient);

            // Purge the documents from the specified envelope
            if (!String.IsNullOrEmpty(status.EnvelopeID))
            {
                DocuSignWeb.PurgeDocumentStatus pStatus = HeartbeatTests.PurgeEnvelope(status.EnvelopeID, _apiClient);
                Assert.True(pStatus.PurgeDocumentSuccess);
                Console.WriteLine("Purging the envelope worked? {0}", pStatus.PurgeDocumentSuccess);
            }
            else
            {
                Assert.Fail("Creation of envelope failed.");
            }
        }

        [Test]
        public void RemoveAddressBookItemsTest()
        {
            // Grab all the address book items from the specified account
            DocuSignWeb.AddressBookItem[] items = _apiClient.GetAddressBookItems(_accountId);
            
            if(items.Length >= 1)
            {

                // Use the address book ID of an item to remove it
                DocuSignWeb.AddressBookRemoveItem toRemove = new DocuSignWeb.AddressBookRemoveItem();
                toRemove.AddressBookID = items[0].AddressBookID;

                // Construct an array with all the items you want to remove
                DocuSignWeb.AddressBookRemoveItem[] remove = { toRemove };
                DocuSignWeb.UpdateAddressBookResult result = _apiClient.RemoveAddressBookItems(remove, false);
                
                // Confirm that the update succeeded
                Assert.IsTrue(result.Success);
                Console.WriteLine("Removed item {0} succeeded? {1}", toRemove.AddressBookID, result.Success);
            }
            else
            {
                Assert.Inconclusive("No address book items to remove."); 
            }
        }

        [Test]
        public void RequestCorrectTokenTest()
        {
            try
            {
                // Create and send an envelope
                DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request correct token test");
                envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
                DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
                envelopeIDs.Add(status.EnvelopeID);

                // Request the token with the envelope ID and a return URL
                String token = _apiClient.RequestCorrectToken(status.EnvelopeID, true, "http://127.0.0.1/");

                // Confirm that we got a return token
                Assert.IsNotNullOrEmpty(token);
                Console.WriteLine("The token returned is {0}", token);
            }
            catch (Exception e)
            {
                Assert.Fail(e.StackTrace);
            }
        }

        [Test]
        public void RequestDocumentPDFsExTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request document pdfs ex test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDFs with the envelope's ID
            DocuSignWeb.DocumentPDFs pdf = _apiClient.RequestDocumentPDFsEx(status.EnvelopeID);

            // Confirm that we got at least one PDF returned
            Assert.Greater(pdf.DocumentPDF.Length, 0);
            Console.WriteLine("Envelope has {0} pdfs", pdf.DocumentPDF.Length);
        }

        [Test]
        public void RequestDocumentPDFsRecipientViewTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request document pdfs recipient view test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDFs with the envelope's ID, the recipient's name and email
            DocuSignWeb.DocumentPDFs pdf = _apiClient.RequestDocumentPDFsRecipientsView(status.EnvelopeID, envelope.Recipients[0].UserName, envelope.Recipients[0].Email);

            // Confirm that we got at least one PDF returned
            Assert.Greater(pdf.DocumentPDF.Length, 0);
            Console.WriteLine("Envelope has {0} pdfs", pdf.DocumentPDF.Length);
        }

        [Test]
        public void RequestDocumentPDFsTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request document pdfs test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDFs with the envelope's ID
            DocuSignWeb.DocumentPDFs pdf = _apiClient.RequestDocumentPDFs(status.EnvelopeID);

            // Confirm that we got at least one PDF returned
            Assert.Greater(pdf.DocumentPDF.Length, 0);
            Console.WriteLine("Envelope has {0} pdfs", pdf.DocumentPDF.Length);
        }

        [Test]
        public void RequestEnvelopeTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the envelope with the envelope's ID
            DocuSignWeb.Envelope returnEnvelope = _apiClient.RequestEnvelope(status.EnvelopeID, false);

            // Confirm that we got an envelope returned
            Assert.NotNull(returnEnvelope);
            Console.WriteLine("Return envelope has a subject of \"{0}\"", returnEnvelope.Subject);
        }

        [Test]
        public void RequestPDFNoWaterMarkTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request pdf no watermark test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDF with the envelope ID
            DocuSignWeb.EnvelopePDF pdf = _apiClient.RequestPDFNoWaterMark(status.EnvelopeID);

            // Confirm that the bytes returned are greater than 0
            Assert.Greater(pdf.PDFBytes.Length, 0);
            Console.WriteLine("Pdf has {0} bytes", pdf.PDFBytes.Length);
            
        }

        [Test]
        public void RequestPDFTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request pdf test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDF with the envelope ID
            DocuSignWeb.EnvelopePDF pdf = _apiClient.RequestPDF(status.EnvelopeID);

            // Confirm that the bytes returned are greater than 0
            Assert.Greater(pdf.PDFBytes.Length, 0);
            Console.WriteLine("Pdf has {0} bytes", pdf.PDFBytes.Length);
        }

        [Test]
        public void RequestPDFWithCertTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request pdf with cert test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Call to request the PDF with the envelope ID
            // Here, we chose to suppress the watermark
            DocuSignWeb.EnvelopePDF pdf = _apiClient.RequestPDFWithCert(status.EnvelopeID, false);

            // Confirm that the bytes returned are greater than 0
            Assert.Greater(pdf.PDFBytes.Length, 0);
            Console.WriteLine("Pdf has {0} bytes", pdf.PDFBytes.Length);
        }

        [Test]
        public void RequestRecipientTokenTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request recipient token test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);

            // Need to specify captive info for these recipients
            envelope.Recipients[0].CaptiveInfo = new DocuSignWeb.RecipientCaptiveInfo();
            envelope.Recipients[0].CaptiveInfo.ClientUserId = "User1";

            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Construct the recipient token authentication assertion
            // Specify ID, start time, method and domain
            DocuSignWeb.RequestRecipientTokenAuthenticationAssertion assertion = new DocuSignWeb.RequestRecipientTokenAuthenticationAssertion();
            assertion.AssertionID = new Guid().ToString();
            assertion.AuthenticationInstant = DateTime.Now;
            assertion.AuthenticationMethod = DocuSignWeb.RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.Password;
            assertion.SecurityDomain = "Request Recipient Token Test";

            // Construct the URLs based on username
            DocuSignWeb.Recipient recipient = envelope.Recipients[0];
            DocuSignWeb.RequestRecipientTokenClientURLs urls = new DocuSignWeb.RequestRecipientTokenClientURLs();
            // TODO: replace urlBase with your own test url
            String urlBase = "http://127.0.0.1/";
            urls.OnSigningComplete = urlBase + "?event=SignComplete&uname=" + recipient.UserName;
            urls.OnViewingComplete = urlBase + "?event=ViewComplete&uname=" + recipient.UserName;
            urls.OnCancel = urlBase + "?event=Cancel&uname=" + recipient.UserName;
            urls.OnDecline = urlBase + "?event=Decline&uname=" + recipient.UserName;
            urls.OnSessionTimeout = urlBase + "?event=Timeout&uname=" + recipient.UserName;
            urls.OnTTLExpired = urlBase + "?event=TTLExpired&uname=" + recipient.UserName;
            urls.OnIdCheckFailed = urlBase + "?event=IDCheck&uname=" + recipient.UserName;
            urls.OnAccessCodeFailed = urlBase + "?event=AccessCode&uname=" + recipient.UserName;
            urls.OnException = urlBase + "?event=Exception&uname=" + recipient.UserName;
            
            // Request the token for a specific recipient
            String token = _apiClient.RequestRecipientToken(status.EnvelopeID, recipient.CaptiveInfo.ClientUserId,
                                                            recipient.UserName, recipient.Email, assertion, urls);

            // Confirm that we got a return token
            Assert.IsNotNullOrEmpty(token);
            Console.WriteLine("Recipient token is {0}", token);
        }

        [Test]
        public void RequestSenderTokenTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request sender token test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Request the token for the sender
            String result = _apiClient.RequestSenderToken(status.EnvelopeID, _accountId, "http://127.0.0.1/");

            // Confirm that we got a return token
            Assert.IsNotNullOrEmpty(result);
            Console.WriteLine("Sender token is {0}", result);
        }

        [Test]
        public void RequestStatusChangesTest()
        {
            // Create the status change filter to specify the scope of your search
            // Here, we are limiting the search to envelopes changed today
            // You can also limit by user and status
            DocuSignWeb.EnvelopeStatusChangeFilter filter = new DocuSignWeb.EnvelopeStatusChangeFilter();
            filter.AccountId = _accountId;
            DocuSignWeb.EnvelopeStatusFilterBeginDateTime begin = new DocuSignWeb.EnvelopeStatusFilterBeginDateTime();
            begin.Value = DateTime.Today;
            filter.StatusChangedSince = begin.Value;

            // Now, make the call with the filter we created
            DocuSignWeb.FilteredEnvelopeStatusChanges changes = _apiClient.RequestStatusChanges(filter);

            // This test isn't pass fail, because your filter may not match
            // anything
            if (changes.ResultSetSize > 0)
            {
                Assert.Greater(changes.ResultSetSize, 0);

                // Loop through and print out some information about the results
                Console.WriteLine("Changes since today are:");
                foreach (DocuSignWeb.EnvelopeStatusChange change in changes.EnvelopeStatusChanges)
                {
                    Console.WriteLine("\tEnvelope {0} has status: {1}\n", change.EnvelopeID, change.Status);
                }
            }

            // If we didn't get anything, it isn't necessarily indicative of failure
            else
            {
                Assert.Inconclusive();
            }

        }

        [Test]
        public void RequestStatusCodesTest()
        {
            // Create the status change filter to specify the scope of your search
            // Here, we are limiting the search to envelopes changed today
            // You can also limit by user and status
            DocuSignWeb.EnvelopeStatusFilter filter = new DocuSignWeb.EnvelopeStatusFilter();
            filter.AccountId = _accountId;
            DocuSignWeb.EnvelopeStatusFilterBeginDateTime begin = new DocuSignWeb.EnvelopeStatusFilterBeginDateTime();
            begin.Value = DateTime.Today;
            filter.BeginDateTime = begin;

            // Now, make the call with the filter we created
            DocuSignWeb.FilteredEnvelopeStatusChanges codes = _apiClient.RequestStatusCodes(filter);

            // This test isn't pass fail, because your filter may not match
            // anything
            if (codes.ResultSetSize > 0)
            {
                Assert.Greater(codes.ResultSetSize, 0);
                // Loop through and print out some information about the results
                Console.WriteLine("Changes since today are:");
                foreach (DocuSignWeb.EnvelopeStatusChange code in codes.EnvelopeStatusChanges)
                {
                    Console.WriteLine("\tEnvelope {0} has status: {1}\n", code.EnvelopeID, code.Status);
                }
            }

            // If we didn't get anything, it isn't necessarily indicative of failure
            else
            {
                Assert.Inconclusive();
            }
        }

        [Test]
        public void RequestStatusesExTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope1 = HeartbeatTests.CreateBasicEnvelope(_accountId, "request statuses ex test");
            envelope1 = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope1);
            DocuSignWeb.EnvelopeStatus status1 = _apiClient.CreateAndSendEnvelope(envelope1);
            envelopeIDs.Add(status1.EnvelopeID);

            // Create, send and void another envelope
            DocuSignWeb.Envelope envelope2 = HeartbeatTests.CreateBasicEnvelope(_accountId, "request statuses ex test");
            envelope2 = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope2);
            DocuSignWeb.EnvelopeStatus status2 = _apiClient.CreateAndSendEnvelope(envelope2);
            DocuSignWeb.VoidEnvelopeStatus voidStatus = _apiClient.VoidEnvelope(status2.EnvelopeID, "statuses ex testing");
            
            // Create a filter using account ID and today as a start time
            DocuSignWeb.EnvelopeStatusFilter filter = new DocuSignWeb.EnvelopeStatusFilter();
            filter.AccountId = _accountId;
            DocuSignWeb.EnvelopeStatusFilterBeginDateTime begin = new DocuSignWeb.EnvelopeStatusFilterBeginDateTime();
            begin.Value = DateTime.Today;
            filter.BeginDateTime = begin;

            // Request all envelopes that match the filter
            DocuSignWeb.FilteredEnvelopeStatuses statuses = _apiClient.RequestStatusesEx(filter);
            HeartbeatTests.PurgeEnvelope(status2.EnvelopeID, _apiClient);

            // Confirm that we got at least two envelope statuses back
            Assert.GreaterOrEqual(statuses.EnvelopeStatuses.Length, 2);
            Console.WriteLine("We have {0} statuses that match account ID {1}", statuses.EnvelopeStatuses.Length, statuses.EnvelopeStatusFilter.AccountId);
            foreach (DocuSignWeb.EnvelopeStatus eStatus in statuses.EnvelopeStatuses)
            {
                Console.WriteLine("\tEnvelope with ID {0}", eStatus.EnvelopeID);
                foreach (DocuSignWeb.DocumentStatus dStatus in eStatus.DocumentStatuses)
                {
                    Console.WriteLine("\t\tDocument with ID {0} has name {1}", dStatus.ID,
                        dStatus.Name);
                }
            }
        }

        [Test]
        public void RequestStatusesTest()
        {
            // Create and send one envelope
            DocuSignWeb.Envelope envelope1 = HeartbeatTests.CreateBasicEnvelope(_accountId, "request statuses test");
            envelope1 = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope1);
            DocuSignWeb.EnvelopeStatus status1 = _apiClient.CreateAndSendEnvelope(envelope1);
            envelopeIDs.Add(status1.EnvelopeID);

            // Create, send and void another envelope
            DocuSignWeb.Envelope envelope2 = HeartbeatTests.CreateBasicEnvelope(_accountId, "request statuses test");
            envelope2 = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope2);
            DocuSignWeb.EnvelopeStatus status2 = _apiClient.CreateAndSendEnvelope(envelope2);
            DocuSignWeb.VoidEnvelopeStatus voidStatus = _apiClient.VoidEnvelope(status2.EnvelopeID, "statuses testing");

            // Create a filter using account ID and today as a start time
            DocuSignWeb.EnvelopeStatusFilter filter = new DocuSignWeb.EnvelopeStatusFilter();
            filter.AccountId = _accountId;
            filter.BeginDateTime = new DocuSignWeb.EnvelopeStatusFilterBeginDateTime();
            filter.BeginDateTime.Value = DateTime.Today;

            // Request all envelopes that match the filter
            DocuSignWeb.FilteredEnvelopeStatuses statuses = _apiClient.RequestStatuses(filter);
            HeartbeatTests.PurgeEnvelope(status2.EnvelopeID, _apiClient);

            // Confirm that we got at least two envelope statuses back
            Assert.GreaterOrEqual(statuses.EnvelopeStatuses.Length, 2);
            Console.WriteLine("We have {0} statuses that match account ID {1}", statuses.EnvelopeStatuses.Length, _accountId);
            foreach (DocuSignWeb.EnvelopeStatus eStatus in statuses.EnvelopeStatuses)
            {
                Console.WriteLine("\tEnvelope with ID {0} has status {1}", eStatus.EnvelopeID, eStatus.Status.ToString());
            }
        }

        [Test]
        public void RequestStatusExTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request status ex test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus eStatus = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(eStatus.EnvelopeID);

            // Request the status of that envelope
            DocuSignWeb.EnvelopeStatus status = _apiClient.RequestStatusEx(eStatus.EnvelopeID);

            // Confirm that the status of the envelope is sent
            Assert.True(status.SentSpecified);
            Console.WriteLine("Status for envelope ID {0} is {1}", eStatus.EnvelopeID, status.Status);
            Console.WriteLine("Account status is {0}", status.RecipientStatuses[0].AccountStatus);
        }

        [Test]
        public void RequestStatusTest()
        {
            // Create, send and void an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "request status test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus eStatus = _apiClient.CreateAndSendEnvelope(envelope);
            DocuSignWeb.VoidEnvelopeStatus voidStatus = _apiClient.VoidEnvelope(eStatus.EnvelopeID, "status testing");
            
            // Request the status of that envelope
            DocuSignWeb.EnvelopeStatus status = _apiClient.RequestStatus(eStatus.EnvelopeID);
            HeartbeatTests.PurgeEnvelope(eStatus.EnvelopeID, _apiClient);

            // Confirm that the status we get back is "Voided"
            Assert.AreEqual("Voided", status.Status.ToString());
            Console.WriteLine("Status for envelope ID {0} is {1}", eStatus.EnvelopeID, status.Status);
        }

        [Test]
        public void RequestTemplatesTest()
        {
            // Request all the templates that this account has on file
            DocuSignWeb.EnvelopeTemplateDefinition[] response = _apiClient.RequestTemplates(_accountId, false);
            
            // Make sure the response exists
            Assert.NotNull(response);

            // Print out information about the templates
            if (response.Length > 0)
            {
                foreach (DocuSignWeb.EnvelopeTemplateDefinition def in response)
                {
                    Console.WriteLine("Retrieved template definition with ID {0} with name \"{1}\"", def.TemplateID, def.Name);
                }
            }
            else
            {
                Assert.Inconclusive("We don't have any templates to retrieve!");
            }

        }

        [Test]
        public void RequestTemplateTest()
        {
            // Request all the templates that this account has on file
            DocuSignWeb.EnvelopeTemplateDefinition[] response = _apiClient.RequestTemplates(_accountId, false);

            // Make sure the response exists
            Assert.NotNull(response);

            foreach (DocuSignWeb.EnvelopeTemplateDefinition def in response)
            {
                Console.WriteLine("Retrieved template definition with ID {0}", def.TemplateID);
                
                // Request the template specified by the template ID
                DocuSignWeb.EnvelopeTemplate template = _apiClient.RequestTemplate(def.TemplateID, false);
                
                // Confirm that we got a template returned
                Assert.NotNull(template);
                Console.WriteLine("\tRetrieved template with name \"{0}\" and with {1} pages", template.EnvelopeTemplateDefinition.Name, template.EnvelopeTemplateDefinition.PageCount);
            }
        }

        [Test]
        public void SaveTemplateTest()
        {
            // Request all the templates that this account has on file
            DocuSignWeb.EnvelopeTemplateDefinition[] response = _apiClient.RequestTemplates(_accountId, false);


            // Make sure the response exists
            Assert.NotNull(response);
            foreach (DocuSignWeb.EnvelopeTemplateDefinition def in response)
            {
                Console.WriteLine("Retrieved template definition with ID {0}", def.TemplateID);

                // Request the template specified by the template ID
                DocuSignWeb.EnvelopeTemplate template = _apiClient.RequestTemplate(def.TemplateID, true);

                // Confirm that we got a template returned
                Assert.NotNull(template);
                Console.WriteLine("\tRetrieved template with name \"{0}\"", template.EnvelopeTemplateDefinition.Name);
                
                // Modify the name of the template
                template.EnvelopeTemplateDefinition.Name = "Modified name";
                
                // Save the template back to the account
                DocuSignWeb.SaveTemplateResult result = _apiClient.SaveTemplate(template);
                
                // Confirm that the save operation succeeded
                Assert.True(result.Success);
                Console.WriteLine("\tSaved template? {0}", result.Success);
            }
        }

        [Test]
        public void SendEnvelopeTest()
        {
            // Create an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "send envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);

            // Create the envelope on the server
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateEnvelope(envelope);

            // Send the envelope
            DocuSignWeb.EnvelopeStatus sendStatus = _apiClient.SendEnvelope(status.EnvelopeID, _accountId);
            envelopeIDs.Add(sendStatus.EnvelopeID);

            // Confirm that the sent time is at least today
            Assert.GreaterOrEqual(sendStatus.Sent, DateTime.Today);
            Console.WriteLine("Envelope status is {0}", sendStatus.Status);
            
        }

        [Test]
        public void SynchEnvelopeTest()
        {
            // Create an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "synch envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);

            // Assign it a transaction ID and make it asynchronous send
            envelope.TransactionID = System.Guid.NewGuid().ToString();
            envelope.Asynchronous = true;

            // Go ahead and send the envelope
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            envelopeIDs.Add(status.EnvelopeID);

            // Request a synch using the transaction ID and account ID
            // but don't block it from processing
            DocuSignWeb.SynchEnvelopeStatus synchStatus = _apiClient.SynchEnvelope(envelope.TransactionID, _accountId, false);

            // Confirm we got a status back
            Assert.NotNull(synchStatus);
            Console.WriteLine("Synch envelope status is {0}", synchStatus.EnvelopeStatus);
        }

        //[Test]
        /// <summary>
        /// This needs to be a manual test. You can run this test by:
        /// 1. Send an envelope and capture the envelope ID
        /// 2. Paste the envelope ID into the first parameter
        /// 3. Paste the account ID and user ID of the destination account (in GUID format)
        ///    into the second and third parameters
        /// 4. Call TransferEnvelope
        /// </summary>
        public void TransferEnvelopeTest()
        {
            // Request the envelope specified to be transferred to the account specified
            DocuSignWeb.TransferEnvelopeStatus status = _apiClient.TransferEnvelope("envelope ID", "account ID as GUID", "user ID as GUID");

            // Confirm that the transfer succeeded
            Assert.True(status.TransferEnvelopeSuccess);
            Console.WriteLine("Transfer succeeded? {0}", status.TransferEnvelopeSuccess);
        }

        [Test]
        public void UpdateAddressBookItemsTest()
        {
            // Create an address book item with email, user name, account name
            // and account ID
            DocuSignWeb.AddressBookItem contact = new DocuSignWeb.AddressBookItem();
            //TODO: replace email, username and accountname with a test information
            contact.Email = "test email";
            contact.UserName = "test username";
            contact.AccountName = "test accountname";
            contact.Shared = true;
            contact.AddressBookID = System.Guid.NewGuid().ToString();

            // Create an array with all items to update / add
            DocuSignWeb.AddressBookItem[] items = { contact };

            // Update the address book with the new items
            DocuSignWeb.UpdateAddressBookResult result = _apiClient.UpdateAddressBookItems(items, false);

            // Confirm that the address book updated
            Assert.True(result.Success);
            Console.WriteLine("Updated address book? {0}", result.Success);
        }

        [Test]
        public void UploadTemplateTest()
        {
            // Read the template that you want to upload
            String templateXML = System.IO.File.ReadAllText("resources\\sampleTemplate.dpd");

            // Upload the specified template to the account
            DocuSignWeb.SaveTemplateResult result = _apiClient.UploadTemplate(templateXML, _accountId, false);

            // Confirm that the template saved successfully
            Assert.True(result.Success);
            Console.WriteLine("The template was successfully uploaded? {0}", result.Success);            
        }

        [Test]
        public void VoidEnvelopeTest()
        {
            // Create and send an envelope
            DocuSignWeb.Envelope envelope = HeartbeatTests.CreateBasicEnvelope(_accountId, "void envelope test");
            envelope = HeartbeatTests.CreateEnvelopeWithDocumentAndTabs(envelope);
            DocuSignWeb.EnvelopeStatus status = _apiClient.CreateAndSendEnvelope(envelope);
            
            if (!String.IsNullOrEmpty(status.EnvelopeID))
            {
                // Void the envelope
                DocuSignWeb.VoidEnvelopeStatus voidStatus = HeartbeatTests.VoidEnvelope(status.EnvelopeID, _apiClient);

                // Confirm that the envelope was voided
                Assert.IsTrue(voidStatus.VoidSuccess);
                Console.WriteLine("Voiding the envelope worked? {0}", voidStatus.VoidSuccess);
                
                HeartbeatTests.PurgeEnvelope(status.EnvelopeID, _apiClient);
            }
            else
                Assert.Fail("Creation of envelope failed.");
        }

        [TestFixtureTearDown]
        public void FixtureTeardown()
        {
            foreach (String id in envelopeIDs)
            {
                VoidEnvelope(id, _apiClient);
                PurgeEnvelope(id, _apiClient);
            }
        }

        /// <summary>
        /// A simple construct of one test signer
        /// </summary>
        internal static DocuSignWeb.Recipient[] CreateOneSigner()
        {
            DocuSignWeb.Recipient[] signers = { new DocuSignWeb.Recipient() };
            // TODO: replace email, username and rolename with test information
            signers[0].Email = "test email";
            signers[0].UserName = "test username";
            signers[0].Type = DocuSignWeb.RecipientTypeCode.Signer;
            signers[0].ID = "1";
            signers[0].RoutingOrder = 1;
            signers[0].RoleName = "test rolename";
            return signers;
        }

        /// <summary>
        /// Create a basic envelope without sending
        /// </summary>
        /// <param name="accountId">user's account ID</param>
        /// <param name="subject">email subject line</param>
        /// <returns></returns>
        internal static DocuSignWeb.Envelope CreateBasicEnvelope(String accountId, String subject)
        {
            DocuSignWeb.Recipient[] recipients = HeartbeatTests.CreateOneSigner();

            int nextRecipientId = 1;
            foreach (DocuSignWeb.Recipient recipient in recipients)
            {
                recipient.ID = nextRecipientId.ToString(System.Globalization.CultureInfo.InvariantCulture);
                nextRecipientId++;
            }
            DocuSignWeb.Envelope envelope = new DocuSignWeb.Envelope();
            envelope.Subject = subject;
            envelope.EmailBlurb = "testing docusign creation services";
            envelope.Recipients = recipients;
            envelope.AccountId = accountId;
            return envelope;
        }

        /// <summary>
        /// Create a basic envelope with documents and tabs in preparation for sending
        /// </summary>
        /// <param name="envelope">already created envelope</param>
        /// <returns>updated envelope</returns>
        internal static DocuSignWeb.Envelope CreateEnvelopeWithDocumentAndTabs(DocuSignWeb.Envelope envelope)
        {
            envelope.Documents = new DocuSignWeb.Document[1];
            DocuSignWeb.Document doc = new DocuSignWeb.Document();
            doc.ID = "1";
            doc.Name = "Picture PDF";
            doc.PDFBytes = Resource.picturePdf;
            Assert.IsNotNull(doc.PDFBytes);
            envelope.Documents[0] = doc;
            DocuSignWeb.Tab tab = new DocuSignWeb.Tab();
            tab.DocumentID = "1";
            tab.RecipientID = "1";
            tab.Type = DocuSignWeb.TabTypeCode.SignHere;
            tab.PageNumber = "1";
            tab.XPosition = "100";
            tab.YPosition = "100";
            envelope.Tabs = new DocuSignWeb.Tab[1];
            envelope.Tabs[0] = tab;
            return envelope;
        }

        /// <summary>
        /// Void a specific envelope
        /// </summary>
        /// <param name="envelopeID">envelope ID</param>
        /// <param name="apiClient">API proxy</param>
        /// <returns></returns>
        internal static DocuSignWeb.VoidEnvelopeStatus VoidEnvelope(String envelopeID, DocuSignWeb.APIServiceSoapClient apiClient)
        {
            return apiClient.VoidEnvelope(envelopeID, "void envelope test");
        }

        /// <summary>
        /// Purge the documents from a specific envelope
        /// </summary>
        /// <param name="envelopeID">envelope ID</param>
        /// <param name="apiClient">API proxy</param>
        /// <returns></returns>
        internal static DocuSignWeb.PurgeDocumentStatus PurgeEnvelope(String envelopeID, DocuSignWeb.APIServiceSoapClient apiClient)
        {
            return apiClient.PurgeDocuments(envelopeID);
        }
    }
}