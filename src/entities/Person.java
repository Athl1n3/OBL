package entities;

public class Person {
	protected int ID;
	protected String firstName;
	protected String lastName;
	protected String eMail;
	protected int mobileNum;
	
	public Person()
	{
	}
	
	public Person(int ID, String firstName, String lastName, String eMail, int mobileNum) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = eMail;
		this.mobileNum = mobileNum;
	}

	/**
 	 * Gets the  ID.
 	 * 
 	 * @return  ID
 	 */
	public int getID() {
		return ID;
	}
	/**
	 * Instantiates the ID
	 * @param  set the ID
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
 	/**
 	 * Gets the  first Name.
 	 * 
 	 * @return  firstName
 	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Instantiates the firstName
	 * @param  set the firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 	/**
 	 * Gets the  Last Name.
 	 * 
 	 * @return  LastName
 	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Instantiates the LastName
	 * @param  set the LastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 	/**
 	 * Gets the  eMail.
 	 * 
 	 * @return  eMail
 	 */
	public String getEmail() {
		return eMail;
	}
	/**
	 * Instantiates the eMail
	 * @param  set the eMail
	 */
	public void setEmail(String eMail) {
		this.eMail = eMail;
	}
 	/**
 	 * Gets the  mobile Number.
 	 * 
 	 * @return  mobileNum
 	 */
	public int getMobileNum() {
		return mobileNum;
	}
	/**
	 * Instantiates the mobile Number
	 * @param  set the mobileNum
	 */
	public void setMobileNum(int mobileNum) {
		this.mobileNum = mobileNum;
	}	
}
