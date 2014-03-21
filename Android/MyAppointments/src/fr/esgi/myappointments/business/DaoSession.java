<<<<<<< HEAD
package fr.esgi.myappointments.business;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import fr.esgi.myappointments.business.Event;
import fr.esgi.myappointments.business.User;
import fr.esgi.myappointments.business.Company;

import fr.esgi.myappointments.business.EventDao;
import fr.esgi.myappointments.business.UserDao;
import fr.esgi.myappointments.business.CompanyDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eventDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig companyDaoConfig;

    private final EventDao eventDao;
    private final UserDao userDao;
    private final CompanyDao companyDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        companyDaoConfig = daoConfigMap.get(CompanyDao.class).clone();
        companyDaoConfig.initIdentityScope(type);

        eventDao = new EventDao(eventDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        companyDao = new CompanyDao(companyDaoConfig, this);

        registerDao(Event.class, eventDao);
        registerDao(User.class, userDao);
        registerDao(Company.class, companyDao);
    }
    
    public void clear() {
        eventDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        companyDaoConfig.getIdentityScope().clear();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

}
=======
package fr.esgi.myappointments.business;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import fr.esgi.myappointments.business.Event;
import fr.esgi.myappointments.business.User;
import fr.esgi.myappointments.business.Company;

import fr.esgi.myappointments.business.EventDao;
import fr.esgi.myappointments.business.UserDao;
import fr.esgi.myappointments.business.CompanyDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig eventDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig companyDaoConfig;

    private final EventDao eventDao;
    private final UserDao userDao;
    private final CompanyDao companyDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        eventDaoConfig = daoConfigMap.get(EventDao.class).clone();
        eventDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        companyDaoConfig = daoConfigMap.get(CompanyDao.class).clone();
        companyDaoConfig.initIdentityScope(type);

        eventDao = new EventDao(eventDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        companyDao = new CompanyDao(companyDaoConfig, this);

        registerDao(Event.class, eventDao);
        registerDao(User.class, userDao);
        registerDao(Company.class, companyDao);
    }
    
    public void clear() {
        eventDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
        companyDaoConfig.getIdentityScope().clear();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

}
>>>>>>> 72189d4570307a88d241475477cdc67293633d92
