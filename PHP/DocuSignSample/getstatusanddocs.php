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
 * Lists status of all envelopes in an account
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
                  <td>" . $status->EnvelopeID . "</td>
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

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    echo "";
}
else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    echo "";
}

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


