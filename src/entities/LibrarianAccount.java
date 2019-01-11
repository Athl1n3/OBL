package entities;

public class LibrarianAccount extends Account {

	private int workerID;

	public LibrarianAccount() {

	}

	public LibrarianAccount(int id, String firstName, String lastName, String eMail, String mobileNum, int userID,
			String userName, String password, int workerID, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, userType.Librarian, logged);
		this.workerID = workerID;
	}

	/**
	 * Gets the Worker ID.
	 * 
	 * @return workerID
	 */
	public int getWorkerID() {
		return workerID;
	}

	/**
	 * Instantiates the worker ID
	 * 
	 * @param set the worker ID
	 */
	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

}
