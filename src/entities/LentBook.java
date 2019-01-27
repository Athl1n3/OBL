package entities;

import java.time.LocalDate;
import java.util.Date;

//need to extend from book
public class LentBook {
	
	Book book;
	BookCopy bookCopy;
	private int userID;
	private LocalDate issueDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private boolean  late;
	
	public LentBook(int userID,Book book, BookCopy bookCopy, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate,boolean late) {
		this.userID = userID;
		this.book = book;
		this.bookCopy = bookCopy;
		this.issueDate = issueDate;
		this.dueDate = dueDate;
		this.late = late;
		this.returnDate = returnDate;
	}
	
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
	public Book getBook() {
		return book;
	}
	/**
	 * Instantiates book ID
	 * @param  bookID 
	 */
	public void setBook(Book book) {
		this.book = book;
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
		return issueDate;
	}
	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}
	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
}
