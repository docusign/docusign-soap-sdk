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
        <table align="center" style="padding-top: 20px;">
            <tr>
                <td align="center">
                    <span>Do you find this sample useful? Tell your friends!</span><br />
                    <div class="addthis_toolbox addthis_default_style" style="margin-right: auto; margin-left: auto;
                        width: 210px;">
                        <a class="addthis_button_email"></a><a class="addthis_button_tweet" tw:url="http://www.docusign.com/developers-center/"
                            tw:text="I just tried out the DocuSign API!" tw:via="DocuSignAPI" tw:count="none"
                            tw:related="DocuSign:DocuSign, Inc"></a><a class="addthis_button_delicious">
                        </a><a class="addthis_button_stumbleupon"></a><a class="addthis_button_facebook_like"
                            fb:href="http://www.docusign.com/devcenter/"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <span>Keep up with new developments:</span><br />
                    <a class="addthis_email" href="http://www.docusign.com/blog">
                        <img src="images/blog.png" width="16" height="16" border="0" alt="Blog" /></a>
                    <a class="addthis_email" href="http://www.youtube.com/user/ESIGNwithDocuSign">
                        <img src="images/icon-youtube.png" width="16" height="16" border="0" alt="Youtube" /></a>
                    <a class="addthis_email" href="http://www.docusign.com/blog/feed/">
                        <img src="images/icon-rss.png" width="16" height="16" border="0" alt="RSS" /></a>
                    <a class="addthis_email" href="http://www.facebook.com/pages/DocuSign/71115427991">
                        <img src="images/icon-facebook.png" width="16" height="16" border="0" alt="Facebook" /></a>
                    <a class="addthis_email" href="http://www.twitter.com/DocuSign">
                        <img src="images/icon-twitter.png" width="16" height="16" border="0" alt="Twitter" /></a>
                    <a class="addthis_email" href="http://www.linkedin.com/company/19022?trk=saber_s000001e_1000">
                        <img src="images/icon-linkedin.png" width="16" height="16" border="0" alt="LinkedIn" /></a>
                </td>
            </tr>
        </table>	
     </body>
</html>


