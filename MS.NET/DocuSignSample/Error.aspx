<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="Error.aspx.cs" Inherits="DocuSignSample.Error" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <p>
        This demo has encountered an error:
        <asp:Label ID="lblErrorMessage" runat="server" Text=""></asp:Label>
        <br />
        Please correct the issue and try again.</p>
    <p>
        To get help, please visit the <a href="http://community.docusign.com">DocuSign Community
            Forums</a>.</p>
</asp:Content>
