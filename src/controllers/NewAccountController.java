package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientConnection;
import entities.Account;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	private TextField txtUsername;
	@FXML
	private Label lblUserID;

	@FXML
	private TextField txtConPassword;

	@FXML
	private TextField txtPassword;

	public ClientConnection cc;

	/*
	 * Clear all textFields
	 */
	@FXML
	void btnClearPressed(ActionEvent event) {
		txtID.clear();
		txtFirstName.clear();
		txtLastName.clear();
		txtMobileNum.clear();
		txtEmail.clear();
		txtUsername.clear();
		txtConPassword.clear();
		txtPassword.clear();
	}

	/*
	 * @FXML void btnCreateAccountPressed(ActionEvent event) { UserAccount
	 * newAccount = new UserAccount();
	 * newAccount.setID(Integer.parseInt(txtID.getText()));
	 * newAccount.setFirstName(txtFirstName.getText());
	 * newAccount.setLastName(txtLastName.getText());
	 * newAccount.setMobileNum(txtMobileNum.getText());
	 * newAccount.setEmail(txtEmail.getText()); newAccount.setAccountID(1);
	 * newAccount.setUserName(txtUsername.getText());
	 * newAccount.setPassword(txtPassword.getText()); newAccount.userType =
	 * UserType.User; newAccount.status = accountStatus.Active;
	 * DatabaseController.addAccount(newAccount); }
	 */

	/**
	 * When CreateAccount Button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnCreateAccountPressed(ActionEvent event) {

		// validate if the inputed username length is greater than 5
		if (txtUsername.getText().length() <= 5) {
			// if not , inform the user that the username must be at least 6 characters
			alertWarningMessage("Username length must be longer than 5 characters");
			clearPassFields();
		} else {
			// validate if the inputed password length is greather than 6
			if (txtConPassword.getText().length() < 6 || txtPassword.getText().length() < 6) {
				// if not, inform the user that the password must be at least 7 characters
				alertWarningMessage("Password length must be atleast 6 characters");
				clearPassFields();
			} else {
				// validate that the two password fields are equal
				if (!txtPassword.getText().equals(txtConPassword.getText())) {
					// if not , inform the user that the password does not match
					alertWarningMessage("Password does not match !");
					clearPassFields();
				} else {
					// validate the inputed email address
					if (!validateEmail()) {
						// inform the user that the email is invalid
						alertWarningMessage("Invalid email address");
						clearPassFields();
					} else {
						// if every textfield is valid , then create a new account with all the details
						// inputed
						Account newAccount = new UserAccount(Integer.parseInt(txtID.getText()), txtFirstName.getText(),
								txtLastName.getText(), txtEmail.getText(), txtMobileNum.getText(),
								Integer.parseInt(lblUserID.getText()), txtUsername.getText(), txtPassword.getText(),
								accountStatus.Active, 0, false);

						if (DatabaseController.ifExists("account", "id", txtID.getText())) {
							new Alert(AlertType.INFORMATION, "ID already exists", ButtonType.OK).show();
						} else if (DatabaseController.ifExists("account", "username", txtUsername.getText())) {
							new Alert(AlertType.INFORMATION, "Username already exists", ButtonType.OK).show();
						} else {
							DatabaseController.addAccount((UserAccount) newAccount);
							// inform the user that the creation has been done successfully
							new Alert(AlertType.INFORMATION, "Account has been created successfully", ButtonType.OK)
									.show();
						}
					}
				}
			}
		}

	}

	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/NewAccountForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Create new account");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Back to the previous screen
	 */
	@FXML
	void imgBackClicked(MouseEvent event) {

		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		// get the previous scene
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Users Management");
	}

	/**
	 * Initialise the current screen
	 */
	@FXML
	void initialize() {
		// a listener to validate if the ID length is not greater than 9 digits and if
		// it's only contain numbers
		lblUserID.setText(String.valueOf(DatabaseController.generateAccountID()));
		txtID.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				txtID.setText(newValue.replaceAll("[^\\d]", ""));
				alertWarningMessage("The user ID must contain only numbers");
			}
			if (txtID.getLength() > 9) {
				txtID.setText(oldValue);
				alertWarningMessage("The ID must be 9 numbers");
			}
		});

		// a listener to validate if the FirstName textfield contains only alphabetic
		// characters
		txtFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z]*")) {
				txtFirstName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
				alertWarningMessage("First name must contain only letters");
			}
		});

		// a listener to validate if the LastName textfield contains only alphabetic
		// characters
		txtLastName.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("[a-zA-Z]*")) {
				txtLastName.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
				alertWarningMessage("Last name must contain only letters");
			}
		});

		// a listener to validate of the mobileNumber contains only numbers and his
		// length is not greater than 10 digits
		txtMobileNum.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				txtMobileNum.setText(newValue.replaceAll("[^\\d]", ""));
				alertWarningMessage("Mobile number must contain only numbers");
			}
			if (txtMobileNum.getLength() > 10) {
				txtMobileNum.setText(oldValue);
				alertWarningMessage("Mobile number must be of 10 digits");
			}
		});

		// Enable CreateAccountButton only when all the textfields is not empty
		BooleanBinding booleanBind = txtID.textProperty().isEmpty().or(txtFirstName.textProperty().isEmpty())
				.or(txtLastName.textProperty().isEmpty()).or(txtMobileNum.textProperty().isEmpty())
				.or(txtEmail.textProperty().isEmpty()).or(txtUsername.textProperty().isEmpty())
				.or(txtPassword.textProperty().isEmpty()).or(txtConPassword.textProperty().isEmpty());
		btnCreateAccount.disableProperty().bind(booleanBind);
	}

	/**
	 * Clear password fields when an error occur
	 */
	private void clearPassFields() {
		txtConPassword.clear();
		txtPassword.clear();
	}

	/**
	 * Validate the inputed email address
	 * 
	 * @return Boolean value
	 */
	private boolean validateEmail() {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(txtEmail.getText());
		if (!m.matches())
			return false;
		return true;
	}

	/**
	 * Show an appropriate alert to the user when an error occur
	 * 
	 * @param msg
	 */
	private void alertWarningMessage(String msg) {
		new Alert(AlertType.WARNING, msg, ButtonType.OK).show();
	}
}
