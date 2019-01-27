package entities;

import java.sql.Timestamp;

public class Notification {

	public enum notificationType {
		Lock, Suspend, Message
	};

	private int userID;
	private int notificationNum;
	private Timestamp date;
	private String message;
	private notificationType notfType;

	public Notification(int notificationNum, int userID, Timestamp date, String message, String msgType) {
		this.userID = userID;
		this.date = date;
		this.message = message;
		this.notificationNum = notificationNum;
		switch (msgType) {
		case "Lock":
			this.notfType = notificationType.Lock;
			break;
		case "Suspend":
			this.notfType = notificationType.Suspend;
			break;
		case "Message":
			this.notfType = notificationType.Message;
			break;
		default:
			this.notfType = notificationType.Message;
			break;
		}
	}

	/**
	 * @return the msgType
	 */
	public notificationType getNotfType() {
		return notfType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(notificationType notfType) {
		this.notfType = notfType;
	}

	/**
	 * @return the notificationNum
	 */
	public int getNotificationNum() {
		return notificationNum;
	}

	/**
	 * @param notificationNum the notificationNum to set
	 */
	public void setNotificationNum(int notificationNum) {
		this.notificationNum = notificationNum;
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
	
	/**
	 * Returns true if notification type is lock notification
	 */
	public boolean isLock()
	{
		return this.notfType == notificationType.Lock;
	}
}
