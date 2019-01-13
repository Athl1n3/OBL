package entities;

/**
 * @author азог
 *
 */
public abstract class Account extends Person {

	private int accountID;
	private String userName;
	private String password;
	private boolean logged;

	public enum userType {
		User, Librarian, Manager
	};

	private userType userType;

	public Account() {
		// super();
	}

	public Account(int id, String firstName, String lastName, String eMail, String mobileNum, int accountID,
			String userName, String password, userType userType, boolean logged) {
		super(id, firstName, lastName, eMail, mobileNum);
		this.accountID = accountID;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.logged = logged;
	}

	/**
	 * @return the logged
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @param logged the logged to set
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * @return the userType
	 */
	public userType getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(userType userType) {
		this.userType = userType;
	}

	/**
	 * @return the accountID
	 */
	public int getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID the accountID to set
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
	 * Instantiates the user Name
	 * 
	 * @param set the userName
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
	 * Instantiates the password.
	 * 
	 * @param set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
