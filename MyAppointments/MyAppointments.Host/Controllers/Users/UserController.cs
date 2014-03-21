using MyAppointments.Business;
using MyAppointments.DataAccessLayer.CRUD;
using MyAppointments.DataContracts;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Text;
using System.Web.Http;

namespace MyAppointments.Host.Controllers
{
    [RoutePrefix("Users")]
    public class UserController : ApiController
    {
        [HttpGet, Route("GetUserByMail/{mail}")] 
        public USERSbl GetUserByMail(string mail)
        {
            using (USERScrud db = new USERScrud())
            {
                return db.GetUserByMail(mail);
            }
        }

        [HttpGet, Route("GetAllUsers")]
        public List<USERSbl> GetAllUsers()
        {
            using (USERScrud db = new USERScrud())
            {
                return db.GetAllUsers();
            }
        }

        [HttpPost, Route("RegisterUser")]
        public void RegisterUser(HttpRequestMessage request)
        {
            string jsonBody = Request.Content.ReadAsStringAsync().Result;
            if (!string.IsNullOrEmpty(jsonBody))
            {
                USERSbl newUser = JsonParser.DeserializeObject<USERSbl>(jsonBody);
            }
        }
    }
}
