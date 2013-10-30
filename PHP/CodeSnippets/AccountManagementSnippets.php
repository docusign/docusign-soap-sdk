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
 

// account management api service proxy classes and soapclient
include("api/AccountManagementService.php");
// some helper functions
include_once("include/utils.php");
// user credentials
include_once("include/creds.php");

//=============================================================================
// helper constants
//=============================================================================
// TODO: email used to create and check accounts
$_amEmail = "test email account";


//=============================================================================
// Set up the API
//=============================================================================
$am_api_endpoint = "https://demo.docusign.net/api/3.0/accountmanagement.asmx";
$am_api_wsdl = "api/AccountManagementService.wsdl";

// build credential xml to add to http header
$ds_auth = "<DocuSignCredentials><Username>" . $UserID . "</Username><Password>"
    . "$Password</Password><IntegratorKey>" . $IntegratorsKey . "</IntegratorKey></DocuSignCredentials>";
$ctxStream = stream_context_create(array(
    'http' => array(
        'method' => "GET",
        'header' => "X-DocuSign-Authentication: " . $ds_auth . "\r\n")));

$am_api_options =  array(
    'protocol_version' => "1.0",
	'trace'=>true,
    'stream_context' => $ctxStream);
$am_api = new AccountManagementService($am_api_wsdl, $am_api_options);

//=============================================================================
// Snippets
//=============================================================================
function am_pingSample() {
    global $am_api;

    $pingParams = new Ping();
    $response = $am_api->Ping($pingParams);

    return $response;
}

/**
 * This method will return information for every account this member belongs to.
 * For this reason, the credentials of the api user must have System Administration
 * priviledges
 * @return GetMembershipSummaryResponse
 */
function am_getMembershipSummary() {
    global $am_api;
    global $_amEmail;

    $getMemberSummaryparams = new GetMembershipSummary();
    $getMemberSummaryparams->Email = $_amEmail;
    $response = $am_api->GetMembershipSummary($getMemberSummaryparams);

    return $response;
}

function am_newAccountSample() {
    global $am_api;

    $credit = new CreditCardInformation();

    $accountSettings = new AccountSettings();

    $addressInfo = new AddressInformation();
    $addressInfo->Address1 = "Main";
    $addressInfo->City = "Seattle";
    $addressInfo->Phone = "555-5555";
    $addressInfo->State = "WA";
    $addressInfo->Zip = "99999";

    $member = new Member();
    // TODO: put your new member credentials here
    $member->MemberEmailAddress = "member@email.edu";
    $member->MemberUserName = "Member Name";
    $member->MemberPassword = "some default password";
    $member->MemberForgottenPasswordQuestion = "what if you forget your
                password?";
    $member->MemberForgottenPasswordAnswer = "Don't Forget";


    $newAccountparams = new NewAccount();
    $newAccountparams->AccountName = "Company Name of New Account";
    // TODO: put your distributor credentials here
    $newAccountparams->DistributorCode = "your distributor code";
    $newAccountparams->DistributorPassword = "your distributor password";
    $newAccountparams->Pgp = "Your pgp guid";
    $newAccountparams->AccountSettings = $accountSettings;
    $newAccountparams->Member = $member;
    $newAccountparams->AddressInformation = $addressInfo;
    $response = $am_api->NewAccount($newAccountparams);

    return $response;
}
?>
