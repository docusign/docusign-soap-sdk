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
 
class UpgradeRecipientAccount {
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $AccountId; // string
    public $UserId; // string
    public $Pgp; // string
    public $CreditCardInformation; // CreditCardInformation
    public $ReferralInformation; // ReferralInformation
    public $AccountSettings; // AccountSettings
    public $AddressInformation; // AddressInformation
}

class CreditCardInformation {
    public $ccNumber; // string
    public $ccExpirationMonth; // string
    public $ccExpirationYear; // string
    public $ccUserName; // string
    public $ccType; // string
}

class ReferralInformation {
    public $EnableSupport; // boolean
    public $IncludedSeats; // positiveInteger
    public $SaleDiscountPercent; // decimal
    public $PlanStartMonth; // positiveInteger
    public $ReferrerName; // string
    public $ReferralCode; // string
    public $AdvertisementID; // string
    public $PublisherID; // string
    public $ShopperID; // string
    public $PromoCode; // string
    public $GroupMemberID; // string
    public $IdType; // string
    public $Industry; // string
}

class AccountSettings {
    public $UsesAPI; // boolean
    public $EnableDSPro; // boolean
    public $EnableVaulting; // boolean
    public $EnableEnvelopeStampingByAccountAdmin; // boolean
    public $EnvelopeStampingDefaultValue; // boolean
    public $SignerMustHaveAccount; // boolean
    public $SignerMustLoginToSign; // boolean
    public $SignerCanCreateAccount; // boolean
    public $AllowInPerson; // boolean
    public $EnablePowerForm; // boolean
    public $AllowSignerReassign; // boolean
    public $EnableReservedDomain; // boolean
    public $EnableSequentialSigningAPI; // boolean
    public $EnableSequentialSigningUI; // boolean
    public $EnableAutoNav; // boolean
    public $AutoNavRule; // AutoNavRule
    public $EnableTransactionPoint; // boolean
    public $EnvelopeIntegrationAllowed; // EnvelopeIntegrationAllowed
    public $EnvelopeIntegrationEnabled; // boolean
    public $CanSelfBrandSend; // boolean
    public $CanSelfBrandSign; // boolean
    public $IDCheckRequired; // IDCheckRequired
    public $IDCheckExpire; // IDCheckExpire
    public $IDCheckExpireDays; // integer
    public $SignDateFormat; // string
    public $PKISignDownloadedPDFDocs; // PKISignDownloadedPDFDocs
    public $InPersonIDCheckQuestion; // string
    public $SessionTimeout; // integer
    public $AttachCompletedEnvelope; // boolean
    public $SignerCanSignOnMobile; // boolean
    public $SignerShowSecureFieldInitialValues; // boolean
    public $SignerAttachCertificateToEnvelopePDF; // boolean
    public $EnableSignOnPaper; // boolean
    public $EnableSignOnPaperOverride; // boolean
    public $EnableSignerAttachments; // boolean
    public $UseAccountLevelEmail; // boolean
}

class AutoNavRule {
}

class EnvelopeIntegrationAllowed {
}

class IDCheckRequired {
}

class IDCheckExpire {
}

class PKISignDownloadedPDFDocs {
}

class AddressInformation {
    public $Address1; // string
    public $Address2; // string
    public $City; // string
    public $State; // string
    public $Zip; // string
    public $Phone; // string
    public $Fax; // string
}

class UpgradeRecipientAccountResponse {
    public $UpgradeRecipientAccountResult; // UpgradeRecipientAccountResult
}

class UpgradeRecipientAccountResult {
    public $Success; // boolean
    public $Error; // RequestError
}

class RequestError {
    public $ErrorCode; // ErrorCode
    public $Description; // string
}

