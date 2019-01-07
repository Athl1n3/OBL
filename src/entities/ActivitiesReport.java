package entities;

public class ActivitiesReport {
	
	private int activeUsersNumber;
	private int unActiveUsersNumber;
	private int suspendedUsersNumber;
	private int copiesNumber;
	private int usersWithBookLateReturnNumber;
	
	
	public ActivitiesReport(int activeUsersNumber, int unActiveUsersNumber, int suspendedUsersNumber, int copiesNumber,
			int usersWithBookLateReturnNumber) {
		super();
		this.activeUsersNumber = activeUsersNumber;
		this.unActiveUsersNumber = unActiveUsersNumber;
		this.suspendedUsersNumber = suspendedUsersNumber;
		this.copiesNumber = copiesNumber;
		this.usersWithBookLateReturnNumber = usersWithBookLateReturnNumber;
	}
	
	
    
   	/**
   	 * Gets the active user number.
   	 * 
   	 * @return  activeUsersNumber.
   	 */
	public int getActiveUsersNumber() {
		return activeUsersNumber;
	}
	/**
	 * Instantiates the active user number
	 * @param activeUsersNumber set the active user number
	 */
	public void setActiveUsersNumber(int activeUsersNumber) {
		this.activeUsersNumber = activeUsersNumber;
	}
   	/**
   	 * Gets the unactive user number.
   	 * 
   	 * @return  unActiveUsersNumber
   	 */
	public int getUnActiveUsersNumber() {
		return unActiveUsersNumber;
	}
	/**
	 * Instantiates the unActive Users Number
	 * @param unActiveUsersNumber set the unactive user number
	 */
	public void setUnActiveUsersNumber(int unActiveUsersNumber) {
		this.unActiveUsersNumber = unActiveUsersNumber;
	}
	
   	/**
   	 * Gets the suspended Users Number.
   	 * 
   	 * @return  suspendedUsersNumber
   	 */
	public int getSuspendedUsersNumber() {
		return suspendedUsersNumber;
	}
	/**
	 * Instantiates the suspended Users Number
	 * @param suspendedUsersNumber set the suspended Users Number
	 */
	public void setSuspendedUsersNumber(int suspendedUsersNumber) {
		this.suspendedUsersNumber = suspendedUsersNumber;
	}
   	/**
   	 * Gets the copies Number.
   	 * 
   	 * @return  copiesNumber
   	 */
	public int getCopiesNumber() {
		return copiesNumber;
	}
	/**
	 * Instantiates the copies Number
	 * @param copiesNumber
	 */
	public void setCopiesNumber(int copiesNumber) {
		this.copiesNumber = copiesNumber;
	}
   	/**
   	 * Gets the users number With Book Late  .
   	 * 
   	 * @return  usersWithBookLateReturnNumber
   	 */
	public int getUsersWithBookLateReturnNumber() {
		return usersWithBookLateReturnNumber;
	}
	/**
	 * Instantiates the  the users number With Book Late
	 * @param usersWithBookLateReturnNumber
	 */
	public void setUsersWithBookLateReturnNumber(int usersWithBookLateReturnNumber) {
		this.usersWithBookLateReturnNumber = usersWithBookLateReturnNumber;
	}
	
	

}
