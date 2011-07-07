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
 * uploads selected template, creates an envelope from it, and sends it.
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
function voidSampleEnvelope($envelopeID) {
    $dsapi = getAPI();
    $veParams = new VoidEnvelope();
    $veParams->EnvelopeID = $envelopeID;
    $veParams->Reason = "Envelope voided by sender";
    try {
        $status = $dsapi->VoidEnvelope($veParams)->VoidEnvelopeResult;
        if ($status->VoidSuccess) {
            header("Location: sendatemplate.php");
        }
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
    }
}

function createSampleEnvelope() {
    
    // Create envelope information
    $envinfo = new EnvelopeInformation();
    $envinfo->Subject = $_POST["subject"];
    $envinfo->EmailBlurb = $_POST["emailblurb"];
    $envinfo->AccountId = $_SESSION["AccountID"];
    
    if ($_POST["reminders"] != null) {
        $remind = new DateTime($_POST["reminders"]);
        $new = new DateTime($_SERVER['REQUEST_TIME']);
        $days = $now->diff($remind)->d;
        if ($envinfo->Notification == null) {
            $envinfo->Notification = new Notification();
        }
        $envinfo->Notification->Reminders = new Reminders();
        $envinfo->Notification->Reminders->ReminderEnabled = true;
        $envinfo->Notification->Reminders->ReminderDelay = $days;
        $envinfo->Notification->Reminders->ReminderFrequency = "2";
    }
    
    if ($_POST["expiration"] != null) {
        $expire = new DateTime($_POST["reminders"]);
        $new = new DateTime($_SERVER['REQUEST_TIME']);
        $days = $now->diff($expire)->d;
        if ($envinfo->Notification == null) {
            $envinfo->Notification = new Notification();
        }
        $envinfo->Notification->Expirations = new Expirations();
        $envinfo->Notification->Expirations->ExpireEnabled = true;
        $envinfo->Notification->Expirations->ExpireDelay = $days;
        $envinfo->Notification->Expirations->ExpireFrequency = "2";
    }
    
    // Get all recipients
    $recipients = constructRecipients();
    
    // Construct the template reference
    $tref = new TemplateReference();
    $tref->TemplateLocation = TemplateLocationCode::Server;
    $tref->Template = $_POST["TemplateTable"];
    $tref->RoleAssignments = createFinalRoleAssignments($recipients);
    $trefs = array($tref);
    
    if (isset($_POST["SendNow"])) {
        sendNow($trefs, $envinfo, $recipients);
    }
    else {
        embedSending($trefs, $envinfo, $recipients);
    }
}

/**
 *	TODO: get list of recipients from table
 */
function constructRecipients() {
    $recipients[] = new Recipient();
    
    $count = count($_POST["RoleName"]);
    for ($i = 1; $i <= $count; $i++) {
        if ($_POST["RoleName"] != null) {
            $r = new Recipient();
            $r->UserName = $_POST["Name"][$i];
            $r->Email = $_POST["RoleEmail"][$i];
            $r->ID = $i;
            $r->RoleName = $_POST["RoleName"][$i];
            $r->Type = RecipientTypeCode::Signer;
            array_push($recipients, $r);
        }
    }
    
    // eliminate 0th element
    array_shift($recipients);
    
    return $recipients;
}

/**
 * Create an array of
 * 
 * @return multitype:TemplateReferenceRoleAssignment
 */
function createFinalRoleAssignments($recipients) {
    $roleAssignments[] = new TemplateReferenceRoleAssignment();
    
    foreach ($recipients as $r) {
        $assign = new TemplateReferenceRoleAssignment();
        $assign->RecipientID = $r->ID;
        $assign->RoleName = $r->RoleName;
        array_push($roleAssignments, $assign);
    }
    
    // eliminate 0th element
    array_shift($roleAssignments);
    
    return $roleAssignments;
}

function loadTemplates() {
    $dsapi = getAPI();
    
    $rtParams = new RequestTemplates();
    $rtParams->AccountID = $_SESSION["AccountID"];
    $rtParams->IncludeAdvancedTemplates = "false";
    
    $templates = array();
    try {
      $templates = $dsapi->RequestTemplates($rtParams)->RequestTemplatesResult->EnvelopeTemplateDefinition;
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = $e;
        header("Location: error.php");
        
        //echo "<option value='0'>No Templates Available</option>";
    }
    
    foreach ($templates as $template) {
      echo '<option value="' . $template->TemplateID . '">' .
          $template->Name . "</option>\n";
			// echo $template->TemplateID . " " . $template->Name . "<br />" . "\n";
    }
}

function sendNow($templateReferences, $envelopeInfo, $recipients) {
    $api = getAPI();
    
    $csParams = new CreateEnvelopeFromTemplates();
    $csParams->EnvelopeInformation = $envelopeInfo;
    $csParams->Recipients = $recipients;
    $csParams->TemplateReferences = $templateReferences;
    $csParams->ActivateEnvelope = true;
    try {
        $status = $api->CreateEnvelopeFromTemplates($csParams)->CreateEnvelopeFromTemplatesResult;
        if ($status->Status == EnvelopeStatusCode::Sent) {
            addEnvelopeID($status->EnvelopeID);
            header("Location: getstatusanddocs.php?envelopid=" . $status->EnvelopeID . 
            	"&accountID=" . $envelope->AccountId . "&source=Document");
        }
    } catch (SoapFault $e) {
        $_SESSION["errorMessage"] = array($e, $api->__getLastRequest(), $csParams);
        header("Location: error.php");
    }
}

