using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Common
{
    public class Tools
    {
        public static T ChangeType<T>(object value)
        {
            if (value == DBNull.Value)
                return default(T);

            Type conversionType = typeof(T);
            // Note: This if block was taken from Convert.ChangeType as is, and is needed here since we're
            // checking properties on conversionType below.
            if (conversionType == null)
            {
                throw new ArgumentNullException("conversionType");
            } // end if
            // If it's not a nullable type, just pass through the parameters to Convert.ChangeType

            // It's a nullable type, so instead of calling Convert.ChangeType directly which would throw a
            // InvalidCastException (per http://weblogs.asp.net/pjohnson/archive/2006/02/07/437631.aspx),
            // determine what the underlying type is
            // If it's null, it won't convert to the underlying type, but that's fine since nulls don't really
            // have a type--so just return null
            // Note: We only do this check if we're converting to a nullable type, since doing it outside
            // would diverge from Convert.ChangeType's behavior, which throws an InvalidCastException if
            // value is null and conversionType is a value type.
            if (value == null)
            {
                return default(T);
            }

            // Ajout pour éviter des plantages avec une chaine vide
            if ((value as string) == string.Empty)
            {
                return default(T);
            }

            if (conversionType.IsGenericType &&
              conversionType.GetGenericTypeDefinition().Equals(typeof(Nullable<>)))
            {
                // It's a nullable type, and not null, so that means it can be converted to its underlying type,
                // so overwrite the passed-in conversion type with this underlying type
                NullableConverter nullableConverter = new NullableConverter(conversionType);
                conversionType = nullableConverter.UnderlyingType;
            }

            // Now that we've guaranteed conversionType is something Convert.ChangeType can handle (i.e. not a
            // nullable type), pass the call on to Convert.ChangeType
            return (T)System.Convert.ChangeType(value, conversionType);
        }
    }
}
