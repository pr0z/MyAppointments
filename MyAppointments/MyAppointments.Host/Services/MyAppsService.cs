using Microsoft.Owin.Hosting;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Topshelf;

namespace MyAppointments.Host
{
    public class MyAppsService : ServiceControl
    {
        private static volatile MyAppsService _instance;
        private static readonly object _lockObj = new object();

        private IDisposable _server;

        private MyAppsService() { }

        public static MyAppsService Instance
        {
            get
            {
                if (_instance == null)
                    lock (_lockObj)
                        if (_instance == null)
                            _instance = new MyAppsService();

                return _instance;
            }
        }

        #region ServiceControl Membres

        public bool Start(HostControl hostControl)
        {
            _server = WebApp.Start<Startup>(Config.BaseUrl);
            return true;
        }

        public bool Stop(HostControl hostControl)
        {
            if (null != _server)
                _server.Dispose();

            return true;
        }

        #endregion
    }
}
