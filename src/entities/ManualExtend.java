package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManualExtend {
	
	
	private int bookID;
	private int userID;
	private String WorkerName;
	private String Date;//???????????/
	
	public ManualExtend(int bookID, int userID, String workerName, String date) {
		super();
		this.bookID = bookID;
		this.userID = userID;
		WorkerName = workerName;
		Date = date;
	}
	
	SimpleDateFormat dateForm=new SimpleDateFormat("MM/dd/YY");
	
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
  	 * Gets the Worker Name.
  	 * 
  	 * @return  WorkerName
  	 */
	public String getWorkerName() {
		return WorkerName;
	}
	/**
	 * Instantiates Worker Name
	 * @param  WorkerName 
	 */
	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}
  	/**
  	 * Gets the Date.
  	 * 
  	 * @return  Date
  	 * @throws ParseException 
  	 */
	public Date getDate() throws ParseException {
		return dateForm.parse(Date);
	}
	/**
	 * Instantiates the date
	 * @param  Date 
	 */
	public void setDate(String date) {
		Date = date;
	}
	
}
