package entities;

public class BookCopy {
	
	private int bookID;
	private String SerialNumber;
	//private static int availableCopies;
	private boolean lent;

	public BookCopy(int bookID, String serialNumber, boolean lent) {
		this.bookID = bookID;
		SerialNumber = serialNumber;
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

}
