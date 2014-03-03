package fr.esgi.myappointments.business;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
import java.util.Date;
import android.os.Parcel;
import android.os.Parcelable;
// KEEP INCLUDES END
/**
 * Entity mapped to table EVENT.
 */
public class Event implements Parcelable {

    private Long id;
    private Integer uniqueID;
    /** Not-null value. */
    private String title;
    private String desc;
    private java.util.Date dateStart;
    private java.util.Date dateEnd;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Event() {
    }

    public Event(Long id) {
        this.id = id;
    }

    public Event(Long id, Integer uniqueID, String title, String desc, java.util.Date dateStart, java.util.Date dateEnd) {
        this.id = id;
        this.uniqueID = uniqueID;
        this.title = title;
        this.desc = desc;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Integer uniqueID) {
        this.uniqueID = uniqueID;
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

    public java.util.Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(java.util.Date dateStart) {
        this.dateStart = dateStart;
    }

    public java.util.Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(java.util.Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    // KEEP METHODS - put your custom methods here
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
		@Override
		public Event createFromParcel(Parcel in) {
			return new Event(in);
		}

		@Override
		public Event[] newArray(int size) {
			return new Event[size];
		}
	};
	
	private Event(Parcel in) {
		readFromParcel(in);
	}
	
    @Override
    public int describeContents() {
    	return 0;
    }
    
    @Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeInt(uniqueID);
		dest.writeString(title);
		dest.writeString(desc);
		dest.writeLong((dateStart != null) ? dateStart.getTime() : 0);
		dest.writeLong((dateEnd != null) ? dateEnd.getTime() : 0);
	}

	public void readFromParcel(Parcel in) {
		id = in.readLong();
		uniqueID = in.readInt();
		title = in.readString();
		desc = in.readString();
		dateStart = new Date(in.readLong());
		dateEnd = new Date(in.readLong());
	}
    // KEEP METHODS END

}
