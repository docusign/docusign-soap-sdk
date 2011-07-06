<%session.invalidate();%>
<html>
    <head>
        <meta http-equiv="REFRESH" content="0;url=index.jsp">
    </head>
    <body>
        <p>You shouldn't see this for more than fraction of a second, because all this page does is clear your session and send you back to the login page.</p>
    </body>
+</html>