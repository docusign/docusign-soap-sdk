<?php

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

//  credential api service proxy classes and soapclient
include("api/CredentialService.php");
// transaction api service proxy classes and soapclient
include("api/APIService.php");
// some helper functions
include_once("include/utils.php");
// user credentials
include_once("include/creds.php");

//=============================================================================
// helper constants
//=============================================================================

// TODO: put in a test recipient email
$_apiRecipient1Email = "";
// TODO: put in users name
$_apiUserName = "";

//=============================================================================
// Set up the API
//=============================================================================
$_apiEndpoint = "https://demo.docusign.net/api/3.0/api.asmx";
$_apiWsdl = "api/APIService.wsdl";
$api_options =  array('location'=>$_apiEndpoint,'trace'=>true,'features' => SOAP_SINGLE_ELEMENT_ARRAYS);
$api = new APIService($_apiWsdl, $api_options);
$api->setCredentials("[" . $IntegratorsKey . "]" . $UserID, $Password);

//=============================================================================
// helper functions
//=============================================================================
/**
 * Creates one signer recipient
 * @return array of one Recipient
 */
function createOneSigner() {
    global $_apiRecipient1Email;

    $rcp1 = new Recipient();    // First recipient to put in recipient array
    $rcp1->UserName = "John Doe";
    $rcp1->Email = $_apiRecipient1Email;
    $rcp1->Type = RecipientTypeCode::Signer;
    $rcp1->ID = "1";
    $rcp1->RoutingOrder = 1;
    $rcp1->RequireIDLookup = FALSE;

    return array($rcp1);
}

/**
 * Creates a basic envelope with one recipient
 * @param string $accountID
 * @param string $subject
 * @return Envelope
 */
function createBasicEnvelope($accountID, $subject) {
    $env = new Envelope();
    $env->AccountId = $accountID; // Note: GUID should be used here rather than email
    $env->Subject = $subject;
    $env->EmailBlurb = "testing docusign creation services";
    $env->Recipients = createOneSigner();

    return $env;
}

/**
 * Create a basic envelope with documents and tabs in preparation for sending
 * @param Envelope $envelope
 * @return Envelope
 */
function createEnvelopeWithDocumentAndTabs($envelope) {
    $doc = new Document();
    $doc->ID = "1";
    $doc->Name = "Picture PDF";
    $doc->PDFBytes = file_get_contents("docs/picturePdf.pdf");
    $envelope->Documents = array($doc);

    $tab = new Tab();
    $tab->DocumentID = "1";
    $tab->RecipientID = "1";
    $tab->Type = TabTypeCode::SignHere;
    $tab->PageNumber = "1";
    $tab->XPosition = "100";
    $tab->YPosition = "100";
    $envelope->Tabs = array($tab);

    return $envelope;
}

//=============================================================================
// Snippets
//=============================================================================

/**
 * The Ping API method enables API users make a simple call to the API service
 * to determine its active state.
 * @return PingResponse
 */
function pingSample() {
    global $api;

    $pingParams = new Ping();
    $pingResponse = $api->Ping($pingParams);

    return $pingResponse;
}

/**
 * The GetAddressBookItems API retrieves your address book. This method returns
 * an array of AddressBookItem objects.
 * @return GetAddressBookItemsResponse
 */
function getAddressBookItemsSample() {
    global $api;
    global $AccountID;

    $getAddressBookItemsparams = new GetAddressBookItems();
    $getAddressBookItemsparams->AccountID = $AccountID;
    $getAddressBookItemsResponse = $api->GetAddressBookItems($getAddressBookItemsparams);

    return $getAddressBookItemsResponse;
}

/**
 * Used to create envelopes, specify recipients, documents and actions on those
 * documents within that envelope.
 * @return CreateEnvelopeResponse
 */
function createEnvelopeSample() {
    global $api;
    global $AccountID;

    // Build parameters
    $createEnvelopeparams = new CreateEnvelope();
    $createEnvelopeparams->Envelope = createBasicEnvelope($AccountID, "create envelope test");

    // Send
    $createEnvelopeResponse = $api->CreateEnvelope($createEnvelopeparams);

    return $createEnvelopeResponse;
}

/**
 * TODO: Not implemented yet
 * @return AcknowledgeAuthoritativeCopyExportResponse
function acknowledgeAuthoritativeCopyExportSample() {
    global $api;
    global $AccountID;

    // Send
    $acknowledgeAuthoritativeCopyExportparams = new AcknowledgeAuthoritativeCopyExport();
    $response = $api->AcknowledgeAuthoritativeCopyExport($acknowledgeAuthoritativeCopyExportparams);

    return $response;
}
 */

/**
 * Modify attributes of envelope recipients through the DocuSign web
 * service, as well as resend Envelope activation emails
 * @return Ambigous <CorrectAndResendEnvelopeResponse, mixed>
 */
function correctAndResendEnvelopeSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope we can correct
    $env = createBasicEnvelope($AccountID, "correct and resend envelope test");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResponse = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Create a new recipient correction
    $recip = $env->Recipients[0];
    $correction = new Correction();
    $correction->EnvelopeID = $createResponse->EnvelopeID;
    $recipCorrection = new RecipientCorrection();
    //    Just reuse the same data
    $recipCorrection->PreviousEmail = $recipCorrection->CorrectedEmail = $recip->Email;
    $recipCorrection->PreviousUserName = $recipCorrection->CorrectedUserName = $recip->UserName;
    $recipCorrection->PreviousRoutingOrder = $recipCorrection->CorrectedRoutingOrder = $recip->RoutingOrder;
    $correction->RecipientCorrections = array($recipCorrection);

    $correctAndResendEnvelopeparams = new CorrectAndResendEnvelope();
    $correctAndResendEnvelopeparams->Correction = $correction;

    // Send
    $response = $api->CorrectAndResendEnvelope($correctAndResendEnvelopeparams);

    return $response;
}

/**
 * Used to create envelopes, specify recipients, documents and actions on those
 * documents within that envelope
 * @return CreateAndSendEnvelopeResponse
 */
function createAndSendEnvelopeSample() {
    global $api;
    global $AccountID;

    // Build Parameters
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $env = createBasicEnvelope($AccountID, "create and send envelope test");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams->Envelope = $env;

    // Send
    $response = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams);

    return $api->__getLastRequestHeaders() . "\n=====\n" .
        $api->__getLastRequest() . "\n=====\n" .
        $api->__getLastResponseHeaders() . "\n=====\n" .
        $api->__getLastResponse();
}

/**
 * Creates draft envelope with templateinfo
 * @return CreateEnvelopeFromTemplatesResponse
 */
function createEnvelopeFromTemplatesSample() {
    global $api;
    global $AccountID;

    // Use a server-side template
    $templateRef = new TemplateReference();
    $templateRef->TemplateLocation = TemplateLocationCode::Server;
        // TODO: Replace string with the GUID of a template already uploaded to your account
    $templateRef->Template = "";

    // Construct the envelope info
    $envInfo = new EnvelopeInformation();
    $envInfo->AccountId = $AccountID;
    $envInfo->Subject = "create envelope from templates test";
    $envInfo->EmailBlurb = "testing docusign create services";

    // Send creates draft with all the template info
    $createEnvelopeFromTemplatesparams = new CreateEnvelopeFromTemplates();
    $createEnvelopeFromTemplatesparams->TemplateReferences = array($templateRef);
    $createEnvelopeFromTemplatesparams->Recipients = createOneSigner();
    $createEnvelopeFromTemplatesparams->EnvelopeInformation = $envInfo;
    $createEnvelopeFromTemplatesparams->ActivateEnvelope = false;
    $response = $api->CreateEnvelopeFromTemplates($createEnvelopeFromTemplatesparams);

    return $response;
}

/**
 * Derives its Tab assignments from the pre-configured Template; and provides
 * Recipient information to fulfill the Roles that are specified in the Template.
 * @return reateEnvelopeFromTemplatesAndFormsResponse
 */
function createEnvelopeFromTemplatesAndFormsSample() {
    global $api;
    global $AccountID;

    // Configure and envelope information
    $envInfo = new EnvelopeInformation();
    $envInfo->AccountId = $AccountID;
    $envInfo->EmailBlurb = "testing docusing creation services";
    $envInfo->Subject = "create envelope from templates and forms sample";

    $recipient1 = new Recipient();
    $recipient1->UserName = "SignerOne";
    // TODO: replace email string with actual email
    $recipient1->Email = "";
    $recipient1->Type = RecipientTypeCode::Signer;
    $recipient1->RequireIDLookup = FALSE;
    $recipient1->RoutingOrder = 1;
    $recipient1->RoleName = "One";
    $recipient1->ID = "1";

    $recipient2 = new Recipient();
    $recipient2->UserName = "SignerTwo";
    // TODO: replace email string with actual email
    $recipient2->Email = "";
    $recipient2->Type = RecipientTypeCode::Signer;
    $recipient2->RequireIDLookup = FALSE;
    $recipient2->RoutingOrder = 2;
    $recipient2->RoleName = "Two";
    $recipient2->ID = "2";

    $signers = array($recipient1, $recipient2);

    // Build template
    $inlineTemplate = new InlineTemplate();
    $inlineTemplate->Sequence = "1";
    $env = new Envelope();
    $env->Recipients = $signers;
    $env->AccountId = $AccountID;

    // This tab matches the DateSigned tab assigned to recipient one
    $tab1 = new Tab();
    $tab1->RecipientID = "1";
    $tab1->TabLabel = "DocuSignDateSignedOne";
    $tab1->Type = TabTypeCode::DateSigned;

    // This tab matches the SignHere tabs assigned to recipient two
    $tab2 = new Tab();
    $tab2->RecipientID = "2";
    $tab2->TabLabel = "SignTwo\\*";
    $tab2->Type = TabTypeCode::SignHere;

    // This tab matches the SignHere tabs assigned to recipient one
    $tab3 = new Tab();
    $tab3->RecipientID = "1";
    $tab3->TabLabel = "SignOne\\*";
    $tab3->Type = TabTypeCode::SignHere;

    // This tab matches the DateSigned tab assigned to recipient two
    $tab4 = new Tab();
    $tab4->RecipientID = "2";
    $tab4->TabLabel = "DocuSignDateSignedTwo";
    $tab4->Type = TabTypeCode::DateSigned;

    // This tab matches nothing -- but that's okay!
    // It will just get discarded
    $tab5 = new Tab();
    $tab5->RecipientID = "1";
    $tab5->TabLabel = "asdf";
    $tab5->Type = TabTypeCode::FullName;

    $env->Tabs = array($tab1, $tab2, $tab3, $tab4, $tab5);

    $inlineTemplate->Envelope = $env;
    $template = new CompositeTemplate();
    $template->InlineTemplates = array($inlineTemplate);

    // Configure the document
    $doc = new Document();
    $doc->ID = "1";
    $doc->Name = "Form Document";
    $doc->PDFBytes = file_get_contents("docs/LoremIpsum.pdf");
    $doc->TransformPdfFields = true;
    $doc->FileExtension = "pdf";
    $template->Document = $doc;

    // Send
    $createEnvelopeFromTemplatesAndFormsparams = new CreateEnvelopeFromTemplatesAndForms();
    $createEnvelopeFromTemplatesAndFormsparams->EnvelopeInformation = $envInfo;
    $createEnvelopeFromTemplatesAndFormsparams->CompositeTemplates = array($template);
    $createEnvelopeFromTemplatesAndFormsparams->ActivateEnvelope = false;
    $response = $api->CreateEnvelopeFromTemplatesAndForms($createEnvelopeFromTemplatesAndFormsparams);

    return $response;
}

