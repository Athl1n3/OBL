package controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import client.ClientConnection;
import entities.Account;
import entities.Archive;
import entities.Book;
import entities.Book.bookType;
import entities.BookCopy;
import entities.LentBook;
import entities.LibrarianAccount;
import entities.ManagerAccount;
import entities.UserAccount;
import entities.UserActivity;

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
		String query = "INSERT INTO account(ID, firstName, lastName, eMail, mobileNum, userID, userName, password, userType, status, delays,logged)VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
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
		arr.add(String.valueOf(0));
		arr.add(query);
		clientConnection.executeQuery(arr);
		clientConnection.executeQuery(
				"INSERT INTO archive(userID, ID, userName, password, firstName, lastName, mobileNum, eMail)VALUES ('"
						+ newAccount.getAccountID() + "','" + newAccount.getID() + "','" + newAccount.getUserName()
						+ "','" + newAccount.getPassword() + "','" + newAccount.getFirstName() + "','"
						+ newAccount.getLastName() + "','" + newAccount.getMobileNum() + "','" + newAccount.getEmail()
						+ "')");

	}

	/**
	 * Generate a new account ID for the new user
	 * 
	 * @return account ID for the new user
	 */
	public static int generateAccountID() {
		clientConnection.executeQuery("SELECT COUNT(*) FROM account;");
		return (Integer.parseInt(clientConnection.getList().get(0)) + 1) * 264 + 759;
	}

	/**
	 * update account details including logged field
	 * 
	 * @param account
	 */
	public static void updateAccount(Account account) {
		/*
		 * String query = "UPDATE account SET firstName = '" + account.getFirstName() +
		 * "', lastName = '" + account.getLastName() + "', eMail = '" +
		 * account.getEmail() + "', mobileNum = '" + account.getMobileNum() +
		 * "', userName = '" + account.getUserName() + "', password = '" +
		 * account.getPassword() + "', logged = '" + (account.isLogged() == true ? 1:0)
		 * + "' WHERE userID = '" + account.getAccountID() + "';";
		 */
		String query = "UPDATE account SET firstName = '" + account.getFirstName() + "', lastName = '"
				+ account.getLastName() + "', eMail = '" + account.getEmail() + "', mobileNum = '"
				+ account.getMobileNum() + "', userName = '" + account.getUserName() + "', password = '"
				+ account.getPassword() + "' WHERE userID = '" + account.getAccountID() + "';";
		clientConnection.executeQuery(query);
	}

	public static void logAccount(Account account) {
		clientConnection.executeQuery("UPDATE account SET logged = '" + (account.isLogged() == true ? 1 : 0)
				+ "' WHERE userID = '" + account.getAccountID() + "';");
	}

	/**
	 * update user's status
	 * 
	 * @param userAccount
	 */
	public static void updateUserStatus(UserAccount userAccount) {
		String query = "UPDATE account SET status = '" + userAccount.getStatus() + "' WHERE userID = '"
				+ userAccount.getAccountID() + "';";
		clientConnection.executeQuery(query);
	}

	/**
	 * update user's delays
	 * 
	 * @param userAccount
	 */
	public static void updateUserDelays(UserAccount userAccount) {
		String query = "UPDATE account SET delays = '" + userAccount.getDelays() + "' WHERE userID = '"
				+ userAccount.getAccountID() + "';";
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

	public static boolean ifExists(String table, String field, String fieldVal) {
		clientConnection
				.executeQuery("SELECT EXISTS(SELECT * FROM " + table + " WHERE " + field + " = '" + fieldVal + "');");
		if (clientConnection.getList().get(0).equals("0"))
			return false;// Field value doesn't exist
		return true;// Field value already exists
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
				+ "', shelf = '" + existingBook.getShelf() + "', description = '" + existingBook.getDescription()
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
					res.get(9), Integer.parseInt(res.get(10)),
					res.get(11).equals("Regular") ? bookType.Regular : bookType.Wanted, Integer.parseInt(res.get(12)));

			return book;
		}

		return null;
	}

	/**
	 * return list of books from DB
	 * 
	 * @return ArrayList<Book>
	 */
	public static ArrayList<Book> getAllBooks() {

		clientConnection.executeQuery("SELECT * FROM book");

		ArrayList<String> res = clientConnection.getList();
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (res.size() != 0) {
			Book book = new Book(Integer.parseInt(res.get(0)), res.get(1), res.get(2), res.get(3),
					Integer.parseInt(res.get(4)), res.get(5), res.get(6), Integer.parseInt(res.get(7)), res.get(8),
					res.get(9), Integer.parseInt(res.get(10)),
					res.get(11).equals("Regular") ? bookType.Regular : bookType.Wanted, Integer.parseInt(res.get(12)));
			res.subList(0, 13).clear();
			bookList.add(book);
		}

		return bookList;
	}

	/**
	 * search for specific book according to its name,author, subject or description
	 * 
	 * @param str      search for book
	 * @param searchBy it could be name, author, subject or description
	 * @return ArrayList<Book>
	 */
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
					res.get(9), Integer.parseInt(res.get(10)),
					res.get(11).equals("Regular") ? bookType.Regular : bookType.Wanted, Integer.parseInt(res.get(12)));
			res.subList(0, 13).clear();
			bookList.add(book);
		}

		return bookList;
	}

	/**
	 * update the Book availableCopies -=1
	 * 
	 * @param book
	 */
	public static void updateBookAvailableCopies(Book book) {
		clientConnection.executeQuery("UPDATE Book SET availableCopies = '" + (book.getAvailableCopies() - 1)
				+ "' WHERE BookID = '" + book.getBookID() + "';");
	}

	/**
	 * add new lentBook to the user lentBook list
	 * 
	 * @param newLentBook
	 */
	public static void addLentBook(LentBook newLentBook) {
		ArrayList<String> arr = new ArrayList<String>();
		String query = "INSERT INTO LentBook(userID, bookID, BookCopyID, issueDate, dueDate, late) VALUES(?,?,?,?,?,?)";
		arr.add(String.valueOf(newLentBook.getUserID()));
		arr.add(String.valueOf(newLentBook.getBook().getBookID()));
		arr.add(String.valueOf(newLentBook.getBookCopy().getSerialNumber()));
		arr.add(String.valueOf(newLentBook.getDueDate()));
		arr.add(String.valueOf(newLentBook.getIssueDate()));
		arr.add(String.valueOf(newLentBook.isLate()));
		arr.add(query);
		clientConnection.executeQuery(arr);
	}

	/**
	 * return the user lent books list from DB
	 * 
	 * @return ArrayList<LentBook>
	 */
	public static ArrayList<LentBook> getLentBookList(int userID) {

		clientConnection.executeQuery("SELECT * FROM LentBook WHERE userID  = '" + userID + "';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<LentBook> lentBookList = new ArrayList<LentBook>();
		while (res.size() != 0) {
			LentBook lentBook = new LentBook(Integer.parseInt(res.get(0)), getBook(Integer.parseInt(res.get(1))),
					getBookCopy(res.get(2)), LocalDate.parse(res.get(3)), LocalDate.parse(res.get(4)),
					res.get(5).equals("1") ? true : false);
			res.subList(0, 5).clear();
			lentBookList.add(lentBook);
		}

		return lentBookList;
	}

	/**
	 * return book copy instance from data base according to its serialNumber
	 * 
	 * @param serialNumber
	 * @return BookCopy
	 */
	private static BookCopy getBookCopy(String serialNumber) {

		clientConnection.executeQuery("SELECT * FROM BookCopy WHERE bookID= '" + serialNumber + "' ;");
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			BookCopy bookCopy = new BookCopy(Integer.parseInt(res.get(0)), res.get(1),
					res.get(2).equals("1") ? true : false);
			return bookCopy;
		}
		return null;
	}

	/**
	 * return the book copies list from DB
	 * 
	 * @return ArrayList<BookCopy>
	 */
	public static ArrayList<BookCopy> getbookCopyList(int bookID) {

		clientConnection.executeQuery("SELECT * FROM LentBook WHERE bookID  = '" + bookID + "';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<BookCopy> bookCopyList = new ArrayList<BookCopy>();
		while (res.size() != 0) {
			BookCopy bookCopy = new BookCopy(Integer.parseInt(res.get(0)), res.get(1),
					res.get(2).equals("1") ? true : false);
			res.subList(0, 2).clear();
			bookCopyList.add(bookCopy);
		}

		return bookCopyList;
	}

	/**
	 * update the BookCopy late field
	 * 
	 * @param bookCopy
	 */
	public static void updateBookCopyLentField(BookCopy bookCopy) {
		clientConnection.executeQuery("UPDATE BookCopy SET lent = '" + bookCopy.isLent() + "' WHERE bookID = '"
				+ bookCopy.getBookID() + "' AND serialNumber = '" + bookCopy.getSerialNumber() + "';");
	}

	/**
	 * update the return date of a specific lent book
	 * 
	 * @param LentBook
	 */
	public static void updateBookReturnDate(LentBook lentbook) {
		clientConnection.executeQuery("UPDATE LentBook SET dueDate = '" + lentbook.getDueDate() + "' WHERE bookID = '"
				+ lentbook.getBook().getBookID() + "';");
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

	/**
	 * return user activity list from DB
	 * 
	 * @param AccountID
	 * @return ArrayList<UserActivity>
	 */
	public static ArrayList<UserActivity> getUserActivity(int AccountID) {
		clientConnection.executeQuery("SELECT * FROM useractivity WHERE userID = '" + AccountID + "';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<UserActivity> activityList = new ArrayList<UserActivity>();
		while (res.size() != 0) {
			UserActivity activity = new UserActivity(Integer.parseInt(res.get(0)), res.get(1),
					LocalDate.parse(res.get(2)));
			res.subList(0, 3).clear();
			activityList.add(activity);
		}

		return activityList;
	}

	public static void InitiateClient(ClientConnection newClientConnection) {
		clientConnection = newClientConnection;
	}

	public static void terminateClient() {
		clientConnection.terminate();
	}

}
