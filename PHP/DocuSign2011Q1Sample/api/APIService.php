<?php
require_once("WSASoap.php");
require_once("WSSESoap.php");

class CreateEnvelope {
  public $Envelope; // Envelope
}

class Envelope {
  public $TransactionID; // string
  public $Asynchronous; // boolean
  public $AccountId; // string
  public $Documents; // ArrayOfDocument
  public $Recipients; // ArrayOfRecipient
  public $Tabs; // ArrayOfTab
  public $Subject; // string
  public $EmailBlurb; // string
  public $SigningLocation; // SigningLocationCode
  public $CustomFields; // ArrayOfCustomField
  public $VaultingOptions; // VaultingOptions
  public $AutoNavigation; // boolean
  public $EnvelopeIdStamping; // boolean
  public $AuthoritativeCopy; // boolean
  public $Notification; // Notification
  public $EnvelopeAttachment; // ArrayOfAttachment
  public $EnforceSignerVisibility; // boolean
  public $EnableWetSign; // boolean
  public $AllowMarkup; // boolean
  public $EventNotification; // EventNotification
}

class Document {
  public $ID; // positiveInteger
  public $Name; // string
  public $PDFBytes; // base64Binary
  public $Password; // string
  public $TransformPdfFields; // boolean
  public $FileExtension; // string
  public $MatchBoxes; // ArrayOfMatchBox
  public $AttachmentDescription; // string
}

class MatchBox {
  public $PageNumber; // positiveInteger
  public $XPosition; // int
  public $YPosition; // int
  public $Width; // int
  public $Height; // int
}

class Recipient {
  public $ID; // positiveInteger
  public $UserName; // string
  public $SignerName; // string
  public $Email; // string
  public $Type; // RecipientTypeCode
  public $AccessCode; // string
  public $AddAccessCodeToEmail; // boolean
  public $RequireIDLookup; // boolean
  public $IDCheckConfigurationName; // string
  public $PhoneAuthentication; // RecipientPhoneAuthentication
  public $SignatureInfo; // RecipientSignatureInfo
  public $CaptiveInfo; // RecipientCaptiveInfo
  public $CustomFields; // ArrayOfString1
  public $RoutingOrder; // unsignedShort
  public $IDCheckInformationInput; // IDCheckInformationInput
  public $AutoNavigation; // boolean
  public $RecipientAttachment; // ArrayOfAttachment
  public $Note; // string
  public $RoleName; // string
  public $TemplateLocked; // boolean
  public $TemplateRequired; // boolean
  public $TemplateAccessCodeRequired; // boolean
  public $DefaultRecipient; // boolean
}

class RecipientTypeCode {
  const Signer = 'Signer';
  const Agent = 'Agent';
  const Editor = 'Editor';
  const CarbonCopy = 'CarbonCopy';
  const CertifiedDelivery = 'CertifiedDelivery';
  const InPersonSigner = 'InPersonSigner';
}

class RecipientPhoneAuthentication {
  public $RecipMayProvideNumber; // boolean
  public $ValidateRecipProvidedNumber; // boolean
  public $RecordVoicePrint; // boolean
  public $SenderProvidedNumbers; // ArrayOfString
}

class RecipientSignatureInfo {
  public $SignatureName; // string
  public $SignatureInitials; // string
  public $FontStyle; // FontStyleCode
}

class FontStyleCode {
  const RageItalic = 'RageItalic';
  const Mistral = 'Mistral';
  const BradleyHandITC = 'BradleyHandITC';
  const KaufmannBT = 'KaufmannBT';
  const Freehand575 = 'Freehand575';
  const LuciaBT = 'LuciaBT';
  const DocuSign1 = 'DocuSign1';
  const DocuSign2 = 'DocuSign2';
  const DocuSign3 = 'DocuSign3';
  const DocuSign4 = 'DocuSign4';
  const DocuSign5 = 'DocuSign5';
  const DocuSign6 = 'DocuSign6';
  const DocuSign7 = 'DocuSign7';
  const DocuSign8 = 'DocuSign8';
}

class RecipientCaptiveInfo {
  public $ClientUserId; // string
}

class IDCheckInformationInput {
  public $AddressInformationInput; // AddressInformationInput
  public $DOBInformationInput; // DOBInformationInput
  public $SSN4InformationInput; // SSN4InformationInput
  public $SSN9InformationInput; // SSN9InformationInput
}

class AddressInformationInput {
  public $AddressInformation; // AddressInformation
  public $DisplayLevel; // DisplayLevelCode
  public $ReceiveInResponse; // boolean
}

class AddressInformation {
  public $Street1; // string
  public $Street2; // string
  public $City; // string
  public $State; // string
  public $Zip; // string
  public $ZipPlus4; // string
}

class DisplayLevelCode {
  const ReadOnly = 'ReadOnly';
  const Editable = 'Editable';
  const DoNotDisplay = 'DoNotDisplay';
}

class DOBInformationInput {
  public $DOBInformation; // DOBInformation
  public $DisplayLevel; // DisplayLevelCode
  public $ReceiveInResponse; // boolean
}

class DOBInformation {
  public $DOB; // dateTime
}

class SSN4InformationInput {
  public $SSN4Information; // SSN4Information
  public $DisplayLevel; // DisplayLevelCode
  public $ReceiveInResponse; // boolean
}

class SSN4Information {
  public $SSN4; // string
}

class SSN9InformationInput {
  public $SSN9Information; // SSN9Information
  public $DisplayLevel; // DisplayLevelCode
}

class SSN9Information {
  public $SSN9; // string
}

class Attachment {
  public $Data; // base64Binary
  public $Label; // string
  public $Type; // string
}

class Tab {
  public $DocumentID; // positiveInteger
  public $RecipientID; // positiveInteger
  public $PageNumber; // nonNegativeInteger
  public $XPosition; // nonNegativeInteger
  public $YPosition; // nonNegativeInteger
  public $ScaleValue; // decimal
  public $AnchorTabItem; // AnchorTab
  public $Type; // TabTypeCode
  public $Name; // string
  public $TabLabel; // string
  public $Value; // string
  public $CustomTabType; // CustomTabType
  public $CustomTabWidth; // int
  public $CustomTabHeight; // int
  public $CustomTabRequired; // boolean
  public $CustomTabLocked; // boolean
  public $CustomTabDisableAutoSize; // boolean
  public $CustomTabListItems; // string
  public $CustomTabListValues; // string
  public $CustomTabListSelectedValue; // string
  public $CustomTabRadioGroupName; // string
  public $CustomTabValidationPattern; // string
  public $CustomTabValidationMessage; // string
  public $TemplateLocked; // boolean
  public $TemplateRequired; // boolean
  public $ConditionalParentLabel; // string
  public $ConditionalParentValue; // string
  public $SharedTab; // boolean
  public $RequireInitialOnSharedTabChange; // boolean
  public $ConcealValueOnDocument; // boolean
}

class AnchorTab {
  public $AnchorTabString; // string
  public $XOffset; // double
  public $YOffset; // double
  public $Unit; // UnitTypeCode
  public $IgnoreIfNotPresent; // boolean
}

class UnitTypeCode {
  const Pixels = 'Pixels';
  const Mms = 'Mms';
  const Cms = 'Cms';
  const Inches = 'Inches';
}

class TabTypeCode {
  const InitialHere = 'InitialHere';
  const SignHere = 'SignHere';
  const FullName = 'FullName';
  const Company = 'Company';
  const Title = 'Title';
  const DateSigned = 'DateSigned';
  const InitialHereOptional = 'InitialHereOptional';
  const EnvelopeID = 'EnvelopeID';
  const Custom = 'Custom';
  const SignerAttachment = 'SignerAttachment';
  const SignHereOptional = 'SignHereOptional';
}

class CustomTabType {
  const Text = 'Text';
  const Checkbox = 'Checkbox';
  const Radio = 'Radio';
  const _List = 'List';
  const Date = 'Date';
  const Number = 'Number';
  const SSN = 'SSN';
  const ZIP5 = 'ZIP5';
  const ZIP5DASH4 = 'ZIP5DASH4';
  const Email = 'Email';
}

class SigningLocationCode {
  const InPerson = 'InPerson';
  const Online = 'Online';
}

