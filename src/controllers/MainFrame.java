package controllers;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import server.ClientConsole;

public class MainFrame extends Application {
	
	public List<String> getInputFromCmd(){
		Parameters params = getParameters();
		List<String> list = params.getRaw();
		return list;
	}
	
	@Override
	public void start(Stage stage) {
		List<String> list = getInputFromCmd();
		String host;
		if(list.isEmpty())
		{
			host = "localhost";
		}
		else
			host = list.get(0);
		ClientConsole cc = new ClientConsole(host, 5555);
		MainFrameController mFrame = new MainFrameController();
		mFrame.cc = cc;
		mFrame.start(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
