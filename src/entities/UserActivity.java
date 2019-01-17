package entities;

import java.time.LocalDate;

public class UserActivity {

	private int userID;
	private String activityName;
	private LocalDate date;

	public UserActivity(int userID, String activityName, LocalDate date) {
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
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
