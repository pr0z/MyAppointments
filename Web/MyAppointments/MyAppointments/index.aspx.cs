using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyAppointments
{
    public partial class index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void OnClickConnexion(object sender, EventArgs e)
        {
            if (username.Text == "toto" && password.Value == "toto")
            {
                Response.Redirect(Page.ResolveClientUrl("/admin.aspx"));
                error.Text = "";
            }
            else
            {
                error.Text = "Adresse E-mail ou mot de passe faux";
            }
        }
    }
}