package entities;

import java.sql.Timestamp;

public class Notification {

	private int userID;
	private Timestamp date;
	private String message;

	public Notification(int userID, Timestamp date, String message) {
		this.userID = userID;
		this.date = date;
		this.message = message;
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
