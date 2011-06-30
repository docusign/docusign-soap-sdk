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
function createStatusTable() {
    $count = count($_SESSION["EnvelopeIDs"]);
    if (isset($_SESSION["EnvelopeIDs"]) && count($_SESSION["EnvelopeIDs"]) > 0) {
        $api = getAPI();
        
        $filter = new EnvelopeStatusFilter();
        $filter->AccountId = $_SESSION["AccountID"];
        $filter->EnvelopeIds = $_SESSION["EnvelopeIDs"];
        try {
            $rsexParams = new RequestStatusesEx();
            $rsexParams->EnvelopeStatusFilter = $filter;
            $statuses = $api->RequestStatusesEx($rsexParams)->RequestStatusesExResult;
        } catch (SoapFault $e) {
            $_SESSION["errorMessage"] = $e;
            header("Location: error.php");
        }
        
        if (isset($statuses)) {
            foreach ($statuses->EnvelopeStatuses->EnvelopeStatus as $status) {
                echo 
                "<tr>
                  <td><a href='getstatusofenvelope.php?envelopeid=" . $status->EnvelopeID . "'>" . $status->EnvelopeID . "</a></td>
                  <td>" . $status->Subject . "</td>
                  <td>" . $status->Status . "</td>
                </tr>
                ";
            };
        }
    }
    else {
        echo '<tr><td colspan=3 style="text-align:center">No envelopes yet</td></tr>';
    }
}
//========================================================================
// Main
//========================================================================
loginCheck();

// Get Envelope Details
if(empty($_GET['envelopeid'])){
	echo "Unable to find Envelope";
	exit;
}

$envelope_id = $_GET['envelopeid'];

$api = getAPI();

$requestStatusparams = new RequestStatus();
$requestStatusparams->EnvelopeID = $envelope_id;
$result = $api->RequestStatus($requestStatusparams); // Removed the array() around the $api call

echo '$result->RequestStatusResult->RecipientStatuses->RecipientStatus[0]->UserName';
pr($result->RequestStatusResult->RecipientStatuses->RecipientStatus[0]->UserName);

pr($result);

exit;

?>

<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" />
        <link rel="stylesheet" type="text/css" href="css/GetStatusAndDocs.css" />
        <script type="text/javascript" src="js/Utils.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    	<div class="container">
    		<div class="authbox">
    			<span><?php echo $_SESSION["UserID"]; ?></span> 
    			(<a href="index.php?logout">logout</a>)
    		</div>
        <table class="tabs" cellspacing="0" cellpadding="0">
	        <tr>
	        	<td><a href="senddocument.php">Send Document</a></td>
	        	<td><a href="sendatemplate.php">Send a Template</a></td>
	        	<td><a href="embeddocusign.php">Embed Docusign</a></td>
	        	<td class="current">Get Status and Docs</td>
		    	</tr>
	    	</table>
	    	<div id="statusDiv">
	            <table id="statusTable">
	        		<tr>
	        			<th>EnvelopeID</th>
	        			<th>Subject</th>
	        			<th>Status</th>
	        		</tr>
	        		<?php createStatusTable(); ?>
	            </table>
				</div>
	      <?php include 'include/footer.html';?>
    	</div>
    </body>
</html>


