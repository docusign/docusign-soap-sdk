<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="SendDocument.aspx.cs" Inherits="DocuSignSample.SendDocument" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
    <link rel="stylesheet" type="text/css" href="css/SendDocument.css" />
    <!--<link rel="stylesheet" type="text/css" href="css/SendTemplate.css" />)-->
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
    <script type="text/javascript">
        $(document).ready(function () {
            activate();
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
        });
    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <form id="SendDocumentForm" action="SendDocument.aspx" runat="server">
    <input id="subject" name="subject" type="text" placeholder="<enter the subject>"
        autocomplete="off" class="email" /><!--<img alt="" src="" class="helplink" />--><br />
    <textarea id="emailblurb" cols="20" name="emailblurb" placeholder="<enter the e-mail blurb>"
        rows="4" class="email"></textarea>
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
                Security and Setting
                <!--<img alt="" src="" class="helplink" />-->
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
                <select id="RecipientSecurity1" name="RecipientSecurity1" onchange="javascript:EnableDisableInput(1)">
                    <option value="None">None</option>
                    <option value="AccessCode">Access Code:</option>
                    <option value="PhoneAuthentication">Phone Authentication</option>
                    <option value="IDCheck">ID Check</option>
                </select><input id="RecipientSecuritySetting1" type="text" name="RecipientSecuritySetting1"
                    style="display: none;" />
            </td>
            <td>
                <ul class="switcher" id="RecipientInvite1">
                    <li id="RecipientInviteon1" class="active"><a href="#" title="On">ON</a></li>
                    <li id="RecipientInviteoff1"><a href="#" title="OFF">OFF</a></li>
                    <input id="RecipientInviteToggle1" name="RecipientInviteToggle1" value="RecipientInviteToggle1"
                        type="checkbox" checked style="display:none;" />
                </ul>
            </td>
        </tr>
    </table>
    <input type="button" onclick="addRowToTable()" value="Add Recipient" />
    <div id="files">
        <p>
            Document #1:
            <input class="upload" id="file1" type="file" name="file1" runat="server" /></p>
        <p>
            Document #2:
            <input class="upload" id="file2" type="file" name="file2" runat="server" /></p>
    </div>
    <table class="optionlist">
        <tr>
            <td>
                <input id="sendoption" class="options" type="checkbox" value="stockdoc" name="stockdoc"
                    onclick="EnableDisableDiv()" />
                Use a stock doc
                <!--<img alt="" src="" class="helplink" />-->
            </td>
            <td rowspan="3">
                <input type="text" id="reminders" class="datepickers" name="reminders" onchange="openPicker()"
                    onclick="openPicker()" /><br />
                Add Daily Reminders
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" value="addsig" name="addsigs" checked/>
                Add Signatures
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" value="addformfield" name="formfields" checked/>
                Add Form Fields
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" value="addcondfield" name="conditionalfields" checked/>
                Add Conditional Fields
                <!--<img alt="" src="" class="helplink" />-->
            </td>
            <td rowspan="3">
                <input type="text" id="expiration" class="datepickers" name="expiration" onclick="openPicker()" /><br />
                Add Expiration
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" name="collabfields" value="addcollfield" />
                Add Collaborative Fields
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
        <tr>
            <td>
                <input class="options" type="checkbox" name="enablepaper" value="enablepaper" />
                Enable Signing on Paper
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="options" type="checkbox" name="signerattachment" value="reqattachment" />
                Request a Signer to Add an Attachment
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input class="options" type="checkbox" name="markup" value="enablemarkup" />
                Enable Signers to Mark Up the Documents
                <!--<img alt="" src="" class="helplink" />-->
            </td>
        </tr>
    </table>
    <p />
    <table class="submit">
        <tr>
            <td class="fourcolumn">
            </td>
            <td class="fourcolumn">
                <input type="submit" value="Send Now" name="SendNow" style="width: 100%;" class="docusignbutton orange" />
            </td>
            <td class="fourcolumn">
                <input type="submit" value="Edit Before Sending" name="EditFirst" style="width: 100%;"
                    class="docusignbutton brown" />
            </td>
            <td class="fourcolumn">
            </td>
        </tr>
    </table>
    </form>
</asp:Content>
