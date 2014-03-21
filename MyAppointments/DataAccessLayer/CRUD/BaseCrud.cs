using Common.Enums;
using Microsoft.Practices.EnterpriseLibrary.Data.Sql;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Data.SqlTypes;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MyAppointments.DataAccessLayer.CRUD
{
    public class BaseCrud : IDisposable
    {
        public State ObjectState { get; set; }

        public BaseCrud()
        {
            this.ObjectState = State.None;
        }

        public IDataReader ToDataReader(string storedProcedureName, Dictionary<string, string> parameters)
        {
            using (SqlCommand cmd = new SqlCommand(storedProcedureName))
            {
                cmd.CommandType = CommandType.StoredProcedure;
                foreach (string key in parameters.Keys)
                {
                    SqlParameter parameter = new SqlParameter(key, parameters[key]);

                    if (parameter.Value == null)
                        parameter.Value = DBNull.Value;
                    
                    if (parameter.SqlDbType == SqlDbType.DateTime && parameter.Value is DateTime && (DateTime)parameter.Value == default(DateTime))
                        parameter.Value = SqlDateTime.MinValue.Value;

                    cmd.Parameters.Add(parameter);

                }

                SqlDatabase database = new SqlDatabase(ConfigurationManager.ConnectionStrings["MyAppsLocal"].ConnectionString);

                try
                {
                    IDataReader reader = database.ExecuteReader(cmd);
                    return reader;
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.ToString());
                    return null;
                }

            }
        }

        public void Dispose()
        {
            GC.SuppressFinalize(this);
        }
    }
}
