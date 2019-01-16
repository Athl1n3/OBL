package controllers;

import java.util.List;

import client.ClientConnection;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainTest extends Application{

/*
	public List<String> getInputFromCmd(){
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		return list;
	}
	*/
	public void start(Stage stage) {
		//List<String> list = getInputFromCmd();
		String host;
		//if(list.isEmpty())
		//{
			host = "localhost";
		//}
		//else
			//host = list.get(0);
		ClientConnection cc = new ClientConnection(host, 5555);
		NewAccountController mFrame = new NewAccountController();
		cc.init();
		mFrame.cc = cc;
		mFrame.start(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
