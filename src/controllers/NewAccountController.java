package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewAccountController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnCreateAccount;

	@FXML
	private Button btnClear;

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtMobileNum;

	@FXML
	private TextField txtEmail;

	@FXML
	private Label lblUserID;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtConPassword;

	@FXML
	private TextField txtPassword;

	@FXML
	void btnClearPressed(ActionEvent event) {

	}

	@FXML
	void btnCreateAccountPressed(ActionEvent event) {
		Account newAccount = new UserAccount();
		// newAccount.setID(Integer.parseInt(txtID.getText()));

	}

	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/NewAccountForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Create new account");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Users Management");
	}

	@FXML
	void initialize() {

	}
}
