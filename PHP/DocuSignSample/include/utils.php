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

include_once 'include/account_creds.php'; //endpoints 'n such
// DocuSign api service proxy classes and soapclient
include_once 'api/APIService.php';
// TODO: put in your timezone or make it null
$TimeZone = 'America/Los_Angeles';

/**
 * Prints variable dump pretty for debug and browser
 * @param unknown_type $val
 */
function print_r2($val) {
    echo '<pre>';
    print_r($val);
    echo  '</pre>';
}

/**
 * Prints variable dump pretty for debug and browser
 * @param unknown_type $val
 */
function pr($val) {
    echo '<pre>';
    print_r($val);
    echo  '</pre>';
}

/**
 * Returns xsd format datetime for start of today
 * @return string
 */
function todayXsdDate() {
    global $TimeZone;
    if ($TimeZone != null) {
        date_default_timezone_set($TimeZone);
    }
    return (date("Y") . "-" . date("m") . "-" . date("d") . "T00:00:00.00");
}

/**
 * Returns xsd format datetime for now
 * @return string
 */
function nowXsdDate() {
    global $TimeZone;
    if ($TimeZone != null) {
        date_default_timezone_set($TimeZone);
    }
    return (date("Y") . "-" . date("m") . "-" . date("d") . "T" . date("H") . ":" . date("i") . ":" . date("s"));
}

/**
 * A guid maker for all seasons (note that com_create_guid() only works on windows
 * @return string
 */
function guid(){
    if (function_exists('com_create_guid')){
        // somehow the function com_create_guid includes {}, while our webservice
        // doesn't.  Here we are changint the format by taking those curly braces out.
        $uuid = com_create_guid();
        $uuid = str_ireplace("{", "", $uuid );
        $uuid = str_ireplace("}", "", $uuid );
        return $uuid;
    }else{
        mt_srand((double)microtime()*10000);//optional for php 4.2.0 and up.
        $charid = strtoupper(md5(uniqid(rand(), true)));
        $hyphen = chr(45);// "-"
        $uuid = chr(123)// "{"
        .substr($charid, 0, 8).$hyphen
        .substr($charid, 8, 4).$hyphen
        .substr($charid,12, 4).$hyphen
        .substr($charid,16, 4).$hyphen
        .substr($charid,20,12)
        .chr(125);// "}"
        return $uuid;
    }
}


function curPageURL() {
    $pageURL = 'http';
    if (isset($_SERVER["HTTPS"]) && $_SERVER["HTTPS"] == "on") {$pageURL .= "s";}
    $pageURL .= "://";
    if ($_SERVER["SERVER_PORT"] != "80") {
        $pageURL .= $_SERVER["SERVER_NAME"].":".$_SERVER["SERVER_PORT"].$_SERVER["REQUEST_URI"];
    } else {
        $pageURL .= $_SERVER["SERVER_NAME"].$_SERVER["REQUEST_URI"];
    }
    return $pageURL;
}

function getCallbackURL($callbackPage){
    $urlbase =  curPageURL();
    $urlbase = substr($urlbase, 0, strrpos($urlbase, '/'));
    $urlbase = $urlbase . "/" . $callbackPage;
    return $urlbase;
}

/**
 * Gets APIService SOAP client proxy that uses SOAP header authentication
 * @return APIService
 */
function getAPI() {
    loginCheck();
    global $api_endpoint;
    $api_wsdl = "api/APIService.wsdl";
    $api_options =  array('location'=>$api_endpoint,'trace'=>true,'features' => SOAP_SINGLE_ELEMENT_ARRAYS);
    $api = new APIService($api_wsdl, $api_options);
    $api->setCredentials("[" . $_SESSION["IntegratorsKey"] . "]" . $_SESSION["UserID"], $_SESSION["Password"]);
    
    return $api;
}

function addEnvelopeID($envelopeID) {
    if (isset($_SESSION["EnvelopeIDs"])) {
        array_push($_SESSION["EnvelopeIDs"], $envelopeID);
    }
    else {
        $_SESSION["EnvelopeIDs"] = array($envelopeID);
    }
}

function getLastEnvelopeID() {
    $index = count($_SESSION["EnvelopeIDs"]) - 1;
    return $_SESSION["EnvelopeIDs"][$index];
}