class ErrorCode {
    const Unspecified_Error = 'Unspecified_Error';
    const Invalid_Account_ID = 'Invalid_Account_ID';
    const Account_Requires_User_Name_And_Password_For_Activation = 'Account_Requires_User_Name_And_Password_For_Activation';
    const Account_Is_Already_Activated_For_Salesforce = 'Account_Is_Already_Activated_For_Salesforce';
    const Invalid_Distributor_For_Account = 'Invalid_Distributor_For_Account';
    const Invalid_User_ID = 'Invalid_User_ID';
    const Invalid_Account = 'Invalid_Account';
    const User_Is_Not_An_Account_Manager = 'User_Is_Not_An_Account_Manager';
    const Invalid_Login = 'Invalid_Login';
    const Invalid_Member_User_Name = 'Invalid_Member_User_Name';
    const Invalid_Member_Email = 'Invalid_Member_Email';
    const Member_Email_Not_Allowed = 'Member_Email_Not_Allowed';
    const Member_Email_And_User_Name_Awaiting_Activation = 'Member_Email_And_User_Name_Awaiting_Activation';
    const Member_Email_And_User_Name_Already_Exists_For_This_Account = 'Member_Email_And_User_Name_Already_Exists_For_This_Account';
    const Member_Password_Blank = 'Member_Password_Blank';
    const Member_Forgotten_Password_Question_Blank = 'Member_Forgotten_Password_Question_Blank';
    const Member_Forgotten_Password_Answer_Blank = 'Member_Forgotten_Password_Answer_Blank';
    const Invalid_Password_Format = 'Invalid_Password_Format';
    const Invalid_Member_Data = 'Invalid_Member_Data';
    const Member_Email_And_User_Name_Already_Exists = 'Member_Email_And_User_Name_Already_Exists';
    const Not_Authorized = 'Not_Authorized';
    const Invalid_Distributor_Selected = 'Invalid_Distributor_Selected';
    const Invalid_PGP_For_Distributor = 'Invalid_PGP_For_Distributor';
    const Invalid_Credit_Card_Type = 'Invalid_Credit_Card_Type';
    const CreditCard_Auth_Failed = 'CreditCard_Auth_Failed';
    const Invalid_PGP = 'Invalid_PGP';
    const Invalid_Plan_Retired = 'Invalid_Plan_Retired';
    const Invalid_Successor_Plan = 'Invalid_Successor_Plan';
    const Invalid_Credit_Card = 'Invalid_Credit_Card';
    const Credit_Card_Expiration = 'Credit_Card_Expiration';
    const Invalid_AppToken = 'Invalid_AppToken';
    const Distributor_Not_Enabled_For_AppToken = 'Distributor_Not_Enabled_For_AppToken';
    const Plan_Group_Not_Enabled_For_Distributor = 'Plan_Group_Not_Enabled_For_Distributor';
    const Invalid_Configuration_Number = 'Invalid_Configuration_Number';
    const Invalid_Salesforce_Credentials = 'Invalid_Salesforce_Credentials';
    const Invalid_Salesforce_External_Instance_ID = 'Invalid_Salesforce_External_Instance_ID';
    const Invalid_DocuSign_Connect_Configuration_For_Account = 'Invalid_DocuSign_Connect_Configuration_For_Account';
    const Invalid_User = 'Invalid_User';
    const Invalid_Membership = 'Invalid_Membership';
    const Invalid_Account_Member = 'Invalid_Account_Member';
    const Invalid_Edit_User = 'Invalid_Edit_User';
    const Invalid_Edit_Membership = 'Invalid_Edit_Membership';
    const Invalid_CanEditSharedAddressBook_Value = 'Invalid_CanEditSharedAddressBook_Value';
    const Invalid_CanManageTemplates_Value = 'Invalid_CanManageTemplates_Value';
    const Invalid_Membership_ID = 'Invalid_Membership_ID';
    const Invalid_Request = 'Invalid_Request';
    const Partner_Authentication_Failed = 'Partner_Authentication_Failed';
}

class NewAccount {
    public $AccountName; // string
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $Pgp; // string
    public $CreditCardInformation; // CreditCardInformation
    public $ReferralInformation; // ReferralInformation
    public $AccountSettings; // AccountSettings
    public $Member; // Member
    public $AddressInformation; // AddressInformation
}

class Member {
    public $MemberEmailAddress; // string
    public $MemberUserName; // string
    public $MemberPassword; // string
    public $MemberForgottenPasswordQuestion; // string
    public $MemberForgottenPasswordAnswer; // string
    public $MemberTitle; // string
    public $MemberFirstName; // string
    public $MemberMiddleName; // string
    public $MemberLastName; // string
    public $MemberSuffix; // string
    public $EnableConnectForUser; // boolean
    public $MemberSettings; // MemberSettings
    public $ReturnEncryptedPassword; // boolean
}

