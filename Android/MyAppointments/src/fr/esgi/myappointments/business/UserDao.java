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

import fr.esgi.myappointments.business.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table USER.
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ServerId = new Property(1, Integer.class, "serverId", false, "SERVER_ID");
        public final static Property Firstname = new Property(2, String.class, "firstname", false, "FIRSTNAME");
        public final static Property Lastname = new Property(3, String.class, "lastname", false, "LASTNAME");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Email = new Property(5, String.class, "email", false, "EMAIL");
        public final static Property Password = new Property(6, String.class, "password", false, "PASSWORD");
        public final static Property CreationDate = new Property(7, java.util.Date.class, "creationDate", false, "CREATION_DATE");
        public final static Property BirthDate = new Property(8, java.util.Date.class, "birthDate", false, "BIRTH_DATE");
        public final static Property LocationId = new Property(9, Long.class, "locationId", false, "LOCATION_ID");
    };

    private DaoSession daoSession;


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SERVER_ID' INTEGER," + // 1: serverId
                "'FIRSTNAME' TEXT NOT NULL ," + // 2: firstname
                "'LASTNAME' TEXT NOT NULL ," + // 3: lastname
                "'PHONE' TEXT NOT NULL ," + // 4: phone
                "'EMAIL' TEXT NOT NULL ," + // 5: email
                "'PASSWORD' TEXT," + // 6: password
                "'CREATION_DATE' INTEGER," + // 7: creationDate
                "'BIRTH_DATE' INTEGER," + // 8: birthDate
                "'LOCATION_ID' INTEGER);"); // 9: locationId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer serverId = entity.getServerId();
        if (serverId != null) {
            stmt.bindLong(2, serverId);
        }
        stmt.bindString(3, entity.getFirstname());
        stmt.bindString(4, entity.getLastname());
        stmt.bindString(5, entity.getPhone());
        stmt.bindString(6, entity.getEmail());
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(7, password);
        }
 
        java.util.Date creationDate = entity.getCreationDate();
        if (creationDate != null) {
            stmt.bindLong(8, creationDate.getTime());
        }
 
        java.util.Date birthDate = entity.getBirthDate();
        if (birthDate != null) {
            stmt.bindLong(9, birthDate.getTime());
        }
 
        Long locationId = entity.getLocationId();
        if (locationId != null) {
            stmt.bindLong(10, locationId);
        }
    }

    @Override
    protected void attachEntity(User entity) {
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
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // serverId
            cursor.getString(offset + 2), // firstname
            cursor.getString(offset + 3), // lastname
            cursor.getString(offset + 4), // phone
            cursor.getString(offset + 5), // email
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // password
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // creationDate
            cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)), // birthDate
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9) // locationId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setServerId(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setFirstname(cursor.getString(offset + 2));
        entity.setLastname(cursor.getString(offset + 3));
        entity.setPhone(cursor.getString(offset + 4));
        entity.setEmail(cursor.getString(offset + 5));
        entity.setPassword(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCreationDate(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setBirthDate(cursor.isNull(offset + 8) ? null : new java.util.Date(cursor.getLong(offset + 8)));
        entity.setLocationId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(User entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getLocationDao().getAllColumns());
            builder.append(" FROM USER T");
            builder.append(" LEFT JOIN LOCATION T0 ON T.'LOCATION_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected User loadCurrentDeep(Cursor cursor, boolean lock) {
        User entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Location location = loadCurrentOther(daoSession.getLocationDao(), cursor, offset);
        entity.setLocation(location);

        return entity;    
    }

    public User loadDeep(Long key) {
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
    public List<User> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<User> list = new ArrayList<User>(count);
        
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
    
    protected List<User> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<User> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
