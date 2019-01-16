package controllers;

import java.util.ArrayList;

import client.ClientConnection;
import entities.Account;
import entities.Book;
import entities.UserAccount;

public class DatabaseController {
	private static ClientConnection ClientConnection;

	public static void AddAccount(Account newAccount) {

	}

	public static void EditAccount(Account existingAccount) {

	}

	public static Account getAccount(int ID) {
		ClientConnection.executeQuery("SELECT * FROM Account WHERE ID = +" + ID + ";");
		ArrayList<String> res = ClientConnection.getList();
		if (res.size() != 0) {
			Account userAccount = new UserAccount();
			((UserAccount) userAccount).parseArrayIntoAccount(res);
			return userAccount;
		} else
			return null;
	}

	public static Account getAccount(String username, String password) {
		Account userAccount = new UserAccount();
		ClientConnection.executeQuery(
				"SELECT * FROM Account WHERE username = '" + username + "' AND password = '" + password + "';");
		ArrayList<String> res = ClientConnection.getList();
		// TEST
		return userAccount;
	}

	public static void AddBook(Book newBook) {

	}

	public static void EditBook(Book existingBook) {

	}

	/**
	 * Initiate a client connection to the server
	 * 
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection) {
		ClientConnection = newClientConnection;
	}

	public static void terminateClient() {
		ClientConnection.terminate();
	}
}
