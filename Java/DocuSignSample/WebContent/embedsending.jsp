<%@ page session="true" language="java" contentType="text/html; charset=UTF-8"
    import="net.docusign.sample.Utils, java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>API Sample on Embedded Sending</title>
	<link rel="stylesheet" href="css/homestyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/Tabs.css" />
	<link rel="stylesheet" type="text/css" href="css/default.css" />
</head>
    <body>
        <div style="width:1024px;height:800px;margin-left:auto;margin-right:auto">
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
        </div>
    </body>
</html>