class CustomField {
  public $Name; // string
  public $Show; // string
  public $Required; // string
  public $Value; // string
  public $CustomFieldType; // CustomFieldType
  public $ListItems; // string
}

class CustomFieldType {
  const Text = 'Text';
  const _List = 'List';
}

class VaultingOptions {
  public $VaultingMode; // VaultingModeCode
  public $EODTransactionName; // string
  public $EODDocumentName; // string
  public $EODDocumentDescription; // string
}

class VaultingModeCode {
  const None = 'None';
  const EODeStore = 'EODeStore';
  const EODAuthoritativeCopy = 'EODAuthoritativeCopy';
}

class Notification {
  public $UseAccountDefaults; // boolean
  public $Reminders; // Reminders
  public $Expirations; // Expirations
}

class Reminders {
  public $ReminderEnabled; // boolean
  public $ReminderDelay; // nonNegativeInteger
  public $ReminderFrequency; // nonNegativeInteger
}

class Expirations {
  public $ExpireEnabled; // boolean
  public $ExpireAfter; // nonNegativeInteger
  public $ExpireWarn; // nonNegativeInteger
}

class EventNotification {
  public $URL; // string
  public $LoggingEnabled; // boolean
  public $EnvelopeEvents; // ArrayOfEnvelopeEvent
}

class EnvelopeEvent {
  public $EnvelopeEventStatusCode; // EnvelopeEventStatusCode
  public $IncludeDocuments; // boolean
}

class EnvelopeEventStatusCode {
  const Sent = 'Sent';
  const Delivered = 'Delivered';
  const Completed = 'Completed';
  const Declined = 'Declined';
  const Voided = 'Voided';
}

class CreateEnvelopeResponse {
  public $CreateEnvelopeResult; // EnvelopeStatus
}

class EnvelopeStatus {
  public $RecipientStatuses; // ArrayOfRecipientStatus
  public $TimeGenerated; // dateTime
  public $EnvelopeID; // string
  public $Subject; // string
  public $UserName; // string
  public $Email; // string
  public $Status; // EnvelopeStatusCode
  public $Created; // dateTime
  public $Deleted; // dateTime
  public $Sent; // dateTime
  public $Delivered; // dateTime
  public $Signed; // dateTime
  public $Completed; // dateTime
  public $Declined; // dateTime
  public $TimedOut; // dateTime
  public $ACStatus; // string
  public $ACStatusDate; // dateTime
  public $ACHolder; // string
  public $ACHolderEmail; // string
  public $ACHolderLocation; // string
  public $SigningLocation; // SigningLocationCode
  public $SenderIPAddress; // string
  public $EnvelopePDFHash; // string
  public $CustomFields; // ArrayOfCustomField
  public $VaultingDetails; // VaultingDetails
  public $AutoNavigation; // boolean
  public $EnvelopeIdStamping; // boolean
  public $AuthoritativeCopy; // boolean
  public $EnvelopeAttachment; // ArrayOfAttachment
  public $DocumentStatuses; // ArrayOfDocumentStatus
  public $FormData; // FormData
}

class RecipientStatus {
  public $Type; // RecipientTypeCode
  public $Email; // string
  public $UserName; // string
  public $RoutingOrder; // unsignedShort
  public $Sent; // dateTime
  public $Delivered; // dateTime
  public $Signed; // dateTime
  public $Declined; // dateTime
  public $DeclineReason; // string
  public $Status; // RecipientStatusCode
  public $RecipientIPAddress; // string
  public $ClientUserId; // string
  public $AutoNavigation; // boolean
  public $IDCheckInformation; // IDCheckInformation
  public $RecipientAuthenticationStatus; // AuthenticationStatus
  public $CustomFields; // ArrayOfString1
  public $TabStatuses; // ArrayOfTabStatus
  public $RecipientAttachment; // ArrayOfAttachment
  public $AccountStatus; // string
  public $EsignAgreementInformation; // RecipientStatusEsignAgreementInformation
  public $FormData; // FormData
  public $RecipientId; // string
}

class RecipientStatusCode {
  const Created = 'Created';
  const Sent = 'Sent';
  const Delivered = 'Delivered';
  const Signed = 'Signed';
  const Declined = 'Declined';
  const Completed = 'Completed';
  const FaxPending = 'FaxPending';
}

class IDCheckInformation {
  public $AddressInformation; // AddressInformation
  public $DOBInformation; // DOBInformation
  public $SSN4Information; // SSN4Information
}

class AuthenticationStatus {
  public $AccessCodeResult; // EventResult
  public $IDQuestionsResult; // EventResult
  public $IDLookupResult; // EventResult
  public $AgeVerifyResult; // EventResult
  public $STANPinResult; // EventResult
  public $OFACResult; // EventResult
  public $PhoneAuthResult; // EventResult
}

class EventResult {
  public $Status; // EventStatusCode
  public $EventTimestamp; // dateTime
}

class EventStatusCode {
  const Passed = 'Passed';
  const Failed = 'Failed';
}

class TabStatus {
  public $TabType; // TabTypeCode
  public $Status; // string
  public $XPosition; // double
  public $YPosition; // double
  public $Signed; // dateTime
  public $TabLabel; // string
  public $TabName; // string
  public $TabValue; // string
  public $DocumentID; // positiveInteger
  public $PageNumber; // nonNegativeInteger
  public $OriginalValue; // string
  public $ValidationPattern; // string
  public $RoleName; // string
  public $ListValues; // string
  public $ListSelectedValue; // string
  public $ScaleValue; // decimal
  public $CustomTabType; // CustomTabType
}

class RecipientStatusEsignAgreementInformation {
  public $AccountEsignId; // string
  public $UserEsignId; // string
  public $AgreementDate; // dateTime
}

class FormData {
  public $xfdf; // FormDataXfdf
}

class FormDataXfdf {
  public $fields; // ArrayOfFormDataXfdfField
}

class FormDataXfdfField {
  public $value; // string
  public $name; // string
}

class EnvelopeStatusCode {
  const Any = 'Any';
  const Voided = 'Voided';
  const Created = 'Created';
  const Deleted = 'Deleted';
  const Sent = 'Sent';
  const Delivered = 'Delivered';
  const Signed = 'Signed';
  const Completed = 'Completed';
  const Declined = 'Declined';
  const TimedOut = 'TimedOut';
  const Template = 'Template';
  const Processing = 'Processing';
}

class VaultingDetails {
  public $EODTransactionName; // string
  public $EODTransactionID; // string
  public $EODDocumentProfileID; // string
}

class DocumentStatus {
  public $ID; // positiveInteger
  public $Name; // string
  public $TemplateName; // string
  public $Sequence; // positiveInteger
}

class CreateAndSendEnvelope {
  public $Envelope; // Envelope
}

class CreateAndSendEnvelopeResponse {
  public $CreateAndSendEnvelopeResult; // EnvelopeStatus
}

class SendEnvelope {
  public $EnvelopeId; // string
  public $AccountId; // string
}

class SendEnvelopeResponse {
  public $SendEnvelopeResult; // EnvelopeStatus
}

class CorrectAndResendEnvelope {
  public $Correction; // Correction
}

class Correction {
  public $EnvelopeID; // string
  public $EnvelopeSettingsCorrection; // EnvelopeSettings
  public $RecipientCorrections; // ArrayOfRecipientCorrection
}

class EnvelopeSettings {
  public $AutoNavigation; // boolean
  public $EnvelopeIdStamping; // boolean
}

class RecipientCorrection {
  public $PreviousUserName; // string
  public $PreviousEmail; // string
  public $PreviousRoutingOrder; // unsignedShort
  public $PreviousSignerName; // string
  public $CorrectedUserName; // string
  public $CorrectedSignerName; // string
  public $CorrectedEmail; // string
  public $CorrectedCaptiveInfo; // RecipientCorrectionCorrectedCaptiveInfo
  public $CorrectedAccessCode; // string
  public $CorrectedAccessCodeRequired; // boolean
  public $CorrectedRequireIDLookup; // boolean
  public $CorrectedIDCheckConfigurationName; // string
  public $CorrectedRoutingOrder; // unsignedShort
  public $CorrectedAutoNavigation; // boolean
  public $CorrectedIDCheckInformationInput; // IDCheckInformationInput
  public $Resend; // boolean
}

