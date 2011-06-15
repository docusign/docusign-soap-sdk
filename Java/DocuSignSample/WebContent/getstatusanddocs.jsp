<%@ page  session="true" 
    import="
        net.docusign.sample.Utils, 
        net.docusign.api_3_0.*,
        java.util.List,
        java.util.Iterator" 
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
        <link rel="stylesheet" type="text/css" href="css/GetStatusAndDocs.css" />
        <script type="text/javascript" src="js/Utils.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table class="tabs">
        <tr>
            <td><a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>">Send Document</a></td>
            <td><a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</a></td>
            <td><a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed Docusign</a></td>
            <td class="current">Get Status and Docs</td>
        </tr>
        </table>
    	<div id="statusDiv">
            <table id="statusTable">
        		<tr>
        			<th>EnvelopeID</th>
        			<th>Subject</th>
        			<th>Status</th>
        		</tr>
        		<%
        		  Boolean hasEnv = false;
        		  if (session.getAttribute(Utils.SESSION_STATUSES) != null) {
                      List<EnvelopeStatus> envelopes = 
                          (List<EnvelopeStatus>) session.getAttribute(Utils.SESSION_STATUSES);
                      if (envelopes.size() > 0) {
                    	  hasEnv = true;
                    	  for (EnvelopeStatus env : envelopes) {
                              out.println("<tr>");
                              out.println("<td>" + env.getEnvelopeID() + "</td>");
                              out.println("<td>" + env.getSubject() + "</td>");
                              out.println("<td>" + env.getStatus() + "</td>");
                              out.println("</tr>");
                          }
                      }
        		  }
        		  if (hasEnv == false) {
        			  out.println("<tr><td colspan=3 style='text-align:center'>No envelopes yet</td></tr>");
        		  }

        		%>
            </table>
		</div>
        <%@ include file="footer.html" %>
     </body>
</html>


