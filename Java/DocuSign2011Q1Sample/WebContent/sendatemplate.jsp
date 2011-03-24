<%@ page session="true"
    import="
        net.docusign.sample.Utils,
        net.docusign.api_3_0.*,
        java.util.List,
        java.util.Iterator"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
    <link rel="Stylesheet" href="css/SendTemplate.css" />
    <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
    <script type="text/javascript" src="js/jquery.ui.dialog.js"></script>
    <script type="text/javascript" src="js/jquery.bgiframe-2.1.2.js"></script>
    <script type="text/javascript" src="js/jquery.ui.mouse.js"></script>
    <script type="text/javascript" src="js/jquery.ui.draggable.js"></script>
    <script type="text/javascript" src="js/jquery.ui.position.js"></script>
    <script type="text/javascript" src="js/Utils.js"></script>
    <script type="text/javascript" charset="utf-8">
        $(function () {
            var today = new Date().getDate();
            $("#reminders").datepicker({
                showOn: "button",
                buttonImage: "images/calendar-blue.gif",
                buttonImageOnly: true,
                minDate: today
            });
            $("#expiration").datepicker({
                showOn: "button",
                buttonImage: "images/calendar-blue.gif",
                buttonImageOnly: true,
                minDate: today
            });
            $("#dialogmodal").dialog({
                height: 350,
                modal: true,
                autoOpen: false
            });
            $(".switcher li").bind("click", function () {
            var act = $(this);
            $(act).parent().children('li').removeClass("active").end();
            $(act).addClass("active");
            });
        });

    </script>
	</head>
    <body>
        <nav class="tabs">
            <a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>" class="current">Send Document</a>
            <a href="<%= Utils.CONTROLLER_SENDTEMPLATE %>">Send a Template</a>
            <a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed Docusign</a>
            <a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a>
        </nav>

    <form id="SendTemplateForm" action="<%= Utils.CONTROLLER_SENDTEMPLATE %>" method="post">
    <div>
        <input id="subject" name="<%= Utils.NAME_SUBJECT %>" placeholder="<enter the subject>" type="text"
            class="email" /><img alt="" src="" class="helplink" /><br />
        <textarea id="emailblurb" cols="20" name="<%= Utils.NAME_EMAILBLURB %>" placeholder="<enter the e-mail blurb>"
            rows="4" class="email"></textarea>
    </div>
    <div>
        Select a Template<br />
        <select id="TemplateTable" name="<%= Utils.NAME_TEMPLATETABLE %>" >
        	<%
        	   if (session.getAttribute(Utils.SESSION_TEMPLATES) != null) {
        		   List<EnvelopeTemplateDefinition> tdefs = 
        			   (List<EnvelopeTemplateDefinition>) session.getAttribute(Utils.SESSION_TEMPLATES);
        		   for (EnvelopeTemplateDefinition tdef : tdefs) {
        			   out.println("<option value='" + tdef.getTemplateID() + "'>" +
        					   tdef.getName() + "</option>");
        		   }
        	   }
        	%>
        </select>
<!--         <input type="button" id="selectTemplateButton" name="selectTemplateButton"
            value="Go"  /> 
-->
    </div>
    <br />
    <div>
        <table width="100%" id="RecipientTable" name="RecipientTable" >
            <tr class="rowheader">
                <th class="fivecolumn">
                    <b>Role Name</b>
                </th>
                <th class="fivecolumn">
                    <b>Name</b>
                </th>
                <th class="fivecolumn">
                    <b>E-mail</b>
                </th>
                <th class="fivecolumn">
                    <b>Security</b>
                    <img alt="" src="" class="helplink" />
                </th>
                <th class="fivecolumn">
                    <b>Send E-mail Invite</b>
                </th>
            </tr>
        </table>
         <input type="button" onclick="addRoleRowToTable()" value="Add Role"/>
    </div>
    <div>
        <table width="100%">
            <tr class="rowbody">
                <td class="fourcolumn">
                </td>
                <td class="fourcolumn">
                    <input type="text" id="reminders" name="reminders" class="datepickers" />
                </td>
                <td class="fourcolumn">
                    <input type="text" id="expiration" name="expiration" class="datepickers" />
                </td>
                <td class="fourcolumn">
                </td>
            </tr>
            <tr>
                <td class="fourcolumn">
                </td>
                <td class="fourcolumn">
                    Add Daily Reminders
                </td>
                <td class="fourcolumn">
                    Add Expiration
                </td>
                <td class="fourcolumn">
                </td>
            </tr>
            <tr>
                <td class="fourcolumn">
                </td>
                <td class="leftbutton">
                    <input type="submit" value="Send Now" name="SendNow" align="right" style="width: 100%;"
                        class="docusignbutton blue" />
                </td>
                <td class="rightbutton">
                    <input type="submit" value="Edit Before Sending" name="EditFirst" align="left" style="width: 100%;"
                        class="docusignbutton blue" />
                </td>
                <td class="fourcolumn">
                </td>
            </tr>
        </table>
    </div>
    </form>
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