class RecipientCorrectionCorrectedCaptiveInfo {
  public $ClientUserId; // string
}

class CorrectAndResendEnvelopeResponse {
  public $CorrectAndResendEnvelopeResult; // CorrectionStatus
}

class CorrectionStatus {
  public $EnvelopeSettingsCorrectionStatus; // EnvelopeSettings
  public $RecipientCorrectionStatuses; // ArrayOfRecipientCorrectionStatus
}

class RecipientCorrectionStatus {
  public $CorrectionSucceeded; // boolean
  public $RecipientCorrection; // RecipientCorrection
  public $RecipientStatus; // RecipientStatus
}

class RequestPDFNoWaterMark {
  public $EnvelopeID; // string
}

class RequestPDFNoWaterMarkResponse {
  public $RequestPDFNoWaterMarkResult; // EnvelopePDF
}

class EnvelopePDF {
  public $EnvelopeID; // string
  public $PDFBytes; // base64Binary
}

class RequestPDF {
  public $EnvelopeID; // string
}

class RequestPDFResponse {
  public $RequestPDFResult; // EnvelopePDF
}

class RequestPDFWithCert {
  public $EnvelopeID; // string
  public $AddWaterMark; // boolean
}

class RequestPDFWithCertResponse {
  public $RequestPDFWithCertResult; // EnvelopePDF
}

class RequestDocumentPDFs {
  public $EnvelopeID; // string
}

class RequestDocumentPDFsResponse {
  public $RequestDocumentPDFsResult; // DocumentPDFs
}

class DocumentPDFs {
  public $EnvelopeId; // string
  public $DocumentPDF; // DocumentPDF
}

class DocumentPDF {
  public $Name; // string
  public $PDFBytes; // base64Binary
  public $DocumentID; // positiveInteger
  public $DocumentType; // DocumentType
}

class DocumentType {
  const SUMMARY = 'SUMMARY';
  const CONTENT = 'CONTENT';
}

class RequestDocumentPDFsEx {
  public $EnvelopeID; // string
}

class RequestDocumentPDFsExResponse {
  public $RequestDocumentPDFsExResult; // DocumentPDFs
}

class RequestDocumentPDFsRecipientsView {
  public $EnvelopeID; // string
  public $RecipientName; // string
  public $RecipientEmail; // string
}

class RequestDocumentPDFsRecipientsViewResponse {
  public $RequestDocumentPDFsRecipientsViewResult; // DocumentPDFs
}

class RequestStatusEx {
  public $EnvelopeID; // string
}

class RequestStatusExResponse {
  public $RequestStatusExResult; // EnvelopeStatus
}

class RequestStatus {
  public $EnvelopeID; // string
}

class RequestStatusResponse {
  public $RequestStatusResult; // EnvelopeStatus
}

class RequestStatusCodes {
  public $EnvelopeStatusFilter; // EnvelopeStatusFilter
}

class EnvelopeStatusFilter {
  public $UserInfo; // UserInfo
  public $AccountId; // string
  public $BeginDateTime; // EnvelopeStatusFilterBeginDateTime
  public $EndDateTime; // dateTime
  public $Statuses; // ArrayOfEnvelopeStatusCode
  public $EnvelopeIds; // ArrayOfString2
  public $StartAtIndex; // nonNegativeInteger
  public $ACStatus; // EnvelopeACStatusCode
}

class UserInfo {
  public $UserName; // string
  public $Email; // string
}

class EnvelopeStatusFilterBeginDateTime {
  public $_; // dateTime
  public $statusQualifier; // string
}

class EnvelopeACStatusCode {
  const Unknown = 'Unknown';
  const Original = 'Original';
  const Transferred = 'Transferred';
  const AuthoritativeCopy = 'AuthoritativeCopy';
  const AuthoritativeCopyExportPending = 'AuthoritativeCopyExportPending';
  const AuthoritativeCopyExported = 'AuthoritativeCopyExported';
  const DepositPending = 'DepositPending';
  const Deposited = 'Deposited';
  const DepositedEO = 'DepositedEO';
  const DepositFailed = 'DepositFailed';
}

class RequestStatusCodesResponse {
  public $RequestStatusCodesResult; // FilteredEnvelopeStatusChanges
}

class FilteredEnvelopeStatusChanges {
  public $ResultSetSize; // int
  public $EnvelopeStatusChanges; // ArrayOfEnvelopeStatusChange
}

class EnvelopeStatusChange {
  public $EnvelopeID; // string
  public $Status; // EnvelopeStatusCode
  public $StatusChanged; // dateTime
}

class RequestStatusChanges {
  public $EnvelopeStatusChangeFilter; // EnvelopeStatusChangeFilter
}

class EnvelopeStatusChangeFilter {
  public $AccountId; // string
  public $UserInfo; // UserInfo
  public $StatusChangedSince; // dateTime
  public $Statuses; // ArrayOfEnvelopeStatusCode
}

class RequestStatusChangesResponse {
  public $RequestStatusChangesResult; // FilteredEnvelopeStatusChanges
}

class RequestStatusesEx {
  public $EnvelopeStatusFilter; // EnvelopeStatusFilter
}

class RequestStatusesExResponse {
  public $RequestStatusesExResult; // FilteredEnvelopeStatuses
}

class FilteredEnvelopeStatuses {
  public $ResultSetSize; // int
  public $EnvelopeStatusFilter; // EnvelopeStatusFilter
  public $EnvelopeStatuses; // ArrayOfEnvelopeStatus
}

class RequestStatuses {
  public $EnvelopeStatusFilter; // EnvelopeStatusFilter
}

class RequestStatusesResponse {
  public $RequestStatusesResult; // FilteredEnvelopeStatuses
}

class GetRecipientEsignList {
  public $UserName; // string
  public $SenderEmail; // string
  public $SenderAccountId; // string
  public $RecipientEmail; // string
}

class GetRecipientEsignListResponse {
  public $GetRecipientEsignListResult; // RecipientEsignList
}

class RecipientEsignList {
  public $AccountId; // string
  public $RecipientEsign; // ArrayOfRecipientEsign
}

class RecipientEsign {
  public $UserName; // string
  public $Email; // string
  public $Esign; // boolean
  public $ReservedRecipientEmail; // boolean
  public $ReservedRecipientNames; // string
}

class GetRecipientList {
  public $SenderAccountId; // string
  public $RecipientEmail; // string
}

class GetRecipientListResponse {
  public $GetRecipientListResult; // RecipientList
}

class RecipientList {
  public $ReservedRecipientEmail; // boolean
  public $MultipleUsers; // boolean
  public $RecipientName; // ArrayOfString3
}

class VoidEnvelope {
  public $EnvelopeID; // string
  public $Reason; // string
}

class VoidEnvelopeResponse {
  public $VoidEnvelopeResult; // VoidEnvelopeStatus
}

class VoidEnvelopeStatus {
  public $VoidSuccess; // boolean
}

class RequestRecipientToken {
  public $EnvelopeID; // string
  public $ClientUserID; // string
  public $Username; // string
  public $Email; // string
  public $AuthenticationAssertion; // RequestRecipientTokenAuthenticationAssertion
  public $ClientURLs; // RequestRecipientTokenClientURLs
}

class RequestRecipientTokenAuthenticationAssertion {
  public $AssertionID; // string
  public $AuthenticationInstant; // dateTime
  public $AuthenticationMethod; // RequestRecipientTokenAuthenticationAssertionAuthenticationMethod
  public $SecurityDomain; // string
}

class RequestRecipientTokenAuthenticationAssertionAuthenticationMethod {
  const Password = 'Password';
  const Email = 'Email';
  const PaperDocuments = 'PaperDocuments';
  const HTTPBasicAuth = 'HTTPBasicAuth';
  const SSLMutualAuth = 'SSLMutualAuth';
  const X509Certificate = 'X509Certificate';
  const Kerberos = 'Kerberos';
  const SingleSignOn_CASiteminder = 'SingleSignOn_CASiteminder';
  const SingleSignOn_InfoCard = 'SingleSignOn_InfoCard';
  const SingleSignOn_MicrosoftActiveDirectory = 'SingleSignOn_MicrosoftActiveDirectory';
  const SingleSignOn_Passport = 'SingleSignOn_Passport';
  const SingleSignOn_SAML = 'SingleSignOn_SAML';
  const SingleSignOn_Other = 'SingleSignOn_Other';
  const Smartcard = 'Smartcard';
  const RSASecureID = 'RSASecureID';
  const Biometric = 'Biometric';
  const None = 'None';
  const KnowledgeBasedAuth = 'KnowledgeBasedAuth';
}

