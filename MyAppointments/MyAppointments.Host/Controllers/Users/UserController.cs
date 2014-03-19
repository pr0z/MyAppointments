using DataAccessLayer.CRUD;
using DataContracts;
using System;
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
    }
}
