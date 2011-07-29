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
  // Post the post.sample just as if we were receiving it from Connect
  // if this is a sample load
  $xml = simplexml_load_file("post.sample") or die("Unable to load sample XML file (post.sample)!");
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
  	
} else if( ! empty( $postedXml ) ) {
  // See if this is a post to this page
  // - if yes, then we have to save it.
  // - your will need a writable "tmp" directory for this to work correctly
  $xml = simplexml_load_string($postedXml);
  print 'Got Envelope ID: ' . $xml->EnvelopeStatus->EnvelopeID . '<br/>';
  file_put_contents('tmp/'.$xml->EnvelopeStatus->EnvelopeID . '.xml', $xml->asXML());
  exit;
}  

// Now load the posts we already have into an array
// - load from "tmp" directory
$posts = array();
if ($dh = opendir('tmp')) {
    // iterate over file list
    while (($filename = readdir($dh)) !== false) {
        // if filename matches search pattern, print it
        if (ereg('.*\.xml', $filename)) {
            $xml = simplexml_load_file("tmp/".$filename);
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
    <link rel="stylesheet" href="css/default.css" />
    <script type="text/javascript" src="js/Utils.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
    <title>DocuSign PHP Push Sample</title>
    
    <style type="text/css">
        .comment {
        	font: Verdana;
        	font-weight: bold;
        }
        
        table.contentTable {
        	font: 11px/24px Verdana;
        	border-collapse: collapse;
        	width:100%;
        }
        
        table.contentTable thead {
        	padding: 0 0.5em;
        	text-align: left;
        	border-top: 1px solid #0087dd;
        	border-bottom: 1px solid #0087dd;
        	background: #f0f0f0;
        }
        
        table.contentTable td {
        	border-bottom: 1px solid #cacaca;
        	padding: 0 0.5em;
        }
        
        table.contentTable td:first-child {
            text-align: left;
        }
        
        table.contentTable td+td {
        	border-left: 1px solid #cacaca;
        	text-align: center;
        }
				
    </style>
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
	        	<td><a href="sendatemplate.php">Send a Template</a></td>
	        	<td><a href="embeddocusign.php">Embed Docusign</a></td>
		    		<td><a href="getstatusanddocs.php">Get Status and Docs</a></td>
		    	</tr>
	    	</table>
				
		    <div class="samplePost">
	        <span class="comment">You can test this page by POSTing a sample post (located at post.sample)</span> 
	        <form method="post" action="<?php echo $postBackPath ?>">
	            <input type="hidden" name="post" value="sample">
				    	<input class="docusignbutton orange" type="submit" value="Submit a sample post">
	        </form>
		    </div>
				
				<br />
				
				<div class="results">
			    <div class="header"><span class="comment">Here are the currently posted items:</span></div> 
			    <table class="contentTable">
			        <thead>
			            <tr>
			                <td class="headCell">Sender</td>
			                <td class="headCell">Envelope ID</td>
			                <td class="headCell">Subject</td>
			                <td class="headCell">Status</td>
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
			                <td class="contentCell"><a href="tmp/<?php echo ($post->EnvelopeStatus->EnvelopeID . '.xml')?>">Download</a></td>
				            </tr>
			            <?php } ?>
			            <? if(empty($posts)){ ?>
			            	
			            	<tr>
			            		<td colspan="5" style="text-align:center;font-style:italic;">
			            			No Envelopes
			            		</td>
			            	</tr>
			            	
			          	<? } ?>
			        </tbody>
		    	</table>
		    </div>
					    
	  	<?php include 'include/footer.html';?>
		</div>
	</body>
</html>