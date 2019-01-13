package entities;

import java.time.LocalDate;
import java.util.Date;

public class LentBook {
	public LentBook(int userID, int bookID, LocalDate issueDate, LocalDate dueDate, boolean late, String bookName , String bookEdition , String bookAuthor, String bookTopic) {
		this.userID = userID;
		this.bookID = bookID;
		IssueDate = issueDate;
		DueDate = dueDate;
		this.late = late;
		this.bookName = bookName;
		this.bookEdition = bookEdition;
		this.bookTopic = bookTopic;
		this.bookAuthor = bookAuthor;
	}
	private String bookName;
	private String bookEdition;
	private String bookAuthor;
	private String bookTopic;
	private int userID;
	private int bookID;
	private LocalDate IssueDate;
	private LocalDate DueDate;
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
	public LocalDate getIssueDate() {
		return IssueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(LocalDate issueDate) {
		IssueDate = issueDate;
	}
	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return DueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		DueDate = dueDate;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	
	public String getBookEdition() {
		return bookEdition;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	
	public String getBookTopic() {
		return bookTopic;
	}
}
