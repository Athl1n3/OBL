package entities;

import java.time.LocalDate;

public class BookCopy {
	
	private int bookID;
	private String SerialNumber;
	private LocalDate purchaseDate;
	private boolean lent;

	public BookCopy(int bookID, String serialNumber,LocalDate purchaseDate, boolean lent) {
		this.bookID = bookID;
		SerialNumber = serialNumber;
		this.setPurchaseDate(purchaseDate);
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
	 * Gets the Serial Number.
	 * 
	 * @return SerialNumber
	 */
	public String getSerialNumber() {
		return SerialNumber;
	}

	/**
	 * Instantiates Serial Number.
	 * 
	 * @param SerialNumber
	 */
	public void setSerialNumber(String serialNumber) {
		SerialNumber = serialNumber;
	}
	
	/**
	 * returns copy purchase Date
	 * @return LocalDate 
	 */
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	
	/**
	 * set copy purchase Date
	 * @param purchaseDate
	 */
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

}
