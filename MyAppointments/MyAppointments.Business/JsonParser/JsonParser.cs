using MyAppointments.DataContracts;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Configuration;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Net.Http.Formatting;
using System.Text;
using System.Threading.Tasks;

namespace MyAppointments.Business
{
    public class JsonParser
    {

        private static string baseUrl { get { return ConfigurationManager.AppSettings["BaseUrl"]; } }

        public static List<T> ToObjectList<T>(string controller, string method, params string[] parameters) where T : class, new()
        {
            string requestUri = BuildUrl(controller, method, parameters);
            HttpWebRequest request = WebRequest.Create(requestUri) as HttpWebRequest;
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                if (response.StatusCode != HttpStatusCode.OK)
                    Console.WriteLine(String.Format("Server error (HTTP {0}: {1}).", response.StatusCode, response.StatusDescription));

                Byte[] jsonByteArray = ReadFully(response.GetResponseStream());
                var jsonString = System.Text.Encoding.UTF8.GetString(jsonByteArray);


                return DeserializeObject<List<T>>(jsonString);
            }
        }

        public static T ToObject<T>(string controller, string method, params string[] parameters) where T : class, new()
        {
            string requestUri = BuildUrl(controller, method, parameters);
            HttpWebRequest request = WebRequest.Create(requestUri) as HttpWebRequest;
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                if (response.StatusCode != HttpStatusCode.OK)
                    Console.WriteLine(String.Format("Server error (HTTP {0}: {1}).", response.StatusCode, response.StatusDescription));

                Byte[] jsonByteArray = ReadFully(response.GetResponseStream());
                var jsonString = System.Text.Encoding.UTF8.GetString(jsonByteArray);

                return DeserializeObject<T>(jsonString);
            }
        }

        public static T DeserializeObject<T>(string serializedObject)
        {
            return JsonConvert.DeserializeObject<T>(serializedObject, new JsonSerializerSettings
            {
                DefaultValueHandling = Newtonsoft.Json.DefaultValueHandling.Ignore,
                NullValueHandling = Newtonsoft.Json.NullValueHandling.Ignore,
                TypeNameHandling = TypeNameHandling.Auto
            });
        }

        public static void PostRequest<T>(string controller, string method, T postData)
        {
            string uri = BuildUrl(controller, method);
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(uri);
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            MediaTypeFormatter jsonFormatter = new JsonMediaTypeFormatter();
            HttpContent content = new ObjectContent<T>(postData, jsonFormatter);
            var resp = client.PostAsync(uri, content).Result;
        }

        private static string BuildUrl(string controller, string method, params string[] parameters)
        {
            string requestUrl = string.Empty;
            requestUrl = string.Format("{0}/{1}/{2}", baseUrl, controller, method);
            foreach (string param in parameters)
                requestUrl += "/" + param;

            return requestUrl;
        }

        private static byte[] ReadFully(Stream input)
        {
            byte[] buffer = new byte[16 * 1024];
            using (MemoryStream ms = new MemoryStream())
            {
                int read;
                while ((read = input.Read(buffer, 0, buffer.Length)) > 0)
                    ms.Write(buffer, 0, read);

                return ms.ToArray();
            }
        }
    }
}
