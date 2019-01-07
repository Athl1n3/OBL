package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LentBook {
	public LentBook(int userID, int bookID, String issueDate, String dueDate, String returnDate, boolean late) {
		super();
		this.userID = userID;
		this.bookID = bookID;
		IssueDate = issueDate;
		DueDate = dueDate;
		ReturnDate = returnDate;
		this.late = late;
	}
	private int userID;
	private int bookID;
	private String IssueDate;//?????????????
	private String DueDate;//?
	private String ReturnDate;//?
	private boolean  late;
	
	SimpleDateFormat dateForm=new SimpleDateFormat("MM/dd/YY");
	
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
	 * Gets the Issue Date.
	 * 
	 * @return  IssueDate
	 * @throws ParseException 
	 */
	public Date getIssueDate() throws ParseException {
		return dateForm.parse(IssueDate);
	}
	/**
	 * Instantiates Issue Date
	 * @param  IssueDate 
	 */
	public void setIssueDate(String issueDate) {
		IssueDate = issueDate;
	}
	/**
	 * Gets the Due Date.
	 * 
	 * @return  DueDate
	 * @throws ParseException 
	 */
	public Date getDueDate() throws ParseException {
		return dateForm.parse(DueDate);
	}
	/**
	 * Instantiates Due Date
	 * @param  DueDate 
	 */
	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}
	/**
	 * Gets the Return Date.
	 * 
	 * @return  ReturnDate
	 * @throws ParseException 
	 */
	public Date getReturnDate() throws ParseException {
		return dateForm.parse(ReturnDate);
	}
	/**
	 * Instantiates Return Date
	 * @param  ReturnDate 
	 */
	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
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
	


}
