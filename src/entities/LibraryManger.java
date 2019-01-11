package entities;

public class LibraryManger extends Account {

	private int managerID;

	public LibraryManger(int id, String firstName, String lastName, String eMail, int mobileNum, int userID,
			String userName, String password, int managerID, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, userType.Manager, logged);
		this.managerID = managerID;
	}

	// get Id manager
	public int getIDmanager() {
		return managerID;
	}

	/**
	 * 
	 * Instantiates Id manager
	 * 
	 * @param iDmanager
	 */
	public void setIDmanager(int iDmanager) {
		managerID = iDmanager;
	}
}
