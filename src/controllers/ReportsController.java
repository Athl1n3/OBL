package controllers;

import entities.Account;
import entities.LibrarianAccount;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ReportsController {
	private static LibrarianAccount loggedLibAccount;

	void start(Stage primaryStage, Account loggedLibAccount) throws Exception {
		this.loggedLibAccount = (LibrarianAccount) loggedLibAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ReportsForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Reports");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
