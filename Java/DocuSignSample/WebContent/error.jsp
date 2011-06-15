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
    	   if (session.getAttribute(Utils.SESSION_ERROR_MSG) != null) {
    		   out.println(session.getAttribute(Utils.SESSION_ERROR_MSG).toString());
   		   }
    	   else {
    		   out.println("You shouldn't be on this page");
   		   }    	
   		%> 
    </body>
</html>