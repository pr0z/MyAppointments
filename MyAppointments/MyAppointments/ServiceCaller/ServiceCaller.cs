using MyAppointments.Business;
using MyAppointments.DataContracts;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyAppointments
{
    public static class ServiceCaller
    {
        public static void ParseJsonResult()
        {
            //List<USERSbl> listUser = JsonParser.ToObjectList<USERSbl>("Users", "GetAllUsers");
            //USERSbl user = JsonParser.ToObject<USERSbl>("Users", "GetUserByMail", "roman.leichnig@gmail.com");

            USERSbl register = new USERSbl()
            {
                Id = 3,
                FirstName = "Paul",
                LastName = "Dupont",
                Email = "paul.dupont@gmail.com",
                Password = "toto",
                Phone = "0607080910",
                BirthDate = new DateTime(1976, 6, 9),
                IdLocation = 1,
                CreationDate = DateTime.Now
            };

            JsonParser.PostRequest("Users", "RegisterUser", register);
        }
    }
}
