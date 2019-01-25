package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.Account.UserType;
import entities.LibrarianAccount;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Adam Mahameed
 * @version 1.4 [16.1.2019]
 * 
 */
public class UserLookupController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnLock;

	@FXML
	private Label lblUserID;

	@FXML
	private Button btnSuspend;

	@FXML
	private Label lblStatus;

	@FXML
	private TextField txtUsername;

	@FXML
	private TextField txtPassword;

	@FXML
	private CheckBox cbEditUser;

	@FXML
	private Button btnView;

	@FXML
	private Button btnClear;

	@FXML
	private Button btnViewHistory;

	@FXML
	private Button btnArchive;

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
	private ImageView imgBack;

	@FXML
	private Button btnEditData;

	private static UserAccount lookupAccount;
	private static LibrarianAccount librarianAccount;

	@FXML
	void btnArchivePressed(ActionEvent event) {
		if (txtID.isDisabled()) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			ArchivedDataController archiveForm = new ArchivedDataController();
			try {
				archiveForm.start(stage, lookupAccount.getID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			new Alert(AlertType.WARNING, "A user must be looked up first!", ButtonType.OK).show();
	}

	/**
	 * Clear layout to its original state
	 * 
	 * @param event
	 */
	@FXML
	void btnClearPressed(ActionEvent event) {
		txtUsername.clear();
		txtID.clear();
		txtFirstName.clear();
		txtLastName.clear();
		txtMobileNum.clear();
		txtEmail.clear();
		txtPassword.clear();
		lblStatus.setText("---");
		lblUserID.setText("---");
		txtID.setDisable(false);
		txtFirstName.setStyle(null);
		txtLastName.setStyle(null);
		txtMobileNum.setStyle(null);
		txtEmail.setStyle(null);
		txtPassword.setStyle(null);
		cbEditUser.setSelected(false);
	}

	@FXML
	void btnEditDataPressed(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION, "Are you sure to update user data?", ButtonType.YES,
				ButtonType.CANCEL);
		if (msg.showAndWait().get() == ButtonType.YES) {
			txtFirstName.setStyle(null);
			txtLastName.setStyle(null);
			txtMobileNum.setStyle(null);
			txtEmail.setStyle(null);
			if (validateInput()) {
				lookupAccount.setUserName(txtUsername.getText());
				lookupAccount.setPassword(txtPassword.getText());
				lookupAccount.setFirstName(txtFirstName.getText());
				lookupAccount.setLastName(txtLastName.getText());
				lookupAccount.setMobileNum(txtMobileNum.getText());
				lookupAccount.setEmail(txtEmail.getText());
				LoadUserData();
				cbEditUser.setSelected(false);
				DatabaseController.updateAccount(lookupAccount);
				new Alert(AlertType.INFORMATION, "User data was updated successfully!", ButtonType.OK).show();
			}
		}
	}

	/**
	 * Validate updated user data
	 * 
	 * @return true in case of a valid input
	 */
	private boolean validateInput() {
		Alert msg = new Alert(AlertType.ERROR, "", ButtonType.OK);// Prepare alert box
		msg.setHeaderText("Input Error");
		msg.setContentText("One or more of inputs are in an invalid format!");
		boolean validInput = true;
		////////////
		if (!txtFirstName.getText().isEmpty()) {
			for (char c : txtFirstName.getText().toCharArray())// Parse text field into chars array and validate
				if (Character.isDigit(c)) {
					msg.setContentText(msg.getContentText() + "\n*First name must contain letters only!");
					;
					txtFirstName.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
					validInput = false;
					break;
				}
		} else {
			msg.setContentText(msg.getContentText() + "\n*First name can't be empty!");
			txtFirstName.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		////////////
		if (!txtLastName.getText().isEmpty()) {
			for (char c : txtLastName.getText().toCharArray())// Parse text field into chars array and validate
				if (Character.isDigit(c)) {
					msg.setContentText(msg.getContentText() + "\n*Last name must contain letters only!");
					txtLastName.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
					validInput = false;
					break;
				}
		} else {
			msg.setContentText(msg.getContentText() + "\n*Last name can't be empty!");
			txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		//////////
		if (!txtMobileNum.getText().isEmpty()) {
			for (char c : txtMobileNum.getText().toCharArray())// Parse text field into chars array and validate
				if (Character.isAlphabetic(c)) {
					msg.setContentText(msg.getContentText() + "\n*Mobile number must contain numbers only!");
					txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
					validInput = false;
					break;
				}
		} else {
			msg.setContentText(msg.getContentText() + "\n*Mobile number can't be empty!");
			txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		////////////
		if (txtUsername.getText().isEmpty()) {
			msg.setContentText(msg.getContentText() + "\n*Username can't be empty!");
			txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		////////////
		if (txtPassword.getText().length() < 6) {
			msg.setContentText(msg.getContentText() + "\n*Password must be 6 characters minimum!");
			txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		////////////
		if (!txtMobileNum.getText().isEmpty()) {
			// Validate email format using
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(txtEmail.getText());
			if (!m.matches()) {
				msg.setContentText(msg.getContentText() + "\n*Invalid email format!");
				txtEmail.requestFocus();
				txtEmail.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				validInput = false;
			}
		} else {
			msg.setContentText(msg.getContentText() + "\n*Email can't be empty!");
			txtMobileNum.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
			validInput = false;
		}
		////////////
		if (!validInput)
			msg.show();
		return validInput;// If all inputs are valid
	}

	@FXML
	void btnLockPressed(ActionEvent event) {
		boolean status;
		Alert statMsg = new Alert(AlertType.INFORMATION, "", ButtonType.OK);

		if (txtID.isDisabled()) {
			if (lookupAccount.getStatus() == accountStatus.Locked) {
				lookupAccount.setStatus(accountStatus.Active);
				status = true;
			} else {
				lookupAccount.setStatus(accountStatus.Locked);
				status = false;
			}
			statMsg.setContentText(status ? "User account was successfully set to 'Active'"
					: "User account was successfully set to 'Locked'");
			DatabaseController.updateUserStatus(lookupAccount);
			LoadUserData();
		} else {
			statMsg.setAlertType(AlertType.WARNING);
			statMsg.setContentText("A user must be looked up first!");
			txtID.requestFocus();
		}
		statMsg.show();
	}

	@FXML
	void btnSuspendPressed(ActionEvent event) {
		boolean status;
		Alert statMsg = new Alert(AlertType.INFORMATION, "", ButtonType.OK);

		if (txtID.isDisabled()) {
			if (lookupAccount.getStatus() == accountStatus.Suspended) {
				lookupAccount.setStatus(accountStatus.Active);
				status = true;
			} else {
				lookupAccount.setStatus(accountStatus.Suspended);
				status = false;
			}

			statMsg.setContentText(status ? "User account was successfully set to 'Active'"
					: "User account was successfully set to 'Suspended'");
			DatabaseController.updateUserStatus(lookupAccount);
			LoadUserData();
		} else {
			statMsg.setAlertType(AlertType.WARNING);
			statMsg.setContentText("A user must be looked up first!");
			txtID.requestFocus();
		}
		statMsg.show();
	}

	@FXML
	void btnView(ActionEvent event) {

		if (txtID.getText().isEmpty()) {
			Alert msg = new Alert(AlertType.WARNING, "User ID must be inserted", ButtonType.OK);
			msg.show();
		} else {
			try {
				Integer.parseInt((txtID.getText()));
				lookupAccount = (UserAccount) DatabaseController.getAccount(Integer.parseInt(txtID.getText()));
				if (!(lookupAccount.getStatus() == null)) {
					if (lookupAccount != null) {
						LoadUserData();
						txtID.setDisable(true);
					} else
						new Alert(AlertType.WARNING, "User doesn't exist!", ButtonType.OK).show();
				} else
					new Alert(AlertType.WARNING, "Unable to lookup for a librarian/manager account!", ButtonType.OK)
							.show();
			} catch (NumberFormatException exc) {
				new Alert(AlertType.WARNING, "ID must contain numbers only", ButtonType.OK).show();
			}
		}
	}

	@FXML
	void btnViewHistoryPressed(ActionEvent event) {
		if (txtID.isDisabled()) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = ((Node) event.getSource()).getScene();
			SceneController.push(scene);
			ActivityController activityForm = new ActivityController();
			try {
				activityForm.start(stage, lookupAccount);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			new Alert(AlertType.WARNING, "A user must be looked up first!", ButtonType.OK).show();
	}

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Users Management");
	}

	/**
	 * Initialize GUI layout and checkbox event handler and initialize gui depending
	 * on user type
	 */
	@FXML
	void initialize() {
		if (librarianAccount.getUserType() == UserType.Librarian) {
			btnLock.setVisible(false);
			btnSuspend.setVisible(false);
			btnArchive.setVisible(false);
		}
		lblStatus.setText("---");

		btnEditData.setDisable(true);
		cbEditUser.setOnAction(new EventHandler<ActionEvent>() {// Edit user checkbox event handler
			@Override
			public void handle(ActionEvent event) {
				if (txtID.isDisabled()) {
					btnEditData.setDisable(cbEditUser.isSelected() ? false : true);
					txtFirstName.setEditable(cbEditUser.isSelected() ? true : false);
					txtLastName.setEditable(cbEditUser.isSelected() ? true : false);
					txtMobileNum.setEditable(cbEditUser.isSelected() ? true : false);
					txtEmail.setEditable(cbEditUser.isSelected() ? true : false);
					if (cbEditUser.isSelected() == false && lookupAccount != null)// Revert looked up user data
						LoadUserData();
				} else {
					new Alert(AlertType.WARNING, "A user must be looked up first!", ButtonType.OK).show();
					cbEditUser.setSelected(false);
				}
			}
		});
	}

	void start(Stage primaryStage, Account librarian) throws Exception {
		librarianAccount = (LibrarianAccount) librarian;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/UserLookupForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("User lookup");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Load user account data into GUI corresponding text fields
	 */
	void LoadUserData() {
		txtUsername.setText(lookupAccount.getUserName());
		txtPassword.setText(lookupAccount.getPassword());
		txtFirstName.setText(lookupAccount.getFirstName());
		txtLastName.setText(lookupAccount.getLastName());
		txtMobileNum.setText(String.valueOf(lookupAccount.getMobileNum()));
		txtEmail.setText(String.valueOf(lookupAccount.getEmail()));
		lblUserID.setText(String.valueOf(lookupAccount.getAccountID()));
		switch (lookupAccount.getStatus()) {
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
		lblStatus.setText(lookupAccount.getStatus().toString());
	}
}