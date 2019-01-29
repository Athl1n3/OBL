package entities;

import java.util.Date;

public class ManualExtend {
	private int bookID;
	private int userID;
	private String WorkerName;
	private Date date;

	public ManualExtend(int bookID, int userID, String workerName, Date date) {
		super();
		this.bookID = bookID;
		this.userID = userID;
		WorkerName = workerName;
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the book ID.
	 * 
	 * @return bookID
	 */
	public int getBookID() {
		return bookID;
	}

	/**
	 * Instantiates book ID
	 * 
	 * @param bookID
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
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
	 * Gets the Worker Name.
	 * 
	 * @return WorkerName
	 */
	public String getWorkerName() {
		return WorkerName;
	}

	/**
	 * set worker name
	 * @param workerName
	 */
	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}
}
