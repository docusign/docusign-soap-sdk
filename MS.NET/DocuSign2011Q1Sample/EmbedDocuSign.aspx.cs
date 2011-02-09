using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace DocuSign2011Q1Sample
{
    public partial class EmbedDocuSign : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!Page.IsPostBack)
            {
                hostiframe.Visible = false;
            }
            else
            {
                DocuSignAPI.EnvelopeStatus status = null;
                buttonTable.Visible = false;
                if (Request.Form["OneSigner"] != null)
                {
                    status = CreateAndSendOne();
                }
                else
                {
                    status = CreateAndSendTwo();
                }

                GetToken(status);
            }
        }

        protected DocuSignAPI.EnvelopeStatus CreateAndSendOne()
        {
            return new DocuSignAPI.EnvelopeStatus();
        }

        protected DocuSignAPI.EnvelopeStatus CreateAndSendTwo()
        {
            return new DocuSignAPI.EnvelopeStatus();
        }

        protected void GetToken(DocuSignAPI.EnvelopeStatus status)
        {

        }
    }
}