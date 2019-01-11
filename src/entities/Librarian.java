package entities;

public class Librarian extends Account {

	private int workerID;

	public Librarian(int id, String firstName, String lastName, String eMail, int mobileNum, int userID,
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
