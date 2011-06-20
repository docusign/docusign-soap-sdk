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
include_once 'api/APIService.php';
include 'include/utils.php';

//========================================================================
// Functions
//========================================================================

function buildEnvelope() {
    $envelope = new Envelope();
    if (isset($_POST["subject"])) {
        $envelope->Subject = $_POST["subject"];
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
    $envelope->Tabs = addTabs(count($envelope->Recipients));
    $envelope = processOptions($envelope);
    $envelope->Documents = getDocuments();
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
        
        $r->UserName = $_POST["RecipientName"][$i];
        $r->Email = $_POST["RecipientEmail"][$i];
        $r->RequireIDLookup = false;
        
        switch ($_POST["RecipientSecurity"][$i]) {
            case "IDCheck":
                $r->RequireIDLookup = true;
                break;
                
            case "AccessCode":
                $r->AccessCode = $_POST["RecipientSecuritySetting"][$i];
                break;
            
            case "PhoneAuthentication":
                $r->RequireIDLookup = true;
                $r->IDCheckConfigurationName = "Phone Auth $";
                $pa = new RecipientPhoneAuthentication();
                $pa->RecipMayProvideNumber = true;
                $r->PhoneAuthentication = $pa;
                break;
            
            default:
                ;
                break;
        }
        $r->ID = $i;
        $r->Type = RecipientTypeCode::Signer;
        $r->RoutingOrder = $i;
        
        array_push($recipients, $r);
    }
    
    // Remove 0th element because it is empty
    array_shift($recipients);
    
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
    $documents[] = null;
    $id = 1;
    
    if (isset($_POST["stockdoc"])) {
        $d = new Document();
        $d->PDFBytes = file_get_contents("resources/Docusign_Demo_11.pdf");
        $d->Name = "Demo Document";
        $d->ID = $id++;;
        $d->FileExtension = "pdf";
        array_push($documents, $d);
    }
    else {
        foreach ($_FILES_ as $f) {
            if (isset($f["name"])) {
                $d = new Document();
                $d->PDFBytes = file_get_contents($f["tmp_name"]);
                $d->Name = $f["name"];
                $d->ID = $id++;
                $d->FileExtension = substr($f["type"], strpos($f["type"], '/') + 1);
                array_push($documents, $d);
            };
        }
    }
    
    if (isset($_POST["signerattachment"])) {
        $d = new Document();
        $d->PDFBytes = file_get_contents("resources/BlankPDF.pdf");
        $d->Name = "Signer Attachment";
        $d->ID = $id++;
        $d->FileExtension = "pdf";
        $d->AttachmentDescription = "Please attach your document here";
        array_push($documents, $d);
    }
    
    // eliminate empty 0th element
    array_shift($documents);
    
    return $documents;
}

function addTabs($count) {
    $tabs[] = new Tab();
        
    $pageTwo = isset($_POST["stockdoc"]) ? "2" : "1";
    $pageThree = isset($_POST["stockdoc"]) ? "3" : "1";
    
    if (isset($_POST["addsigs"])) {
        $company = new Tab();
        $company->Type = TabTypeCode::Company;
        $company->DocumentID = "1";
        $company->PageNumber = $pageTwo;
        $company->RecipientID = "1";
        $company->XPosition = "342";
        $company->YPosition = "303";
        array_push($tabs, $company);
        
        $init1 = new Tab();
        $init1->Type = TabTypeCode::InitialHere;
        $init1->DocumentID = "1";
        $init1->PageNumber = $pageThree;
        $init1->RecipientID = "1";
        $init1->XPosition = "454";
        $init1->YPosition = "281";
        array_push($tabs, $init1);
        
        $sign1 = new Tab();
        $sign1->Type = TabTypeCode::SignHere;
        $sign1->DocumentID = "1";
        $sign1->PageNumber = $pageTwo;
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
        $fullAnchor->PageNumber = $pageTwo;
        $fullAnchor->RecipientID = "1";
        array_push($tabs, $fullAnchor);
        
        $date1 = new Tab();
        $date1->Type = TabTypeCode::DateSigned;
        $date1->DocumentID = "1";
        $date1->PageNumber = $pageTwo;
        $date1->RecipientID = "1";
        $date1->XPosition = "343";
        $date1->YPosition = "492";
        array_push($tabs, $date1);
        
        $init2 = new Tab();
        $init2->Type = TabTypeCode::InitialHere;
        $init2->DocumentID = "1";
        $init2->PageNumber = $pageThree;
        $init2->RecipientID = "1";
        $init2->XPosition = "179";
        $init2->YPosition = "583";
        $init2->ScaleValue = "0.6";
        array_push($tabs, $init2);
        
        if ($count > 1) {
            $sign2 = new Tab();
            $sign2->Type = TabTypeCode::SignHere;
            $sign2->DocumentID = "1";
            $sign2->PageNumber = $pageTwo;
            $sign2->RecipientID = "1";
            $sign2->XPosition = "338";
            $sign2->YPosition = "330";
            array_push($tabs, $sign2);

            $date2 = new Tab();
            $date2->Type = TabTypeCode::DateSigned;
            $date2->DocumentID = "1";
            $date2->PageNumber = $pageTwo;
            $date2->RecipientID = "1";
            $date2->XPosition = "343";
            $date2->YPosition = "492";
            array_push($tabs, $date2);
        }
        
        if (isset($_POST["formfields"])) {
            $favColor = new Tab();
            $favColor->Type = TabTypeCode::Custom;
            $favColor->CustomTabType = CustomTabType::Text;
            $favColor->DocumentID = "1";
            $favColor->PageNumber = $pageThree;
            $favColor->RecipientID = "1";
            $favColor->XPosition = "301";
            $favColor->YPosition = "416";
            
            if (isset($_POST["collabfields"])) {
                $favColor->SharedTab = true;
                $favColor->RequireInitialOnSharedTabChange = true;
            }
        
            array_push($tabs, $favColor);
        }
        
        if (isset($_POST["conditionalfields"])) {
            $fruitNo = new Tab();
            $fruitNo->Type = TabTypeCode::Custom;
            $fruitNo->CustomTabType = CustomTabType::Radio;
            $fruitNo->CustomTabRadioGroupName= "fruit";
            $fruitNo->TabLabel = "No";
            $fruitNo->Name = "No";
            $fruitNo->DocumentID = "1";
            $fruitNo->PageNumber = $pageThree;
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
            $fruitYes->PageNumber = $pageThree;
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
            $data1->PageNumber = $pageThree;
            $data1->RecipientID = "1";
            $data1->XPosition = "265";
            $data1->YPosition = "547";
            array_push($tabs, $data1);
        }
        
        if (isset($_POST["signerattachment"])) {
            $attach = new Tab();
            $attach->Type = TabTypeCode::SignerAttachment;
            $attach->TabLabel = "Signer Attachment";
            $attach->Name = "Signer Attachment";
            $attach->DocumentID = "2";
            $attach->PageNumber = "1";
            $attach->RecipientID = "1";
            $attach->XPosition = "20";
            $attach->YPosition = "20";
            array_push($tabs, $attach);
        }
    }
    
    // eliminate 0th element
    array_shift($tabs);
    
    return $tabs;
}

