package entities;

import java.util.Date;

public class Notification {

	private int userID;
	private Date date;
	private String message;

	public Notification(int userID, Date date, String message) {
		this.userID = userID;
		this.date = date;
		this.message = message;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
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
	 * Instantiates the userID
	 * 
	 * @param set the userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Gets the message
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Instantiates the message
	 * 
	 * @param set the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
