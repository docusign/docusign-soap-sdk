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
        <nav class="tabs">
            <a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>" class="current">Send Document</a>
            <a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</a>
            <a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed Docusign</a>
            <a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a>
    	</nav>
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
                          for (Iterator<EnvelopeStatus> iter = envelopes.iterator(); iter.hasNext();) {
                              EnvelopeStatus env = iter.next();
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
        <table align="center" style="padding-top: 20px;">
            <tr>
                <td align="center">
                    <span>Do you find this sample useful? Tell your friends!</span><br />
                    <div class="addthis_toolbox addthis_default_style" style="margin-right: auto; margin-left: auto;
                        width: 210px;">
                        <a class="addthis_button_email"></a><a class="addthis_button_tweet" tw:url="http://www.docusign.com/developers-center/"
                            tw:text="I just tried out the DocuSign API!" tw:via="DocuSignAPI" tw:count="none"
                            tw:related="DocuSign:DocuSign, Inc"></a><a class="addthis_button_delicious">
                        </a><a class="addthis_button_stumbleupon"></a><a class="addthis_button_facebook_like"
                            fb:href="http://www.docusign.com/devcenter/"></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <span>Keep up with new developments:</span><br />
                    <a class="addthis_email" href="http://www.docusign.com/blog">
                        <img src="images/blog.png" width="16" height="16" border="0" alt="Blog" /></a>
                    <a class="addthis_email" href="http://www.youtube.com/user/ESIGNwithDocuSign">
                        <img src="images/icon-youtube.png" width="16" height="16" border="0" alt="Youtube" /></a>
                    <a class="addthis_email" href="http://www.docusign.com/blog/feed/">
                        <img src="images/icon-rss.png" width="16" height="16" border="0" alt="RSS" /></a>
                    <a class="addthis_email" href="http://www.facebook.com/pages/DocuSign/71115427991">
                        <img src="images/icon-facebook.png" width="16" height="16" border="0" alt="Facebook" /></a>
                    <a class="addthis_email" href="http://www.twitter.com/DocuSign">
                        <img src="images/icon-twitter.png" width="16" height="16" border="0" alt="Twitter" /></a>
                    <a class="addthis_email" href="http://www.linkedin.com/company/19022?trk=saber_s000001e_1000">
                        <img src="images/icon-linkedin.png" width="16" height="16" border="0" alt="LinkedIn" /></a>
                </td>
            </tr>
    	</table>
     </body>
</html>


