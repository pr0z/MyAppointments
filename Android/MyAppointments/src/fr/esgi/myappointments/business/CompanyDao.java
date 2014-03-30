package fr.esgi.myappointments.business;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import fr.esgi.myappointments.business.Company;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table COMPANY.
*/
public class CompanyDao extends AbstractDao<Company, Long> {

    public static final String TABLENAME = "COMPANY";

    /**
     * Properties of entity Company.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ServerId = new Property(1, Integer.class, "serverId", false, "SERVER_ID");
        public final static Property Siren = new Property(2, String.class, "siren", false, "SIREN");
        public final static Property CompanyName = new Property(3, String.class, "companyName", false, "COMPANY_NAME");
        public final static Property ContactFirstname = new Property(4, String.class, "contactFirstname", false, "CONTACT_FIRSTNAME");
        public final static Property ContactLastname = new Property(5, String.class, "contactLastname", false, "CONTACT_LASTNAME");
        public final static Property Email = new Property(6, String.class, "email", false, "EMAIL");
        public final static Property Phone = new Property(7, String.class, "phone", false, "PHONE");
        public final static Property CreationDate = new Property(8, java.util.Date.class, "creationDate", false, "CREATION_DATE");
        public final static Property CategoryId = new Property(9, long.class, "categoryId", false, "CATEGORY_ID");
        public final static Property AddressId = new Property(10, long.class, "addressId", false, "ADDRESS_ID");
    };

    private DaoSession daoSession;


    public CompanyDao(DaoConfig config) {
        super(config);
    }
    
    public CompanyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'COMPANY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SERVER_ID' INTEGER," + // 1: serverId
                "'SIREN' TEXT," + // 2: siren
                "'COMPANY_NAME' TEXT NOT NULL ," + // 3: companyName
                "'CONTACT_FIRSTNAME' TEXT NOT NULL ," + // 4: contactFirstname
                "'CONTACT_LASTNAME' TEXT NOT NULL ," + // 5: contactLastname
                "'EMAIL' TEXT NOT NULL ," + // 6: email
                "'PHONE' TEXT NOT NULL ," + // 7: phone
                "'CREATION_DATE' INTEGER," + // 8: creationDate
                "'CATEGORY_ID' INTEGER NOT NULL ," + // 9: categoryId
                "'ADDRESS_ID' INTEGER NOT NULL );"); // 10: addressId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'COMPANY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Company entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer serverId = entity.getServerId();
        if (serverId != null) {
            stmt.bindLong(2, serverId);
        }
 
        String siren = entity.getSiren();
        if (siren != null) {
            stmt.bindString(3, siren);
        }
        stmt.bindString(4, entity.getCompanyName());
        stmt.bindString(5, entity.getContactFirstname());
        stmt.bindString(6, entity.getContactLastname());
        stmt.bindString(7, entity.getEmail());
        stmt.bindString(8, entity.getPhone());
 
        java.util.Date creationDate = entity.getCreationDate();
        if (creationDate != null) {
            stmt.bindLong(9, creationDate.getTime());
        }
        stmt.bindLong(10, entity.getCategoryId());
        stmt.bindLong(11, entity.getAddressId());
    }

    @Override
    protected void attachEntity(Company entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Company readEntity(Cursor cursor, int offset) {
        Company entity = new Company( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // serverId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // siren
            cursor.getString(offset + 3), // companyName
            cursor.getString(offset + 4), // contactFirstname
            cursor.getString(offset + 5), // contactLastname
            cursor.getString(offset + 6), // email
            cursor.getString(offset + 7), // phone
            cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)), // creationDate
            cursor.getLong(offset + 9), // categoryId
            cursor.getLong(offset + 10) // addressId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Company entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setServerId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setSiren(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCompanyName(cursor.getString(offset + 3));
        entity.setContactFirstname(cursor.getString(offset + 4));
        entity.setContactLastname(cursor.getString(offset + 5));
        entity.setEmail(cursor.getString(offset + 6));
        entity.setPhone(cursor.getString(offset + 7));
        entity.setCreationDate(cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)));
        entity.setCategoryId(cursor.getLong(offset + 9));
        entity.setAddressId(cursor.getLong(offset + 10));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Company entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Company entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCategoryDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getAddressDao().getAllColumns());
            builder.append(" FROM COMPANY T");
            builder.append(" LEFT JOIN CATEGORY T0 ON T.'CATEGORY_ID'=T0.'_id'");
            builder.append(" LEFT JOIN ADDRESS T1 ON T.'ADDRESS_ID'=T1.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Company loadCurrentDeep(Cursor cursor, boolean lock) {
        Company entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Category category = loadCurrentOther(daoSession.getCategoryDao(), cursor, offset);
         if(category != null) {
            entity.setCategory(category);
        }
        offset += daoSession.getCategoryDao().getAllColumns().length;

        Address address = loadCurrentOther(daoSession.getAddressDao(), cursor, offset);
         if(address != null) {
            entity.setAddress(address);
        }

        return entity;    
    }

    public Company loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Company> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Company> list = new ArrayList<Company>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Company> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Company> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