/**
 * requests the events performed on an envelope
 * @return EnvelopeAuditEventsResponse
 */
function envelopeAuditEventsSample() {
    global $api;
    global $AccountID;

    // Construct, create, and send an envelope
    $env = createBasicEnvelope($AccountID, "evelope audit events test");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send and request audit history
    $envelopeAuditEventsparams = new EnvelopeAuditEvents();
    $envelopeAuditEventsparams->EnvelopeId = $createResult->EnvelopeID;
    $response = $api->EnvelopeAuditEvents($envelopeAuditEventsparams);

    return $response;
}

/**
 * TODO: not implemented yet
 * @return ExportAuthoritativeCopyResponse
function exportAuthoritativeCopySample() {
    global $api;
    global $AccountID;

    $exportAuthoritativeCopyparams = new ExportAuthoritativeCopy();

    // Send
    $response = $api->ExportAuthoritativeCopy($exportAuthoritativeCopyparams);

    return $response;
}
 */

/**
 * retrieves the features available to the membership
 * @return GetAccountMembershipFeaturesListResponse
 */
function getAccountMembershipFeaturesListSample() {
    global $api;
    global $AccountID;

    // Send
    $getAccountMembershipFeaturesListparams = new GetAccountMembershipFeaturesList();
    $getAccountMembershipFeaturesListparams->AccountId = $AccountID;
    $response = $api->GetAccountMembershipFeaturesList($getAccountMembershipFeaturesListparams);

    return $response;
}

/**
 * retrieves the settings available to the account
 * @return GetAccountSettingsListResponse
 */
function getAccountSettingsListSample() {
    global $api;
    global $AccountID;

    // Send
    $getAccountSettingsListparams = new GetAccountSettingsList();
    $getAccountSettingsListparams->AccountId = $AccountID;
    $response = $api->GetAccountSettingsList($getAccountSettingsListparams);

    return $response;
}

/**
 * gets a onetime use URL with an authentication token to launch the DocuSign
 * member system
 * @return GetAuthenticationTokenResponse
 */
