<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true" CodeBehind="EmbedResult.aspx.cs" Inherits="DocuSign2011Q1Sample.EmbedResult" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<p>
<%=_stat %> Go to <a href="GetStatusAndDocs.aspx">the detailed status page</a> to check on your envelopes.
</p>
</asp:Content>
