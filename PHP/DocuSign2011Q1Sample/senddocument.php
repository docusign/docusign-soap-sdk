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
 * Illustrates the remote sending experience
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides
// DocuSign api service proxy classes and soapclient
include("api/DSAPIService.php");

//========================================================================
// Functions
//========================================================================
/**
 * Takes credentials, inserts them into HTTP Header, and returns SOAP
 * service object for dsapi
 * 
 * @return DSAPIService
 */
function getDSAPI() {
    loginCheck(); // jumps to login page if not logged in
    
	$dsapi_wsdl = "api/DSAPIService.wsdl";

	// build credential xml to add to http header
	$ds_auth = "
		<DocuSignCredentials>
			<Username>" . $_SESSION["UserID"] . "</Username>
	    	<Password>" . $_SESSION["Password"] . "</Password>
	    	<IntegratorKey>" . $_SESSION["IntegratorsKey"] . "</IntegratorKey>
    	</DocuSignCredentials>";
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

function buildEnvelope() {
    $envelope = new Envelope();
    if (isset($_POST["subject"])) {
        $envelope->Subject = $_POST["subect"];
    }
    else {
        $_SESSION["errorMessage"] = "You must have a subject";
        header("Location: error.php");
    }
    
    if ($_POST["emailBlurb"]) {
        $envelope->EmailBlurb = $_POST["emailBlurb"];
    }
    else {
        $_SESSION["errorMessage"] = "You must have an email blurb";
        header("Location: error.php");
    }
    
    $envelope->AccountId = $_SESSION["AccountID"];
    $envelope->Recipients = constructRecipients();
    $envelope->Documents = getDocuments();
    $envelope->Tabs = addTabs();
    $envelope = processOptions($envelope);
    return $envelope;
}

/**
 * creates a recipient object for each row in the recipientList table and returns
 * an array of them
 * 
 * @return multitype:Recipient 
 */
function constructRecipients() {
    $recipients[] = new Recipient();
    
    $count = count($_POST["RecipientName"]);
    for ($i = 1; $i <= $count; $i++) {
        $r = new Recipient();
        
        $name = $_POST["RecipientName"][$i];
        $r->UserName = $name;
        
        $email = $_POST["RecipientEmail"][$i];
        $r->Email = $email;
        
        if (isset($_POST["RecipientSecurity"][$i])) {
            $r->AccessCode = $accessCode;
        }
        
        $r->ID = $i;
        $r->Type = RecipientTypeCode::Signer;
        
        array_push($recipients, $r);
    }
    
    return $recipients;
}

/**
 * Upload documents to send with the envelope
 * 
 * TODO: currently just uses stock documents only.
 * 
 * @return multitype:Document 
 */
function getDocuments() {
    $document = new Document();
    
    return array($document);
}

function addTabls($count) {
    $tab = new Tab();
    
    return array($tab);
}

function processOptions($envelope) {
    return $envelope;
}

function sendNow($envelope) {
    
}

function embedSending($envelope) {
    
}

//========================================================================
// Main
//========================================================================
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $envelope = buildEnvelope();
    if (isset($_POST["SendNow"])) {
        sendNow($envelope);
    }
    else {
        embedSending($envelope);
    }
}
else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    ;
}
?>

<!DOCTYPE html">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/SendDocument.css" />
        <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
        <script type="text/javascript" src="js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/Utils.js"></script>        
        <script type="text/javascript">
            function EnableDisableDiv() {
                if ($("#sendoption").attr("checked")) {
                    $("#files").hide();
                    $("#files").disableSelection();
                } else {
                    $("#files").show();
                    $("#files").enableSelection();
                }
	        }
	    </script>
        <script type="text/javascript">
            $(document).ready(function () {
                var today = new Date().getDate();
                $("#reminders").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar-blue.gif",
                    buttonImageOnly: true,
                    minDate: today
                });
                $("#expiration").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar-blue.gif",
                    buttonImageOnly: true,
                    minDate: today + 3
                });
                $(".switcher li").bind("click", function () {
                    var act = $(this);
                    $(act).parent().children('li').removeClass("active").end();
                    $(act).addClass("active");
                });
            });
        </script>
    </head>
    <body>
        <form id="SendDocumentForm" method="post" >
            <input id="subject" name="subject" type="text" placeholder="<enter the subject>" autocomplete="off"/>
            <p>
            </p>
            <input id="emailBlurb" name="emailBlurb" type="text" placeholder="<enter e-mail blurb>" autocomplete="off" />
            <p>
            </p>
            <table id="recipientList" name="recipientList" class="recipientList">
                <tr class="recipientListHeader">
                    <th>
                        Recipient
                    </th>
                    <th>
                        E-mail
                    </th>
                    <th>
                        Security
                    </th>
                    <th>
                        Send E-mail Invite
                    </th>
                </tr>
            </table>
            <input type="button" onclick="addRowToTable()" value="Add Recipient"/>
            <div id="files">
            <p>
                Document #1:
                <input class="upload" id="file1" type="file" name="file1" /></p>
            <p>
                Document #2:
                <input class="upload" id="file2" type="file" name="file2"/></p>
                </div>
            <table class="optionlist">
                <tr>
                    <td>
                        <input id="sendoption" class="options" type="checkbox" value="stockdoc" name="stockdoc"  onclick="EnableDisableDiv()"/>
                        Use a stock doc
                    </td>
                    <td rowspan="3">
                        <input type="text" id="reminders" class="datepickers" name="reminders"/><br />
                        Add Daily Reminders
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="options" type="checkbox" value="addsig" name="addsigs" />
                        Add Signatures
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="options" type="checkbox" value="addformfield" name="formfields"/>
                        Add Form Fields
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="options" type="checkbox" value="addcondfield" name="conditionalfields"/>
                        Add Conditional Fields
                    </td>
                    <td rowspan="3">
                        <input type="text" id="expiration" class="datepickers" name="expiration"/><br />
                        Add Expiration
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="options" type="checkbox" name="collabfields" value="addcollfield" />
                        Add Collaborative Fields
                    </td>
                </tr>
                <tr>
                    <td>
                        <input class="options" type="checkbox" name="enablepaper" value="enablepaper" />
                        Enable Signing on Paper
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="options" type="checkbox" name="signerattachment" value="reqattachment" />
                        Request a Signer to Add an Attachment
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input class="options" type="checkbox" name="markup" value="enablemarkup" />
                        Enable Signers to Mark Up the Documents
                    </td>
                </tr>
            </table>
            <p />
            <table class="submit">
                <tr>
                    <td>
                        <input type="submit" value="Send Now" name="SendNow"/>
                    </td>
                    <td>
                        <input type="submit" value="Edit Before Sending" name="EditFirst"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