function getAuthenticationTokenSample() {
    global $api;
    global $AccountID;

    // Create the envelope to show the optional navigation to an envelope
    $env = createBasicEnvelope($AccountID, "get authentication token sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $getAuthenticationTokenparams = new GetAuthenticationToken();
    $getAuthenticationTokenparams->GoToEnvelopeID = $createResult->EnvelopeID;
    $response = $api->GetAuthenticationToken($getAuthenticationTokenparams);

    return $response;
}


/**
 * requests all the envelopes in a specified folder for the requestor or a Folder
 * Owner
 * @return GetFolderItemsResponse
 */
function getFolderItemsSample() {
    global $api;
    global $AccountID;

    // Create the folder filter to specify the scope of your search
    // Here, we are limiting the item search to the inbox
    // You can also limit by owner, date, status and position
    $filter = new FolderFilter();
    $filter->AccountId = $AccountID;
    $filter->StartPosition = 0;
    $filterTypeInfo = new FolderTypeInfo();
    $filterTypeInfo->FolderType = FolderType::Inbox;
    $filter->FolderTypeInfo = $filterTypeInfo;

    // Send
    $getFolderItemsparams = new GetFolderItems();
    $getFolderItemsparams->FolderFilter = $filter;
    $response = $api->GetFolderItems($getFolderItemsparams);

    return $response;
}

/**
 * requests the list of all folders, including shared folders, available for the
 * account.
 * @return GetFolderListResponse
 */
function getFoldersListSample() {
    global $api;
    global $AccountID;

    // Create the folders filter with an account ID
    $filter = new FoldersFilter();
    $filter->AccountId = $AccountID;

    // Send
    $getFolderListparams = new GetFolderList();
    $getFolderListparams->FoldersFilter = $filter;
    $response = $api->GetFolderList($getFolderListparams);

    return $response;
}

/**
 * determine if an Esign agreement pre-exists between the sender and
 * recipient
 * @return GetRecipientEsignListResponse
 */
function getRecipientEsignListSample() {
    global $api;
    global $AccountID;
    global $UserID;
    global $_apiUserName;
    global $_apiRecipient1Email;

    // Enter account holder's username, email and accountID, and the email of
    // the user that you wish to determine if an esign agreement exists
    $getRecipientEsignListparams = new GetRecipientEsignList();
    $getRecipientEsignListparams->SenderAccountId = $AccountID;
    $getRecipientEsignListparams->SenderEmail = $UserID;
    $getRecipientEsignListparams->UserName = $_apiUserName;
    $getRecipientEsignListparams->RecipientEmail = $_apiRecipient1Email;
    $response = $api->GetRecipientEsignList($getRecipientEsignListparams);

    return $response;
}

/**
 * determine which recipients are available at the given email address
 * @return GetRecipientListResponse
 */
function getRecipientListSample() {
    global $api;
    global $AccountID;
    global $_apiRecipient1Email;

    // Enter the account holder's ID and the target email to find all recipients
    // at the target email address
    $getRecipientListparams = new GetRecipientList();
    $getRecipientListparams->SenderAccountId = $AccountID;
    $getRecipientListparams->RecipientEmail = $_apiRecipient1Email;
    $response = $api->GetRecipientList($getRecipientListparams);

    return $response;
}

/**
 * TODO: Reserved for future use
 * @return GetStatusInDocuSignConnectFormatResponse

function getStatusInDocuSignConnectFormatSample() {
    global $api;
    global $AccountID;

    // Send
    $getStatusInDocuSignConnectFormatparams = new GetStatusInDocuSignConnectFormat();
    $response = $api->GetStatusInDocuSignConnectFormat($getStatusInDocuSignConnectFormatparams);

    return $response;
}
 */

/**
 * purges completed documents from the DocuSign system
 * @return PurgeDocumentsResponse
 */
function purgeDocumentsSample() {
    global $api;
    global $AccountID;

    // Create, send , and void an envelope
    $env = createBasicEnvelope($AccountID, "purge document sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;
    $voidEnvelopeparams = new VoidEnvelope();
    $voidEnvelopeparams->EnvelopeID = $createResult->EnvelopeID;
    $voidEnvelopeparams->Reason = "void envelope for PurgeDocuments sample";
    $voidResult = $api->VoidEnvelope($voidEnvelopeparams);

    // Send
    $purgeDocumentsparams = new PurgeDocuments();
    $purgeDocumentsparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->PurgeDocuments($purgeDocumentsparams);

    return $response;
}

/**
 * Removes all the specified items passed from your address book
 * @return RemoveAddressBookItemsResponse
 */
function removeAddressBookItemsSample() {
    global $api;
    global $AccountID;

    // Grab all the address book items from the specified account
    $getAddressBookItemsparams = new GetAddressBookItems();
    $getAddressBookItemsparams->AccountID = $AccountID;
    $addBookItems = $api->GetAddressBookItems($getAddressBookItemsparams)->GetAddressBookItemsResult->AddressBookItem;

    if (count($addBookItems) >= 1) {
        $addToRemove = new AddressBookRemoveItem();
        $addToRemove->AddressBookID = $addBookItems[0]->AddressBookID;

        // Send
        $removeAddressBookItemsparams = new RemoveAddressBookItems();
        $removeAddressBookItemsparams->AddressBookRemoveItems = array($addToRemove);
        $removeAddressBookItemsparams->ReturnAddressBook = false;
        $response = $api->RemoveAddressBookItems($removeAddressBookItemsparams);
    }
    else {
        $response = "No address book items to remove.";
    }

    return $response;
}

/**
 * returns a token to place a user in a web session in Advanced Correct
 * mode on an envelope
 * @return RequestCorrectTokenResponse
 */
function requestCorrectTokenSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request correct token sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Request the token with the envelope ID and a return URL
    $requestCorrectTokenparams = new RequestCorrectToken();
    $requestCorrectTokenparams->EnvelopeID = $createResult->EnvelopeID;
    $requestCorrectTokenparams->ReturnURL = "http://127.0.0.1/";
    $requestCorrectTokenparams->SuppressNavigation = true;
    $response = $api->RequestCorrectToken($requestCorrectTokenparams);

    return $response;
}

/**
 * Requests pdfs
 * @return equestDocumentPDFsResponse
 */
function requestDocumentPDFsSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request documents pdfs sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $requestDocumentPDFsparams = new RequestDocumentPDFs();
    $requestDocumentPDFsparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestDocumentPDFs($requestDocumentPDFsparams);

    return $response;
}

/**
 * Requests pdfs with some extra info
 * @return RequestDocumentPDFsExResponse
 */
function requestDocumentPDFsExSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request documents pdfs ex sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send
    $requestDocumentPDFsExparams = new RequestDocumentPDFsEx();
    $requestDocumentPDFsExparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestDocumentPDFsEx($requestDocumentPDFsExparams);

    return $response;
}

/**
 * Returns the PDF a recipients view will see
 * @return RequestDocumentPDFsRecipientsViewResponse
 */
function requestDocumentPDFsRecipientsViewSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request documents pdfs recipients vew sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $requestDocumentPDFsRecipientsViewparams = new RequestDocumentPDFsRecipientsView();
    $requestDocumentPDFsRecipientsViewparams->EnvelopeID = $createResult->EnvelopeID;
    $requestDocumentPDFsRecipientsViewparams->RecipientEmail = $env->Recipients[0]->Email;
    $requestDocumentPDFsRecipientsViewparams->RecipientName = $env->Recipients[0]->UserName;
    $response = $api->RequestDocumentPDFsRecipientsView($requestDocumentPDFsRecipientsViewparams);

    return $response;
}

/**
 * returns an API Envelope object containing all the data of an envelope
 * @return RequestEnvelopeResponse
 */
function requestEnvelopeSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request envelope sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $requestEnvelopeparams = new RequestEnvelope();
    $requestEnvelopeparams->EnvelopeID = $createResult->EnvelopeID;
    $requestEnvelopeparams->IncludeDocumentBytes = false;
    $response = $api->RequestEnvelope($requestEnvelopeparams);

    return $response;
}

/**
 * returns all of the documents combined into a single, contiguous PDF
 * @return RequestPDFResponse
 */
function requestPDFSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request pdf sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

        // Send
    $requestPDFparams = new RequestPDF();
    $requestPDFparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestPDF($requestPDFparams);

    return $response;
}

/**
 * Returns pdf without the watermark
 * @return RequestPDFNoWaterMarkResponse
 */
function requestPDFNoWaterMarkSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request pdf no watermark sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $requestPDFNoWaterMarkparams = new RequestPDFNoWaterMark();
    $requestPDFNoWaterMarkparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestPDFNoWaterMark($requestPDFNoWaterMarkparams);

    return $response;
}

/**
 * Returns pdf with cert
 * @return RequestPDFWithCertResponse
 */
function requestPDFWithCertSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request pdf with cert sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send
    $requestPDFWithCertparams = new RequestPDFWithCert();
    $requestPDFWithCertparams->EnvelopeID = $createResult->EnvelopeID;
    $requestPDFWithCertparams->AddWaterMark = false;
    $response = $api->RequestPDFWithCert($requestPDFWithCertparams);

    return $response;
}

/**
 * Requests recipient token used by embedded signing process
 * @return RequestRecipientTokenResponse
 */
function requestRecipientTokenSample() {
    global $api;
    global $AccountID;

    // Create an envelope
    $env = createBasicEnvelope($AccountID, "request recipient token sample");
    $env = createEnvelopeWithDocumentAndTabs($env);

    // Specify captive info for recipients
    $captiveInfo = new RecipientCaptiveInfo();
    $captiveInfo->ClientUserId = "User1";
    $env->Recipients[0]->CaptiveInfo = $captiveInfo;

    // Now send the envelope
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Construct the recipient token authentication assertion and specify
    // ID, start time, method, and domain
    $assertion = new RequestRecipientTokenAuthenticationAssertion();
    $assertion->AssertionID = guid();
    $assertion->AuthenticationInstant = nowXsdDate();
    $assertion->AuthenticationMethod = RequestRecipientTokenAuthenticationAssertionAuthenticationMethod::Password;
    $assertion->SecurityDomain = "Request Recipient Token Test";

    // Construct the URLs based on UserName
    $recip = $env->Recipients[0];
    $urls = new RequestRecipientTokenClientURLs();
    $urlbase = "http://127.0.0.1/";
    $urls->OnSigningComplete = $urlbase . "?event=SignComplete&uname=" . $recip->UserName;
    $urls->OnViewingComplete = $urlbase . "?event=ViewComplete&uname=" . $recip->UserName;
    $urls->OnCancel = $urlbase . "?event=Cancel&uname=" . $recip->UserName;
    $urls->OnDecline = $urlbase . "?event=Decline&uname=" . $recip->UserName;
    $urls->OnSessionTimeout = $urlbase . "?event=Timeout&uname=" . $recip->UserName;
    $urls->OnTTLExpired = $urlbase . "?event=TTLExpired&uname=" . $recip->UserName;
    $urls->OnIdCheckFailed = $urlbase . "?event=IDCheck&uname=" . $recip->UserName;
    $urls->OnAccessCodeFailed = $urlbase . "?event=AccesssCode&uname=" . $recip->UserName;
    $urls->OnException = $urlbase . "?event=Exception&uname=" . $recip->UserName;

    // Send
    $requestRecipientTokenparams = new RequestRecipientToken();
    $requestRecipientTokenparams->EnvelopeID = $createResult->EnvelopeID;
    $requestRecipientTokenparams->ClientUserID = $recip->CaptiveInfo->ClientUserId;
    $requestRecipientTokenparams->Username = $recip->UserName;
    $requestRecipientTokenparams->Email = $recip->Email;
    $requestRecipientTokenparams->AuthenticationAssertion = $assertion;
    $requestRecipientTokenparams->ClientURLs = $urls;
    $response = $api->RequestRecipientToken($requestRecipientTokenparams);

    return $response;
}

