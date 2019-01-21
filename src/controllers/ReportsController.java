package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.LibrarianAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReportsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

	@FXML
	private Button btnGetReport;

	@FXML
	private RadioButton rdActivityReport;

	@FXML
	private ToggleGroup colorToggleGroup;

	@FXML
	private RadioButton rdLendsReport;

	@FXML
	private RadioButton rdDelaysReport;

	private static LibrarianAccount loggedLibAccount;

	@FXML
	void btnGetReportPressed(ActionEvent event) {

	}

	@FXML
	void imgBackPressed(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Library Management");
	}

	void start(Stage primaryStage, Account loggedLibAccount) throws Exception {
		this.loggedLibAccount = (LibrarianAccount) loggedLibAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ReportsForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Reports");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void initialize() {
	}
}
