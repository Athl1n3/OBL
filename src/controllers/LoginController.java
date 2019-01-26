package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.Account.UserType;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnLogin;

	@FXML
	private Label lblUsername;

	@FXML
	private Label lblPassword;

	private Account account;

	@FXML
	void btnCancelPressed(ActionEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Main Window");
	}

	@FXML
	void btnLoginPressed(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		try {
			account = DatabaseController.getAccount(txtUsername.getText(), txtPassword.getText());
			if (!account.isLogged()) {
				Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
				if (account.getUserType() == UserType.User) {
					if (!((UserAccount) account).getStatus().equals(accountStatus.Locked)) {
						account.setLogged(true);
						//DatabaseController.loggedAccount = account;
						DatabaseController.logAccount(account);
						openNewForm("User", stage);
						
					}
					else {
						alert.setContentText("Account is \"Locked\"! \n Contact library for appeal.");
						alert.setHeaderText("Locked");
						alert.show();
					}
				} else {
					account.setLogged(true);
					//DatabaseController.loggedAccount = account;
					DatabaseController.logAccount(account);
					openNewForm("Librarian", stage);
				}
			} else {
				alert.setContentText("Account is already connected!");
				alert.setHeaderText("Login Failure");
				alert.show();
			}
		} catch (NullPointerException e) {
			alert.setContentText("Incorrect Username or Password!!!\n\n Please try again.");
			alert.setHeaderText("Login Failure");
			alert.show();
			lblUsername.setText("*" + lblUsername.getText());
			lblPassword.setText("*" + lblPassword.getText());
			txtUsername.clear();
			txtPassword.clear();
			btnLogin.setDisable(true);
			txtPassword.setDisable(true);
		}
	}

	@FXML
	void checkPasswordTextField(KeyEvent event) {
		btnLogin.setDisable(false);
	}

	@FXML
	void checkUserNameTextField(KeyEvent event) {
		txtPassword.setDisable(false);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnLogin.setDisable(true);
		txtPassword.setDisable(true);
	}

	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../gui/LoginForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void openNewForm(String userType, Stage primaryStage) {
		try {
			if (userType.equals("User")) {
				UserMainController MainForm = new UserMainController();
				MainForm.start(primaryStage, account);
			} else {
				LibrarianMainController MainForm = new LibrarianMainController();
				MainForm.start(primaryStage, account);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