/**
 * get a onetime use login token that allows the user to be placed into the
 * DocuSign sending wizard
 * @return RequestSenderTokenResponse
 */
function requestSenderTokenSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request sender token sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;

    // Send
    $requestSenderTokenparams = new RequestSenderToken();
    $requestSenderTokenparams->EnvelopeID = $createResult->EnvelopeID;
    $requestSenderTokenparams->AccountID = $AccountID;
    $requestSenderTokenparams->ReturnURL = "heep://127.0.0.1/";
    $response = $api->RequestSenderToken($requestSenderTokenparams);

    return $response;
}

/**
 * query the status of existing envelopes
 * @return RequestStatusResponse
 */
function requestStatusSample() {
    global $api;
    global $AccountID;

    // Create, send , and void an envelope
    $env = createBasicEnvelope($AccountID, "request status sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;
    $voidEnvelopeparams = new VoidEnvelope();
    $voidEnvelopeparams->EnvelopeID = $createResult->EnvelopeID;
    $voidEnvelopeparams->Reason = "void envelope for PurgeDocuments sample";
    $voidResult = $api->VoidEnvelope($voidEnvelopeparams);

    // Send (check for Voided status in returned data dump)
    $requestStatusparams = new RequestStatus();
    $requestStatusparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestStatus($requestStatusparams);

    return $response;
}

/**
 * requests the envelope status changes for the envelopes for account on or
 * after the specified date/time.
 * @return RequestStatusChangesResponse
 */
function requestStatusChangesSample() {
    global $api;
    global $AccountID;

    // Create the status change filter to specify the scope of your search
    // Here, we are limiting the search to envelopes changed today
    // You can also limit by user and status
    $filter = new EnvelopeStatusChangeFilter();
    $filter->AccountId = $AccountID;
    $filter->StatusChangedSince = todayXsdDate();

    // Send
    $requestStatusChangesparams = new RequestStatusChanges();
    $requestStatusChangesparams->EnvelopeStatusChangeFilter = $filter;
    $response = $api->RequestStatusChanges($requestStatusChangesparams);

    return $response;
}

/**
 * requests the current state (Delivered, Complete, Voided, etc.) of the
 * specified envelopes.
 * @return RequestStatusCodesResponse
 */
function requestStatusCodesSample() {
    global $api;
    global $AccountID;

    // Create the status change filter to specify the scope of your search
    // Here, we are limiting the search to envelopes changed today
    // You can also limit by user and status
    $filter = new EnvelopeStatusFilter();
    $filter->AccountId = $AccountID;
    $filter->BeginDateTime = todayXsdDate();

    // Send
    $requestStatusCodesparams = new RequestStatusCodes();
    $requestStatusCodesparams->EnvelopeStatusFilter = $filter;
    $response = $api->RequestStatusCodes($requestStatusCodesparams);

    return $response;
}

/**
 * query the status of existing envelopes with a little Extra
 * @return RequestStatusExResponse
 */
function requestStatusExSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "request status ex sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $createResult = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send
    $requestStatusExparams = new RequestStatusEx();
    $requestStatusExparams->EnvelopeID = $createResult->EnvelopeID;
    $response = $api->RequestStatusEx($requestStatusExparams);

    return $response;
}

/**
 * request the status of multiple envelopes in a single call. Up to 200
 * envelopes can be retrieved
 * @return RequestStatusesResponse
 */
function requestStatusesSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env1 = createBasicEnvelope($AccountID, "request statuses 1 sample");
    $env1 = createEnvelopeWithDocumentAndTabs($env1);
    $createAndSendEnvelopeparams1 = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams1->Envelope = $env1;
    $createResult1 = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams1)->CreateAndSendEnvelopeResult;

    // Create, send, and void another envelope
    $env2 = createBasicEnvelope($AccountID, "request status sample");
    $env2 = createEnvelopeWithDocumentAndTabs($env2);
    $createAndSendEnvelopeparams2 = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams2->Envelope = $env2;
    $createResult2 = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams2)->CreateAndSendEnvelopeResult;
    $voidEnvelopeparams = new VoidEnvelope();
    $voidEnvelopeparams->EnvelopeID = $createResult2->EnvelopeID;
    $voidEnvelopeparams->Reason = "void envelope for PurgeDocuments sample";
    $voidResult = $api->VoidEnvelope($voidEnvelopeparams);

    // Create a filter using account ID and today as a start time
    $envStatusFilter = new EnvelopeStatusFilter();
    $envStatusFilter->AccountId = $AccountID;

    $beginDateTime = new EnvelopeStatusFilterBeginDateTime();
    $beginDateTime->_ = todayXsdDate();
    $envStatusFilter->BeginDateTime = $beginDateTime;

    // Send
    $requestStatusesparams = new RequestStatuses();
    $requestStatusesparams->EnvelopeStatusFilter = $envStatusFilter;
    $response = $api->RequestStatuses($requestStatusesparams);

    return $response;
}

