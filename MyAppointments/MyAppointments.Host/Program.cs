using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Topshelf;

namespace MyAppointments.Host
{
    class Program
    {
        static void Main(string[] args)
        {
            HostFactory.Run((config) =>
            {
                config.SetDisplayName(Config.DisplayName);
                config.SetDescription(Config.DisplayName);
                config.SetServiceName(Config.DisplayName);
                config.Service<MyAppsService>(() => MyAppsService.Instance);
            });
        }
    }
}
