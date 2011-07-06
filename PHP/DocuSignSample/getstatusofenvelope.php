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

/*
 * List details for an Envelope
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides
include_once 'api/APIService.php';
include 'include/utils.php';

//========================================================================
// Functions
//========================================================================

function getEnvelopeStatus($envelope_id){
	// Start API
	$api = getAPI();
	
	// Create parameters for RequestStatus
	$requestStatusparams = new RequestStatus();
	$requestStatusparams->EnvelopeID = $envelope_id;
	$result = $api->RequestStatus($requestStatusparams); // Removed the array() around the $api call
	
	return $result;
}

//========================================================================
// Main
//========================================================================
loginCheck();

// Get Envelope ID
if(empty($_GET['envelopeid'])){
	echo "Unable to find Envelope ID";
	exit;
}
$envelope_id = $_GET['envelopeid'];

// Call function that returns Envelope Status
$envelopeStatus = getEnvelopeStatus($envelope_id);

// Print out the result ("function pr" in include/utils.php)
pr($envelopeStatus);
exit;

?>