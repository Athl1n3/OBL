package entities;

import java.util.ArrayList;

public class UserAccount extends Account {

	private int delays;

	public enum accountStatus {
		Active, Locked, Suspended
	};

	public accountStatus status;

	public UserAccount() {
		this.setUserType(userType.User);
	}

	public UserAccount(int id, String firstName, String lastName, String eMail, String mobileNum, int userID,
			String userName, String password, accountStatus status, int delays, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum, userID, userName, password, UserType.User, logged);
		this.status = status;
		this.delays = delays;
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
	public void setStatus(accountStatus status) {
		this.status = status;
	}

	public void setStatusString(String str) {
		switch (str) {
		case "Active":
			this.setStatus(accountStatus.Active);
			break;
		case "Suspended":
			this.setStatus(accountStatus.Suspended);
			break;
		case "Locked":
			this.setStatus(accountStatus.Locked);
			break;
		}
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


	public void parseArrayIntoAccount(ArrayList<String> accountArray) {
		this.setID(Integer.parseInt(accountArray.get(0)));
		this.setFirstName(accountArray.get(1));
		this.setLastName(accountArray.get(2));
		this.setEmail(accountArray.get(3));
		this.setMobileNum(accountArray.get(4));
		this.setAccountID(Integer.parseInt(accountArray.get(5)));
		this.setUserName(accountArray.get(6));
		this.setPassword(accountArray.get(7));
		this.setUserTypeString(accountArray.get(8));
		this.setStatusString(accountArray.get(9));
		this.setDelays(Integer.parseInt(accountArray.get(10)));
		this.setLogged(accountArray.get(11).equals("1") ? true : false);
	}
}