/**
 * request the status of multiple envelopes in a single call. Up to 200
 * envelopes can be retrieved
 * @return RequestStatusesExResponse
 */
function requestStatusesExSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env1 = createBasicEnvelope($AccountID, "request statuses 1 sample");
    $env1 = createEnvelopeWithDocumentAndTabs($env1);
    $createAndSendEnvelopeparams1 = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams1->Envelope = $env1;
    $createResult1 = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams1)->CreateAndSendEnvelopeResult;

    // Create, send, and void another envelope
    $env2 = createBasicEnvelope($AccountID, "request status sample");
    $env2 = createEnvelopeWithDocumentAndTabs($env2);
    $createAndSendEnvelopeparams2 = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams2->Envelope = $env2;
    $createResult2 = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams2)->CreateAndSendEnvelopeResult;
    $voidEnvelopeparams = new VoidEnvelope();
    $voidEnvelopeparams->EnvelopeID = $createResult2->EnvelopeID;
    $voidEnvelopeparams->Reason = "void envelope for PurgeDocuments sample";
    $voidResult = $api->VoidEnvelope($voidEnvelopeparams);

    // Create a filter using account ID and today as a start time
    $envStatusFilter = new EnvelopeStatusFilter();
    $envStatusFilter->AccountId = $AccountID;

    $beginDateTime = new EnvelopeStatusFilterBeginDateTime();
    $beginDateTime->_ = todayXsdDate();
    $envStatusFilter->BeginDateTime = $beginDateTime;

    // Send
    $requestStatusesExparams = new RequestStatusesEx();
    $requestStatusesExparams->EnvelopeStatusFilter = $envStatusFilter;
    $response = $api->RequestStatusesEx($requestStatusesExparams);

    return $response;
}

/**
 * Retrieves a specific template from the server
 * @return RequestTemplateResponse
 */
function requestTemplateSample() {
    global $api;
    global $AccountID;

    // Request all the templates this account has on file
    $requestTemplatesparams = new RequestTemplates();
    $requestTemplatesparams->AccountID = $AccountID;
    $requestTemplatesparams->IncludeAdvancedTemplates = false;
    $rtsResponses = $api->RequestTemplates($requestTemplatesparams)->RequestTemplatesResult->EnvelopeTemplateDefinition;

    // Send
    $requestTemplateparams = new RequestTemplate();
    $requestTemplateparams->IncludeDocumentBytes = false;
    $responses = array();
    foreach ($rtsResponses as $envTemplateDef) {
        $requestTemplateparams->TemplateID = $envTemplateDef->TemplateID;
        $response = $api->RequestTemplate($requestTemplateparams);
        array_push($responses, $response);
    }

    return $responses;
}

/**
 * returns a list of server side templates available for this account
 * @return RequestTemplatesResponse
 */
function requestTemplatesSample() {
    global $api;
    global $AccountID;

    // Send
    $requestTemplatesparams = new RequestTemplates();
    $requestTemplatesparams->AccountID = $AccountID;
    $requestTemplatesparams->IncludeAdvancedTemplates = false;
    $response = $api->RequestTemplates($requestTemplatesparams);

    return $response;
}

/**
 * Saves template
 * @return SaveTemplateResponse
 */
function saveTemplateSample() {
    global $api;
    global $AccountID;

    // Request all the templates this account has on file
    $requestTemplatesparams = new RequestTemplates();
    $requestTemplatesparams->AccountID = $AccountID;
    $requestTemplatesparams->IncludeAdvancedTemplates = false;
    $rtsResponses = $api->RequestTemplates($requestTemplatesparams)->RequestTemplatesResult->EnvelopeTemplateDefinition;

    // Make a new template from each template on file and save it
    $requestTemplateparams = new RequestTemplate();
    $requestTemplateparams->IncludeDocumentBytes = true;
    $responses = array();
    foreach ($rtsResponses as $envTemplateDef) {
        $requestTemplateparams->TemplateID = $envTemplateDef->TemplateID;
        $template = $api->RequestTemplate($requestTemplateparams)->RequestTemplateResult;
        $template->EnvelopeTemplateDefinition->Name = "Modified Name";
        $saveTemplateparams = new SaveTemplate();
        $saveTemplateparams->EnvelopeTemplate = $template;
        $response = $api->SaveTemplate($saveTemplateparams);
        array_push($responses, $response);
    }

    return $responses;
}

/**
 * sends draft envelopes
 * @return SendEnvelopeResponse
 */
function sendEnvelopeSample() {
    global $api;
    global $AccountID;

    // Create an envelope
    $env = createBasicEnvelope($AccountID, "send envelop sample");
    $env = createEnvelopeWithDocumentAndTabs($env);

    // Create the envelope on the server
    $createEnvelopeparams = new CreateEnvelope();
    $createEnvelopeparams->Envelope = $env;
    $envStatus = $api->CreateEnvelope($createEnvelopeparams)->CreateEnvelopeResult;

    // Send
    $sendEnvelopeparams = new SendEnvelope();
    $sendEnvelopeparams->AccountId = $AccountID;
    $sendEnvelopeparams->EnvelopeId = $envStatus->EnvelopeID;
    $response = $api->SendEnvelope($sendEnvelopeparams);

    return $response;
}

