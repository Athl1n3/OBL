package controllers;

import java.util.ArrayList;

import client.ClientConnection;
import entities.Account;
import entities.Archive;
import entities.Book;
import entities.LentBook;
import entities.LibrarianAccount;
import entities.ManagerAccount;
import entities.UserAccount;

public class DatabaseController {

	private static ClientConnection clientConnection;

	static Account loggedAccount;

	/**
	 * create new account
	 * 
	 * @param arr
	 */
	public static void addAccount(UserAccount newAccount) {
		ArrayList<String> arr = new ArrayList<String>();
		String query = "INSERT INTO account(ID, firstName, lastName, eMail, mobileNum, userID, userName, password, userType, status, delays)VALUES (?,?,?,?,?,?,?,?,?,?,?)";
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
		arr.add(String.valueOf(newAccount.getDelays()));
		arr.add(query);
		clientConnection.executeQuery(arr);
	}

	/**
	 * update account details
	 * 
	 * @param existingAccount
	 */
	public static void updateAccount(Account existingAccount) {
		String query = "UPDATE account SET firstName = '" + existingAccount.getFirstName() + "' lastName = '"
				+ existingAccount.getLastName() + "' eMail = '" + existingAccount.getEmail() + "' mobileNum = '"
				+ existingAccount.getMobileNum() + "' userName = '" + existingAccount.getUserName() + "' password = '"
				+ existingAccount.getPassword() + "' WHERE userID = '" + existingAccount.getAccountID() + "';";
		clientConnection.executeQuery(query);
	}

