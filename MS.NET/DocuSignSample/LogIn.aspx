<%@ Page Title="" Language="C#" MasterPageFile="~/DefaultPage.Master" AutoEventWireup="true"
    CodeBehind="Login.aspx.cs" Inherits="DocuSignSample.Login" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="dropshadow-bottomleft">
        <p class="leftalign">
            <b>Log In</b></p>
        <hr />
        <form action="LogIn.aspx" id="logInForm" runat="server">
        <div id="container" class="centeralign" style="width: 300px;">
            <table style="width: 300px;" align="center">
                <tr>
                    <td>
                        DevCenter E-mail
                    </td>
                    <td>
                        <input id="DevCenterEmail" type="text" name="DevCenterEmail" value="<%=email %>" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Password
                    </td>
                    <td>
                        <input id="DevCenterPassword" type="password" name="DevCenterPassword" value="<%=password %>" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Your Integrator Key
                    </td>
                    <td>
                        <input id="DevCenterIKey" type="text" name="DevCenterIKey" value="<%=key %>" />
                    </td>
                </tr>
            </table>
            <div>
                <table class="triangle-isosceles left" align="center">
                    <tr>
                        <td>
                            <!--<img alt="" src="" style="height: 16px; width: 16px" />-->
                        </td>
                        <td class="leftalign" style="white-space: nowrap;">
                            <span><b>Need a DevCenter Account?</b></span><br />
                            Get it <a href="http://www.docusign.com/developers-center/get-free-developer-account">
                                here</a>.
                        </td>
                    </tr>
                </table>
            </div>
            <div id="action" class="centeralign" style="width: 150px;">
                <input id="Submit1" type="submit" value="Login" style="width: 70px; margin-right: 5px;" /><input
                    id="Reset1" type="Reset" value="Cancel" style="width: 70px; margin-left: 5px;" />
            </div>
        </div>
        </form>
    </div>
</asp:Content>