function embedSending($templateReferences, $envelopeInfo, $recipients) {
    $api = getAPI();
    
    $ceParams = new CreateEnvelopeFromTemplates();
    $ceParams->EnvelopeInformation = $envelopeInfo;
    $ceParams->Recipients = $recipients;
    $ceParams->TemplateReferences = $templateReferences;
    $ceParams->ActivateEnvelope = false;
    try {
        $status = $api->CreateEnvelopeFromTemplates($ceParams)->CreateEnvelopeFromTemplatesResult;
        if ($status->Status == EnvelopeStatusCode::Created) {
            $rstParam = new RequestSenderToken();
            $rstParam->AccountID = $envelopeInfo->AccountId;
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
loginCheck();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  createSampleEnvelope();
} elseif ($_SERVER["REQUEST_METHOD"] == "GET") {
	// Nothing
	
}
?>

<!DOCTYPE html">
<html>
    <head>
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
    <link rel="stylesheet" href="css/default.css" />
    <link rel="Stylesheet" href="css/SendDocument.css" />
    <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
    <script type="text/javascript" src="js/Utils.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
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
                minDate: today
            });
            /*
            $("#dialogmodal").dialog({
                height: 350,
                modal: true,
                autoOpen: false
            });
            */
            $(".switcher li").bind("click", function () {
            var act = $(this);
            $(act).parent().children('li').removeClass("active").end();
            $(act).addClass("active");
            });
        });

    </script>
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
		    	<td class="current">Send a Template</td>
		    	<td><a href="embeddocusign.php">Embed Docusign</a></td>
		    	<td><a href="getstatusanddocs.php">Get Status and Docs</a></td>
				</tr>
			</table>
	    <form id="SendTemplateForm" enctype="multipart/form_data" method="post">
		    <div>
		      <!--<input id="subject" name="subject" placeholder="<enter the subject>" type="text"
		          class="email" />
					<br />
		      <textarea id="emailblurb" cols="20" name="emailblurb" placeholder="<enter the e-mail blurb>"
		          rows="4" class="email"></textarea>
		      -->    
		      <input id="subject" name="subject" type="text" value="Test Subject" placeholder="<enter the subject>" autocomplete="off"/>
          <br />
          <br />
		      <textarea id="emailblurb" cols="20" name="emailBlurb" placeholder="<enter the e-mail blurb>" rows="4" class="email">Test Body</textarea>
          <br />
          <br />
          
		    </div>
		    <div>
		        Select a Template
		        <br />
		        <select id="TemplateTable" name="TemplateTable" >
		        	<?php loadTemplates(); ?>
		        </select>
		    </div>
		    <br />
		    <div>
			    <table width="100%" id="RecipientTable" name="RecipientTable" >
	          <tr class="recipientListHeader">
	              <th>
	                  Role Name
	              </th>
	              <th>
	                  Recipient Name
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
	          
	          <tr id="Role1">
	          	<td>
	          		<input type="text" name="RoleName[1]" id="txtRow1">
	          	</td>
	          	<td>
	          		<input type="text" name="Name[1]" id="txtRow1">
	          	</td>
	          	<td>
	          		<input type="email" name="RoleEmail[1]" id="txtRow1">
	          	</td>
	          	<td>
	          		<select id="RoleSecurity1" name="RoleSecurity[1]" onchange="EnableDisableInput(1,'RoleSecurity');">
	          			<option value="None">None</option><option value="IDCheck">ID Check</option>
	          			<option value="AccessCode">Access Code:</option>
	          			<option value="PhoneAuthentication">Phone Authentication</option>
	          		</select>
	          		<input type="text" name="RoleSecuritySetting[1]" id="RoleSecuritySetting1" value="12345" style="display:none;">
	          	</td>
	          	<td>
	          		<ul class="switcher">
	          			<li class="active">
	          				<a href="#" title="On">ON</a>
	          			</li>
	          			<li>
	          				<a href="#" title="OFF">OFF</a>
	          			</li>
	          			<input title="RoleInviteToggle1" id="RoleInviteToggle1" name="RoleInviteToggle[1]" type="checkbox" style="display: none; ">
	          		</ul>
	          	</td>
	          </tr>
			    </table>
		      <input type="button" onclick="addRoleRowToTable()" value="Add Role"/>
		    </div>
		    <div>
		    	<br />
		      <table width="100%">
		          <tr>
		              <td class="fourcolumn">
		                  <input type="text" id="reminders" name="reminders" class="datepickers" />
		                  <br />
		                  Add Daily Reminders
		              </td>
		          </tr>
		          <tr>
		              <td class="fourcolumn">
		                  <input type="text" id="expiration" name="expiration" class="datepickers" />
		                  <br />
		                  Add Expiration
		              </td>
		          </tr>
		          <!--
		          <tr>
		              <td class="fourcolumn">
		              </td>
		              <td class="leftbutton">
		                  <input type="submit" value="Send Now" name="SendNow" align="right" style="width: 100%;"
		                      class="docusignbutton blue" />
		              </td>
		              <td class="rightbutton">
		                  <input type="submit" value="Edit Before Sending" name="EditFirst" align="left" style="width: 100%;"
		                      class="docusignbutton blue" />
		              </td>
		              <td class="fourcolumn">
		              </td>
		          </tr>
		          -->
		      </table>
		    </div>
		    
          <table class="submit">
              <tr>
                  <td>
                      <input class="docusignbutton orange" type="submit" value="Send Now" name="SendNow"/>
                  </td>
                  <td>
                      <input class="docusignbutton brown" type="submit" value="Edit Before Sending" name="EditFirst"/>
                  </td>
              </tr>
          </table>
          
	    </form>
      <?php include 'include/footer.html'; ?>
  	</div>
	</body>
</html>