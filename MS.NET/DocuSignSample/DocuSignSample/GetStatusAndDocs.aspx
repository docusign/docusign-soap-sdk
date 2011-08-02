<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="GetStatusAndDocs.aspx.cs" Inherits="DocuSignSample.GetStatusAndDocs" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <link rel="Stylesheet" href="css/GetStatusAndDocs.css" />
    <script type="text/javascript" src="js/Utils.js"></script>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <form runat="server" id="GetStatusAndDocsForm" action="GetStatusAndDocs.aspx">
    <div id="statusDiv" runat="server">
        <table id="statusTable" runat="server">
        </table>
    </div>
    <div class="centeralign">
    </div>
    </form>
    <iframe width="100%" height="70%" src="" id="hostiframe" name="hostiframe" runat="server">
    </iframe>
</asp:Content>
