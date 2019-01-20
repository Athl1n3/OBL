package entities;

public class LibrarianAccount extends Account {

	private int workerID;

	public LibrarianAccount() {

	}

	public LibrarianAccount(int id, String firstName, String lastName, String eMail, String mobileNum, int userID,
			String userName, String password, int workerID) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, UserType.Librarian);
		if (this instanceof ManagerAccount)
			this.userType = UserType.Manager;
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
