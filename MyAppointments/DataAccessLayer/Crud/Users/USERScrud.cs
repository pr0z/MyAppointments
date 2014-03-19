using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Configuration;
using Microsoft.Practices.EnterpriseLibrary.Data.Sql;
using DataContracts;
using Common;

namespace DataAccessLayer.CRUD
{
    public class USERScrud : BaseCrud
    {
        public USERSbl GetUserByMail(string mail)
        {
            Dictionary<string, string> parameters = new Dictionary<string,string>();
            parameters.Add("@AI_USR_EMAIL", mail);
            var result = this.MapUSERSbl(this.ToDataReader("USR_GetUsersByMail", parameters)).FirstOrDefault();
            if (result != null)
                return result;

            return null;
        }

        private List<USERSbl> MapUSERSbl(IDataReader dr)
        {
            List<USERSbl> results = new List<USERSbl>();
            while (dr.Read())
            {
                var usersBl = this.InnerMapUSERSbl(dr);
                results.Add(usersBl);
            }
            return results;
        }

        private USERSbl InnerMapUSERSbl(IDataReader dr)
        {
            return new USERSbl()
            {
                Id = Tools.ChangeType<int>(dr["USR_ID"]),
                FirstName = Tools.ChangeType<string>(dr["USR_FIRSTNAME"]),
                LastName = Tools.ChangeType<string>(dr["USR_LASTNAME"]),
                BirthDate = Tools.ChangeType<DateTime>(dr["USR_BIRTHDATE"]),
                Email = Tools.ChangeType<string>(dr["USR_EMAIL"]),
                Password = Tools.ChangeType<string>(dr["USR_PASSWORD"]),
                Phone = Tools.ChangeType<string>(dr["USR_PHONE"]),
                CreationDate = Tools.ChangeType<DateTime>(dr["USR_CREATION_DATE"]),
                IdLocation = Tools.ChangeType<int>(dr["USR_ID_LOCATION"])
            };
        }
    }
}
