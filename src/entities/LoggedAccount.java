package entities;

public class LoggedAccount {
	
	public static Boolean logged = false;
	private Account account;
	private LentBook[] lentBook;
	private BookOrder[] bookOrder;

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
	 * return lent Books list
	 * @return lentBook 
	 */
	public LentBook[] getLentBook() {
		return lentBook;
	}

	public void setLentBook(LentBook[] lentBook) {
		this.lentBook = lentBook;
	}

	public BookOrder[] getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(BookOrder[] bookOrder) {
		this.bookOrder = bookOrder;
	}

}
