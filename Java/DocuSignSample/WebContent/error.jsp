<%@ page session="true" import="net.docusign.sample.Utils, java.util.*"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/default.css" type="text/css">
        <link rel="stylesheet" href="css/Tabs.css" type="text/css">
        <script type="text/javascript">
        function invert(ident) {
            var state = document.getElementById(ident).style.display;
            if (state == 'block') {
                document.getElementById(ident).style.display = 'none';
            } else {
                document.getElementById(ident).style.display = 'block';
            }
        }
        </script>
    </head>
    <body>
        <div style="width:1024px;height:800px;margin-left:auto;margin-right:auto">
            <!--Navigation-->
            <article class="tabs">
                <section>
                    <h3><a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>">Send Document</a></h3>
                </section>
                <section>
                    <h3><a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</a></h3>
                </section>
                <section>
                    <h3><a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed DocuSign</a></h3>
                </section>
                <section>
                    <h3><a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a></h3>
                </section>
            </article>
            <!--Showcases best practices to show a generic error. Option to show details of the error message are for demo purposes only-->
            <p>This demo has encountered an error. <a class="errorShow" onclick="invert('errorMessage')">Click here to get the error details.</a><p> <p class="errorMessage" id="errorMessage">        
            <% 
               if (session.getAttribute(Utils.SESSION_ERROR_MSG) != null) {
                   out.println(session.getAttribute(Utils.SESSION_ERROR_MSG).toString());
               }
               else {
                   out.println("Unspecified error.");
               }        
            %></p>
            <p>Please correct the issue and try again.</p>
            <!--The community forums are where you can find real help from DocuSign-->
            <p>To get help, please visit the <a href="http://community.docusign.com">DocuSign Community Forums</a>.</p>
        </div>
    </body>
</html>