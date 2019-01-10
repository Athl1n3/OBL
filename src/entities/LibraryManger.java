package entities;

public class LibraryManger extends Librarian{
	
	
	private int managerID;
	public LibraryManger(int id, String firstName, String lastName, String eMail, int mobileNum, int userID,
			String userName, String password, String userType, int workerID, int managerID) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, userType, workerID);
		managerID = managerID;
	}
	//get Id manager
	public int getIDmanager() {
		return managerID;
	}
	/**
	 * 
	 * Instantiates Id manager
	 * @param iDmanager
	 */
	public void setIDmanager(int iDmanager) {
		managerID = iDmanager;
	}

	



}