class MemberSettings {
    public $CanManageAccount; // boolean
    public $CanSendEnvelope; // boolean
    public $CanSendAPIRequests; // boolean
    public $APIAccountWideAccess; // boolean
    public $EnableVaulting; // boolean
    public $VaultingMode; // VaultingMode
    public $EnableTransactionPoint; // boolean
    public $EnableSequentialSigningAPI; // boolean
    public $EnableSequentialSigningUI; // boolean
    public $EnableDSPro; // boolean
    public $PowerFormAdmin; // boolean
    public $PowerFormUser; // boolean
    public $CanEditSharedAddressBook; // CanEditSharedAddressBook
    public $CanManageTemplates; // CanManageTemplates
    public $EnableSignOnPaperOverride; // boolean
    public $EnableSignerAttachments; // boolean
}

class VaultingMode {
}

class CanEditSharedAddressBook {
}

class CanManageTemplates {
}

class NewAccountResponse {
    public $NewAccountResult; // NewAccountResult
}

class NewAccountResult {
    public $AccountId; // string
    public $SiteId; // string
    public $UserId; // string
    public $MembershipId; // string
    public $EncryptedPassword; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class AddMembersToAccount {
    public $AccountId; // string
    public $Members; // ArrayOfMember
}

class AddMembersToAccountResponse {
    public $AddMembersToAccountResult; // AddMembersToAccountResult
}

class AddMembersToAccountResult {
    public $Members; // ArrayOfMemberResult
    public $Success; // boolean
    public $Error; // RequestError
}

class MemberResult {
    public $UserId; // string
    public $EncryptedPassword; // string
    public $MembershipId; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class GetPlanPricingInformation {
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $Pgp; // string
}

class GetPlanPricingInformationResponse {
    public $GetPlanPricingInformationResult; // GetPlanPricingInformationResult
}

class GetPlanPricingInformationResult {
    public $Plan; // Plan
    public $Success; // boolean
    public $Error; // RequestError
}

class Plan {
    public $PlanName; // string
    public $PaymentCycle; // PaymentCycle
    public $PaymentMethod; // PaymentMethod
    public $PerSeatPrice; // decimal
    public $OtherDiscountPercent; // decimal
    public $SupportIncidentFee; // decimal
    public $SupportPlanFee; // decimal
    public $IncludedSeats; // integer
    public $EnableSupport; // boolean
    public $PgpId; // string
    public $SeatDiscounts; // ArrayOfSeatDiscount
}

class PaymentCycle {
    const Monthly = 'Monthly';
    const Annually = 'Annually';
}

class PaymentMethod {
    const CreditCard = 'CreditCard';
    const PurchaseOrder = 'PurchaseOrder';
    const Premium = 'Premium';
    const PrePaid = 'PrePaid';
}

class SeatDiscount {
    public $BeginSeatCount; // integer
    public $EndSeatCount; // integer
    public $SeatDiscountPercent; // decimal
}

class GetSuccessorPlanInformation {
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $AccountId; // string
}

class GetSuccessorPlanInformationResponse {
    public $GetSuccessorPlanInformationResult; // GetSuccessorPlanInformationResult
}

class GetSuccessorPlanInformationResult {
    public $CurrentPlan; // Plan
    public $SuccessorPlans; // ArrayOfPlan
    public $Success; // boolean
    public $Error; // RequestError
}

class GetPlanGroupInformation {
    public $DistributorCode; // string
    public $DistributorPassword; // string
}

class GetPlanGroupInformationResponse {
    public $GetPlanGroupInformationResult; // GetPlanGroupInformationResult
}

class GetPlanGroupInformationResult {
    public $PlanGroups; // ArrayOfPlanGroup
    public $Success; // boolean
    public $Error; // RequestError
}

class PlanGroup {
    public $PlanGroupName; // string
    public $PlanGroupPlans; // ArrayOfPlanGroupPlan
}

class PlanGroupPlan {
    public $PlanName; // string
    public $Pgp; // string
    public $PaymentMethod; // PaymentMethod
}

class GetMembershipSummary {
    public $Email; // string
}

class GetMembershipSummaryResponse {
    public $GetMembershipSummaryResult; // GetMembershipSummaryResult
}

class GetMembershipSummaryResult {
    public $MembershipSummaries; // ArrayOfMembershipSummary
    public $Success; // boolean
    public $Error; // RequestError
}

class MembershipSummary {
    public $AccountName; // string
    public $AccountId; // string
    public $UserName; // string
    public $UserId; // string
    public $UserType; // UserType
    public $UserStatus; // UserStatus
    public $PlanName; // string
    public $Pgp; // string
}

class UserType {
    const CompanyUser = 'CompanyUser';
    const Recipient = 'Recipient';
}

class UserStatus {
    const Created = 'Created';
    const Active = 'Active';
    const Closed = 'Closed';
}

class ChangeAccountPricePlan {
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $AccountId; // string
    public $Pgp; // string
    public $EnableSupport; // boolean
    public $IncludedSeats; // int
    public $CreditCardInformation; // CreditCardInformation
    public $AddressInformation; // AddressInformation
}

class ChangeAccountPricePlanResponse {
    public $ChangeAccountPricePlanResult; // ChangeAccountPricePlanResult
}

class ChangeAccountPricePlanResult {
    public $Success; // boolean
    public $Error; // RequestError
}

class GetProvisioningInformation {
    public $AppToken; // string
}

class GetProvisioningInformationResponse {
    public $GetProvisioningInformationResult; // GetProvisioningInformationResult
}

class GetProvisioningInformationResult {
    public $DistributorCode; // string
    public $DistributorPassword; // string
    public $Pgp; // string
    public $PlanPromoText; // string
    public $DefaultConnectConfigurationId; // integer
    public $PasswordRuleText; // string
    public $PurchaseOrderOrPromoAllowed; // boolean
    public $Success; // boolean
    public $Error; // RequestError
}

class SetConnectCredentials {
    public $AccountId; // string
    public $ConnectUsername; // string
    public $ConnectPassword; // string
    public $ConnectConfigurationId; // int
    public $SalesforceEnvironment; // string
}

class SetConnectCredentialsResponse {
    public $SetConnectCredentialsResult; // SetConnectCredentialsResult
}

class SetConnectCredentialsResult {
    public $ConnectTestResult; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class GetAccountInformation {
    public $AccountId; // string
}

class GetAccountInformationResponse {
    public $GetAccountInformationResult; // GetAccountInformationResult
}

class GetAccountInformationResult {
    public $CurrentPlanId; // string
    public $CurrentPlanName; // string
    public $CurrentPlanStartDate; // dateTime
    public $CurrentPlanEndDate; // dateTime
    public $CurrentBillingPeriodStartDate; // dateTime
    public $CurrentBillingPeriodEndDate; // dateTime
    public $CurrentBillingPeriodEnvelopesSent; // integer
    public $CurrentBillingPeriodEnvelopesAllowed; // integer
    public $AccountSuspensionStatus; // string
    public $AccountSuspensionDate; // dateTime
    public $AccountName; // string
    public $ExternalAccountId; // string
    public $ConnectPermission; // string
    public $DocusignLandingUrl; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class GetConnectCredentials {
    public $AccountId; // string
}

class GetConnectCredentialsResponse {
    public $GetConnectCredentialsResult; // GetConnectCredentialsResult
}

class GetConnectCredentialsResult {
    public $ConnectUserName; // string
    public $ConnectConfig; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class GetEncryptedPassword {
}

class GetEncryptedPasswordResponse {
    public $GetEncryptedPasswordResult; // GetEncryptedPasswordResult
}

class GetEncryptedPasswordResult {
    public $EncryptedPassword; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class GetAccountSettings {
    public $AccountId; // string
}

class GetAccountSettingsResponse {
    public $GetAccountSettingsResult; // GetAccountSettingsResult
}

class GetAccountSettingsResult {
    public $AccountSettings; // AccountSettings
    public $Success; // boolean
    public $Error; // RequestError
}

class UpdateAccountSettings {
    public $AccountId; // string
    public $AccountSettings; // AccountSettings
}

class UpdateAccountSettingsResponse {
    public $UpdateAccountSettingsResult; // UpdateAccountSettingsResult
}

class UpdateAccountSettingsResult {
    public $Success; // boolean
    public $Error; // RequestError
}

class AuthenticateMember {
    public $AccountId; // string
}

class AuthenticateMemberResponse {
    public $AuthenticateMemberResult; // AuthenticateMemberResult
}

class AuthenticateMemberResult {
    public $UserId; // string
    public $AccountId; // string
    public $UsesAPI; // boolean
    public $MemberSettings; // MemberSettings
    public $Success; // boolean
    public $Error; // RequestError
}

class GetMemberSettings {
    public $AccountId; // string
    public $UserId; // string
}

class GetMemberSettingsResponse {
    public $GetMemberSettingsResult; // GetMemberSettingsResult
}

class GetMemberSettingsResult {
    public $MemberSettings; // MemberSettings
    public $Success; // boolean
    public $Error; // RequestError
}

class UpdateMemberSettings {
    public $AccountId; // string
    public $UserId; // string
    public $MemberSettings; // MemberSettings
}

class UpdateMemberSettingsResponse {
    public $UpdateMemberSettingsResult; // UpdateMemberSettingsResult
}

class UpdateMemberSettingsResult {
    public $Success; // boolean
    public $Error; // RequestError
}

class CheckAccountMember {
    public $AccountId; // string
    public $Email; // string
}

class CheckAccountMemberResponse {
    public $CheckAccountMemberResult; // CheckAccountMemberResult
}

class CheckAccountMemberResult {
    public $UserId; // string
    public $Status; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class ActivateSalesforceInstance {
    public $AccountId; // string
    public $ExternalInstanceId; // string
    public $ConnectUsername; // string
    public $ConnectPassword; // string
    public $SalesforceEnvironment; // string
    public $Member; // Member
}

class ActivateSalesforceInstanceResponse {
    public $ActivateSalesforceInstanceResult; // ActivateSalesforceInstanceResult
}

class ActivateSalesforceInstanceResult {
    public $AccountId; // string
    public $SiteId; // string
    public $UserId; // string
    public $MembershipId; // string
    public $EncryptedPassword; // string
    public $Success; // boolean
    public $Error; // RequestError
}

class Ping {
}

class PingResponse {
    public $PingResult; // boolean
}


/**
 * AccountManagementService class
 *
 *
 *
 * @author    {author}
 * @copyright {copyright}
 * @package   {package}
 */
class AccountManagementService extends SoapClient {

