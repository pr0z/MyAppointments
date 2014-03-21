using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace MyAppointments.ComptePro
{
    public partial class indexPro : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void OnClickConnexion(object sender, EventArgs e)
        {
            var user = username.Text;
            var pass = password.Value;
            SqlConnection cn = new SqlConnection(ConfigurationManager.ConnectionStrings["MyAppointments"].ConnectionString);
            SqlCommand cmd = new SqlCommand("SELECT USR_EMAIL, USR_PASSWORD FROM USERS", cn);
            cn.Open();
            SqlDataReader rdr = cmd.ExecuteReader(CommandBehavior.CloseConnection);
            while (rdr.Read())
            {
                var userbdd = rdr[0].ToString();
                var passbdd = rdr[1].ToString();
                if (user == userbdd && pass == passbdd)
                    Response.Redirect(Page.ResolveClientUrl("/admin.aspx"));
                else
                    error.Text = "Votre compte est inexistant";
            }
        }
    }
}