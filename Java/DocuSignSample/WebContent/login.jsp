<%@ page  session="true" import="net.docusign.sample.Utils" language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/jquery.ui.all.css" type="text/css">
        <link rel="stylesheet" href="css/homestyle.css" type="text/css">
        <style>
            .triangle-isosceles{
                position: relative;
                left: 95px;
            }
        </style>
        <title>DocuSign Login</title>
    </head>
    <body>
        <div class="dropshadow-bottomleft">
            <p class="leftalign"><b>Log In</b></p>
        <hr/>
            <form action="Login" id="logInForm" method="post">
                <div id="container" style="width: 350px;">
                    <table style="width: 500px;" align="center">
                        <tr>
                            <td>DevCenter E-mail:</td>
                            <td><input size="50" id="DevCenterEmail" type="text" name="DevCenterEmail"
                                    value="<%= session.getAttribute(Utils.SESSION_EMAIL).toString() %>" /></td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input size="50" id="DevCenterPassword" type="password"
                                    name="DevCenterPassword" value="<%= session.getAttribute(Utils.SESSION_PASSWORD).toString() %>"
                            /></td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                        <tr>
                            <td>Your Integrator Key:</td>
                            <td><input size="50" id="DevCenterIKey" type="text" name="DevCenterIKey"
                                    value="<%= session.getAttribute(Utils.SESSION_INTEGRATORS_KEY).toString() %>" /></td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                    </table>
                <div id="action">
                    <input id="Submit1" name="submit" type="submit" value="Login" style="width: 70px; margin-left: 5px;" />
                    <input id="Reset1" name="reset" type="submit" value="Reset" style="width: 70px; margin-left: 5px;"/></div>
                </div>
            </form>
            <br>
            <table class="triangle-isosceles left">
                <tr>
                    <td><img alt="" src="images/follow-us_reasonably_small.png"
                            style="height: 25px; width: 25px" /></td>
                    <td><b>Need a DevCenter Account?</b><br />
                    Get it <a href="http://www.docusign.com/developers-center/get-free-developer-account">here</a>.</td>
                </tr>
            </table>                         
        </div>
        <%@ include file="footer.html" %>
        <%session.setAttribute(Utils.SESSION_EMBEDTOKEN, "");%>
    </body>
</html>
