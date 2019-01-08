package controllers;

import java.util.ArrayList;

import client.ClientConnection;
import entities.*;

public class DatabaseController {
	private static ClientConnection ClientConnection;
	
	public static void AddAccount(Account newAccount)
	{
		
	}
	
	public static void EditAccount(Account existingAccount)
	{
		
	}
	
	public static Account GetUserAccount(String username, String password)
	{
		Account userAccount = new Account();
		ClientConnection.executeQuery("SELECT * FROM Account WHERE username = '"+username+"' AND password = '"+password+"';");
		ArrayList<String> res = ClientConnection.getList();
		//TEST 
		return userAccount;
	}
	
	public static void AddBook(Book newBook)
	{
		
	}
	
	public static void EditBook(Book existingBook)
	{
		
	}
	
	/**
	 * Initiate a client connection to the server
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection)
	{
		ClientConnection = newClientConnection;
	}
}
