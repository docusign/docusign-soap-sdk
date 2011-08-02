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
    flush();
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
        return com_create_guid();
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

