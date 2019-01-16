package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.Account.userType;
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
 * @version 1.2 [12.1.2019]
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
			Scene scene = (Scene) ((Node) event.getSource()).getScene();
			SceneController.push(scene);
			// stage.initModality(Modality.APPLICATION_MODAL);
			ArchivedDataController archiveForm = new ArchivedDataController();
			try {
				archiveForm.start(stage, lookupAccount.getAccountID());
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
		lblStatus.setText("---");
		lblUserID.setText("---");
		txtID.setDisable(false);
	}

	@FXML
	void btnEditDataPressed(ActionEvent event) {
		Alert msg = new Alert(AlertType.CONFIRMATION, "Are you sure to update user data?", ButtonType.YES,
				ButtonType.CANCEL);
		if (msg.showAndWait().get() == ButtonType.YES)
			if (validateInput()) {
				lookupAccount.setUserName(txtUsername.getText());
				lookupAccount.setFirstName(txtFirstName.getText());
				lookupAccount.setLastName(txtLastName.getText());
				lookupAccount.setMobileNum(txtMobileNum.getText());
				lookupAccount.setEmail(txtEmail.getText());
				LoadUserData();
				// DatabaseController.UpdateAccount(lookupAccount);
				new Alert(AlertType.INFORMATION, "User data was updated successfully!", ButtonType.OK).show();
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
		return true;// If all inputs are valid
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
				if (lookupAccount != null) {
					LoadUserData();
					txtID.setDisable(true);
				} else
					new Alert(AlertType.WARNING, "User doesn't exist!", ButtonType.OK).show();
			} catch (NumberFormatException exc) {
				exc.printStackTrace();
				new Alert(AlertType.WARNING, "ID must contain numbers only", ButtonType.OK).show();
			}
		}
	}

	@FXML
	void btnViewHistoryPressed(ActionEvent event) {
		if (txtID.isDisabled()) {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = (Scene) ((Node) event.getSource()).getScene();
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

	}

	/**
	 * Initialize GUI layout and checkbox event handler and initialize gui depending
	 * on user type
	 */
	@FXML
	void initialize() {
		if (librarianAccount.getUserType() == userType.Librarian) {
			btnLock.setVisible(false);
			btnSuspend.setVisible(false);
			btnArchive.setVisible(false);
		}
		lblStatus.setText("---");

		btnEditData.setDisable(true);
		cbEditUser.setOnAction(new EventHandler<ActionEvent>() {// Edit user checkbox event handler
			@Override
			public void handle(ActionEvent event) {
				btnEditData.setDisable(cbEditUser.isSelected() ? false : true);
				txtFirstName.setEditable(cbEditUser.isSelected() ? true : false);
				txtLastName.setEditable(cbEditUser.isSelected() ? true : false);
				txtMobileNum.setEditable(cbEditUser.isSelected() ? true : false);
				txtEmail.setEditable(cbEditUser.isSelected() ? true : false);
				if (cbEditUser.isSelected() == false && lookupAccount != null)// Revert looked up user data
					LoadUserData();
			}
		});
	}

	void start(Stage primaryStage, Account acc, Account librarian) throws Exception {
		// lookupAccount = (UserAccount) acc;// FOR TEST ONLY
		librarianAccount = (LibrarianAccount) librarian;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/UserLookupForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Userlookup");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Load user account data into GUI corresponding text fields
	 */
	void LoadUserData() {
		txtUsername.setText(lookupAccount.getUserName());
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