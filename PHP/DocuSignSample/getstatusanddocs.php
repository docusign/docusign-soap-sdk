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
        	?> <ul class=""> <?
	            foreach ($statuses->EnvelopeStatuses->EnvelopeStatus as $status) {
		           ?>
		           		<li>
		           			<span><u><?= $status->Subject ?></u> 
		           				[<?= $status->Status ?>] - 
		           				<?= $status->EnvelopeID; ?> 
		           				<a href="getstatusofenvelope.php?envelopeid=<?= $status->EnvelopeID; ?>" target="_blank" title="Click to see a RequestStatus SOAP return for this Envelope">View RequestStatus</a>
		           				&nbsp;&nbsp;<a href="getpdf.php?envelopeid=<?= $status->EnvelopeID; ?>" target="_blank" title="Click to download PDF for this Envelope">Download PDF</a></span>
		           			<ul>
		           				<!-- Recipients -->
				           		<li>
				           			<span>Recipients (<?= count($status->RecipientStatuses->RecipientStatus); ?>)</span>
				           			<ul id="<?= $status->EnvelopeID; ?>">
				           				
				           				<? foreach($status->RecipientStatuses->RecipientStatus as $rcpStatus){ ?>
				           							<li>
				           								<?= $rcpStatus->UserName; ?>
				           							</li>
				           				<? } ?>
				           				
				           			</ul>
				           		</li>
				           		
				           		
		           				<!-- Documents -->
		           				<li>
		           					<span>Documents (<?= count($status->DocumentStatuses->DocumentStatus); ?>)</span>
		           					<ul>
		           						<? foreach($status->DocumentStatuses->DocumentStatus as $docStatus){ ?>
				           						<li>
				           							<?= $docStatus->Name; ?>
				           						</li>
				           				<? } ?>
		           					</ul>
		           				</li>
				           		
				           	</ul>
		           		</li>
		           <?
	            };
        	?> </ul> <?
        }
    } else {
      // No Envelopes created yet
			echo '<tr><td><div class="sampleMessage">';
			echo '	No envelopes created, yet. Use the tabs to create an Envelope.';
			echo '</div></td></tr>';
        
    }
}
//========================================================================
// Main
//========================================================================
loginCheck();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
	
}
else if ($_SERVER["REQUEST_METHOD"] == "GET") {
	
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
    	
    	<script type="text/javascript">
    			// Invert rows when clicking (not implemented, simple enough to view without deep-clicking)
			    function invert(ident) {
			        var state = document.getElementById(ident).style.display;
			        if (state == 'block') {
			            document.getElementById(ident).style.display = 'none';
			        } else {
			            document.getElementById(ident).style.display = 'block';
			        }
			    }
			</script>
			
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
	      	<?php createStatusTable(); ?>
				</div>
	      <?php include 'include/footer.html';?>
    	</div>
    </body>
</html>


