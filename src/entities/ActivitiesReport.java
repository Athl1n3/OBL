package entities;

import java.util.ArrayList;

public class ActivitiesReport {
	
	private int totalUsers;
	private int activeUsersNumber;
	private int frozenUsersNumber;
	private int lockedUsersNumber;
	private int usersWithBookLateReturnNumber;
	private ArrayList<Book> books;
	private ArrayList<Account> accounts;
	
	
	public ActivitiesReport(int totalUsers, int activeUsersNumber, int frozenUsersNumber, int lockedUsersNumber,
			int usersWithBookLateReturnNumber, ArrayList<Book> books, ArrayList<Account> accounts) {
		super();
		this.totalUsers = totalUsers;
		this.activeUsersNumber = activeUsersNumber;
		this.frozenUsersNumber = frozenUsersNumber;
		this.lockedUsersNumber = lockedUsersNumber;
		this.usersWithBookLateReturnNumber = usersWithBookLateReturnNumber;
		this.books = books;
		this.accounts = accounts;
	}
	
	/**
	 * Initialise the total users in the system
	 * @param totalUsers
	 */
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	/**
	 * Get the number of total users in the system
	 * @return totalUsers
	 */
    public int getTotalUsers() {
    	return totalUsers;
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
	 * Initialise the active user number
	 * @param activeUsersNumber set the active user number
	 */
	public void setActiveUsersNumber(int activeUsersNumber) {
		this.activeUsersNumber = activeUsersNumber;
	}
   	/**
   	 * Gets the frozen users number.
   	 * 
   	 * @return  frozenUsersNumber
   	 */
	public int getFrozenUsersNumber() {
		return frozenUsersNumber;
	}
	/**
	 * Initialise the frozen Users Number
	 * @param unActiveUsersNumber set the frozen user number
	 */
	public void setFrozenUsersNumber(int frozenUsersNumber) {
		this.frozenUsersNumber = frozenUsersNumber;
	}
	
   	/**
   	 * Gets the locked Users Number.
   	 * 
   	 * @return  lockedUsersNumber
   	 */
	public int getLockedUsersNumber() {
		return lockedUsersNumber;
	}
	/**
	 * Initialise the locked Users Number
	 * @param lockedUsersNumber set the locked Users Number
	 */
	public void setLockedUsersNumber(int lockedUsersNumber) {
		this.lockedUsersNumber = lockedUsersNumber;
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
	
	public ArrayList<Book> getBooks(){
		return books;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
	public int getAllLibraryBooksNum() {
		int num = 0;
		for(Book x : books) {
			num = num + x.getCopiesNumber();
		}
		return num;
		
	}
	
}
