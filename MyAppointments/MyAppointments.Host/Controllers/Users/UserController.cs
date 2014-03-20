using MyAppointments.DataAccessLayer.CRUD;
using MyAppointments.DataContracts;
using System;
using System.Collections.Generic;
using System.Web.Http;

namespace MyAppointments.Host.Controllers
{
    [RoutePrefix("Users")]
    public class UserController : ApiController
    {
        [HttpGet, HttpPost, Route("GetUserByMail/{mail}")] 
        public USERSbl GetUserByMail(string mail)
        {
            using (USERScrud db = new USERScrud())
            {
                return db.GetUserByMail(mail);
            }
        }

        [HttpGet, HttpPost, Route("GetAllUsers")]
        public List<USERSbl> GetAllUsers()
        {
            using (USERScrud db = new USERScrud())
            {
                return db.GetAllUsers();
            }
        }
    }
}
