package controllers;

import java.util.ArrayList;

import client.ClientConnection;
import entities.*;

public class DatabaseController {
	private static ClientConnection clientConnection;
	
	static LoggedUser loggedUser;
	
	private static ArrayList<Account> users = new ArrayList<Account>();
	
	/**
	 * create new account
	 * @param newAccount
	 */
	public static void AddAccount(Account newAccount)
	{
		users.add(newAccount);
		ArrayList<Object> arr = new ArrayList<Object>(); 
		String query = "INSERT INTO person(ID, firstName, lastName, eMail, mobileNum, userID, userName, password, userType)VALUES (?,?,?,?,?,?,?,?,?)";
		arr.add(newAccount.getID());
		arr.add(newAccount.getFirstName());
		arr.add(newAccount.getLastName());
		arr.add(newAccount.geteMail());
		arr.add(newAccount.getMobileNum());
		arr.add(newAccount.getUserID());
		arr.add(newAccount.getUserName());
		arr.add(newAccount.getPassword());
		arr.add(newAccount.getUserType());
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
				"' lastName = '" + existingAccount.getLastName() + "' eMail = '" + existingAccount.geteMail()
				+ "' mobileNum = '" + existingAccount.getMobileNum() +
				"' userName = '" + existingAccount.getUserName() + "' password = '" + existingAccount.getPassword() + 
				"' WHERE userID = '" + existingAccount.getUserID() +"';";
		clientConnection.executeQuery(query);
	}
	
	/**
	 * return user account 
	 * @param userID
	 * @return Account
	 */
	public static Account GetUserAccount(int userID)
	{
		Account userAccount = new Account();
		clientConnection.executeQuery("SELECT * FROM Account WHERE userID = '"+userID+"';");
		ArrayList<String> res = clientConnection.getList(); 
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