    private static $classmap = array(
                                    'UpgradeRecipientAccount' => 'UpgradeRecipientAccount',
                                    'CreditCardInformation' => 'CreditCardInformation',
                                    'ReferralInformation' => 'ReferralInformation',
                                    'AccountSettings' => 'AccountSettings',
                                    'AutoNavRule' => 'AutoNavRule',
                                    'EnvelopeIntegrationAllowed' => 'EnvelopeIntegrationAllowed',
                                    'IDCheckRequired' => 'IDCheckRequired',
                                    'IDCheckExpire' => 'IDCheckExpire',
                                    'PKISignDownloadedPDFDocs' => 'PKISignDownloadedPDFDocs',
                                    'AddressInformation' => 'AddressInformation',
                                    'UpgradeRecipientAccountResponse' => 'UpgradeRecipientAccountResponse',
                                    'UpgradeRecipientAccountResult' => 'UpgradeRecipientAccountResult',
                                    'RequestError' => 'RequestError',
                                    'ErrorCode' => 'ErrorCode',
                                    'NewAccount' => 'NewAccount',
                                    'Member' => 'Member',
                                    'MemberSettings' => 'MemberSettings',
                                    'VaultingMode' => 'VaultingMode',
                                    'CanEditSharedAddressBook' => 'CanEditSharedAddressBook',
                                    'CanManageTemplates' => 'CanManageTemplates',
                                    'NewAccountResponse' => 'NewAccountResponse',
                                    'NewAccountResult' => 'NewAccountResult',
                                    'AddMembersToAccount' => 'AddMembersToAccount',
                                    'AddMembersToAccountResponse' => 'AddMembersToAccountResponse',
                                    'AddMembersToAccountResult' => 'AddMembersToAccountResult',
                                    'MemberResult' => 'MemberResult',
                                    'GetPlanPricingInformation' => 'GetPlanPricingInformation',
                                    'GetPlanPricingInformationResponse' => 'GetPlanPricingInformationResponse',
                                    'GetPlanPricingInformationResult' => 'GetPlanPricingInformationResult',
                                    'Plan' => 'Plan',
                                    'PaymentCycle' => 'PaymentCycle',
                                    'PaymentMethod' => 'PaymentMethod',
                                    'SeatDiscount' => 'SeatDiscount',
                                    'GetSuccessorPlanInformation' => 'GetSuccessorPlanInformation',
                                    'GetSuccessorPlanInformationResponse' => 'GetSuccessorPlanInformationResponse',
                                    'GetSuccessorPlanInformationResult' => 'GetSuccessorPlanInformationResult',
                                    'GetPlanGroupInformation' => 'GetPlanGroupInformation',
                                    'GetPlanGroupInformationResponse' => 'GetPlanGroupInformationResponse',
                                    'GetPlanGroupInformationResult' => 'GetPlanGroupInformationResult',
                                    'PlanGroup' => 'PlanGroup',
                                    'PlanGroupPlan' => 'PlanGroupPlan',
                                    'GetMembershipSummary' => 'GetMembershipSummary',
                                    'GetMembershipSummaryResponse' => 'GetMembershipSummaryResponse',
                                    'GetMembershipSummaryResult' => 'GetMembershipSummaryResult',
                                    'MembershipSummary' => 'MembershipSummary',
                                    'UserType' => 'UserType',
                                    'UserStatus' => 'UserStatus',
                                    'ChangeAccountPricePlan' => 'ChangeAccountPricePlan',
                                    'ChangeAccountPricePlanResponse' => 'ChangeAccountPricePlanResponse',
                                    'ChangeAccountPricePlanResult' => 'ChangeAccountPricePlanResult',
                                    'GetProvisioningInformation' => 'GetProvisioningInformation',
                                    'GetProvisioningInformationResponse' => 'GetProvisioningInformationResponse',
                                    'GetProvisioningInformationResult' => 'GetProvisioningInformationResult',
                                    'SetConnectCredentials' => 'SetConnectCredentials',
                                    'SetConnectCredentialsResponse' => 'SetConnectCredentialsResponse',
                                    'SetConnectCredentialsResult' => 'SetConnectCredentialsResult',
                                    'GetAccountInformation' => 'GetAccountInformation',
                                    'GetAccountInformationResponse' => 'GetAccountInformationResponse',
                                    'GetAccountInformationResult' => 'GetAccountInformationResult',
                                    'GetConnectCredentials' => 'GetConnectCredentials',
                                    'GetConnectCredentialsResponse' => 'GetConnectCredentialsResponse',
                                    'GetConnectCredentialsResult' => 'GetConnectCredentialsResult',
                                    'GetEncryptedPassword' => 'GetEncryptedPassword',
                                    'GetEncryptedPasswordResponse' => 'GetEncryptedPasswordResponse',
                                    'GetEncryptedPasswordResult' => 'GetEncryptedPasswordResult',
                                    'GetAccountSettings' => 'GetAccountSettings',
                                    'GetAccountSettingsResponse' => 'GetAccountSettingsResponse',
                                    'GetAccountSettingsResult' => 'GetAccountSettingsResult',
                                    'UpdateAccountSettings' => 'UpdateAccountSettings',
                                    'UpdateAccountSettingsResponse' => 'UpdateAccountSettingsResponse',
                                    'UpdateAccountSettingsResult' => 'UpdateAccountSettingsResult',
                                    'AuthenticateMember' => 'AuthenticateMember',
                                    'AuthenticateMemberResponse' => 'AuthenticateMemberResponse',
                                    'AuthenticateMemberResult' => 'AuthenticateMemberResult',
                                    'GetMemberSettings' => 'GetMemberSettings',
                                    'GetMemberSettingsResponse' => 'GetMemberSettingsResponse',
                                    'GetMemberSettingsResult' => 'GetMemberSettingsResult',
                                    'UpdateMemberSettings' => 'UpdateMemberSettings',
                                    'UpdateMemberSettingsResponse' => 'UpdateMemberSettingsResponse',
                                    'UpdateMemberSettingsResult' => 'UpdateMemberSettingsResult',
                                    'CheckAccountMember' => 'CheckAccountMember',
                                    'CheckAccountMemberResponse' => 'CheckAccountMemberResponse',
                                    'CheckAccountMemberResult' => 'CheckAccountMemberResult',
                                    'ActivateSalesforceInstance' => 'ActivateSalesforceInstance',
                                    'ActivateSalesforceInstanceResponse' => 'ActivateSalesforceInstanceResponse',
                                    'ActivateSalesforceInstanceResult' => 'ActivateSalesforceInstanceResult',
                                    'Ping' => 'Ping',
                                    'PingResponse' => 'PingResponse',
    );

