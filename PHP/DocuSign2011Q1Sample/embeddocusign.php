<?php 

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
		<table width="100%">
			<tr>
				<td class="rightalign">
					<input id="OneSigner" type="submit" value="Create an Envelope with 1 Signer" />
				</td>
				<td class="leftalign">
					<input id="TwoSigners" type="submit" value="Create an Envelope with 2 Signers" />
				</td>
			</tr>
		</table>
		<iframe width="100%" height="70%" src="" id="hostiframe" name="hostiframe"></iframe>
     </body>
</html>


