package server;

import java.io.IOException;
import java.util.ArrayList;

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
	Object obj;
	
	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	@Override
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("[Client "+client.getId()+"] Message received: " + msg.toString());
		if(msg instanceof Integer)
		{
			graduateStudent((Integer)msg);
			obj = new String("Student ID "+msg+"has been set as graduated");
		}
		else if(msg instanceof ArrayList)
		{	
			@SuppressWarnings("unchecked")
			ArrayList<String> arr = (ArrayList<String>)msg;
			if(arr.get(0).equals("#"))
			{
				arr.remove(0);
				orderNotification(Integer.parseInt(arr.get(0))); //book ID
			}
			obj = DBcon.executeQuery(msg);
		}
		else
			obj = DBcon.executeQuery(msg);
		if(obj == null)
				obj = true;
		try {
			client.sendToClient(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Call libraryServices graduate student method and set student status as graduated
	 * @param studentID
	 */
	public void graduateStudent(int studentID)
	{
		libraryServices.graduateStudent(studentID);
	}
	
	/**
	 * Send a notification to the next student in book order queue
	 * @param bookID
	 */
	public void orderNotification(int bookID)
	{
		libraryServices.orderNotification(bookID);
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
	
	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
	 */
	
	/**
	 * Hook method called each time a new client connection is accepted.
	 * @param client the connection connected to the client.
	 */
	@Override
	protected void clientConnected(ConnectionToClient client) {
		System.out.println("Client " + client.getId() + " has connected to the server");
	}

	/**
	 * Hook method called each time a client disconnects.
	 * @param client the connection with the client.
	 */
	@Override
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.println("Client " + client.getId() + " has disconnected from the server");
	}

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
			//sv.orderNotification(1234);
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class
