<%@ page  session="true" 
    import="net.docusign.sample.Utils"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/homestyle.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
	    <table class="tabs">
	    <tr>
	        <td><a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>">Send Document</a></td>
	        <td><a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</td>
	        <td class="current">Embed Docusign</td>
	        <td><a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a></td>
	    </tr>
	    </table>
    	<form action="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>" method="post" id="EmbedDocuSignForm">
    		<table width="100%">
    			<tr>
    				<td class="rightalign">
    					<input name="<%= Utils.NAME_ONESIGNER %>" id="OneSigner" type="submit" value="Create an Envelope with 1 Signer" />
    				</td>
    				<td class="leftalign">
    					<input name="<%= Utils.NAME_TWOSIGNERS %>" id="TwoSigners" type="submit" value="Create an Envelope with 2 Signers" />
    				</td>
    			</tr>
    		</table>
    		<%
    		  
    		%>
    		<iframe width="100%" height="70%" 
    		  src="<%= session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString() %>" id="hostiframe" name="hostiframe">
    		</iframe>
		</form>
        <%@ include file="footer.html" %>
     </body>
</html>


