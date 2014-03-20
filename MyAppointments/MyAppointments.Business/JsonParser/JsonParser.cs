using MyAppointments.DataContracts;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace MyAppointments.Business
{
    public class JsonParser
    {
        public static T GetResponse<T>() where T : class, new()
        {
            HttpWebRequest request = WebRequest.Create("http://localhost:1664/Users/GetUserByMail/roman.leichnig@gmail.com") as HttpWebRequest;
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                if (response.StatusCode != HttpStatusCode.OK)
                    Console.WriteLine(String.Format("Server error (HTTP {0}: {1}).", response.StatusCode, response.StatusDescription));

                Byte[] jsonByteArray = ReadFully(response.GetResponseStream());
                var jsonString = System.Text.Encoding.UTF8.GetString(jsonByteArray);

                return JsonConvert.DeserializeObject<T>(jsonString, new JsonSerializerSettings
                {
                    DefaultValueHandling = Newtonsoft.Json.DefaultValueHandling.Ignore,
                    NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore,
                    TypeNameHandling = TypeNameHandling.Auto
                });
            }
        }

        public static byte[] ReadFully(Stream input)
        {
            byte[] buffer = new byte[16 * 1024];
            using (MemoryStream ms = new MemoryStream())
            {
                int read;
                while ((read = input.Read(buffer, 0, buffer.Length)) > 0)
                {
                    ms.Write(buffer, 0, read);
                }
                return ms.ToArray();
            }
        }
    }
}
