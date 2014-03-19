using System.Configuration;

namespace MyAppointments.Host
{
    public static class Config
    {
        private static string _displayName;
        private static string _baseUrl;

        public static string DisplayName
        {
            get
            {
                return _displayName ?? (_displayName = ConfigurationManager.AppSettings["ServiceName"]);
            }
        }

        public static string BaseUrl
        {
            get
            {
                return _baseUrl ?? (_baseUrl = ConfigurationManager.AppSettings["ListenUrl"]);
            }
        }
    }
}
