package entities;

public abstract class Account extends Person {

	private int accountID;
	private String userName;
	private String password;
	private boolean logged;

	public enum UserType {
		User, Librarian, Manager
	};

	public UserType userType;

	public Account() {
		// super();
	}

	public Account(int id, String firstName, String lastName, String eMail, String mobileNum, int accountID,
			String userName, String password, UserType userType, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum);
		this.accountID = accountID;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	/**
	 * @return if user is logged 
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * Set user logged status
	 * @param logged set user online status
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * @return the userType
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param userType set user type
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	/**
	 * set user type via string
	 * @param userType set usertype via string
	 */
	public void setUserTypeString(String userType) {
		if (UserType.User.equals(userType))
			this.userType = UserType.User;
		else if (UserType.Librarian.equals(userType))
			this.userType = UserType.Librarian;
		else if (UserType.Manager.equals(userType))
			this.userType = UserType.Manager;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID set accountID
	 */
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	/**
	 * Gets the user Name.
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName set Username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the password.
	 * 
	 * @param password set password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return get full name
	 */
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}
