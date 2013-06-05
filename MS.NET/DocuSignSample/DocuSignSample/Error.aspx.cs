using DocuSignSample.resources;
using System;

namespace DocuSignSample
{
    public partial class Error : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (null != Session[Keys.ErrorMessage])
            {
                lblErrorMessage.Text = (string)Session[Keys.ErrorMessage];
            }
            else
            {
                lblErrorMessage.Text = "No error message in session. ?";

            }
        }
    }
}