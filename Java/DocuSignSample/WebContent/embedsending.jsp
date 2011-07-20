<%@ page session="true" language="java" contentType="text/html; charset=UTF-8"
    import="net.docusign.sample.Utils, java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>API Sample on Embedded Sending</title>
	<link rel="stylesheet" href="css/homestyle.css" type="text/css">
</head>
    <body>
    	<% 
    	    if (session.getAttribute(Utils.SESSION_EMBEDTOKEN) != null) {
    	        out.println("<iframe src=\"" +
    	        		session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString() +
    	        		"\" width=\"100%\" height=\"720px\"></iframe>");
    	    }
    	    else {
    	        out.println("You shouldn't be on this page");
    	    }
	    %>
        <%@ include file="footer.html" %>
    </body>
</html>