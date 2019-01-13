package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
		newAccount.setID(Integer.parseInt(txtID.getText()));

	}

	@FXML
	void imgBackClicked(MouseEvent event) {

	}

	@FXML
	void initialize() {

	}
}
