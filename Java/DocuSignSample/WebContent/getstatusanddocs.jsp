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
<title>API Sample on how to get status and documents</title>
        <link rel="stylesheet" href="css/default.css" /> 
        <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/GetStatusAndDocs.css" />
        <link rel="stylesheet" type="text/css" href="css/Tabs.css" />
        <script type="text/javascript" src="js/Utils.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%@include file="header.jsp" %>
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
    		<section class="current">
    			<h3><a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a></h3>
    		</section>
    	</article>
    	<form action="<%= Utils.CONTROLLER_GETSTATUS %>" method="post" id="GetStatusForm">
    	<% 
    	    if (session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString().equals("")) {%>
    	<div id="statusDiv">
            	<ul style="list-style:none">
        		<%
        		  Boolean hasEnv = false;
        		  if (session.getAttribute(Utils.SESSION_STATUSES) != null) {
                      List<EnvelopeStatus> envelopes = 
                          (List<EnvelopeStatus>) session.getAttribute(Utils.SESSION_STATUSES);
                      if (envelopes.size() > 0) {
                    	  hasEnv = true;
                    	  for (EnvelopeStatus env : envelopes) {
                    	      out.println("<li>");
                    	      out.println("<span onclick=\"invert('" + env.getEnvelopeID() + "');\"><img alt=\"Plus\" src=\"images/plus.png\"></span>");
                    	      out.println(" " + env.getSubject() + " (" + env.getStatus() + ") - " + env.getEnvelopeID());
                    	      out.println("<ul style=\"list-style-type: none; display:none;\" id=\"" + env.getEnvelopeID() + "\">");
                    	      for (RecipientStatus rec : env.getRecipientStatuses().getRecipientStatus()) {
                    	          out.println("<li>");
                    	          out.println("<span onclick=\"invert('" + env.getEnvelopeID() + "Recipient" + rec.getUserName() + "');\"><img alt=\"Plus\" src=\"images/plus.png\"></span>");
                    	          if (rec.getClientUserId() != null && rec.getStatus().toString() != "COMPLETED") {
                    	              out.println("Recipient (" + rec.getType() + ") - " + rec.getUserName() + " <input name=\"SignDocEnvelope+" + env.getEnvelopeID() + "&Email+" + rec.getEmail() + "&UserName+" + rec.getUserName() + "&CID+" + rec.getClientUserId() + "\" type=\"submit\" value=\"Start Signing\">");
                    	          }
                    	          else{
                    	              out.println("Recipient (" + rec.getType() + ") - " + rec.getUserName());
                    	          }
                    	          
                    	          out.println("<ul style=\"list-style-type: none; display: none;\" id=\"" + env.getEnvelopeID() + "Recipient" + rec.getUserName() + "\">");
                    	          for (TabStatus tab : rec.getTabStatuses().getTabStatus()) {
                    	              out.println("<li>" + tab.getTabName() + ": " + tab.getTabValue() + "</li>");
                    	          }
                    	          out.println("</ul>");
                    	          out.println("</li>");
                    	      }
                    	      if (env.getStatus().toString() == "COMPLETED"){
                    	          out.println("<li><span onclick=\"invert('"+env.getEnvelopeID() + "Documents');\"><img alt=\"Plus\" src=\"images/plus.png\"></span>                    Documents <input name=\"DownloadDocEnvelope+" + env.getEnvelopeID() + "\" type=\"submit\" value=\"Download\">");
                    	      }
                    	      else {
                    	          out.println("<li><span onclick=\"invert('"+env.getEnvelopeID() + "Documents');\"><img alt=\"Plus\" src=\"images/plus.png\"></span>                    Documents");
                    	      }
                    	      
                    	      out.println("<ul style=\"list-style-type: none; display: none; \" id=\"" + env.getEnvelopeID() + "Documents\">");
                    	      for (DocumentStatus doc : env.getDocumentStatuses().getDocumentStatus()) {
                    	          out.println("<li>" + doc.getName() + "</li>");
                    	      }
                    	      out.println("</ul>");
                    	      out.println("</li>");
                    	      out.println("</ul>");
                    	      out.println("</li>");
                          }
                      }
        		  }

        		%>
        		</ul>
		</div>
		<%
    	    }
	    %>
		<% 
    	    if (!session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString().equals("")) {%>
    	        <iframe src="<%= session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString() %>" width="100%" height="720px"></iframe>
    	    <%
    	    }
	    %>
		</form>
		</div>
        <%@ include file="footer.html" %>
     </body>
</html>


