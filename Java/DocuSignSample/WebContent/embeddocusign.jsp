<%@ page  session="true" 
    import="net.docusign.sample.Utils"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <title>DocuSign Sample</title>
    <head>
        <link rel="stylesheet" href="css/default.css" /> 
        <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/homestyle.css" />
        <link rel="stylesheet" type="text/css" href="css/default.css" />
        <link rel="stylesheet" type="text/css" href="css/Tabs.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <%@include file="header.jsp" %>
    <div style="width:1024px;height:800px;margin-left:auto;margin-right:auto">
        <!--Navigation-->
        <article class="tabs">
            <section>
                <h3><a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>">Send Document</a></h3>
            </section>
            <section>
                <h3><a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</a></h3>
            </section>
            <section class="current">
                <h3><a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed DocuSign</a></h3>
            </section>
            <section>
                <h3><a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a></h3>
            </section>
        </article>
        <!--Do you want to create an experience with one signer, or two sequential signers?-->
        <form action="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>" method="post" id="EmbedDocuSignForm">
            <table width="100%">
                <tr>
                    <td class="rightalign">
                        <input name="<%= Utils.NAME_ONESIGNER %>" class="docusignbutton orange" id="OneSigner" type="submit" value="Create an Envelope with 1 Signer" />
                    </td>
                    <td class="leftalign">
                        <input name="<%= Utils.NAME_TWOSIGNERS %>" class="docusignbutton brown" id="TwoSigners" type="submit" value="Create an Envelope with 2 Signers" />
                    </td>
                </tr>
            </table>
            <!--We're in the signing experience, so display the message and iframe-->
        <% 
            if (!session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString().equals("")) {%>
            <div class="signermessage"><%= session.getAttribute(Utils.MESSAGE_SIGNING).toString() %></div>
            <iframe width="100%" height="70%" 
              src="<%= session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString() %>" id="hostiframe" name="hostiframe">
            </iframe>
            <%
            }
        %>
        </form>
        </div>
        <%@ include file="footer.html" %>
     </body>
</html>