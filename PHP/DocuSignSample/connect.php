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


//========================================================================
// Includes
//========================================================================
include_once 'include/session.php'; // initializes session and provides
include_once 'api/APIService.php';
include 'include/utils.php';

//========================================================================
// Functions
//========================================================================

// Figure out the URL of this server
// NOTE: DocuSign only pushes status to HTTPS!
// - but, demo can handle unsecure HTTP
$postBackPath = empty($_SERVER['HTTPS']) ? 'http://' : 'https://';
$postBackPath .= ($_SERVER['SERVER_NAME'] . ':' . $_SERVER['SERVER_PORT'] . $_SERVER['REQUEST_URI'] );

$postedXml = @file_get_contents('php://input');
if ( !empty($_POST['post'])) {
  //
  // if this is a sample load
  $xml = simplexml_load_file("post.sample") or die("Unable to load sample XML file!");
  $xml->EnvelopeStatus->EnvelopeID = guid(); // here we replace the GUID so we have unique files
  //
  // using the curl library to get the post
  if(!function_exists('curl_init')){
  	echo "cURL is not installed. Please install it before proceeding.";
  	exit;
  }
  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, $postBackPath);
  curl_setopt($curl, CURLOPT_HEADER, array("Content-Type: application/xml"));
  curl_setopt($curl, CURLOPT_POST, 1);
  curl_setopt($curl, CURLOPT_POSTFIELDS, $xml->asXML());
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
  $head = curl_exec ( $curl );
  curl_close ($curl); 
  
  pr($head);
  exit;
	
} else if( ! empty( $postedXml ) ) {
  // see if this is a post to this page
  // if it is then we have to save it.
  $xml = simplexml_load_string($postedXml);
  print 'Got Envelope ID: ' . $xml->EnvelopeStatus->EnvelopeID . '<br/>';
  file_put_contents('tmp/'.$xml->EnvelopeStatus->EnvelopeID . '.xml', $xml->asXML());
  if(file_exists('tmp/'.$xml->EnvelopeStatus->EnvelopeID . '.xml')){
  	pr(1);
	} else {
		pr(0);
	}
  exit;
}  

// now load the posts we already have into an array
$posts = array();
if ($dh = opendir('.')) {
    // iterate over file list
    while (($filename = readdir($dh)) !== false) {
        // if filename matches search pattern, print it
        if (ereg('.*\.xml', $filename)) {
            $xml = simplexml_load_file($filename);
            $posts[] = $xml;
        }
    }
    // close directory
    closedir($dh);
}
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
	    <title>DocuSign PHP Push Sample</title>
	    <style type="text/css">
	        .header {
	            margin-bottom: 5px;
	        }
	        .footer {
	            margin-top: 5px;
	        }
	        .comment {
	        	font: Verdana;
	        	font-weight: bold;
	        }
	        table {
	        	font: 11px/24px Verdana;
	        	border-collapse: collapse;
	        	}
	        
	        thead {
	        	padding: 0 0.5em;
	        	text-align: left;
	        	border-top: 1px solid #0087dd;
	        	border-bottom: 1px solid #0087dd;
	        	background: #f0f0f0;
	        	}
	        
	        td {
	        	border-bottom: 1px solid #cacaca;
	        	padding: 0 0.5em;
	        	}
	        
	        td:first-child {
	            text-align: left;
	        	}
	        
	        td+td {
	        	border-left: 1px solid #cacaca;
	        	text-align: center;
	        	}
	
	    </style>
	</head>
	<body>
	    <div class="header"><span class="comment">Here are the currently posted items:</span></div> 
	    <table class="contentTable">
	        <thead>
	            <tr>
	                <td class="headCell">Sender:</td>
	                <td class="headCell">Envelope ID:</td>
	                <td class="headCell">Subject:</td>
	                <td class="headCell">Status:</td>
	                <td class="headCell">Raw XML</td>
	            </tr>
	        </thead>
	        <tbody>
	            <?php foreach( $posts as $post) {?>
	            <tr>
	                <td class="contentCell"><?php echo $post->EnvelopeStatus->UserName ?></td>
	                <td class="contentCell"><?php echo $post->EnvelopeStatus->EnvelopeID ?></td>
	                <td class="contentCell"><?php echo $post->EnvelopeStatus->Subject ?></td>
	                <td class="contentCell"><?php echo $post->EnvelopeStatus->Status ?></td>
	                <td class="contentCell"><a href="<?php echo ($post->EnvelopeStatus->EnvelopeID . '.xml')?>">Download</a></td>
	            </tr><?php } ?>
	        </tbody>
	    </table>
	
	    <div class="footer">
	        <span class="comment">You can test this page by posting a sample post here</span> 
	        <form method="post" action="<?php echo $postBackPath ?>">
	            <input type="hidden" name="post" value="sample"> <input type="submit" value="Submit a sample post">
	        </form>
	    </div>
	</body>
</html>