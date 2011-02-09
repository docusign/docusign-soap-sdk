<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="SendDocument.aspx.cs" Inherits="DocuSign2011Q1Sample.SendDocument" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="css/SendDocument.css" />
        <!--<link rel="stylesheet" type="text/css" href="css/SendTemplate.css" />)-->
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
                buttonImage: "images/calendar-blue.gif",
                buttonImageOnly: true,
                minDate: today
            });
            $("#expiration").datepicker({
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
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <form id="SendDocumentForm" action="SendDocument.aspx" runat="server" >
    <input id="subject" name="subject" type="text" placeholder="<enter the subject>" autocomplete="off"/>
    <p>
    </p>
    <input id="emailBlurb" name="emailBlurb" type="text" placeholder="<enter e-mail blurb>" autocomplete="off" />
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
        <tr id="Recipient1" name="Recipient1">
            <td>
                <input id="RecipientName" type="text" name="RecipientName1" />
            </td>
            <td>
                <input id="RecipientEmail" type="email" name="RecipientEmail1" />
            </td>
            <td>
                <input id="RecipientSecurity" type="text" name="RecipientSecurity1" />
            </td>
            <td>
                <ul class="switcher" name="RecipientInvite1" >
                    <li class="active"><a href="#" title="On">ON</a></li>
                    <li><a href="#" title="OFF">OFF</a></li>
                </ul>
            </td>
        </tr>
    </table>
    <input type="button" onclick="addRowToTable()" value="Add Recipient"/>
    <div id="files">
    <p>
        Document #1:
        <input class="upload" id="file1" type="file" name="file1" runat="server" /></p>
    <p>
        Document #2:
        <input class="upload" id="file2" type="file" name="file2" runat="server"/></p>
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
                <input class="options" type="checkbox" value="addsig" name="addsigs" />
                Add Signatures
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" value="addformfield" name="formfields"/>
                Add Form Fields
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" value="addcondfield" name="conditionalfields"/>
                Add Conditional Fields
            </td>
            <td rowspan="3">
                <input type="text" id="expiration" class="datepickers" name="expiration"/><br />
                Add Expiration
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" name="collabfields" value="addcollfield" />
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
                <input class="options" type="checkbox" name="markup" value="enablemarkup" />
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
</asp:Content>
