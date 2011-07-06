<%@ page  session="true" 
    import="
        net.docusign.sample.Utils,
        net.docusign.api_3_0.*,
        net.docusign.api_3_0.EnvelopeTemplates,
        net.docusign.api_3_0.APIServiceSoap,
        java.util.List,
        java.util.Iterator"
    language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--css style sheets-->
        <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/SendDocument.css" />
        <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <link rel="Stylesheet" href="css/SendTemplate.css" />
        <style>
            iframe{
                height:1000px;
                width:1200px;
            }      
            .logout{
                position: relative;
                left: 800px;
            }
        </style>
        <!--javascript and jquery scripts-->
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
        <script src="../../ui/jquery.ui.tabs.js"></script>
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
        <script type="text/javascript">
            function EnableDisableDiv() {
                if ($("#sendoption").attr("checked")) {
                    $("#files").hide();
                    $("#files").disableSelection();
                } else {
                    $("#files").show();
                    $("#files").enableSelection();
                }
            }
        </script>
        <script type="text/javascript">
            $(document).ready(function () {
                var today = new Date().getDate();
                $(".reminders").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar-blue.gif",
                    buttonImageOnly: true,
                    minDate: today
                });
                $(".expiration").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar-blue.gif",
                    buttonImageOnly: true,
                    minDate: today + 3
                });
                $(".switcher li").bind("click", function () {
                    var act = $(this);
                    $(act).parent().children('li').removeClass("active").end();
                    $(act).addClass("active");
                });
                
                // adding a recipient row as well.
                addRecipientRowToTable();
            });
        </script>
        <script>
            $(document).ready(function() {
                $("#tabs").tabs();
            });
        </script>
    </head>
    <body>
        <% //This next chunk of code loads your Docusign account templates into the current session for use in the send a template tab or separate page.
        APIServiceSoap api = Utils.getAPI(request);
        EnvelopeTemplates templates = api.requestTemplates(session.getAttribute(Utils.SESSION_ACCOUNT_ID).toString(), false);%>
        <p class="logout">Logged in as: <%= session.getAttribute(Utils.SESSION_EMAIL).toString() %>  |  <a href="logout.jsp">Logout</a></p>         
        <div id="tabs">    <!--jquery tabs container-->
            <ul>    <!--jquery tabs links (script turns them into the tab buttons)-->
                <li><a href="#tabs-1">Send Document</a></li>
                <li><a href="#tabs-2">Send a Template</a></li>
                <li><a href="#tabs-3">Embed Docusign</a></li>
                <li><a href="#tabs-4">Get Status and Docs</a></li>
                <li><a href="#tabs-5">View features implemented separately</a></li>  
            </ul>
            <div id="tabs-1"> <!--jquery tab 1, contains the form for sending a document for signing.-->
                <form id="SendDocumentForm" action="SendDocument" method="post" enctype="multipart/form_data" >
                    <input id="subject" name="subject" type="text" placeholder="<enter the subject>" autocomplete="off"/>
                    <p></p>
                    <textarea id="emailblurb1" cols="20" name="emailBlurb" placeholder="<enter the e-mail blurb>" rows="4" class="email"></textarea>
                    <p>
                    </p>
                    <table id="recipientList" name="recipientList" class="recipientList">
                        <tr class="recipientListHeader">
                            <th>
                                Recipient
                            </th>
                            <th>
                                E-mail
                            </th>
                            <th>
                                Security
                            </th>
                            <th>
                                Send E-mail Invite
                            </th>
                        </tr>
                    </table>
                    <input type="button" onclick="addRecipientRowToTable()" value="Add Recipient"/>
                    <div id="files">
                        <p>
                            Document #1:
                            <input class="upload" id="file1" type="file" name="file1" /></p>
                        <p>
                            Document #2:
                            <input class="upload" id="file2" type="file" name="file2"/></p>
                    </div>
                    <table class="optionlist">
                        <tr>
                            <td>
                                <input id="sendoption" class="options" type="checkbox" value="stockdoc" name="stockdoc"  onclick="EnableDisableDiv()"/>
                                Use a stock doc
                            </td>
                            <td rowspan="3">
                                <input type="text" id="reminders" class="datepickers reminders" name="reminders"/><br />
                                Add Daily Reminders
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="options" type="checkbox" value="addsig" name="addsigs"  checked="true"/>
                                Add Signatures
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="options" type="checkbox" value="addformfield" name="formfields" checked="true"/>
                                Add Form Fields
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="options" type="checkbox" value="addcondfield" name="conditionalfields"  checked="true"/>
                                Add Conditional Fields
                            </td>
                            <td rowspan="3">
                                <input type="text" id="expiration" class="datepickers expiration" name="expiration"/><br />
                                Add Expiration
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="options" type="checkbox" name="collabfields" value="addcollfield"  checked="true"/>
                                Add Collaborative Fields
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="options" type="checkbox" name="enablepaper" value="enablepaper" />
                                Enable Signing on Paper
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input class="options" type="checkbox" name="signerattachment" value="reqattachment" />
                                Request a Signer to Add an Attachment
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input class="options" type="checkbox" name="markup" value="enablemarkup"  checked="true"/>
                                Enable Signers to Mark Up the Documents
                            </td>
                        </tr>
                    </table>
                    <p />
                    <table class="submit">
                        <tr>
                            <td>
                                <input type="submit" value="Send Now" name="SendNow"/>
                            </td>
                            <td>
                                <input type="submit" value="Edit Before Sending" name="EditFirst"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div id="tabs-2">    <!--jquery tab 2, contains the form for sending a docusign template.-->
                <%session.setAttribute(Utils.SESSION_TEMPLATES, templates.getEnvelopeTemplateDefinition());%>
                <form id="SendTemplateForm" action="<%= Utils.CONTROLLER_SENDTEMPLATE %>" method="post">
                    <div>
                        <input id="subject" name="<%= Utils.NAME_SUBJECT %>" placeholder="<enter the subject>" type="text" class="email" />
                        <p></p>
                        <textarea id="emailblurb" cols="20" name="<%= Utils.NAME_EMAILBLURB %>" placeholder="<enter the e-mail blurb>" rows="4" class="email"></textarea>
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
                                    <input type="text" id="reminders1" class="datepickers reminders" name="reminders"/>
                                </td>
                                <td class="fourcolumn">
                                    <input type="text" id="expiration1" class="datepickers expiration" name="expiration"/>
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
            </div>
            <div id="tabs-3">    <!--jquery tab 3, contains an iframe which shows the ability to sign a document on your own website.-->
                <form action="<%= Utils.CONTROLLER_EMBEDDOCUSIGN %>" method="post" id="EmbedDocuSignForm">
                    <table width="100%">
                        <tr>
                            <td class="rightalign">
                                <input name="<%= Utils.NAME_ONESIGNER %>" id="OneSigner" type="submit" value="Create an Envelope with 1 Signer" />
                            </td>
                            <td class="leftalign">
                                <input name="<%= Utils.NAME_TWOSIGNERS %>" id="TwoSigners" type="submit" value="Create an Envelope with 2 Signers" />
                            </td>
                        </tr>
                    </table>
                    <iframe <%String EmbedToken=(session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString());
                            if(EmbedToken.length()<1){
                                out.print("style='display:none;'");
                            }%> src=<%out.print("'"+session.getAttribute(Utils.SESSION_EMBEDTOKEN).toString()+"'");%>id="hostiframe" name="hostiframe"></iframe>
                </form>
            </div>
            <div id="tabs-4">    <!--jquery tab 4, contains the code for retrieving status updates on what envelopes you have sent via the example site which this code generates.-->
                 <div id="statusDiv">
                    <table id="statusTable" cellpadding="10px">
                                <tr>
                                        <th>EnvelopeID</th>
                                        <th>Subject</th>
                                        <th>Status</th>
                                        <th>Recpients</th>
                                        <th>Documents</th>
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
                                      out.println("<td>" + "TODO:  recipient info will go here" + "</td>");
                                      out.println("<td>" + "TODO:  document info will go here" + "</td>");
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
            </div>
            <div id="tabs-5">    <!--jquery tab 5, contains links to the previous four features implemented on their own web pages.-->
                <table>
                    <tr>
                        <td><p>Below are links to the features built into separate pages for greater clarity on how each works.</p></td>
                    </tr>
                    <tr>
                        <td><a href="senddocument.jsp">Send Document</a></td>  
                    <tr>
                        <td><a href="sendatemplate.jsp">Send a Template</a></td>
                    </tr>
                    <tr>
                        <td><a href="embeddocusign.jsp">Embed Docusign</a></td>
                    </tr>
                    <tr>
                        <td><a href="getstatusanddocs.jsp">Get Status and Docs</a></td>
                    </tr>
                </table>
            </div>
        <%@ include file="footer.html" %>
        </div>
    </body>
</html>
