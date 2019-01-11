package entities;

import java.util.Date;

public class BookCopy {
		private int bookID;
      private String SerialNumber; 
      private static int availableCopies;
      private Date purchaseDate;
      private boolean lent;
	
      public BookCopy(int bookID, String serialNumber, Date purchaseDate, boolean lent) {
		super();
		this.bookID = bookID;
		SerialNumber = serialNumber;
		this.purchaseDate = purchaseDate;
		this.lent = lent;
	}	

  	/**
	 * @return the lent
	 */
	public boolean isLent() {
		return lent;
	}

	/**
	 * @param lent the lent to set
	 */
	public void setLent(boolean lent) {
		this.lent = lent;
	}

	/**
	 * @return the purchaseDate
	 */
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	/**
	 * @param purchaseDate the purchaseDate to set
	 */
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
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
  	 * Gets the Serial Number.
  	 * 
  	 * @return  SerialNumber
  	 */
	public String getSerialNumber() {
		return SerialNumber;
	}
	/**
	 * Instantiates Serial Number.
	 * @param  SerialNumber 
	 */
	public void setSerialNumber(String serialNumber) {
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
      
      
}
