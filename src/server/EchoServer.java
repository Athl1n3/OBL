package server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 */
public class EchoServer extends AbstractServer {
	// The default port to listen on.
	final public static int DEFAULT_PORT = 5555;
	private static MySQLConnection DBcon;
	private static LibraryServices libraryServices;

	// @param port The port number to connect on.
	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	Object obj;

	@Override
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("Message received: " + msg.toString() + " from " + client);
		if(msg instanceof Integer)
		{
			graduateStudent((Integer)msg);
			obj = new String("Student ID "+msg+"has been set as graduated");
		}
		/*else if(msg instanceof String )
		{
			Implementation for order detection
		}*/
		else
		{
			obj = DBcon.executeQuery(msg);
			if(obj == null)
				obj = true;
		}
		try {
			client.sendToClient(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void graduateStudent(int studentID)
	{
		libraryServices.graduateStudent(studentID);
	}
	
	public void orderNotification(int studentID)
	{
		//libraryServices.orderNotification(studentID);
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	@Override
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	@Override
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
	 */

	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[3]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}
		DBcon = new MySQLConnection(args[0], args[1], args[2]);
		EchoServer sv = new EchoServer(port);
		libraryServices = new LibraryServices(EchoServer.DBcon);
		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class
