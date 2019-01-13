package entities;

import java.util.Date;

public class LentBook {
	public LentBook(int userID, int bookID, Date issueDate, Date dueDate, boolean late) {
		super();
		this.userID = userID;
		this.bookID = bookID;
		IssueDate = issueDate;
		DueDate = dueDate;
		this.late = late;
	}
	private int userID;
	private int bookID;
	private Date IssueDate;
	private Date DueDate;
	private boolean  late;
	
	/**
	 * Gets the user ID.
	 * 
	 * @return  userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * Instantiates user ID
	 * @param  userID 
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * Gets the book ID.
	 * 
	 * @return  bookID
	 */
	public int getBookID() {
		return bookID;
	}
	/**
	 * Instantiates book ID
	 * @param  bookID 
	 */
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	
	/**
	 * Gets true if late.
	 * 
	 * @return  late
	 */
	public boolean isLate() {
		return late;
	}
	/**
	 * Instantiates update late
	 * @param  late 
	 */
	public void setLate(boolean late) {
		this.late = late;
	}
	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return IssueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		IssueDate = issueDate;
	}
	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return DueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}
}
