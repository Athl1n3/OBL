package entities;

public class UserAccount extends Account {
	
	private String status;
	private int delays;
	private int lostBooks;
	private boolean logged;
	
	public UserAccount(int iD, float firstName, float lastName, float eMail, float mobileNum, int userID,
			String userName, String password, String userType, String status, int delays, int lostBooks,
			boolean logged) {
		super(iD, firstName, lastName, eMail, mobileNum, userID, userName, password, userType);
		this.status = status;
		this.delays = delays;
		this.lostBooks = lostBooks;
		this.logged = logged;
	}
	/**
	 * Gets the status user account.
	 * 
	 * @return  status
	 */

	public String getStatus() {
		return status;
	}
	/**
	 * Instantiates the status
	 * @param  status 
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * Gets the delays time.
	 * 
	 * @return  delays
	 */
	public int getDelays() {
		return delays;
	}
	/**
	 * Instantiates the delays time
	 * @param  delays 
	 */
	public void setDelays(int delays) {
		this.delays = delays;
	}
	/**
	 * Gets how many time lost books.
	 * 
	 * @return  delays
	 */
	public int getLostBooks() {
		return lostBooks;
	}
	/**
	 * Instantiates and update the lost Books
	 * @param  lostBooks 
	 */
	public void setLostBooks(int lostBooks) {
		this.lostBooks = lostBooks;
	}
	/**
	 * Gets true if logged.
	 * 
	 * @return  logged
	 */
	public boolean isLogged() {
		return logged;
	}
	/**
	 * Instantiates and update the status logged
	 * @param  logged 
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	



}
