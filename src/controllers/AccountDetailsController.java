package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AccountDetailsController implements Initializable {

	@FXML
	private TextField txtUserID;

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
	private Label lblStatus;

	@FXML
	private Button btnApplyChanges;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtVerPassword;

	@FXML
	private TextField txtPassword;

	@FXML
	private Button btnUpdateLogin;

	private static UserAccount loggedAccount;

	@FXML
	void btnApplyChangesPressed(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION, "Are you sure to update user details?", ButtonType.YES,
				ButtonType.CANCEL);
		if (msg.showAndWait().get() == ButtonType.YES)
			if (validateInput()) {

				loggedAccount.setFirstName(txtFirstName.getText());
				loggedAccount.setLastName(txtLastName.getText());
				loggedAccount.setMobileNum(txtMobileNum.getText());
				loggedAccount.setEmail(txtEmail.getText());
				// DatabaseController.LoggedUser(loggedAccount);
				new Alert(AlertType.INFORMATION, "User details was changes successfully!", ButtonType.OK).show();
			}

	}

	@FXML
	void btnUpdateLoginPressed(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION, "Are you sure to update Login details?", ButtonType.YES,
				ButtonType.CANCEL);
		if (msg.showAndWait().get() == ButtonType.YES)
			if (validatepasswordinput()) {
				loggedAccount.setPassword(txtPassword.getText());
				// DatabaseController.LoggedUser(loggedAccount);
				new Alert(AlertType.INFORMATION, "password changed successfully!", ButtonType.OK).show();
			}
	}

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Main");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblUserID.setText(String.valueOf(loggedAccount.getAccountID()));
		txtUserID.setText(String.valueOf(loggedAccount.getID()));
		txtUserID.setDisable(true);
		txtFirstName.setText(loggedAccount.getFirstName());
		txtLastName.setText(loggedAccount.getLastName());
		txtMobileNum.setText(loggedAccount.getMobileNum());
		txtEmail.setText(loggedAccount.getEmail());

		switch (loggedAccount.getStatus()) {
		case Active:
			lblStatus.setTextFill(javafx.scene.paint.Color.GREEN);
			break;
		case Suspended:
			lblStatus.setTextFill(javafx.scene.paint.Color.rgb(153, 153, 0));
			break;
		case Locked:
			lblStatus.setTextFill(javafx.scene.paint.Color.RED);
			break;
		}
		lblStatus.setText(loggedAccount.getStatus().toString());

		// login details
		txtUsername.setText(loggedAccount.getUserName());
	}

	/**
	 * Validate updated user data
	 * 
	 * @return true in case of a valid input
	 */
	@FXML
	public boolean validateInput() {

		Alert msg = new Alert(AlertType.ERROR, "", ButtonType.OK);// Prepare alert box
		msg.setHeaderText("Input Error");

		for (char c : txtFirstName.getText().toCharArray())// Parse text field into chars array and validate
			if (Character.isDigit(c)) {
				msg.setContentText("First name must contain letters only!");
				msg.show();
				txtFirstName.requestFocus();
				return false;
			}
		for (char c : txtLastName.getText().toCharArray())// Parse text field into chars array and validate
			if (Character.isDigit(c)) {
				msg.setContentText("Last name must contain letters only!");
				msg.show();
				txtLastName.requestFocus();
				return false;
			}
		for (char c : txtMobileNum.getText().toCharArray())// Parse text field into chars array and validate
			if (Character.isAlphabetic(c)) {
				msg.setContentText("Mobile number must contain numbers only!");
				msg.show();
				txtMobileNum.requestFocus();
				return false;
			}
		// Validate email format using
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(txtEmail.getText());
		if (!m.matches()) {
			msg.setContentText("Invalid email format!");
			msg.show();
			txtEmail.requestFocus();
			return false;
		}

		if ((txtUserID.getText() == null) || (txtFirstName.getText() == null) || (txtLastName.getText() == null)
				|| (txtMobileNum.getText() == null) || (txtEmail.getText() == null)) {
			msg.setContentText("Please fill all requested fields!");
			msg.show();
			txtEmail.requestFocus();
			return false;
		}

		return true;// If all inputs are valid

	}

	boolean validatepasswordinput() {
		Alert msg = new Alert(AlertType.ERROR, "", ButtonType.OK);// Prepare alert box
		msg.setHeaderText("Input Error");
		if (txtPassword.getLength() < 8) {
			msg.setContentText("Password length must be at least 8 character !");
			msg.show();
			txtUserID.requestFocus();
			return false;
		}

		if (txtPassword.getText() != txtVerPassword.getText()) {
			msg.setContentText("Password are not matching !");
			msg.show();
			txtUserID.requestFocus();
			return false;
		}

		// success confirm password
		return true;
	}

	void start(Stage primaryStage, Account acc) throws Exception {
		loggedAccount = (UserAccount) acc;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/AccountDetailsForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Userlookup");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
