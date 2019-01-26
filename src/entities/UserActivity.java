package entities;

import java.sql.Timestamp;

public class UserActivity {

	private int userID;
	private String activityName;
	private Timestamp date;

	public UserActivity(int userID, String activityName, Timestamp date) {
		this.userID = userID;
		this.activityName = activityName;
		this.date = date;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Instantiates user ID
	 * 
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Gets the activity Name.
	 * 
	 * @return activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * Instantiates activity Name
	 * 
	 * @param activityName
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
