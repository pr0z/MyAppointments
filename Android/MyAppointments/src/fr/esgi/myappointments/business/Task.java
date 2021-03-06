package fr.esgi.myappointments.business;

import fr.esgi.myappointments.business.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;
// KEEP INCLUDES END
/**
 * Entity mapped to table TASK.
 */
public class Task implements Parcelable {

    private Long id;
    private Integer serverId;
    /** Not-null value. */
    private String title;
    private String desc;
    private java.util.Date dateBegin;
    private java.util.Date dateEnd;
    private long categoryId;
    private long userId;
    private Long companyId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TaskDao myDao;

    private Category category;
    private Long category__resolvedKey;

    private User user;
    private Long user__resolvedKey;

    private Company company;
    private Long company__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Task(Long id, Integer serverId, String title, String desc, java.util.Date dateBegin, java.util.Date dateEnd, long categoryId, long userId, Long companyId) {
        this.id = id;
        this.serverId = serverId;
        this.title = title;
        this.desc = desc;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.categoryId = categoryId;
        this.userId = userId;
        this.companyId = companyId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public java.util.Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(java.util.Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public java.util.Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(java.util.Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /** To-one relationship, resolved on first access. */
    public Category getCategory() {
        long __key = this.categoryId;
        if (category__resolvedKey == null || !category__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoryDao targetDao = daoSession.getCategoryDao();
            Category categoryNew = targetDao.load(__key);
            synchronized (this) {
                category = categoryNew;
            	category__resolvedKey = __key;
            }
        }
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new DaoException("To-one property 'categoryId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.category = category;
            categoryId = category.getId();
            category__resolvedKey = categoryId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public User getUser() {
        long __key = this.userId;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
            	user__resolvedKey = __key;
            }
        }
        return user;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new DaoException("To-one property 'userId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            userId = user.getId();
            user__resolvedKey = userId;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Company getCompany() {
        Long __key = this.companyId;
        if (company__resolvedKey == null || !company__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CompanyDao targetDao = daoSession.getCompanyDao();
            Company companyNew = targetDao.load(__key);
            synchronized (this) {
                company = companyNew;
            	company__resolvedKey = __key;
            }
        }
        return company;
    }

    public void setCompany(Company company) {
        synchronized (this) {
            this.company = company;
            companyId = company == null ? null : company.getId();
            company__resolvedKey = companyId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
		@Override
		public Task createFromParcel(Parcel in) {
			return new Task(in);
		}

		@Override
		public Task[] newArray(int size) {
			return new Task[size];
		}
	};
	
	private Task(Parcel in) {
		readFromParcel(in);
	}
	
    @Override
    public int describeContents() {
    	return 0;
    }
    
    @Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(serverId);
		dest.writeString(title);
		dest.writeString(desc);
		dest.writeLong((dateBegin != null) ? dateBegin.getTime() : 0);
		dest.writeLong((dateEnd != null) ? dateEnd.getTime() : 0);
		dest.writeLong(categoryId);
		dest.writeLong(companyId);
	}

	public void readFromParcel(Parcel in) {
		id = in.readLong();
		serverId = in.readInt();
		title = in.readString();
		desc = in.readString();
		dateBegin = new Date(in.readLong());
		dateEnd = new Date(in.readLong());
		categoryId = in.readLong();
		companyId = in.readLong();
	}
	
	@Override
	public String toString() {
		return title;
	}
    // KEEP METHODS END

}