/**
 * This method is only useful when the ‘Asynchronous’ flag is set to true on a
 * CreateAndSendEnvelope call. It will determine when the queued envelope has
 * been processed by the system
 * @return SynchEnvelopeResponse
 */
function synchEnvelopeSample() {
    global $api;
    global $AccountID;

    // Create an envelope
    $env = createBasicEnvelope($AccountID, "synch envelop sample");
    $env = createEnvelopeWithDocumentAndTabs($env);

    // Assign a transaction ID and make it an asynchronous send
    $env->TransactionID = guid();
    $env->Asynchronous = true;

    // Send the envelope
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $status = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send without blocking
    $synchEnvelopeparams = new SynchEnvelope();
    $synchEnvelopeparams->AccountID = $AccountID;
    $synchEnvelopeparams->Block = false;
    $synchEnvelopeparams->TransactionID = $env->TransactionID;
    $response = $api->SynchEnvelope($synchEnvelopeparams);

    return $response;
}

/**
 * transfers all documents in the specified envelope to the new owner
 *
 * This needs to be a manual test. You can run this test by:
 * 1. Send an envelope and capture the envelope ID
 * 2. Paste the envelope ID into the first parameter
 * 3. Paste the account ID and user ID of the destination account (in GUID format)
 *    into the second and third parameters
 * 4. Call TransferEnvelope
 *
 * @return TransferEnvelopeResponse
 */
function transferEnvelopeSample() {
    global $api;

    // Request the envelope specified to be transferred to the account specified
    $transferEnvelopeparams = new TransferEnvelope();
        // TODO: replace string with account ID GUID that you will transfer the envelope to
    $transferEnvelopeparams->AccountID = "";
        // TODO: replace string with envelope ID GUID that will be transferred
    $transferEnvelopeparams->EnvelopeID = "";
        // TODO: replace string with user ID GUID that you will transfer the envelope to
    $transferEnvelopeparams->UserID = "";
    $response = $api->TransferEnvelope($transferEnvelopeparams);

    return $response;
}

/**
 * Updates and inserts all the specified items passed to your address book
 * @return UpdateAddressBookItemsResponse
 */
function updateAddressBookItemsSample() {
    global $api;
    global $AccountID;

    // Create an address book item with email, user name, account name, and
    // account ID
    $contact = new AddressBookItem();
    $contact->Email = "somename@somedomain.com";
    $contact->UserName = "Docusign Test";
    $contact->AccountName = "test account";
    $contact->Shared = true;
    $contact->AddressBookID = guid();
    $contacts = array($contact);

    // Send
    $updateAddressBookItemsparams = new UpdateAddressBookItems();
    $updateAddressBookItemsparams->AddressBookItems = $contacts;
    $updateAddressBookItemsparams->ReturnAddressBook = false;
    $response = $api->UpdateAddressBookItems($updateAddressBookItemsparams);

    return $response;
}

/**
 * Upload a template. Note that this method currently only supports DocuSign
 * Professional Template XML
 * @return UploadTemplateResponse
 */
function uploadTemplateSample() {
    global $api;
    global $AccountID;

    // Read the template that you want to upload
    $templateXML = file_get_contents("docs/autoInsuranceApplication.dpd");

    // Send
    $uploadTemplateparams = new UploadTemplate();
    $uploadTemplateparams->AccountID = $AccountID;
    $uploadTemplateparams->TemplateXML = $templateXML;
    $uploadTemplateparams->Shared = true;
    $response = $api->UploadTemplate($uploadTemplateparams);

    return $response;
}

/**
 * voidS envelopes. Only incomplete envelopes can be voided
 * @return VoidEnvelopeResponse
 */
function voidEnvelopeSample() {
    global $api;
    global $AccountID;

    // Create and send an envelope
    $env = createBasicEnvelope($AccountID, "void envelope sample");
    $env = createEnvelopeWithDocumentAndTabs($env);
    $createAndSendEnvelopeparams = new CreateAndSendEnvelope();
    $createAndSendEnvelopeparams->Envelope = $env;
    $status = $api->CreateAndSendEnvelope($createAndSendEnvelopeparams)->CreateAndSendEnvelopeResult;


    // Send
    $voidEnvelopeparams = new VoidEnvelope();
    $voidEnvelopeparams->EnvelopeID = $status->EnvelopeID;
    $voidEnvelopeparams->Reason = "void envelope sample";
    $response = $api->VoidEnvelope($voidEnvelopeparams);

    return $response;
}

function loginSample() {
    global $UserID, $Password, $IntegratorsKey;
    $options = array(
    	'location'=>"https://demo.docusign.net/api/3.0/credential.asmx",
        'trace'=>true,
        'features'=>SOAP_SINGLE_ELEMENT_ARRAYS);
    $credApi = new CredentialService("api/CredentialService.wsdl", $options);
    $loginParams = new Login();
    $loginParams->Email = "[" . $IntegratorsKey . "]" . $UserID;
    $loginParams->Password = $Password;

    $response = $credApi->Login($loginParams);

    return $response;
}

?>