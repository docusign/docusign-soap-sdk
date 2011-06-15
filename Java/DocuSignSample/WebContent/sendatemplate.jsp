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
    <table class="tabs">
    <tr>
        <td><a href="<%= Utils.CONTROLLER_SENDDOCUMENT %>">Send Document</a></td>
        <td class="current">Send a Template</td>
        <td><a href="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>">Embed Docusign</a></td>
        <td><a href="<%= Utils.CONTROLLER_GETSTATUS %>">Get Status and Docs</a></td>
    </tr>
    </table>
    <form id="SendTemplateForm" action="<%= Utils.CONTROLLER_SENDTEMPLATE %>" method="post">
    <div>
        <input id="subject" name="<%= Utils.NAME_SUBJECT %>" placeholder="<enter the subject>" type="text"
            class="email" /><br />
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
    <%@ include file="footer.html" %>
	</body>
</html>
