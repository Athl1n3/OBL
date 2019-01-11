package entities;

public class LoggedUser {
	
	public static Boolean logged = false;
	private int userID;
	private Account account;
	private BookLendList bookLendList;
	private BookOrderList BookOrderList;
	
	/**
	 * 
	 * @return account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * 
	 * @param Account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * 
	 * @return BookLendList
	 */
	public BookLendList getBookLendList() {
		return bookLendList;
	}

	/**
	 * 
	 * @param bookLendList
	 */
	public void setBookLendList(BookLendList bookLendList) {
		this.bookLendList = bookLendList;
	}

	/**
	 * 
	 * @return BookOrderList
	 */
	public BookOrderList getBookOrderList() {
		return BookOrderList;
	}

	/**
	 * 
	 * @param bookOrderList
	 */
	public void setBookOrderList(BookOrderList bookOrderList) {
		BookOrderList = bookOrderList;
	}

	/**
	 * 
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * 
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

}
