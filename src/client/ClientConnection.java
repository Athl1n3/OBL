package client;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import common.OBLclientIF;
import common.PDFfile;
import controllers.DatabaseController;

public class ClientConnection implements OBLclientIF {

	// The default port to connect on.
	final public static int DEFAULT_PORT = 5555;

	// The instance of the client that created this ConsoleChat.
	OBLclient client;

	/*
	 * @param host The host to connect to.
	 * 
	 * @param port The port to connect on.
	 */

	public ClientConnection() {
		this("localhost", DEFAULT_PORT);
		/*
		 * try { client = new ChatClient("localhost", DEFAULT_PORT, this);
		 * System.out.println("connected"); } catch (IOException Exception) {
		 * System.out.println("Error: Can't setup connection!" +
		 * " Terminating client."); System.exit(1); }
		 */
	}

	public ClientConnection(String host, int port) {
		try {
			client = new OBLclient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public void executeQuery(Object obj) {
		client.handleMessageFromClientUI(obj);
	}

	public void saveFile(String bookName, String filePath, int bookID) {
		client.handleFileFromClientUI(bookName, filePath, bookID);
	}

	public void uploadFile(Object obj) {
		client.handleMessageFromClientUI(obj);
	}
	
	/**
	 * This method was built only for testing purposes (External system sends a
	 * graduation note with graduated student ID to OBL server)
	 */
	public void graduateStudent(Integer studentID) {
		client.handleMessageFromClientUI(studentID);
	}

	// **************************************//
	private Object obj;

	@Override
	public void serverObj(Object obj) {
		System.out.println("> Object received from server.");
		this.obj = obj;
	}

	public Object getObject() {
		return this.obj;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<String> getList() {
		return (ArrayList<String>) this.obj;
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	@Override
	public void display(String message) {
		System.out.println("> " + message);
	}

	/**
	 * Terminate client connection to the server
	 */
	public void terminate() {
		client.quit();
	}

	public void init() {
		DatabaseController.InitiateClient(this);
	}

}
//End of ConsoleChat class