class RequestRecipientTokenClientURLs {
  public $OnSigningComplete; // string
  public $OnViewingComplete; // string
  public $OnCancel; // string
  public $OnDecline; // string
  public $OnSessionTimeout; // string
  public $OnTTLExpired; // string
  public $OnException; // string
  public $OnAccessCodeFailed; // string
  public $OnIdCheckFailed; // string
  public $OnFaxPending; // string
  public $GenerateSignedDocumentAsynch; // boolean
}

class RequestRecipientTokenResponse {
  public $RequestRecipientTokenResult; // string
}

class TransferEnvelope {
  public $EnvelopeID; // string
  public $AccountID; // string
  public $UserID; // string
}

class TransferEnvelopeResponse {
  public $TransferEnvelopeResult; // TransferEnvelopeStatus
}

class TransferEnvelopeStatus {
  public $TransferEnvelopeSuccess; // boolean
}

class GetAccountMembershipFeaturesList {
  public $AccountId; // string
}

class GetAccountMembershipFeaturesListResponse {
  public $GetAccountMembershipFeaturesListResult; // AccountMembershipFeaturesList
}

class AccountMembershipFeaturesList {
  public $Email; // string
  public $UserName; // string
  public $EnabledFeaturesSet; // ArrayOfString4
}

class GetAccountSettingsList {
  public $AccountId; // string
}

class GetAccountSettingsListResponse {
  public $GetAccountSettingsListResult; // AccountSettingsList
}

class AccountSettingsList {
  public $AccountSetting; // AccountSetting
}

class AccountSetting {
  public $Name; // string
  public $Value; // string
  public $Type; // string
  public $TestSetting; // string
}

class AcknowledgeAuthoritativeCopyExport {
  public $EnvelopeId; // string
  public $TransactionId; // string
  public $checkSumHash; // base64Binary
}

class AcknowledgeAuthoritativeCopyExportResponse {
  public $AcknowledgeAuthoritativeCopyExportResult; // AuthoritativeCopyExportStatus
}

class AuthoritativeCopyExportStatus {
  public $AuthoritativeCopyExportSuccess; // boolean
  public $EnvelopeId; // string
  public $ExportKey; // string
}

class ExportAuthoritativeCopy {
  public $EnvelopeId; // string
}

class ExportAuthoritativeCopyResponse {
  public $ExportAuthoritativeCopyResult; // AuthoritativeCopyExportDocuments
}

class AuthoritativeCopyExportDocuments {
  public $EnvelopeId; // string
  public $TransactionId; // string
  public $Count; // int
  public $DocumentPDF; // DocumentPDF
}

class EnvelopeAuditEvents {
  public $EnvelopeId; // string
}

class EnvelopeAuditEventsResponse {
  public $EnvelopeAuditEventsResult; // EnvelopeAuditEventsResult
}

class EnvelopeAuditEventsResult {
  public $any; // <anyXML>
}

class Ping {
}

class PingResponse {
  public $PingResult; // boolean
}

class CreateEnvelopeFromTemplates {
  public $TemplateReferences; // ArrayOfTemplateReference
  public $Recipients; // ArrayOfRecipient1
  public $EnvelopeInformation; // EnvelopeInformation
  public $ActivateEnvelope; // boolean
}

class TemplateReference {
  public $TemplateLocation; // TemplateLocationCode
  public $Template; // string
  public $Document; // Document
  public $RoleAssignments; // ArrayOfTemplateReferenceRoleAssignment
  public $FieldData; // TemplateReferenceFieldData
  public $AdditionalTabs; // ArrayOfTab
  public $Sequence; // positiveInteger
  public $TemplateAttachments; // ArrayOfAttachment
}

class TemplateLocationCode {
  const SOAP = 'SOAP';
  const PDFMetaData = 'PDFMetaData';
  const Server = 'Server';
}

class TemplateReferenceRoleAssignment {
  public $RoleName; // string
  public $RecipientID; // positiveInteger
}

class TemplateReferenceFieldData {
  public $DataValues; // ArrayOfTemplateReferenceFieldDataDataValue
}

class TemplateReferenceFieldDataDataValue {
  public $TabLabel; // string
  public $Value; // string
}

class EnvelopeInformation {
  public $TransactionID; // string
  public $Asynchronous; // boolean
  public $AccountId; // string
  public $EmailBlurb; // string
  public $Subject; // string
  public $SigningLocation; // SigningLocationCode
  public $CustomFields; // ArrayOfCustomField
  public $VaultingOptions; // VaultingOptions
  public $AutoNavigation; // boolean
  public $EnvelopeIdStamping; // boolean
  public $AuthoritativeCopy; // boolean
  public $Notification; // Notification
  public $EnforceSignerVisibility; // boolean
  public $EnableWetSign; // boolean
  public $AllowRecipientRecursion; // boolean
  public $AllowMarkup; // boolean
  public $EventNotification; // EventNotification
}

class CreateEnvelopeFromTemplatesResponse {
  public $CreateEnvelopeFromTemplatesResult; // EnvelopeStatus
}

class CreateEnvelopeFromTemplatesAndForms {
  public $EnvelopeInformation; // EnvelopeInformation
  public $CompositeTemplates; // ArrayOfCompositeTemplate
  public $ActivateEnvelope; // boolean
}

class CompositeTemplate {
  public $ServerTemplates; // ArrayOfServerTemplate
  public $InlineTemplates; // ArrayOfInlineTemplate
  public $PDFMetaDataTemplate; // PDFMetaDataTemplate
  public $Document; // Document
}

class ServerTemplate {
  public $Sequence; // positiveInteger
  public $TemplateID; // string
}

class InlineTemplate {
  public $Sequence; // positiveInteger
  public $Envelope; // Envelope
}

class PDFMetaDataTemplate {
  public $Sequence; // positiveInteger
}

class CreateEnvelopeFromTemplatesAndFormsResponse {
  public $CreateEnvelopeFromTemplatesAndFormsResult; // EnvelopeStatus
}

class GetStatusInDocuSignConnectFormat {
  public $EnvelopeID; // string
}

class GetStatusInDocuSignConnectFormatResponse {
  public $GetStatusInDocuSignConnectFormatResult; // DocuSignEnvelopeInformation
}

class DocuSignEnvelopeInformation {
  public $EnvelopeStatus; // EnvelopeStatus
  public $DocumentPDFs; // ArrayOfDocumentPDF
}

class PurgeDocuments {
  public $EnvelopeID; // string
}

class PurgeDocumentsResponse {
  public $PurgeDocumentsResult; // PurgeDocumentStatus
}

class PurgeDocumentStatus {
  public $PurgeDocumentSuccess; // boolean
  public $PurgeDocumentError; // string
}

class SaveTemplate {
  public $EnvelopeTemplate; // EnvelopeTemplate
}

class EnvelopeTemplate {
  public $EnvelopeTemplateDefinition; // EnvelopeTemplateDefinition
  public $Envelope; // Envelope
}

class EnvelopeTemplateDefinition {
  public $TemplateID; // string
  public $Name; // string
  public $Shared; // boolean
  public $TemplatePassword; // string
  public $TemplateDescription; // string
  public $LastModified; // dateTime
  public $PageCount; // int
}

class SaveTemplateResponse {
  public $SaveTemplateResult; // SaveTemplateResult
}

class SaveTemplateResult {
  public $Success; // boolean
  public $TemplateID; // string
  public $Name; // string
}

class UploadTemplate {
  public $TemplateXML; // string
  public $AccountID; // string
  public $Shared; // boolean
}

class UploadTemplateResponse {
  public $UploadTemplateResult; // SaveTemplateResult
}

class RequestTemplates {
  public $AccountID; // string
  public $IncludeAdvancedTemplates; // boolean
}

class RequestTemplatesResponse {
  public $RequestTemplatesResult; // EnvelopeTemplates
}

class EnvelopeTemplates {
  public $EnvelopeTemplateDefinition; // EnvelopeTemplateDefinition
}

