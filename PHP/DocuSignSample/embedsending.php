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
 * Presents embedded signing.
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides

//========================================================================
// Main
//========================================================================

?>
<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" href="css/default.css" />
        <link rel="stylesheet" href="css/homestyle.css" type="text/css">
    </head>
    <body>
    	<div class="container">
        <table class="tabs" cellspacing="0" cellpadding="0">
	        <tr>
	        	<td><a href="senddocument.php">Send Document</a></td>
	        	<td><a href="sendatemplate.php">Send a Template</a></td>
	        	<td class="current">Embed Docusign</td>
	        	<td><a href="getstatusanddocs.php">Get Status and Docs</a></td>
	    		</tr>
    		</table>
    	</div>
    	<!-- iFrame -->
    	<?php 
    	    if (isset($_SESSION["embedToken"])) {
    	        echo '<iframe src="' . $_SESSION['embedToken'] . '" width="100%" height="720px"></iframe>';
    	    } else {
    	        echo "You shouldn't be on this page";
    	    }
	    ?>
      <?php include 'include/footer.html';?>
    </body>
</html>