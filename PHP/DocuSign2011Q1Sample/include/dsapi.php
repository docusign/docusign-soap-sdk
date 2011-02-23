<?php
/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
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
include_once("api/DSAPIService.php");
// user credentials
include_once("include/account_creds.php");

//=============================================================================
// helper constants
//=============================================================================

/**
 * Takes credentials, inserts them into HTTP Header, and returns SOAP
 * service object for dsapi
 * @return DSAPIService
 */
function getDSAPI() {
	$dsapi_wsdl = "api/DSAPIService.wsdl";

	// build credential xml to add to http header
	$ds_auth = "<DocuSignCredentials><Username>" . $UserID . "</Username><Password>"
	. "$Password</Password><IntegratorKey>" . $IntegratorsKey . "</IntegratorKey></DocuSignCredentials>";
	$ctxStream = stream_context_create(array(
    'http' => array(
        'method' => "GET",
        'header' => "X-DocuSign-Authentication: " . $ds_auth . "\r\n")));

	$dsapi_options =  array(
    'protocol_version' => "1.0",
	'trace'=>true,
    'stream_context' => $ctxStream);
	$dsapi = new DSAPIService($dsapi_wsdl, $dsapi_options);
	
	return $dsapi;
}
?>