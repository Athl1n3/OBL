package entities;

public class BookOrder {
	
	private int orderID;
	private int userID;
	private int bookID;
	
	public BookOrder(int orderID, int userID, int bookID) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.bookID = bookID;
	}

	
	
  	/**
  	 * Gets the order ID.
  	 * 
  	 * @return  orderID
  	 */
	public int getOrderID() {
		return orderID;
	}
	/**
	 * Instantiates orderID
	 * @param  orderID 
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
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
	
	


	

}
