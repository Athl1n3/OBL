package entities;

public class Person {
	private int ID;
	private float firstName;
	private float lastName;
	private float eMail;
	private float mobileNum;
	
	public Person()
	{
		
	}
	
	public Person(int iD, float firstName, float lastName, float eMail, float mobileNum) {
		super();
		ID = iD;
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
	public void setID(int iD) {
		ID = iD;
	}
 	/**
 	 * Gets the  first Name.
 	 * 
 	 * @return  firstName
 	 */
	public float getFirstName() {
		return firstName;
	}
	/**
	 * Instantiates the firstName
	 * @param  set the firstName
	 */
	public void setFirstName(float firstName) {
		this.firstName = firstName;
	}
 	/**
 	 * Gets the  Last Name.
 	 * 
 	 * @return  LastName
 	 */
	public float getLastName() {
		return lastName;
	}
	/**
	 * Instantiates the LastName
	 * @param  set the LastName
	 */
	public void setLastName(float lastName) {
		this.lastName = lastName;
	}
 	/**
 	 * Gets the  eMail.
 	 * 
 	 * @return  eMail
 	 */
	public float geteMail() {
		return eMail;
	}
	/**
	 * Instantiates the eMail
	 * @param  set the eMail
	 */
	public void seteMail(float eMail) {
		this.eMail = eMail;
	}
 	/**
 	 * Gets the  mobile Number.
 	 * 
 	 * @return  mobileNum
 	 */
	public float getMobileNum() {
		return mobileNum;
	}
	/**
	 * Instantiates the mobile Number
	 * @param  set the mobileNum
	 */
	public void setMobileNum(float mobileNum) {
		this.mobileNum = mobileNum;
	}


	
	

}
