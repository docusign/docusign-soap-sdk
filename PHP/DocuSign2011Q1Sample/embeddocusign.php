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
 * Display embedded document for signing
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides
include 'include/utils.php';

//========================================================================
// globals
//========================================================================
$_oneSigner = true;

//========================================================================
// Functions
//========================================================================

/**
 * Creates an embedded signing experience.
 */
function createAndSend() {
    global $_oneSigner;
    $status = "";
    
    // Construct basic envelope
    $env = new Envelope();
    $env->Subject = "DocuSign API SDK Sample";
    $env->EmailBlurb = "This envelope demonstrates embedded signing";
    $env->AccountId = $_SESSION["AccountID"];
    
    $env->Recipients = constructRecipients($_oneSigner);
    
    $doc = new Document();
    $doc->PDFBytes = file_get_contents("resources/Docusign_Demo_11.pdf");
    $doc->Name = "Demo Document";
    $doc->ID = "1";
    $doc->FileExtension = "pdf";
    $env->Documents = array($doc);
    
    $env->Tabs = addTabs(count($env->Recipients));
    
    $api = getAPI();
    try {
        $csParams = new CreateAndSendEnvelope();
        $csParams->Envelope = $env;
        $status = $api->CreateAndSendEnvelope($csParams)->CreateAndSendEnvelopeResult;
        addEnvelopeID($status->EnvelopeID);
        getToken($status, 0);
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
}

/**
 * Construct recipients
 * 
 * @param boolean $oneSigner
 * 	true - create one recipient
 * 	false = create two recipient
 * 
 * @return Recipient[]
 */
function constructRecipients() {
    global $_oneSigner;
    
    $recipients[] = new Recipient();
    
    $r1 = new Recipient();
    $r1->UserName = $_SESSION["UserID"];
    $r1->Email = $_SESSION["UserID"];
    $r1->ID = 1;
    $r1->Type = RecipientTypeCode::Signer;
    $r1->CaptiveInfo = new RecipientCaptiveInfo();
    $r1->CaptiveInfo->ClientUserId = 1;
    array_push($recipients, $r1);
    
    if ($_oneSigner != true) {
        $r2 = new Recipient();
        $r2->UserName = "DocuSign Recipient2";
        $r2->Email = "DocuSignRecipient2@mailinator.com";
        $r2->ID = 2;
        $r2->Type = RecipientTypeCode::Signer;
        $r2->CaptiveInfo = new RecipientCaptiveInfo();
        $r2->CaptiveInfo->ClientUserId = 2;
        array_push($recipients, $r2);
    }    
    
    // remove 0th element
    array_shift($recipients);
    
    return $recipients;
}

function addTabs($count) {
    $tabs[] = new Tab();
    
    $company = new Tab();
    $company->Type = TabTypeCode::Company;
    $company->DocumentID = "1";
    $company->PageNumber = "2";
    $company->RecipientID = "1";
    $company->XPosition = "342";
    $company->YPosition = "303";
    array_push($tabs, $company);
        
    $init1 = new Tab();
    $init1->Type = TabTypeCode::InitialHere;
    $init1->DocumentID = "1";
    $init1->PageNumber = "3";
    $init1->RecipientID = "1";
    $init1->XPosition = "454";
    $init1->YPosition = "281";
    array_push($tabs, $init1);
        
    $sign1 = new Tab();
    $sign1->Type = TabTypeCode::SignHere;
    $sign1->DocumentID = "1";
    $sign1->PageNumber = "2";
    $sign1->RecipientID = "1";
    $sign1->XPosition = "338";
    $sign1->YPosition = "330";
    array_push($tabs, $sign1);
        
    $fullAnchor = new Tab();
    $fullAnchor->Type = TabTypeCode::FullName;
    $anchor = new AnchorTab();
    $anchor->AnchorTabString = "Client Name (printed)";
    $anchor->XOffset = -123;
    $anchor->YOffset = 31;
    $anchor->Unit = UnitTypeCode::Pixels;
    $anchor->IgnoreIfNotPresent = true;
    $fullAnchor->AnchorTabItem = $anchor;
    $fullAnchor->DocumentID = "1";
    $fullAnchor->PageNumber = "2";
    $fullAnchor->RecipientID = "1";
    array_push($tabs, $fullAnchor);
        
    $date1 = new Tab();
    $date1->Type = TabTypeCode::DateSigned;
    $date1->DocumentID = "1";
    $date1->PageNumber = "2";
    $date1->RecipientID = "1";
    $date1->XPosition = "343";
    $date1->YPosition = "492";
    array_push($tabs, $date1);
        
    $init2 = new Tab();
    $init2->Type = TabTypeCode::InitialHere;
    $init2->DocumentID = "1";
    $init2->PageNumber = "3";
    $init2->RecipientID = "1";
    $init2->XPosition = "179";
    $init2->YPosition = "583";
    $init2->ScaleValue = "0.6";
    array_push($tabs, $init2);
        
    if ($count > 1) {
        $sign2 = new Tab();
        $sign2->Type = TabTypeCode::SignHere;
        $sign2->DocumentID = "1";
        $sign2->PageNumber = "3";
        $sign2->RecipientID = "2";
        $sign2->XPosition = "339";
        $sign2->YPosition = "97";
        array_push($tabs, $sign2);

        $date2 = new Tab();
        $date2->Type = TabTypeCode::DateSigned;
        $date2->DocumentID = "1";
        $date2->PageNumber = "3";
        $date2->RecipientID = "2";
        $date2->XPosition = "339";
        $date2->YPosition = "97";
        array_push($tabs, $date2);
    }
        
    $favColor = new Tab();
    $favColor->Type = TabTypeCode::Custom;
    $favColor->CustomTabType = CustomTabType::Text;
    $favColor->DocumentID = "1";
    $favColor->PageNumber = "3";
    $favColor->RecipientID = "1";
    $favColor->XPosition = "301";
    $favColor->YPosition = "416";
    array_push($tabs, $favColor);
    
    $fruitNo = new Tab();
    $fruitNo->Type = TabTypeCode::Custom;
    $fruitNo->CustomTabType = CustomTabType::Radio;
    $fruitNo->CustomTabRadioGroupName= "fruit";
    $fruitNo->TabLabel = "No";
    $fruitNo->Name = "No";
    $fruitNo->DocumentID = "1";
    $fruitNo->PageNumber = "3";
    $fruitNo->RecipientID = "1";
    $fruitNo->XPosition = "296";
    $fruitNo->YPosition = "508";
    array_push($tabs, $fruitNo);
    
    $fruitYes = new Tab();
    $fruitYes->Type = TabTypeCode::Custom;
    $fruitYes->CustomTabType = CustomTabType::Radio;
    $fruitYes->CustomTabRadioGroupName= "fruit";
    $fruitYes->TabLabel = "Yes";
    $fruitYes->Name = "Yes";
    $fruitYes->Value = "Yes";
    $fruitYes->DocumentID = "1";
    $fruitYes->PageNumber = "3";
    $fruitYes->RecipientID = "1";
    $fruitYes->XPosition = "202";
    $fruitYes->YPosition = "509";
    array_push($tabs, $fruitYes);
        
    $data1 = new Tab();
    $data1->Type = TabTypeCode::Custom;
    $data1->CustomTabType = CustomTabType::Text;
    $data1->ConditionalParentLabel = "fruit";
    $data1->ConditionalParentValue = "Yes";
    $data1->TabLabel = "Preferred Fruit";
    $data1->Name = "Fruit";
    $data1->DocumentID = "1";
    $data1->PageNumber = "3";
    $data1->RecipientID = "1";
    $data1->XPosition = "265";
    $data1->YPosition = "547";
    array_push($tabs, $data1);
    
    // eliminate 0th element
    array_shift($tabs);
    
    return $tabs;
}

function getToken($status, $index) {
    global $_oneSigner;
    $token = null;
    $_SESSION["embedToken"];
       
    // get recipient token
    $assertion = new RequestRecipientTokenAuthenticationAssertion();
    $assertion->AssertionID = guid();
    $assertion->AuthenticationInstant = todayXsdDate();
    $assertion->AuthenticationMethod = RequestRecipientTokenAuthenticationAssertionAuthenticationMethod::Password;
    $assertion->SecurityDomain = "DocuSign2011Q1Sample";
    
    $recipient = $status->RecipientStatuses->RecipientStatus[$index];
    
    $urls = new RequestRecipientTokenClientURLs();
    $urlbase = getCallbackURL("pop.html") . "?source=Embedded";
    
    $urls->OnAccessCodeFailed = $urlbase . "&event=AccessCodeFailed&uname=" . $recipient->UserName;
    $urls->OnCancel = $urlbase . "&event=Cancel&uname=" . $recipient->UserName;
    $urls->OnDecline = $urlbase . "&event=Decline&uname=" . $recipient->UserName;
    $urls->OnException = $urlbase . "&event=Exception&uname=" . $recipient->UserName;
    $urls->OnFaxPending = $urlbase . "&event=FaxPending&uname=" . $recipient->UserName;
    $urls->OnIdCheckFailed = $urlbase . "&event=IdCheckFailed&uname=" . $recipient->UserName;
    $urls->OnSessionTimeout = $urlbase . "&event=SessionTimeout&uname=" . $recipient->UserName;
    $urls->OnTTLExpired = $urlbase . "&event=TTLExpired&uname=" . $recipient->UserName;
    $urls->OnViewingComplete = $urlbase . "&event=ViewingComplete&uname=" . $recipient->UserName;
    if ($_oneSigner) {
        $urls->OnSigningComplete = $urlbase . "&event=SigningComplete&uname=" . $recipient->UserName;
    }
    else {
        $urls->OnSigningComplete = getCallbackURL("pop2.html") . "?envelopeID=" . $status->EnvelopeID;
    }
    
    $api = getAPI();
    $rrtParams = new RequestRecipientToken();
    $rrtParams->AuthenticationAssertion = $assertion;
    $rrtParams->ClientURLs = $urls;
    $rrtParams->ClientUserID = $recipient->ClientUserId;
    $rrtParams->Email = $recipient->Email;
    $rrtParams->EnvelopeID = $status->EnvelopeID;
    $rrtParams->Username = $recipient->UserName;
    
    try {
        $token = $api->RequestRecipientToken($rrtParams)->RequestRecipientTokenResult;
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
    
     $_SESSION["embedToken"] = $token;
}

function getStatus($envelopeID) {
    $status = null;
    
    $api = getAPI();
    
    $rsParams = new RequestStatus();
    $rsParams->EnvelopeID = $envelopeID;
    
    try {
        $status = $api->RequestStatus($rsParams)->RequestStatusResult;
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
    
    return $status;
}

//========================================================================
// Main
//========================================================================
loginCheck();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $_oneSigner = isset($_POST["OneSigner"]);
    createAndSend();
}
else if ($_SERVER["REQUEST_METHOD"] == "GET") {
    if (isset($_GET["envelopeID"])) {
        getToken(getStatus($_GET["envelopeID"]), 1);
    }
    else {
        $_SESSION["embedToken"] = "";
    }
}
?>

<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/homestyle.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="tabs">
        	<a href="senddocument.php">Send Document</a>
        	<a href="sendatemplate.php">Send a Template</a>
        	<a href="embeddocusign.php" class="current">Embed Docusign</a>
        	<a href="getstatusanddocs.php">Get Status and Docs</a>
    	</nav>
    	<form method="post" id="EmbedDocuSignForm">
    		<table width="100%">
    			<tr>
    				<td class="rightalign">
    					<input name="OneSigner" id="OneSigner" type="submit" value="Create an Envelope with 1 Signer" />
    				</td>
    				<td class="leftalign">
    					<input name="TwoSigners" id="TwoSigners" type="submit" value="Create an Envelope with 2 Signers" />
    				</td>
    			</tr>
    		</table>
    		<iframe width="100%" height="70%" src="<?php echo $_SESSION["embedToken"]; ?>" id="hostiframe" name="hostiframe"></iframe>
		</form>
        <?php include 'include/footer.html';?>
     </body>
</html>


