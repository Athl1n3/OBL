package entities;

public class Archive {
	
	
	
	private int Id;
	private int UserId;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String mobileNum;
	private String eMail;
	
	
	public Archive(int id, int userId, String username, String password, String firstname, String lastname,
			String mobileNum, String eMail) {
		super();
		Id = id;
		UserId = userId;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobileNum = mobileNum;
		this.eMail = eMail;
	}
	/**
	 * 
	 */

	
	/**
	 * Gets the ID.
	 * 
	 * @return  ID
	 */
	public int getId() {
		return Id;
	}
	/**
	 * Instantiates Id
	 * @param setId set the id
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * Gets the user ID.
	 * 
	 * @return  userId
	 */
	public int getUserId() {
		return UserId;
	}
	/**
	 * Instantiates the user Id
	 * @param setUserId set the user id
	 */
	public void setUserId(int userId) {
		UserId = userId;
	}
	/**
	 * Gets the user name.
	 * 
	 * @return  username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Instantiates the user name
	 * @param setUsername set the user name
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * Gets the password.
	 * 
	 * @return  password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Instantiates the password
	 * @param  set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Gets the first name.
	 * 
	 * @return  firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * Instantiates the first name
	 * @param firstname set the first name
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * Gets the last name.
	 * 
	 * @return  lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * Instantiates the last name
	 * @param lastname set the last name
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * Gets the phone number.
	 * 
	 * @return  mobileNum
	 */
	public String getMobileNum() {
		return mobileNum;
	}
	/**
	 * Instantiates the phone number
	 * @param mobileNum set the phone number
	 */
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	/**
	 * Gets the phone Email.
	 * 
	 * @return  eMail
	 */
	public String getEmail() {
		return eMail;
	}
	/**
	 * Instantiates the Email.
	 * @param email set the Email
	 */
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
	
	
	
	
	
	
	
	
	
	

}