    public function AccountManagementService($wsdl = "accountmanagement.wsdl", $options = array()) {
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
     * @param UpgradeRecipientAccount $parameters
     * @return UpgradeRecipientAccountResponse
     */
    public function UpgradeRecipientAccount(UpgradeRecipientAccount $parameters) {
        return $this->__soapCall('UpgradeRecipientAccount', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param NewAccount $parameters
     * @return NewAccountResponse
     */
    public function NewAccount(NewAccount $parameters) {
        return $this->__soapCall('NewAccount', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param AddMembersToAccount $parameters
     * @return AddMembersToAccountResponse
     */
    public function AddMembersToAccount(AddMembersToAccount $parameters) {
        return $this->__soapCall('AddMembersToAccount', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetPlanPricingInformation $parameters
     * @return GetPlanPricingInformationResponse
     */
    public function GetPlanPricingInformation(GetPlanPricingInformation $parameters) {
        return $this->__soapCall('GetPlanPricingInformation', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetSuccessorPlanInformation $parameters
     * @return GetSuccessorPlanInformationResponse
     */
    public function GetSuccessorPlanInformation(GetSuccessorPlanInformation $parameters) {
        return $this->__soapCall('GetSuccessorPlanInformation', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetPlanGroupInformation $parameters
     * @return GetPlanGroupInformationResponse
     */
    public function GetPlanGroupInformation(GetPlanGroupInformation $parameters) {
        return $this->__soapCall('GetPlanGroupInformation', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetMembershipSummary $parameters
     * @return GetMembershipSummaryResponse
     */
    public function GetMembershipSummary(GetMembershipSummary $parameters) {
        return $this->__soapCall('GetMembershipSummary', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param ChangeAccountPricePlan $parameters
     * @return ChangeAccountPricePlanResponse
     */
    public function ChangeAccountPricePlan(ChangeAccountPricePlan $parameters) {
        return $this->__soapCall('ChangeAccountPricePlan', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetProvisioningInformation $parameters
     * @return GetProvisioningInformationResponse
     */
    public function GetProvisioningInformation(GetProvisioningInformation $parameters) {
        return $this->__soapCall('GetProvisioningInformation', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param SetConnectCredentials $parameters
     * @return SetConnectCredentialsResponse
     */
    public function SetConnectCredentials(SetConnectCredentials $parameters) {
        return $this->__soapCall('SetConnectCredentials', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetAccountInformation $parameters
     * @return GetAccountInformationResponse
     */
    public function GetAccountInformation(GetAccountInformation $parameters) {
        return $this->__soapCall('GetAccountInformation', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetConnectCredentials $parameters
     * @return GetConnectCredentialsResponse
     */
    public function GetConnectCredentials(GetConnectCredentials $parameters) {
        return $this->__soapCall('GetConnectCredentials', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetEncryptedPassword $parameters
     * @return GetEncryptedPasswordResponse
     */
    public function GetEncryptedPassword(GetEncryptedPassword $parameters) {
        return $this->__soapCall('GetEncryptedPassword', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetAccountSettings $parameters
     * @return GetAccountSettingsResponse
     */
    public function GetAccountSettings(GetAccountSettings $parameters) {
        return $this->__soapCall('GetAccountSettings', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param UpdateAccountSettings $parameters
     * @return UpdateAccountSettingsResponse
     */
    public function UpdateAccountSettings(UpdateAccountSettings $parameters) {
        return $this->__soapCall('UpdateAccountSettings', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param AuthenticateMember $parameters
     * @return AuthenticateMemberResponse
     */
    public function AuthenticateMember(AuthenticateMember $parameters) {
        return $this->__soapCall('AuthenticateMember', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param GetMemberSettings $parameters
     * @return GetMemberSettingsResponse
     */
    public function GetMemberSettings(GetMemberSettings $parameters) {
        return $this->__soapCall('GetMemberSettings', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param UpdateMemberSettings $parameters
     * @return UpdateMemberSettingsResponse
     */
    public function UpdateMemberSettings(UpdateMemberSettings $parameters) {
        return $this->__soapCall('UpdateMemberSettings', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param CheckAccountMember $parameters
     * @return CheckAccountMemberResponse
     */
    public function CheckAccountMember(CheckAccountMember $parameters) {
        return $this->__soapCall('CheckAccountMember', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }

    /**
     *
     *
     * @param ActivateSalesforceInstance $parameters
     * @return ActivateSalesforceInstanceResponse
     */
    public function ActivateSalesforceInstance(ActivateSalesforceInstance $parameters) {
        return $this->__soapCall('ActivateSalesforceInstance', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/AccountManagement',
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
            'uri' => 'http://www.docusign.net/API/AccountManagement',
            'soapaction' => ''
            )
            );
    }
}
//=============================================================================
// NOT INCLUDED BY WSDL
//=============================================================================

?>
