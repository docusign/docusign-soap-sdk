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
 * Gathers credentials to authenticate api. Prefill from include/account_creds.php
 * if there's stuff in there.
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides
include 'include/account_creds.php';
include 'api/Credential.php';

//========================================================================
// Library Requirements
//========================================================================

// MCrypt
if(!function_exists('mcrypt_module_get_algo_key_size')){
	//echo "MCrypt not loaded";
	//exit;
}

//========================================================================
// Functions
//========================================================================

/**
 * Returns Credential API SOAP Proxy object
 * 
 * @return  Credential
 */
function getCredentialApi() {
  global $credapi_endpoint;
	$credapi_wsdl = "api/Credential.wsdl";

	$credapi_options =  array('location'=>$credapi_endpoint, 'trace'=>true,
		'features' => SOAP_SINGLE_ELEMENT_ARRAYS);
	try {
	  $credapi = new Credential($credapi_wsdl, $credapi_options);
	} catch (SoapFault $fault) {
		$_SESSION["errorMessage"] = $fault;
	  $credapi = null;
	}
	
	return $credapi;
}

/**
 * Initializes the $_SESSION map that holds the credentials 
 * @param string $user
 * @param string $password
 * @param string $integratorsKey
 */
function initCreds() {
    global $UserID, $Password, $IntegratorsKey;
    
    $_SESSION["UserID"] = $UserID;
    $_SESSION["Password"] = $Password;
    $_SESSION["IntegratorsKey"] = $IntegratorsKey;
    $_SESSION["AccountID"] = "";
}

/**
 * Logs into the DocuSign web service using the Credentials api and sets account 
 * id.
 * 
 * NOTE: Credentials must be set in $_SESSION
 * 
 * @param string $user
 * @param string $password
 * @param string $integratorsKey
 * @return boolean
 */
function login() {
  $retval = false;    
	$_SESSION["errorMessage"] = null;

	// TODO: add error handling
	$credApi = getCredentialApi();

	$login = new Login();
	$login->Email="[" . $_SESSION["IntegratorsKey"] . "]" . $_SESSION["UserID"];
	$login->Password=$_SESSION["Password"];

	try {
		$response = $credApi->Login($login);
    } catch( SoapFault $fault) {
		$_SESSION["errorMessage"] = $fault;
		$retval = false;
		return $retval;
    }
    
    if ($response->LoginResult->Success == true) {
        $_SESSION["AccountID"] = $response->LoginResult->Accounts->Account[0]->AccountID;
        $retval = true;
    }
    else {
    	$_SESSION["errorMessage"] = $response->LoginResult->ErrorCode;
    	$retval = false;
    }
	return $retval;
}

/**
 * Logs out of the DocuSign web service. Deletes $_SESSION
 * 
 * @return boolean
 */
function logout() {
	session_destroy();
	return true;
}

//========================================================================
// Main
//========================================================================
// Did we try to log out?
if(isset($_GET['logout'])){
	logout();
	header("Location: index.php");
}

// if we are already logged in, head to senddocument.php
if (isset($_SESSION["LoggedIn"]) && $_SESSION["LoggedIn"] == true) {
  header("Location: senddocument.php");
} else {
	// Load up the session map
	initCreds($UserID, $Password, $IntegratorsKey);
	// initialize some session variables
	$_SESSION["LoggedIn"] = false;
	$_SESSION["errorMessage"] = null;
	$_SESSION["EnvelopeIDs"] = null;
}

// POST and GET handlers
if ($_SERVER["REQUEST_METHOD"]=="POST") {
    // if the "submit" button has been pressed, test for validity and log us in!
    if (isset($_POST["submit"])) {
        $_SESSION["UserID"] = $_POST["DevCenterEmail"];
        $_SESSION["Password"] = $_POST["DevCenterPassword"];
        $_SESSION["IntegratorsKey"] = $_POST["DevCenterIKey"];
        if (login()) {
            $_SESSION["LoggedIn"] = true;
            header("Location: senddocument.php");
        }
        else {
            $_SESSION["LoggedIn"] = false;
            header("Location: error.php");
        }
    }
    
    // if the "reset" button has been pressed, set all values back to original
    if (isset($_POST["reset"])) {
        initCreds($UserID, $Password, $IntegratorsKey);
    }
}
else if($_SERVER["REQUEST_METHOD"] == "GET") {
    ;
}

?>

<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" href="css/homestyle.css" type="text/css">
    </head>
    <body>
        <div class="dropshadow-bottomleft">
            <p class="leftalign"><b>Log In</b></p>
            <hr />
            <form action="" id="logInForm" method="post">
                <div id="container" class="centeralign" style="width: 300px;">
                    <table style="width: 300px;" align="center">
                    	<tr>
                    		<td>DevCenter E-mail</td>
                    		<td><input id="DevCenterEmail" type="text" name="DevCenterEmail"
                    			value="<?php echo $_SESSION["UserID"]; ?>" /></td>
                    	</tr>
                    	<tr>
                    		<td>Password</td>
                    		<td><input id="DevCenterPassword" type="password"
                    			name="DevCenterPassword" value="<?php echo $_SESSION["Password"]; ?>" /></td>
                    	</tr>
                    	<tr>
                    		<td>Your Integrator Key</td>
                    		<td><input id="DevCenterIKey" type="password" name="DevCenterIKey"
                    			value="<?php echo $_SESSION["IntegratorsKey"]; ?>" /></td>
                    	</tr>
                    </table>
                    <div>
                        <table class="triangle-isosceles left" align="center">
                        	<tr>
                        		<td><img alt="" src="images/follow-us_reasonably_small.png" style="height: 25px; width: 25px" /></td>
                        		<td><b>Need a DevCenter Account?</b><br />
                        			Get it
                        			<a href="http://www.docusign.com/developers-center/get-free-developer-account">
                        				here
                    				</a>.</td>
                        	</tr>
                        </table>
                    </div>
                    <div id="action" class="centeralign" style="width: 200px;">
                    	<input id="Submit1" name="submit" type="submit" value="Login" style="width: 70px; margin-right: 5px;" /> 
                    	<input id="Reset1" name="reset" type="submit" value="Reset" style="width: 70px; margin-left: 5px;" />
                    </div>
                </div>
            </form>
        </div>
        <?php include 'include/footer.html';?>
    </body>
</html>
