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
            USERSbl myUser = JsonParser.SendRequest<USERSbl>();
        }
    }
}
