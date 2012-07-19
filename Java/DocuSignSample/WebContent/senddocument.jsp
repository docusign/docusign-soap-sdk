<%@ page  session="true" import="net.docusign.sample.Utils" language="java" 
    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>DocuSign Sample</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DocuSign API Sending Page</title>
        <link rel="stylesheet" href="css/default.css" /> 
        <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/SendDocument.css" />
        <link rel="stylesheet" type="text/css" href="css/default.css" />
        <link rel="stylesheet" type="text/css" href="css/Tabs.css" />
        <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
        <script type="text/javascript" src="js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/Utils.js"></script>        
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
                $("#reminders").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar.png",
                    buttonImageOnly: true,
                    minDate: today
                });
                $("#expiration").datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar.png",
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
    </head>
    <body>
        <%@include file="header.jsp" %>
    <div style="width:1024px;height:800px;margin-left:auto;margin-right:auto">
        <!--Display all the templates on the logged in account-->
    	<article class="tabs">
    		<section class="current">
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
        <form id="SendDocumentForm" action="SendDocument" method="post" enctype="multipart/form-data" >
            <input id="subject" name="subject" type="text" placeholder="<enter the subject>" autocomplete="off"/>
            <p>
            </p>
            <textarea id="emailBlurb" name="emailBlurb" placeholder="<enter e-mail blurb>" rows="4" cols="20"></textarea>
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
                        <input type="text" id="reminders" class="datepickers" name="reminders"/><br />
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
                        <input type="text" id="expiration" class="datepickers" name="expiration"/><br />
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
            <!--Give the option to send right away or enter into the embedded sending experience-->
            <table class="submit">
                <tr>
                    <td>
                        <input class="docusignbutton orange" type="submit" value="Send Now" name="SendNow"/>
                    </td>
                    <td>
                        <input class="docusignbutton brown" type="submit" value="Edit Before Sending" name="EditFirst"/>
                    </td>
                </tr>
            </table>
        </form>
        <%@ include file="footer.html" %>
        </div>
    </body>
</html>