function processOptions($envelope) {
    if (isset($_POST["markup"])) {
        $envelope->AllowMarkup = true;
    }
    if (isset($_POST["enablepaper"])) {
        $envelope->EnableWetSign = true;
    }
    if ($_POST["reminders"] != null) {
        $remind = new DateTime($_POST["reminders"]);
        $now = new DateTime("now");
        $days = $now->diff($remind)->d;
        
        if ($envelope->Notification == null) {
            $envelope->Notification = new Notification();
        }
        $envelope->Notification->Reminders = new Reminders();
        $envelope->Notification->Reminders->ReminderEnabled = true;
        $envelope->Notification->Reminders->ReminderDelay = $days;
        $envelope->Notification->Reminders->ReminderFrequency = "2";
    }
    if ($_POST["expiration"] !=null) {
        $expire = new DateTime($_POST["expiration"]);
        $now = new DateTime("now");
        $days = $now->diff($expire)->d;
        
        if ($envelope->Notification == null) {
            $envelope->Notification = new Notification();
        }
        $envelope->Notification->Expirations = new Expirations();
        $envelope->Notification->Expirations->ExpireEnabled = true;
        $envelope->Notification->Expirations->ExpireAfter = $days;
        $envelope->Notification->Expirations->ExpireWarn = $days - 2;
    }
    
    return $envelope;
}

function sendNow($envelope) {
    $api = getAPI();

    $csParams = new CreateAndSendEnvelope();
    $csParams->Envelope = $envelope;
    try {
        $status = $api->CreateAndSendEnvelope($csParams)->CreateAndSendEnvelopeResult;
        if ($status->Status == EnvelopeStatusCode::Sent) {
            addEnvelopeID($status->EnvelopeID);
            header("Location: getstatusanddocs.php?envelopid=" . $status->EnvelopeID . 
            	"&accountID=" . $envelope->AccountId . "&source=Document");
        }
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
}

function embedSending($envelope) {
    $api = getAPI();
    
    $ceParams = new CreateEnvelope();
    $ceParams->Envelope = $envelope;
    try {
        $status = $api->CreateEnvelope($ceParams)->CreateEnvelopeResult;
        if ($status->Status == EnvelopeStatusCode::Created) {
            $rstParam = new RequestSenderToken();
            $rstParam->AccountID = $envelope->AccountId;
            $rstParam->EnvelopeID = $status->EnvelopeID;
            $rstParam->ReturnURL = getCallbackURL("getstatusanddocs.php");
            addEnvelopeID($status->EnvelopeID);
            $_SESSION["embedToken"] = $api->RequestSenderToken($rstParam)->RequestSenderTokenResult;
            header("Location: embedsending.php?envelopid=" . $status->EnvelopeID . 
            	"&accountID=" . $envelope->AccountId . "&source=Document");
            
        }
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
    
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
    echo "";
}
?>

<!DOCTYPE html">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/default.css" />
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
        <table class="tabs">
        <tr>
        	<td class="current">Send Document</td>
        	<td><a href="sendatemplate.php">Send a Template</a></td>
        	<td><a href="embeddocusign.php">Embed Docusign</a></td>
        	<td><a href="getstatusanddocs.php">Get Status and Docs</a></td>
    	</tr>
    	</table>
        <form id="SendDocumentForm" enctype="multipart/form_data" method="post" >
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
            <input type="button" onclick="addRecipientRowToTable()" value="Add Recipient"/>
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
        <?php include 'include/footer.html';?>
    </body>
</html>

