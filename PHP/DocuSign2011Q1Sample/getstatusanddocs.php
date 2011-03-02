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
        <link rel="stylesheet" type="text/css" href="css/GetStatusAndDocs.css" />
        <script type="text/javascript" src="js/Utils.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="tabs">
        	<a href="senddocument.php">Send Document</a>
        	<a href="sendatemplate.php">Send a Template</a>
        	<a href="embeddocusign.php">Embed Docusign</a>
        	<a href="getstatusanddocs.php" class="current">Get Status and Docs</a>
    	</nav>
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
        <table align="center" style="padding-top: 20px;">
            <tr>
                <td align="center">
                    <span>Do you find this sample useful? Tell your friends!</span><br />
                    <div class="addthis_toolbox addthis_default_style" style="margin-right: auto; margin-left: auto;
                        width: 210px;">
                        <a class="addthis_button_email"></a><a class="addthis_button_tweet" tw:url="http://www.docusign.com/developers-center/"
                            tw:text="I just tried out the DocuSign API!" tw:via="DocuSignAPI" tw:count="none"
                            tw:related="DocuSign:DocuSign, Inc"></a><a class="addthis_button_delicious">
                        </a><a class="addthis_button_stumbleupon"></a><a class="addthis_button_facebook_like"
                            fb:href="http://www.docusign.com/devcenter/"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <span>Keep up with new developments:</span><br />
                    <a class="addthis_email" href="http://www.docusign.com/blog">
                        <img src="images/blog.png" width="16" height="16" border="0" alt="Blog" /></a>
                    <a class="addthis_email" href="http://www.youtube.com/user/ESIGNwithDocuSign">
                        <img src="images/icon-youtube.png" width="16" height="16" border="0" alt="Youtube" /></a>
                    <a class="addthis_email" href="http://www.docusign.com/blog/feed/">
                        <img src="images/icon-rss.png" width="16" height="16" border="0" alt="RSS" /></a>
                    <a class="addthis_email" href="http://www.facebook.com/pages/DocuSign/71115427991">
                        <img src="images/icon-facebook.png" width="16" height="16" border="0" alt="Facebook" /></a>
                    <a class="addthis_email" href="http://www.twitter.com/DocuSign">
                        <img src="images/icon-twitter.png" width="16" height="16" border="0" alt="Twitter" /></a>
                    <a class="addthis_email" href="http://www.linkedin.com/company/19022?trk=saber_s000001e_1000">
                        <img src="images/icon-linkedin.png" width="16" height="16" border="0" alt="LinkedIn" /></a>
                </td>
            </tr>
    	</table>
     </body>
</html>