class RequestTemplate {
  public $TemplateID; // string
  public $IncludeDocumentBytes; // boolean
}

class RequestTemplateResponse {
  public $RequestTemplateResult; // EnvelopeTemplate
}

class GetAuthenticationToken {
  public $GoToEnvelopeID; // string
}

class GetAuthenticationTokenResponse {
  public $GetAuthenticationTokenResult; // string
}

class GetAddressBookItems {
  public $AccountID; // string
}

class GetAddressBookItemsResponse {
  public $GetAddressBookItemsResult; // ArrayOfAddressBookItem
}

class AddressBookItem {
  public $AddressBookID; // string
  public $Email; // string
  public $UserName; // string
  public $AccountName; // string
  public $Shared; // boolean
  public $Created; // dateTime
  public $Owner; // boolean
  public $Phone1; // AddressBookPhoneNumber
  public $Phone2; // AddressBookPhoneNumber
  public $Phone3; // AddressBookPhoneNumber
  public $Phone4; // AddressBookPhoneNumber
}

class AddressBookPhoneNumber {
  public $PhoneNumber; // string
  public $Designation; // PhoneNumberDesignation
}

class PhoneNumberDesignation {
  const Home = 'Home';
  const Mobile = 'Mobile';
  const Work = 'Work';
  const Other = 'Other';
}

class UpdateAddressBookItems {
  public $AddressBookItems; // ArrayOfAddressBookItem
  public $ReturnAddressBook; // boolean
}

class UpdateAddressBookItemsResponse {
  public $UpdateAddressBookItemsResult; // UpdateAddressBookResult
}

class UpdateAddressBookResult {
  public $Success; // boolean
  public $AddressBookItems; // ArrayOfAddressBookItem1
}

class RemoveAddressBookItems {
  public $AddressBookRemoveItems; // ArrayOfAddressBookRemoveItem
  public $ReturnAddressBook; // boolean
}

class AddressBookRemoveItem {
  public $AddressBookID; // string
}

class RemoveAddressBookItemsResponse {
  public $RemoveAddressBookItemsResult; // UpdateAddressBookResult
}

class SynchEnvelope {
  public $TransactionID; // string
  public $AccountID; // string
  public $Block; // boolean
}

class SynchEnvelopeResponse {
  public $SynchEnvelopeResult; // SynchEnvelopeStatus
}

class SynchEnvelopeStatus {
  public $EnvelopeStatus; // EnvelopeStatusCode
  public $EnvelopeID; // string
}

class RequestSenderToken {
  public $EnvelopeID; // string
  public $AccountID; // string
  public $ReturnURL; // string
}

class RequestSenderTokenResponse {
  public $RequestSenderTokenResult; // string
}

class RequestCorrectToken {
  public $EnvelopeID; // string
  public $SuppressNavigation; // boolean
  public $ReturnURL; // string
}

class RequestCorrectTokenResponse {
  public $RequestCorrectTokenResult; // string
}

class GetFolderItems {
  public $FolderFilter; // FolderFilter
}

class FolderFilter {
  public $AccountId; // string
  public $FolderOwner; // UserInfo
  public $FolderTypeInfo; // FolderTypeInfo
  public $StartPosition; // int
  public $FromDate; // dateTime
  public $ToDate; // dateTime
  public $SearchText; // string
  public $Status; // EnvelopeStatusCode
}

class FolderTypeInfo {
  public $FolderType; // FolderType
  public $FolderName; // string
}

class FolderType {
  const RecycleBin = 'RecycleBin';
  const Draft = 'Draft';
  const Inbox = 'Inbox';
  const SentItems = 'SentItems';
  const Normal = 'Normal';
}

class GetFolderItemsResponse {
  public $GetFolderItemsResult; // FolderResults
}

class FolderResults {
  public $ResultSetSize; // int
  public $StartPosition; // int
  public $EndPosition; // int
  public $FolderTypeInfo; // FolderTypeInfo
  public $FolderItems; // ArrayOfFolderItem
}

class FolderItem {
  public $EnvelopeId; // string
  public $Status; // EnvelopeStatusCode
  public $Owner; // string
  public $SenderName; // string
  public $SenderEmail; // string
  public $SenderCompany; // string
  public $RecipientStatuses; // ArrayOfRecipientStatus
  public $CustomFields; // ArrayOfCustomField
  public $Created; // dateTime
  public $Sent; // dateTime
  public $Completed; // dateTime
  public $Subject; // string
}

class GetFolderList {
  public $FoldersFilter; // FoldersFilter
}

class FoldersFilter {
  public $AccountId; // string
}

class GetFolderListResponse {
  public $GetFolderListResult; // AvailableFolders
}

class AvailableFolders {
  public $Folders; // ArrayOfFolder
}

class Folder {
  public $FolderOwner; // UserInfo
  public $FolderTypeInfo; // FolderTypeInfo
}

class RequestEnvelope {
  public $EnvelopeID; // string
  public $IncludeDocumentBytes; // boolean
}

class RequestEnvelopeResponse {
  public $RequestEnvelopeResult; // Envelope
}


/**
 * APIService class
 * 
 *  
 * 
 * @author    {author}
 * @copyright {copyright}
 * @package   {package}
 */
class APIService extends SoapClient {

