<%@ page session="true" import="net.docusign.sample.Utils, java.util.*"
         language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/homestyle.css" type="text/css">
    </head>
    <body>
        <% 
          String loginFailed="USER_AUTHENTICATION_FAILED";
          if (session.getAttribute(Utils.SESSION_ERROR_MSG) != null){
              if((session.getAttribute(Utils.SESSION_ERROR_MSG).toString()).equals(loginFailed)){
                  out.println("You typed in your credentials incorrectly, either click<a href=login.jsp> here </a>to go back or click the link below to register for a DevCenter accout.");
                  out.println("<table align='center' class='triangle-isosceles left'>");
                      out.println("<tbody><tr>");
                          out.println("<td><img style='height: 25px; width: 25px' src='images/follow-us_reasonably_small.png' alt=''></td>");
                          out.println("<td><b>Need a DevCenter Account?</b><br>");
                          out.println("Get it <a href='http://www.docusign.com/developers-center/get-free-developer-account'>here</a>.</td>");
                      out.println("</tr>");
                  out.println("</tbody></table>");
              } 
              else{
                  out.println(session.getAttribute(Utils.SESSION_ERROR_MSG).toString());
              }
               
          } 
          else {
            out.println("You shouldn't be on this page");
          } 	
        %> 
    </body>
</html>