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
 * Home page for all of the other functional groups.
 */

//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides

//========================================================================
// Functions
//========================================================================

//========================================================================
// Main
//========================================================================
loginCheck();

?>

<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/homestyle.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DocuSign PHP SDK Sample</title>
        <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
        <script type="text/javascript">
            $(function(){
                $('article.tabs section > h3').click(function(){
                    $('article.tabs section').removeClass('current');
                    $(this) .closest('section').addClass('current');
                });
            });
        </script>
    </head>
    <body>
        <article class="tabs">
            <section class="current">
                <h3>Send Document</h3>
                <iframe src="senddocument.php"></iframe>
            </section>
            <section>
                <h3>Send a Template</h3>
                <iframe src="sendatemplate.php"></iframe>
            </section>
            <section>
                <h3>Embed DocuSign</h3>
                <iframe src="embeddocusign.php"></iframe>
            </section>
            <section>
                <h3>Get Status and Docs</h3>
                <iframe src="getstatusanddocs.php"></iframe>
            </section>
        </article>
    </body>
</html>
