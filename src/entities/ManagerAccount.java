package entities;

public class ManagerAccount extends Account {

	private int managerID;

	public ManagerAccount() {

	}

	public ManagerAccount(int id, String firstName, String lastName, String eMail, String mobileNum, int userID,
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
