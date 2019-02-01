package client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import common.OBLclientIF;
import common.PDFfile;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ocsf.client.AbstractClient;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 */
public class OBLclient extends AbstractClient {
	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	OBLclientIF clientUI;

	// Constructors ****************************************************
	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public OBLclient(String host, int port, OBLclientIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		openConnection();
	}

	Semaphore sem = new Semaphore(0);

	// static Alert load = new Alert(AlertType.INFORMATION, "Please wait...\n
	// Loading data from database");

	// Instance methods ************************************************
	// This method handles all data that comes in from the server.
	@Override
	public void handleMessageFromServer(Object msg) {
		// clientUI.display(msg.toString());
		clientUI.serverObj(msg);
		sem.release();
	}

	static Alert load = new Alert(AlertType.INFORMATION, "Please wait...\n Loading data from database");

	/**
	 * This method handles all data coming from the UI
	 * 
	 * @param obj data recieved from client
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromClientUI(Object obj) {
		try {
			sendToServer(obj);
			if (obj instanceof String) {
				if (((String) obj).startsWith("SELECT"))
					load.show();
			} else if (obj instanceof ArrayList) {
				if (((ArrayList<String>) obj).get(((ArrayList<String>) obj).size() - 1).toString().startsWith("SELECT"))
					load.show();
			}
			sem.acquire();
			load.close();
		} catch (IOException | InterruptedException e) {
			clientUI.display("Could not send message to server.  Terminating client.");
			quit();
		}
	}

	public void handleFileFromClientUI(String bookName, String filePath, int bookID) {
		try {
			PDFfile uploadedFile = new PDFfile(bookName);
			uploadedFile.setFilePath(filePath);
			uploadedFile.setBookID(bookID);
			File newFile = new File(filePath);
			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			uploadedFile.initArray(mybytearray.length);
			uploadedFile.setSize(mybytearray.length);

			bis.read(uploadedFile.getMybytearray(), 0, mybytearray.length);
			sendToServer(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hook method called after the connection has been closed.
	 */
	@Override
	protected void connectionClosed() {
		System.out.println("Successfully disconnected from server");
	}

	/**
	 * Hook method called after a connection has been established.
	 */
	@Override
	protected void connectionEstablished() {
		System.out.println("Successfully connected to the server");
	}
}
//End of ChatClient class
