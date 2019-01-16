package entities;

public class UserAccount extends Account {

	private int delays;
	private int lostBooks;

	public enum accountStatus {
		Active, Locked, Suspended
	};

	public accountStatus status;

	public UserAccount() {

	}

	public UserAccount(int id, String firstName, String lastName, String eMail, int mobileNum, int userID,
			String userName, String password, accountStatus status, int delays, int lostBooks, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, UserType.User);
		this.status = status;
		this.delays = delays;
		this.lostBooks = lostBooks;
	}

	/**
	 * @return the status
	 */
	public accountStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		if(accountStatus.Active.equals(status))
			this.status = accountStatus.Active;
		else
			if(accountStatus.Locked.equals(status))
				this.status = accountStatus.Locked;
			else
				if(accountStatus.Suspended.equals(status))
					this.status = accountStatus.Suspended;
	}

	/**
	 * Gets the delays time.
	 * 
	 * @return delays
	 */
	public int getDelays() {
		return delays;
	}

	/**
	 * Instantiates the delays time
	 * 
	 * @param delays
	 */
	public void setDelays(int delays) {
		this.delays = delays;
	}

	/**
	 * Gets how many time lost books.
	 * 
	 * @return delays
	 */
	public int getLostBooks() {
		return lostBooks;
	}

	/**
	 * Instantiates and update the lost Books
	 * 
	 * @param lostBooks
	 */
	public void setLostBooks(int lostBooks) {
		this.lostBooks = lostBooks;
	}
}