	/**
	 * finds the account in DB according to user id and returned it, if the account
	 * doesn't exists then return null
	 * 
	 * @param ID
	 * @return Account
	 */
	public static Account getAccount(int ID) {
		clientConnection.executeQuery("SELECT * FROM Account WHERE ID = +" + ID + ";");
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			Account userAccount = new UserAccount();
			((UserAccount) userAccount).parseArrayIntoAccount(res);
			return userAccount;
		} else
			return null;
	}

	/**
	 * finds the account in DB according to (username && password) and returned it,
	 * if the account doesn't exists then return null
	 * 
	 * @param username
	 * @param password
	 * @return Account
	 */
	public static Account getAccount(String username, String password) {
		String query = "SELECT * FROM Account WHERE username = '" + username + "' AND password = '" + password + "';";
		clientConnection.executeQuery(query);
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			Account loggedAccount;
			if (res.get(8).equals("User")) {
				loggedAccount = new UserAccount();
				((UserAccount) loggedAccount).parseArrayIntoAccount(res);
			} else {
				if (res.get(8).equals("Librarian"))
					loggedAccount = new LibrarianAccount();
				else
					loggedAccount = new ManagerAccount();
				((LibrarianAccount) loggedAccount).parseArrayIntoAccount(res);
			}
			return loggedAccount;
		} else
			return null;
	}

	/**
	 * adds new book to the library book list
	 * 
	 * @param newBook
	 */
	public static void addBook(Book newBook) {
		ArrayList<String> arr = new ArrayList<String>();
		String query = "INSERT INTO BOOk(bookID, name, author, edition, printYear, subject, description, catalog,"
				+ " tableOfContents, shelf, copiesNumber, Type, availableCopies) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		arr.add(String.valueOf(newBook.getBookID()));
		arr.add(newBook.getName());
		arr.add(newBook.getAuthor());
		arr.add(newBook.getEdition());
		arr.add(String.valueOf(newBook.getPrintYear()));
		arr.add(newBook.getSubject());
		arr.add(newBook.getDescription());
		arr.add(String.valueOf(newBook.getCatalog()));
		arr.add(newBook.getTableOfContents());
		arr.add(newBook.getShelf());
		arr.add(String.valueOf(newBook.getCopiesNumber()));
		arr.add(String.valueOf(newBook.getBookType()));
		arr.add(String.valueOf(newBook.getAvailableCopies()));
		arr.add(query);
		clientConnection.executeQuery(arr);
	}

	/**
	 * this function updates existed book data according to book id
	 * 
	 * @param existingBook
	 */
	public static void editBook(Book existingBook) {
		clientConnection.executeQuery("UPDATE book SET copiesNumber = '" + existingBook.getCopiesNumber()
				+ "' shelf = '" + existingBook.getShelf() + "' description = '" + existingBook.getDescription()
				+ "' WHERE bookID = '" + existingBook.getBookID() + "' ;");
		// need to update the pdf contents file path
	}

	/**
	 * deletes book from library books list
	 * 
	 * @param bookToDelete
	 */
	public static void deleteBook(int bookID) {
		clientConnection.executeQuery("DELETE FROM book WHERE bookID = '" + bookID + "';");
	}

	/**
	 * search for a specific book according to its id and if its founded, return it,
	 * else return null
	 * 
	 * @param id
	 * @return Book
	 */
	public static Book getBook(int id) {
		clientConnection.executeQuery("SELECT * FROM book WHERE  bookID= '" + id + "' ;");
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			Book book = new Book(Integer.parseInt(res.get(0)), res.get(1), res.get(2), res.get(3),
					Integer.parseInt(res.get(4)), res.get(5), res.get(6), Integer.parseInt(res.get(7)), res.get(8),
					res.get(9), Integer.parseInt(res.get(10)), res.get(11), Integer.parseInt(res.get(12)));

			return book;
		}

		return null;
	}

	public static ArrayList<Book> bookSearch(String str, String searchBy) {
		switch (searchBy.toLowerCase()) {
		case "name":
			clientConnection.executeQuery("SELECT * FROM book WHERE  name= '" + str.toLowerCase() + "' ;");
			break;
		case "author":
			clientConnection.executeQuery("SELECT * FROM book WHERE  author= '" + str.toLowerCase() + "' ;");
			break;
		case "subject":
			clientConnection.executeQuery("SELECT * FROM book WHERE  subject= '" + str.toLowerCase() + "' ;");
			break;
		case "description":
			clientConnection.executeQuery("SELECT * FROM book WHERE  description= '" + str.toLowerCase() + "' ;");
			break;
		default:
			return null;
		}
		ArrayList<String> res = clientConnection.getList();
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (res.size() != 0) {
			Book book = new Book(Integer.parseInt(res.get(0)), res.get(1), res.get(2), res.get(3),
					Integer.parseInt(res.get(4)), res.get(5), res.get(6), Integer.parseInt(res.get(7)), res.get(8),
					res.get(9), Integer.parseInt(res.get(10)), res.get(11), Integer.parseInt(res.get(12)));
			res.subList(0, 13).clear();
			bookList.add(book);
		}

		return bookList;
	}

	/**
	 * update the return date of a specific lent book
	 * 
	 * @param updatedLentBook
	 */
	public static void updateLentBook(LentBook updatedLentBook) {
		clientConnection.executeQuery("UPDATE lentbook SET ");
	}

	public static void addLentBook(LentBook newLentBook) {

	}

	/**
	 * this function returns the user's original data from DB according to its id,
	 * if user not founded,return null
	 * 
	 * @param id
	 * @return Archive
	 */
	public static Archive getArchiveData(int id) {
		clientConnection.executeQuery("SELECT * FROM archive WHERE  ID= '" + id + "' ;");
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			Archive archive = new Archive(Integer.parseInt(res.get(0)), Integer.parseInt(res.get(1)), res.get(2),
					res.get(3), res.get(4), res.get(5), res.get(6), res.get(7));
			return archive;
		}

		return null;

	}

	public static void InitiateClient(ClientConnection newClientConnection) {
		clientConnection = newClientConnection;
	}

	public static void terminateClient() {
		clientConnection.terminate();
	}
	

	public static ArrayList<Book> getAllBooks() {
		
			clientConnection.executeQuery("SELECT * FROM book");
	
		ArrayList<String> res = clientConnection.getList();
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (res.size() != 0) {
			Book book = new Book(Integer.parseInt(res.get(0)), res.get(1), res.get(2), res.get(3),
					Integer.parseInt(res.get(4)), res.get(5), res.get(6), Integer.parseInt(res.get(7)), res.get(8),
					res.get(9), Integer.parseInt(res.get(10)), res.get(11), Integer.parseInt(res.get(12)));
			res.subList(0, 13).clear();
			bookList.add(book);
		}

		return bookList;
	}
}
