<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="SendTemplate.aspx.cs" Inherits="DocuSign2011Q1Sample.SendTemplate" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link rel="stylesheet" href="css/jquery.ui.all.css" />
    <link rel="Stylesheet" href="css/SendTemplate.css" />
    <script src="js/jquery-1.4.4.js"></script>
    <script src="js/jquery.ui.core.js"></script>
    <script src="js/jquery.ui.widget.js"></script>
    <script src="js/jquery.ui.datepicker.js"></script>
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
            $(".switcher li").bind("click", function () {
                var act = $(this);
                $(act).parent().children('li').removeClass("active").end();
                $(act).addClass("active");
            });
        });

    </script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<form id="SendTemplateForm" action="SendTemplate.aspx" runat="server" >
    <div>
        <input id="subject" name="subject" placeholder="<enter the subject>" type="text"
            class="email" /><img alt="" src="" class="helplink" /><br />
        <textarea id="emailblurb" cols="20" name="emailblurb" placeholder="<enter the e-mail blurb>"
            rows="4" class="email"></textarea>
    </div>
    <div>
    Select a Template<br />
        <select id="TemplateTable" name="TemplateTable" runat="server">
        </select><input type="button" id="selectTemplateButton" name="selectTemplateButton" value="Go" runat="server" />
    </div>
    <div>
        <table width="100%" id="RecipientTable" name="RecipientTable" runat="server">
            <tr class="rowheader">
                <td class="fivecolumn">
                    <b>Role Name</b>
                </td>
                <td class="fivecolumn">
                    <b>Name</b>
                </td>
                <td class="fivecolumn">
                    <b>E-mail</b>
                </td>
                <td class="fivecolumn">
                    <b>Security</b>
                </td>
                <td class="fivecolumn">
                    <b>Send E-mail Invite</b>
                </td>
            </tr>
        </table>
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
                    <input  type="submit" value="Send Now" name="SendNow" align="right" />
                </td>
                <td class="rightbutton">
                    <input type="submit" value="Edit Before Sending" name="EditFirst" align="left" />
                </td>
                <td class="fourcolumn">
                </td>
            </tr>
        </table>
        <div class="notification" style="visibility: hidden">
            <table>
                <tr>
                    <td>
                        <img alt="" src="" />
                    </td>
                    <td>
                        Your envelope was sent. The unique envelope ID is:<br />
                      <br />
                        Go to the detailed status page to check on it!
                    </td>
                </tr>
            </table>
        </div>
    </div>
    </form>
</asp:Content>
