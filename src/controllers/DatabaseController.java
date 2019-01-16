package controllers;

import java.util.ArrayList;

import client.ClientConnection;
import entities.*;
import entities.Account.UserType;
import entities.UserAccount.accountStatus;

public class DatabaseController {

	private static ClientConnection clientConnection;
	
	static LoggedUser loggedUser;
	
	private static ArrayList<Account> users = new ArrayList<Account>();
	
	/**
	 * create new account
	 * @param arr
	 */
	public static void AddAccount(UserAccount newAccount)
	{
		ArrayList<String> arr = new ArrayList<String>(); 
		users.add(newAccount);
		String query = "INSERT INTO account(ID, firstName, lastName, eMail, mobileNum, userID, userName, password, userType, status)VALUES (?,?,?,?,?,?,?,?,?,?)";
		arr.add(String.valueOf(newAccount.getID()));
		arr.add(newAccount.getFirstName());
		arr.add(newAccount.getLastName());
		arr.add(newAccount.getEmail());
		arr.add(String.valueOf(newAccount.getMobileNum()));
		arr.add(String.valueOf(newAccount.getAccountID()));
		arr.add(newAccount.getUserName());
		arr.add(newAccount.getPassword());
		arr.add(newAccount.getUserType().toString());
		arr.add(newAccount.getStatus().toString());
		arr.add(query);
		clientConnection.executeQuery(arr);
	}
	
	/**
	 * update account details
	 * @param existingAccount
	 */
	public static void EditAccount(Account existingAccount)
	{
		String query = "UPDATE account SET firstName = '" + existingAccount.getFirstName() +
				"' lastName = '" + existingAccount.getLastName() + "' eMail = '" + existingAccount.getEmail()
				+ "' mobileNum = '" + existingAccount.getMobileNum() +
				"' userName = '" + existingAccount.getUserName() + "' password = '" + existingAccount.getPassword() + 
				"' WHERE userID = '" + existingAccount.getAccountID() +"';";
		clientConnection.executeQuery(query);
	}
	
	/**
	 * return user account 
	 * @param userID
	 * @return Account
	 */
	public static UserAccount GetUserAccount(int userID)
	{
		UserAccount userAccount = new UserAccount();
		clientConnection.executeQuery("SELECT * FROM Account WHERE userID = '" + userID + "';");
		ArrayList<String> res = clientConnection.getList(); 
		
		userAccount.setID(Integer.parseInt(res.get(0)));
		userAccount.setFirstName(res.get(1));
		userAccount.setLastName(res.get(2));
		userAccount.setEmail(res.get(3));
		userAccount.setMobileNum(Integer.parseInt(res.get(4)));
		userAccount.setAccountID(Integer.parseInt(res.get(5)));
		userAccount.setUserName(res.get(6));
    	userAccount.setPassword(res.get(7));
    	userAccount.setUserTypeString(res.get(8));
    	userAccount.setStatus(res.get(9));
		return userAccount;
	}
	
	public static void AddBook(Book newBook)
	{
		
	}
	
	public static void EditBook(Book existingBook)
	{
		
	}
	public static Archive getArchiveData(int id) {
		
		return null;
		
	}
	
	public static void initLoggedUser(String userName, String Password) {
		loggedUser = new LoggedUser();
		//loggedUser.setAccount();
		
	}
	
	/**
	 * Initiate a client connection to the server
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection)
	{
		clientConnection = newClientConnection;
	}
}
