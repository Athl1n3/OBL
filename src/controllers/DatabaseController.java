package controllers;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import client.ClientConnection;
import entities.Account;
import entities.Archive;
import entities.Book;
import entities.Book.bookType;
import entities.BookCopy;
import entities.BookOrder;
import entities.LentBook;
import entities.LibrarianAccount;
import entities.ManagerAccount;
import entities.Notification;
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
	 * Lock user account
	 * 
	 * @param userAccount
	 */
	public static boolean lockAccount(int accountID) {
		String query = "UPDATE account SET status = 'Locked' WHERE userID = '" + accountID + "';";
		clientConnection.executeQuery(query);
		clientConnection.getObject();
		return (Boolean)clientConnection.getObject();
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

	/**
	 * finds the account in DB according to user id and returned it, if the account
	 * doesn't exists then return null
	 * 
	 * @param ID
	 * @return Account
	 */
	public static Account getAccountByAccountID(int accountID) {
		clientConnection.executeQuery("SELECT * FROM Account WHERE userID = +" + accountID + ";");
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
	public static ArrayList<Book> bookSearch(String str, String searchBy)throws NumberFormatException{
		switch (searchBy.toLowerCase()) {
		case "book id":
			clientConnection.executeQuery("SELECT * FROM book WHERE bookID = '" + Integer.parseInt(str) + "' ;");
			break;
		case "name":
			clientConnection.executeQuery("SELECT * FROM book WHERE name= '" + str.toLowerCase() + "' ;");
			break;
		case "author":
			clientConnection.executeQuery("SELECT * FROM book WHERE author= '" + str.toLowerCase() + "' ;");
			break;
		case "subject":
			clientConnection.executeQuery("SELECT * FROM book WHERE subject= '" + str.toLowerCase() + "' ;");
			break;
		case "description":
			clientConnection.executeQuery("SELECT * FROM book WHERE description= '" + str.toLowerCase() + "' ;");
			break;
		default:
			return null;
		}
		ArrayList<String> res = clientConnection.getList();
		if(res.isEmpty())
			return null;
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
	public static void updateBookAvailableCopies(Book book, int val) {
		clientConnection.executeQuery("UPDATE Book SET availableCopies = '" + (book.getAvailableCopies() + val)
				+ "' WHERE BookID = '" + book.getBookID() + "';");
	}

	/**
	 * add new lentBook to the user lentBook list
	 * 
	 * @param newLentBook
	 */
	public static void addLentBook(LentBook newLentBook) {
		ArrayList<String> arr = new ArrayList<String>();
		String query = "INSERT INTO LentBook(userID, bookID,copySerialNumber, issueDate, dueDate, late) VALUES(?,?,?,?,?,?)";
		arr.add(String.valueOf(newLentBook.getUserID()));
		arr.add(String.valueOf(newLentBook.getBook().getBookID()));
		arr.add(String.valueOf(newLentBook.getBookCopy().getSerialNumber()));
		arr.add(String.valueOf(newLentBook.getDueDate()));
		arr.add(String.valueOf(newLentBook.getIssueDate()));
		arr.add(String.valueOf(newLentBook.isLate()));
		arr.add(query);
		clientConnection.executeQuery(arr);
	}

	public static void deleteLendBook(int accountID, int bookID) {
		clientConnection
				.executeQuery("DELETE FROM LentBook WHERE userID = '" + accountID + "' AND bookID = '" + bookID + "';");
	}

	/**
	 * return the user lent books list from DB
	 * if(userID>=0) return only the user lent Book list 
	 * if(userID<0) return the whole lent Book list 
	 * @return ArrayList<LentBook>
	 */
	public static ArrayList<LentBook> getLentBookList(int userID) {
		String query;
		if(userID>=0)
			query = "SELECT userID,bookID, copySerialNumber, issueDate,dueDate,returnDate,late FROM LentBook WHERE userID  = '" + userID + "';";
		else
			query = "SELECT * FROM LentBook";
		clientConnection.executeQuery(query);
		ArrayList<String> res = clientConnection.getList();
		ArrayList<LentBook> lentBookList = new ArrayList<LentBook>();
		while (res.size() != 0) {
			LentBook lentBook = new LentBook(Integer.parseInt(res.get(0)), getBook(Integer.parseInt(res.get(1))),
					getBookCopy(res.get(2)), LocalDate.parse(res.get(3)), LocalDate.parse(res.get(4)),
					LocalDate.parse(res.get(5)), res.get(6).equals("1") ? true : false);
			res.subList(0, 7).clear();
			lentBookList.add(lentBook);
		}

		return lentBookList;
	}
	

	public static LentBook getLentBook(int userID, int bookID) {
		clientConnection
				.executeQuery("SELECT userID,bookID, copySerialNumber, issueDate,dueDate,returnDate,late FROM LentBook WHERE userID  = '" + userID + "' AND bookID = '" + bookID + "';");
		ArrayList<String> res = clientConnection.getList();
		LentBook lentBook;
		if (!res.isEmpty()) {
			lentBook = new LentBook(Integer.parseInt(res.get(0)), getBook(Integer.parseInt(res.get(1))),
					getBookCopy(res.get(2)), LocalDate.parse(res.get(3)), LocalDate.parse(res.get(4)),
					LocalDate.parse(res.get(5)), res.get(6).equals("1") ? true : false);
			return lentBook;
		}
		return null;
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

		clientConnection.executeQuery("SELECT * FROM BookCopy WHERE bookID  = '" + bookID + "';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<BookCopy> bookCopyList = new ArrayList<BookCopy>();
		while (res.size() != 0) {
			BookCopy bookCopy = new BookCopy(Integer.parseInt(res.get(0)), res.get(1),
					res.get(2).equals("1") ? true : false);
			res.subList(0, 3).clear();
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
	 * place an Book Order in DB
	 * 
	 * @param order
	 */
	public static void placeOrder(BookOrder order) {
		ArrayList<String> arr = new ArrayList<String>();
		String query = "INSERT INTO BookOrder(orderID, userID, bookID, orderDate) VALUES(?,?,?,?)";
		arr.add(String.valueOf(order.getOrderID()));
		arr.add(String.valueOf(order.getUserID()));
		arr.add(String.valueOf(order.getBookID()));
		arr.add(String.valueOf(order.getOrderDate()));
		arr.add(query);
		clientConnection.executeQuery(arr);
		// clientConnection.executeQuery("Select * FROM BookOrder Order By orderDate ASC
		// LIMIT 1");
		// System.out.println(clientConnection.getList());

	}

	/**
	 * returns the last order id
	 * 
	 * @return int
	 */
	public static int getLatestOrderID() {
		clientConnection.executeQuery("SELECT orderID FROM BookOrder ORDER BY orderID DESC");
		if (clientConnection.getList().size() != 0)
			return Integer.parseInt(clientConnection.getList().get(0));
		else
			return 0;
	}

	public static boolean checkExistingOrder(int userID, int bookID) {
		clientConnection
				.executeQuery("SELECT * FROM BookOrder WHERE userID = '" + userID + "' AND bookID = '" + bookID + "';");
		ArrayList<String> res = clientConnection.getList();
		if (res.size() != 0) {
			return true;
		}
		return false;
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
		clientConnection.executeQuery(
				"SELECT userid, activityName, date FROM useractivity WHERE userID = '" + AccountID + "';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<UserActivity> activityList = new ArrayList<UserActivity>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while (res.size() != 0) {
			try {
				Date parsedDate = dateFormat.parse(res.get(2));
				UserActivity activity = new UserActivity(Integer.parseInt(res.get(0)), res.get(1),
						new Timestamp(parsedDate.getTime()));
				res.subList(0, 3).clear();
				activityList.add(activity);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	}

	/**
	 * return user notifications list from DB
	 * 
	 * @param AccountID
	 * @return ArrayList<Notification>
	 */
	public static ArrayList<Notification> getNotifications(int AccountID) {
		if (AccountID != 1 && AccountID != 2)
			clientConnection.executeQuery(
					"SELECT notificationNum, userID, date, message, messageType FROM notification WHERE userID = '"
							+ AccountID + "';");
		else if (AccountID == 1)
			clientConnection.executeQuery(
					"SELECT notificationNum, userID, date, message, messageType FROM notification WHERE userType = 'Manager' OR userType='Librarian';");
		else
			clientConnection.executeQuery(
					"SELECT notificationNum, userID, date, message, messageType FROM notification WHERE userType = 'Librarian';");
		ArrayList<String> res = clientConnection.getList();
		ArrayList<Notification> notificationsList = new ArrayList<Notification>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while (res.size() != 0) {
			try {
				Date parsedDate = dateFormat.parse(res.get(2));
				Notification notification = new Notification(Integer.parseInt(res.get(0)), Integer.parseInt(res.get(1)),
						new Timestamp(parsedDate.getTime()), res.get(3), res.get(4));
				res.subList(0, 5).clear();
				notificationsList.add(notification);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return notificationsList;
	}
	
	/**
	 * get the closest return date from DB according to BookID
	 * @param bookID
	 * @return LocalDate
	 */
	public static LocalDate getClosestReturnDate(int bookID) {
		clientConnection.executeQuery("SELECT dueDate From LentBook WHERE bookID = '" + bookID + "' ORDER BY dueDate LIMIT 1");
		ArrayList<String> res = clientConnection.getList();
		if(!res.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			return LocalDate.parse(res.get(0), formatter);
		}else
			return null;
	}

	/**
	 * Delete notification from database
	 * 
	 * @param delNotf
	 */
	public static void deleteNotfication(Notification delNotf) {
		clientConnection.executeQuery(
				"DELETE FROM notification WHERE notificationNum = '" + delNotf.getNotificationNum() + "';");
	}

	/**
	 * Initiate a new client connection to the server
	 * 
	 * @param newClientConnection
	 */
	public static void InitiateClient(ClientConnection newClientConnection) {
		clientConnection = newClientConnection;
	}

	/**
	 * Shutdown client server connection when primary stage closes and logout logged in account
	 */
	public static void terminateClient() {
		if(loggedAccount != null)
		{
			loggedAccount.setLogged(false);
			logAccount(loggedAccount);
			System.out.println("Logging user out");
		}
		clientConnection.terminate();
	}

	/**
	 * This method was built only for testing purposes (External system sends a
	 * graduation note with graduated student ID to OBL server)
	 * 
	 * @param accountID
	 */
	public static void graduateStudent(Integer studentID) {
		clientConnection.graduateStudent(studentID);
	}
}
