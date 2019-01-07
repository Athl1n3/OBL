package entities;

/**
 * @author азог
 *
 */
public class Account extends Person {

	private int userID;
	private String userName;
	private String password;
	private String userType;

	public Account()
	{
		//super();
	}
	
	public Account(int iD, float firstName, float lastName, float eMail, float mobileNum, int userID, String userName,
			String password, String userType) {
		super(iD, firstName, lastName, eMail, mobileNum);
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}

	/**
	 * Gets the user ID.
	 * 
	 * @return userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Instantiates the userID
	 * 
	 * @param set the userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
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

	/**
	 * Gets the user Type.
	 * 
	 * @return userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Instantiates the user Type.
	 * 
	 * @param set the userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
