package entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookCopy {
      public BookCopy(int bookID, int serialNumber, String purchaseDate) {
		super();
		this.bookID = bookID;
		SerialNumber = serialNumber;
		this.purchaseDate = purchaseDate;
	}
	private int bookID;
      private int SerialNumber; 
      private static int availableCopies;
      private String purchaseDate;//?????????????????
	

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
  	 * Gets the Serial Number.
  	 * 
  	 * @return  SerialNumber
  	 */
	public int getSerialNumber() {
		return SerialNumber;
	}
	/**
	 * Instantiates Serial Number.
	 * @param  SerialNumber 
	 */
	public void setSerialNumber(int serialNumber) {
		SerialNumber = serialNumber;
	}
  	/**
  	 * Gets the available Copies.
  	 * 
  	 * @return  availableCopies
  	 */
	public static int getAvailableCopies() {
		return availableCopies;
	}
	/**
	 * Instantiates available Copies.
	 * @param  availableCopies 
	 */
	public static void setAvailableCopies(int availableCopies) {
		BookCopy.availableCopies = availableCopies;
	}
  	/**
  	 * Gets the purchase Date.
  	 * 
  	 * @return  purchaseDate
  	 * @throws ParseException 
  	 */
	public Date getPurchaseDate() throws ParseException {
		return dateForm.parse(purchaseDate);
	}
	/**
	 * Instantiates purchase Date.
	 * @param  purchaseDate 
	 */
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
      
      
}
