package controllers;

import java.io.*;
import java.util.ArrayList;

import client.*;
import common.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ClientConsole implements OBLclientIF {

	// The default port to connect on.
	final public static int DEFAULT_PORT = 5555;

	// The instance of the client that created this ConsoleChat.
	OBLclient client;

	/*
	 * @param host The host to connect to.
	 * 
	 * @param port The port to connect on.
	 */

	public ClientConsole() {
		this("localhost", DEFAULT_PORT);
		/*
		 * try { client = new ChatClient("localhost", DEFAULT_PORT, this);
		 * System.out.println("connected"); } catch (IOException Exception) {
		 * System.out.println("Error: Can't setup connection!" +
		 * " Terminating client."); System.exit(1); }
		 */
	}

	public ClientConsole(String host, int port) {
		try {
			client = new OBLclient(host, port, this);
		} catch (IOException exception) {
			System.out.println("Error: Can't setup connection!" + " Terminating client.");
			System.exit(1);
		}
	}

	public void executeQuery(String query) {
		client.handleMessageFromClientUI(query);
	}

	// **************************************//
	private Object obj;

	public void serverObj(Object obj) {
		System.out.println("> Object received from server.");
		System.out.println((ArrayList<String>) obj);
		this.obj = obj;
	}

	public Object getObject() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Loading data from server");
		alert.show();
		try {
			Thread.sleep(1500, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.close();
		return this.obj;
	}
	
	public ArrayList<String> getList()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Loading data from server");
		alert.show();
		try {
			Thread.sleep(1500, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		alert.close();
		return (ArrayList<String>) this.obj;
	}

	/**
	 * This method overrides the method in the ChatIF interface. It displays a
	 * message onto the screen.
	 *
	 * @param message The string to be displayed.
	 */
	public void display(String message) {
		System.out.println("> " + message);
	}

	public void terminate() {
		client.quit();
	}

}
//End of ConsoleChat class