  private static $classmap = array(
                                    'CreateEnvelope' => 'CreateEnvelope',
                                    'Envelope' => 'Envelope',
                                    'Document' => 'Document',
                                    'MatchBox' => 'MatchBox',
                                    'Recipient' => 'Recipient',
                                    'RecipientTypeCode' => 'RecipientTypeCode',
                                    'RecipientPhoneAuthentication' => 'RecipientPhoneAuthentication',
                                    'RecipientSignatureInfo' => 'RecipientSignatureInfo',
                                    'FontStyleCode' => 'FontStyleCode',
                                    'RecipientCaptiveInfo' => 'RecipientCaptiveInfo',
                                    'IDCheckInformationInput' => 'IDCheckInformationInput',
                                    'AddressInformationInput' => 'AddressInformationInput',
                                    'AddressInformation' => 'AddressInformation',
                                    'DisplayLevelCode' => 'DisplayLevelCode',
                                    'DOBInformationInput' => 'DOBInformationInput',
                                    'DOBInformation' => 'DOBInformation',
                                    'SSN4InformationInput' => 'SSN4InformationInput',
                                    'SSN4Information' => 'SSN4Information',
                                    'SSN9InformationInput' => 'SSN9InformationInput',
                                    'SSN9Information' => 'SSN9Information',
                                    'Attachment' => 'Attachment',
                                    'Tab' => 'Tab',
                                    'AnchorTab' => 'AnchorTab',
                                    'UnitTypeCode' => 'UnitTypeCode',
                                    'TabTypeCode' => 'TabTypeCode',
                                    'CustomTabType' => 'CustomTabType',
                                    'SigningLocationCode' => 'SigningLocationCode',
                                    'CustomField' => 'CustomField',
                                    'CustomFieldType' => 'CustomFieldType',
                                    'VaultingOptions' => 'VaultingOptions',
                                    'VaultingModeCode' => 'VaultingModeCode',
                                    'Notification' => 'Notification',
                                    'Reminders' => 'Reminders',
                                    'Expirations' => 'Expirations',
                                    'EventNotification' => 'EventNotification',
                                    'EnvelopeEvent' => 'EnvelopeEvent',
                                    'EnvelopeEventStatusCode' => 'EnvelopeEventStatusCode',
                                    'CreateEnvelopeResponse' => 'CreateEnvelopeResponse',
                                    'EnvelopeStatus' => 'EnvelopeStatus',
                                    'RecipientStatus' => 'RecipientStatus',
                                    'RecipientStatusCode' => 'RecipientStatusCode',
                                    'IDCheckInformation' => 'IDCheckInformation',
                                    'AuthenticationStatus' => 'AuthenticationStatus',
                                    'EventResult' => 'EventResult',
                                    'EventStatusCode' => 'EventStatusCode',
                                    'TabStatus' => 'TabStatus',
                                    'RecipientStatusEsignAgreementInformation' => 'RecipientStatusEsignAgreementInformation',
                                    'FormData' => 'FormData',
                                    'FormDataXfdf' => 'FormDataXfdf',
                                    'FormDataXfdfField' => 'FormDataXfdfField',
                                    'EnvelopeStatusCode' => 'EnvelopeStatusCode',
                                    'VaultingDetails' => 'VaultingDetails',
                                    'DocumentStatus' => 'DocumentStatus',
                                    'CreateAndSendEnvelope' => 'CreateAndSendEnvelope',
                                    'CreateAndSendEnvelopeResponse' => 'CreateAndSendEnvelopeResponse',
                                    'SendEnvelope' => 'SendEnvelope',
                                    'SendEnvelopeResponse' => 'SendEnvelopeResponse',
                                    'CorrectAndResendEnvelope' => 'CorrectAndResendEnvelope',
                                    'Correction' => 'Correction',
                                    'EnvelopeSettings' => 'EnvelopeSettings',
                                    'RecipientCorrection' => 'RecipientCorrection',
                                    'RecipientCorrectionCorrectedCaptiveInfo' => 'RecipientCorrectionCorrectedCaptiveInfo',
                                    'CorrectAndResendEnvelopeResponse' => 'CorrectAndResendEnvelopeResponse',
                                    'CorrectionStatus' => 'CorrectionStatus',
                                    'RecipientCorrectionStatus' => 'RecipientCorrectionStatus',
                                    'RequestPDFNoWaterMark' => 'RequestPDFNoWaterMark',
                                    'RequestPDFNoWaterMarkResponse' => 'RequestPDFNoWaterMarkResponse',
                                    'EnvelopePDF' => 'EnvelopePDF',
                                    'RequestPDF' => 'RequestPDF',
                                    'RequestPDFResponse' => 'RequestPDFResponse',
                                    'RequestPDFWithCert' => 'RequestPDFWithCert',
                                    'RequestPDFWithCertResponse' => 'RequestPDFWithCertResponse',
                                    'RequestDocumentPDFs' => 'RequestDocumentPDFs',
                                    'RequestDocumentPDFsResponse' => 'RequestDocumentPDFsResponse',
                                    'DocumentPDFs' => 'DocumentPDFs',
                                    'DocumentPDF' => 'DocumentPDF',
                                    'DocumentType' => 'DocumentType',
                                    'RequestDocumentPDFsEx' => 'RequestDocumentPDFsEx',
                                    'RequestDocumentPDFsExResponse' => 'RequestDocumentPDFsExResponse',
                                    'RequestDocumentPDFsRecipientsView' => 'RequestDocumentPDFsRecipientsView',
                                    'RequestDocumentPDFsRecipientsViewResponse' => 'RequestDocumentPDFsRecipientsViewResponse',
                                    'RequestStatusEx' => 'RequestStatusEx',
                                    'RequestStatusExResponse' => 'RequestStatusExResponse',
                                    'RequestStatus' => 'RequestStatus',
                                    'RequestStatusResponse' => 'RequestStatusResponse',
                                    'RequestStatusCodes' => 'RequestStatusCodes',
                                    'EnvelopeStatusFilter' => 'EnvelopeStatusFilter',
                                    'UserInfo' => 'UserInfo',
                                    'EnvelopeStatusFilterBeginDateTime' => 'EnvelopeStatusFilterBeginDateTime',
                                    'EnvelopeACStatusCode' => 'EnvelopeACStatusCode',
                                    'RequestStatusCodesResponse' => 'RequestStatusCodesResponse',
                                    'FilteredEnvelopeStatusChanges' => 'FilteredEnvelopeStatusChanges',
                                    'EnvelopeStatusChange' => 'EnvelopeStatusChange',
                                    'RequestStatusChanges' => 'RequestStatusChanges',
                                    'EnvelopeStatusChangeFilter' => 'EnvelopeStatusChangeFilter',
                                    'RequestStatusChangesResponse' => 'RequestStatusChangesResponse',
                                    'RequestStatusesEx' => 'RequestStatusesEx',
                                    'RequestStatusesExResponse' => 'RequestStatusesExResponse',
                                    'FilteredEnvelopeStatuses' => 'FilteredEnvelopeStatuses',
                                    'RequestStatuses' => 'RequestStatuses',
                                    'RequestStatusesResponse' => 'RequestStatusesResponse',
                                    'GetRecipientEsignList' => 'GetRecipientEsignList',
                                    'GetRecipientEsignListResponse' => 'GetRecipientEsignListResponse',
                                    'RecipientEsignList' => 'RecipientEsignList',
                                    'RecipientEsign' => 'RecipientEsign',
                                    'GetRecipientList' => 'GetRecipientList',
                                    'GetRecipientListResponse' => 'GetRecipientListResponse',
                                    'RecipientList' => 'RecipientList',
                                    'VoidEnvelope' => 'VoidEnvelope',
                                    'VoidEnvelopeResponse' => 'VoidEnvelopeResponse',
                                    'VoidEnvelopeStatus' => 'VoidEnvelopeStatus',
                                    'RequestRecipientToken' => 'RequestRecipientToken',
                                    'RequestRecipientTokenAuthenticationAssertion' => 'RequestRecipientTokenAuthenticationAssertion',
                                    'RequestRecipientTokenAuthenticationAssertionAuthenticationMethod' => 'RequestRecipientTokenAuthenticationAssertionAuthenticationMethod',
                                    'RequestRecipientTokenClientURLs' => 'RequestRecipientTokenClientURLs',
                                    'RequestRecipientTokenResponse' => 'RequestRecipientTokenResponse',
                                    'TransferEnvelope' => 'TransferEnvelope',
                                    'TransferEnvelopeResponse' => 'TransferEnvelopeResponse',
                                    'TransferEnvelopeStatus' => 'TransferEnvelopeStatus',
                                    'GetAccountMembershipFeaturesList' => 'GetAccountMembershipFeaturesList',
                                    'GetAccountMembershipFeaturesListResponse' => 'GetAccountMembershipFeaturesListResponse',
                                    'AccountMembershipFeaturesList' => 'AccountMembershipFeaturesList',
                                    'GetAccountSettingsList' => 'GetAccountSettingsList',
                                    'GetAccountSettingsListResponse' => 'GetAccountSettingsListResponse',
                                    'AccountSettingsList' => 'AccountSettingsList',
                                    'AccountSetting' => 'AccountSetting',
                                    'AcknowledgeAuthoritativeCopyExport' => 'AcknowledgeAuthoritativeCopyExport',
                                    'AcknowledgeAuthoritativeCopyExportResponse' => 'AcknowledgeAuthoritativeCopyExportResponse',
                                    'AuthoritativeCopyExportStatus' => 'AuthoritativeCopyExportStatus',
                                    'ExportAuthoritativeCopy' => 'ExportAuthoritativeCopy',
                                    'ExportAuthoritativeCopyResponse' => 'ExportAuthoritativeCopyResponse',
                                    'AuthoritativeCopyExportDocuments' => 'AuthoritativeCopyExportDocuments',
                                    'EnvelopeAuditEvents' => 'EnvelopeAuditEvents',
                                    'EnvelopeAuditEventsResponse' => 'EnvelopeAuditEventsResponse',
                                    'EnvelopeAuditEventsResult' => 'EnvelopeAuditEventsResult',
                                    'Ping' => 'Ping',
                                    'PingResponse' => 'PingResponse',
                                    'CreateEnvelopeFromTemplates' => 'CreateEnvelopeFromTemplates',
                                    'TemplateReference' => 'TemplateReference',
                                    'TemplateLocationCode' => 'TemplateLocationCode',
                                    'TemplateReferenceRoleAssignment' => 'TemplateReferenceRoleAssignment',
                                    'TemplateReferenceFieldData' => 'TemplateReferenceFieldData',
                                    'TemplateReferenceFieldDataDataValue' => 'TemplateReferenceFieldDataDataValue',
                                    'EnvelopeInformation' => 'EnvelopeInformation',
                                    'CreateEnvelopeFromTemplatesResponse' => 'CreateEnvelopeFromTemplatesResponse',
                                    'CreateEnvelopeFromTemplatesAndForms' => 'CreateEnvelopeFromTemplatesAndForms',
                                    'CompositeTemplate' => 'CompositeTemplate',
                                    'ServerTemplate' => 'ServerTemplate',
                                    'InlineTemplate' => 'InlineTemplate',
                                    'PDFMetaDataTemplate' => 'PDFMetaDataTemplate',
                                    'CreateEnvelopeFromTemplatesAndFormsResponse' => 'CreateEnvelopeFromTemplatesAndFormsResponse',
                                    'GetStatusInDocuSignConnectFormat' => 'GetStatusInDocuSignConnectFormat',
                                    'GetStatusInDocuSignConnectFormatResponse' => 'GetStatusInDocuSignConnectFormatResponse',
                                    'DocuSignEnvelopeInformation' => 'DocuSignEnvelopeInformation',
                                    'PurgeDocuments' => 'PurgeDocuments',
                                    'PurgeDocumentsResponse' => 'PurgeDocumentsResponse',
                                    'PurgeDocumentStatus' => 'PurgeDocumentStatus',
                                    'SaveTemplate' => 'SaveTemplate',
                                    'EnvelopeTemplate' => 'EnvelopeTemplate',
                                    'EnvelopeTemplateDefinition' => 'EnvelopeTemplateDefinition',
                                    'SaveTemplateResponse' => 'SaveTemplateResponse',
                                    'SaveTemplateResult' => 'SaveTemplateResult',
                                    'UploadTemplate' => 'UploadTemplate',
                                    'UploadTemplateResponse' => 'UploadTemplateResponse',
                                    'RequestTemplates' => 'RequestTemplates',
                                    'RequestTemplatesResponse' => 'RequestTemplatesResponse',
                                    'EnvelopeTemplates' => 'EnvelopeTemplates',
                                    'RequestTemplate' => 'RequestTemplate',
                                    'RequestTemplateResponse' => 'RequestTemplateResponse',
                                    'GetAuthenticationToken' => 'GetAuthenticationToken',
                                    'GetAuthenticationTokenResponse' => 'GetAuthenticationTokenResponse',
                                    'GetAddressBookItems' => 'GetAddressBookItems',
                                    'GetAddressBookItemsResponse' => 'GetAddressBookItemsResponse',
                                    'AddressBookItem' => 'AddressBookItem',
                                    'AddressBookPhoneNumber' => 'AddressBookPhoneNumber',
                                    'PhoneNumberDesignation' => 'PhoneNumberDesignation',
                                    'UpdateAddressBookItems' => 'UpdateAddressBookItems',
                                    'UpdateAddressBookItemsResponse' => 'UpdateAddressBookItemsResponse',
                                    'UpdateAddressBookResult' => 'UpdateAddressBookResult',
                                    'RemoveAddressBookItems' => 'RemoveAddressBookItems',
                                    'AddressBookRemoveItem' => 'AddressBookRemoveItem',
                                    'RemoveAddressBookItemsResponse' => 'RemoveAddressBookItemsResponse',
                                    'SynchEnvelope' => 'SynchEnvelope',
                                    'SynchEnvelopeResponse' => 'SynchEnvelopeResponse',
                                    'SynchEnvelopeStatus' => 'SynchEnvelopeStatus',
                                    'RequestSenderToken' => 'RequestSenderToken',
                                    'RequestSenderTokenResponse' => 'RequestSenderTokenResponse',
                                    'RequestCorrectToken' => 'RequestCorrectToken',
                                    'RequestCorrectTokenResponse' => 'RequestCorrectTokenResponse',
                                    'GetFolderItems' => 'GetFolderItems',
                                    'FolderFilter' => 'FolderFilter',
                                    'FolderTypeInfo' => 'FolderTypeInfo',
                                    'FolderType' => 'FolderType',
                                    'GetFolderItemsResponse' => 'GetFolderItemsResponse',
                                    'FolderResults' => 'FolderResults',
                                    'FolderItem' => 'FolderItem',
                                    'GetFolderList' => 'GetFolderList',
                                    'FoldersFilter' => 'FoldersFilter',
                                    'GetFolderListResponse' => 'GetFolderListResponse',
                                    'AvailableFolders' => 'AvailableFolders',
                                    'Folder' => 'Folder',
                                    'RequestEnvelope' => 'RequestEnvelope',
                                    'RequestEnvelopeResponse' => 'RequestEnvelopeResponse',
                                   );

