package entities;

public class LibraryManger extends Librarian{
	
	
	private int IDmanager;
	public LibraryManger(int iD, float firstName, float lastName, float eMail, float mobileNum, int userID,
			String userName, String password, String userType, int workerID, int iDmanager) {
		super(iD, firstName, lastName, eMail, mobileNum, userID, userName, password, userType, workerID);
		IDmanager = iDmanager;
	}
	//get Id manager
	public int getIDmanager() {
		return IDmanager;
	}
	/**
	 * 
	 * Instantiates Id manager
	 * @param iDmanager
	 */
	public void setIDmanager(int iDmanager) {
		IDmanager = iDmanager;
	}

	



}
