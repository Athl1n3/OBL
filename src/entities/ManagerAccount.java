package entities;

public class ManagerAccount extends LibrarianAccount {
	public ManagerAccount() {

	}

	public ManagerAccount(int id, String firstName, String lastName, String eMail, String mobileNum, int userID,
			String userName, String password, int managerID) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, managerID);
	}
}