  public function APIService($wsdl = "APIService.wsdl", $options = array()) {
    foreach(self::$classmap as $key => $value) {
      if(!isset($options['classmap'][$key])) {
        $options['classmap'][$key] = $value;
      }
    }
    parent::__construct($wsdl, $options);
  }

  /**
   *  
   *
   * @param CreateEnvelope $parameters
   * @return CreateEnvelopeResponse
   */
  public function CreateEnvelope(CreateEnvelope $parameters) {
    return $this->__soapCall('CreateEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param CreateAndSendEnvelope $parameters
   * @return CreateAndSendEnvelopeResponse
   */
  public function CreateAndSendEnvelope(CreateAndSendEnvelope $parameters) {
    return $this->__soapCall('CreateAndSendEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param SendEnvelope $parameters
   * @return SendEnvelopeResponse
   */
  public function SendEnvelope(SendEnvelope $parameters) {
    return $this->__soapCall('SendEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param CorrectAndResendEnvelope $parameters
   * @return CorrectAndResendEnvelopeResponse
   */
  public function CorrectAndResendEnvelope(CorrectAndResendEnvelope $parameters) {
    return $this->__soapCall('CorrectAndResendEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestPDFNoWaterMark $parameters
   * @return RequestPDFNoWaterMarkResponse
   */
  public function RequestPDFNoWaterMark(RequestPDFNoWaterMark $parameters) {
    return $this->__soapCall('RequestPDFNoWaterMark', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestPDF $parameters
   * @return RequestPDFResponse
   */
  public function RequestPDF(RequestPDF $parameters) {
    return $this->__soapCall('RequestPDF', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestPDFWithCert $parameters
   * @return RequestPDFWithCertResponse
   */
  public function RequestPDFWithCert(RequestPDFWithCert $parameters) {
    return $this->__soapCall('RequestPDFWithCert', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestDocumentPDFs $parameters
   * @return RequestDocumentPDFsResponse
   */
  public function RequestDocumentPDFs(RequestDocumentPDFs $parameters) {
    return $this->__soapCall('RequestDocumentPDFs', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestDocumentPDFsEx $parameters
   * @return RequestDocumentPDFsExResponse
   */
  public function RequestDocumentPDFsEx(RequestDocumentPDFsEx $parameters) {
    return $this->__soapCall('RequestDocumentPDFsEx', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestDocumentPDFsRecipientsView $parameters
   * @return RequestDocumentPDFsRecipientsViewResponse
   */
  public function RequestDocumentPDFsRecipientsView(RequestDocumentPDFsRecipientsView $parameters) {
    return $this->__soapCall('RequestDocumentPDFsRecipientsView', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatusEx $parameters
   * @return RequestStatusExResponse
   */
  public function RequestStatusEx(RequestStatusEx $parameters) {
    return $this->__soapCall('RequestStatusEx', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatus $parameters
   * @return RequestStatusResponse
   */
  public function RequestStatus(RequestStatus $parameters) {
    return $this->__soapCall('RequestStatus', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatusCodes $parameters
   * @return RequestStatusCodesResponse
   */
  public function RequestStatusCodes(RequestStatusCodes $parameters) {
    return $this->__soapCall('RequestStatusCodes', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatusChanges $parameters
   * @return RequestStatusChangesResponse
   */
  public function RequestStatusChanges(RequestStatusChanges $parameters) {
    return $this->__soapCall('RequestStatusChanges', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatusesEx $parameters
   * @return RequestStatusesExResponse
   */
  public function RequestStatusesEx(RequestStatusesEx $parameters) {
    return $this->__soapCall('RequestStatusesEx', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestStatuses $parameters
   * @return RequestStatusesResponse
   */
  public function RequestStatuses(RequestStatuses $parameters) {
    return $this->__soapCall('RequestStatuses', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetRecipientEsignList $parameters
   * @return GetRecipientEsignListResponse
   */
  public function GetRecipientEsignList(GetRecipientEsignList $parameters) {
    return $this->__soapCall('GetRecipientEsignList', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetRecipientList $parameters
   * @return GetRecipientListResponse
   */
  public function GetRecipientList(GetRecipientList $parameters) {
    return $this->__soapCall('GetRecipientList', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param VoidEnvelope $parameters
   * @return VoidEnvelopeResponse
   */
  public function VoidEnvelope(VoidEnvelope $parameters) {
    return $this->__soapCall('VoidEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestRecipientToken $parameters
   * @return RequestRecipientTokenResponse
   */
  public function RequestRecipientToken(RequestRecipientToken $parameters) {
    return $this->__soapCall('RequestRecipientToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param TransferEnvelope $parameters
   * @return TransferEnvelopeResponse
   */
  public function TransferEnvelope(TransferEnvelope $parameters) {
    return $this->__soapCall('TransferEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetAccountMembershipFeaturesList $parameters
   * @return GetAccountMembershipFeaturesListResponse
   */
  public function GetAccountMembershipFeaturesList(GetAccountMembershipFeaturesList $parameters) {
    return $this->__soapCall('GetAccountMembershipFeaturesList', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetAccountSettingsList $parameters
   * @return GetAccountSettingsListResponse
   */
  public function GetAccountSettingsList(GetAccountSettingsList $parameters) {
    return $this->__soapCall('GetAccountSettingsList', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param AcknowledgeAuthoritativeCopyExport $parameters
   * @return AcknowledgeAuthoritativeCopyExportResponse
   */
  public function AcknowledgeAuthoritativeCopyExport(AcknowledgeAuthoritativeCopyExport $parameters) {
    return $this->__soapCall('AcknowledgeAuthoritativeCopyExport', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param ExportAuthoritativeCopy $parameters
   * @return ExportAuthoritativeCopyResponse
   */
  public function ExportAuthoritativeCopy(ExportAuthoritativeCopy $parameters) {
    return $this->__soapCall('ExportAuthoritativeCopy', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param EnvelopeAuditEvents $parameters
   * @return EnvelopeAuditEventsResponse
   */
  public function EnvelopeAuditEvents(EnvelopeAuditEvents $parameters) {
    return $this->__soapCall('EnvelopeAuditEvents', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param Ping $parameters
   * @return PingResponse
   */
  public function Ping(Ping $parameters) {
    return $this->__soapCall('Ping', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param CreateEnvelopeFromTemplates $parameters
   * @return CreateEnvelopeFromTemplatesResponse
   */
  public function CreateEnvelopeFromTemplates(CreateEnvelopeFromTemplates $parameters) {
    return $this->__soapCall('CreateEnvelopeFromTemplates', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param CreateEnvelopeFromTemplatesAndForms $parameters
   * @return CreateEnvelopeFromTemplatesAndFormsResponse
   */
  public function CreateEnvelopeFromTemplatesAndForms(CreateEnvelopeFromTemplatesAndForms $parameters) {
    return $this->__soapCall('CreateEnvelopeFromTemplatesAndForms', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetStatusInDocuSignConnectFormat $parameters
   * @return GetStatusInDocuSignConnectFormatResponse
   */
  public function GetStatusInDocuSignConnectFormat(GetStatusInDocuSignConnectFormat $parameters) {
    return $this->__soapCall('GetStatusInDocuSignConnectFormat', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param PurgeDocuments $parameters
   * @return PurgeDocumentsResponse
   */
  public function PurgeDocuments(PurgeDocuments $parameters) {
    return $this->__soapCall('PurgeDocuments', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param SaveTemplate $parameters
   * @return SaveTemplateResponse
   */
  public function SaveTemplate(SaveTemplate $parameters) {
    return $this->__soapCall('SaveTemplate', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param UploadTemplate $parameters
   * @return UploadTemplateResponse
   */
  public function UploadTemplate(UploadTemplate $parameters) {
    return $this->__soapCall('UploadTemplate', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestTemplates $parameters
   * @return RequestTemplatesResponse
   */
  public function RequestTemplates(RequestTemplates $parameters) {
    return $this->__soapCall('RequestTemplates', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestTemplate $parameters
   * @return RequestTemplateResponse
   */
  public function RequestTemplate(RequestTemplate $parameters) {
    return $this->__soapCall('RequestTemplate', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetAuthenticationToken $parameters
   * @return GetAuthenticationTokenResponse
   */
  public function GetAuthenticationToken(GetAuthenticationToken $parameters) {
    return $this->__soapCall('GetAuthenticationToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetAddressBookItems $parameters
   * @return GetAddressBookItemsResponse
   */
  public function GetAddressBookItems(GetAddressBookItems $parameters) {
    return $this->__soapCall('GetAddressBookItems', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param UpdateAddressBookItems $parameters
   * @return UpdateAddressBookItemsResponse
   */
  public function UpdateAddressBookItems(UpdateAddressBookItems $parameters) {
    return $this->__soapCall('UpdateAddressBookItems', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RemoveAddressBookItems $parameters
   * @return RemoveAddressBookItemsResponse
   */
  public function RemoveAddressBookItems(RemoveAddressBookItems $parameters) {
    return $this->__soapCall('RemoveAddressBookItems', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param SynchEnvelope $parameters
   * @return SynchEnvelopeResponse
   */
  public function SynchEnvelope(SynchEnvelope $parameters) {
    return $this->__soapCall('SynchEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestSenderToken $parameters
   * @return RequestSenderTokenResponse
   */
  public function RequestSenderToken(RequestSenderToken $parameters) {
    return $this->__soapCall('RequestSenderToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestCorrectToken $parameters
   * @return RequestCorrectTokenResponse
   */
  public function RequestCorrectToken(RequestCorrectToken $parameters) {
    return $this->__soapCall('RequestCorrectToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetFolderItems $parameters
   * @return GetFolderItemsResponse
   */
  public function GetFolderItems(GetFolderItems $parameters) {
    return $this->__soapCall('GetFolderItems', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetFolderList $parameters
   * @return GetFolderListResponse
   */
  public function GetFolderList(GetFolderList $parameters) {
    return $this->__soapCall('GetFolderList', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param RequestEnvelope $parameters
   * @return RequestEnvelopeResponse
   */
  public function RequestEnvelope(RequestEnvelope $parameters) {
    return $this->__soapCall('RequestEnvelope', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/3.0',
            'soapaction' => ''
           )
      );
  }

/* Everything above this was generated by the wsdltophp.php script.
 * Below this are some custom functions that add WSE security support to all the api calls
 * these require that two files be included (see top of this file)
 * wsdl2php: http://www.urdalen.no/wsdl2php/
 * WSASoap, WSSESoap, xmlseclibs: http://www.cdatazone.org/index.php?/archives/54-Xmlseclibs-1.2.2-Released.html
*/

	private $_username;
	private $_password;


	public function setCredentials($username, $password)
	{
	    $this->_username = $username;
	    $this->_password = $password;
	}
	public $_lastRequest;
	function __doRequest($request, $location, $saction, $version, $one_way = null)
	{
		include_once 'WSSESoap.php';
		include_once 'WSASoap.php';

		$dom = new DOMDocument('1.0');
		$dom->loadXML($request);
		$objWSA = new WSASoap($dom);
		$objWSA->addAction($saction);
		$objWSA->addTo($location);
		$objWSA->addMessageID();
		$objWSA->addReplyTo();

		$dom = $objWSA->getDoc();

		$objWSSE = new WSSESoap($dom);
		if (isset($this->_username) && isset($this->_password)) {
		    $objWSSE->addUserToken($this->_username, $this->_password);

		}
		/* Sign all headers to include signing the WS-Addressing headers */
		$objWSSE->signAllHeaders = TRUE;

		$objWSSE->addTimestamp(300);
		// if you need to do binary certificate signing you can uncomment this (and provide the path to the cert)
		/* create new XMLSec Key using RSA SHA-1 and type is private key */
		// $objKey = new XMLSecurityKey(XMLSecurityKey::RSA_SHA1, array('type'=>'private'));

		/* load the private key from file - last arg is bool if key in file (TRUE) or is string (FALSE) */

		/* Sign the message - also signs appropraite WS-Security items */
		// $objWSSE->signSoapDoc($objKey);

		/* Add certificate (BinarySecurityToken) to the message and attach pointer to Signature */
		// $token = $objWSSE->addBinaryToken(file_get_contents(CERT_FILE));
		// $objWSSE->attachTokentoSig($token);

		$request = $objWSSE->saveXML();
		$this->_lastRequest = $request;

		return parent::__doRequest($request, $location, $saction, $version);
	}

}

?>
