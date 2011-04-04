<%@ Page Title="" Language="C#" MasterPageFile="~/Tabs.Master" AutoEventWireup="true"
    CodeBehind="EmbedDocuSign.aspx.cs" Inherits="DocuSignSample.EmbedDocuSign" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <form runat="server" id="EmbedDocuSignForm" action="EmbedDocuSign.aspx">
    <table width="100%" id="buttonTable" name="buttonTable" runat="server">
        <tr>
            <td class="rightalign">
                <input id="OneSigner" type="submit" name="OneSigner" class="docusignbutton blue"
                    value="Create an Envelope with 1 Signer" />
            </td>
            <td class="leftalign">
                <input id="TwoSigners" type="submit" name="TwoSigners" class="docusignbutton blue"
                    value="Create an Envelope with 2 Signers" />
            </td>
        </tr>
    </table>
    </form>
    <iframe width="100%" height="70%" src="" id="hostiframe" name="hostiframe" runat="server">
    </iframe>
</asp:Content>
