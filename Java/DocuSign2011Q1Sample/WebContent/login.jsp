<%@ page  session="true" import="net.docusign.sample.Utils" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/homestyle.css" type="text/css">
<title>DocuSign Login</title>
</head>
<body>
<div class="dropshadow-bottomleft">
<p class="leftalign"><b>Log In</b></p>
<hr />
<form action="Login" id="logInForm" method="post">
<div id="container" class="centeralign" style="width: 300px;">
<table style="width: 300px;" align="center">
	<tr>
		<td>DevCenter E-mail</td>
		<td><input id="DevCenterEmail" type="text" name="DevCenterEmail"
			value="<%= session.getAttribute(Utils.SESSION_EMAIL) %>" /></td>
	</tr>
	<tr>
		<td>Password</td>
		<td><input id="DevCenterPassword" type="password"
			name="DevCenterPassword" value="<%= session.getAttribute(Utils.SESSION_PASSWORD) %>"
		/></td>
	</tr>
	<tr>
		<td>Your Integrator Key</td>
		<td><input id="DevCenterIKey" type="text" name="DevCenterIKey"
			value="<%= session.getAttribute(Utils.SESSION_INTEGRATORS_KEY) %>" /></td>
	</tr>
</table>
<div>
<table class="triangle-isosceles left" align="center">
	<tr>
		<td><img alt="" src="images/follow-us_reasonably_small.png"
			style="height: 25px; width: 25px" /></td>
		<td><b>Need a DevCenter Account?</b><br />
		Get it <a
			href="http://www.docusign.com/developers-center/get-free-developer-account">
		here </a>.</td>
	</tr>
</table>
</div>
<div id="action" class="centeralign" style="width: 154px;"><input
	id="Submit1" name="submit" type="submit" value="Submit"
	style="width: 70px; margin-right: 5px;" /> <input id="Reset1"
	name="reset" type="submit" value="Reset"
	style="width: 70px; margin-left: 5px;" /></div>
</div>
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
</form> 
</div>
</body>
</html>