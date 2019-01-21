package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.Account.UserType;
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
		try {
			account = DatabaseController.getAccount(txtUsername.getText(), txtPassword.getText());
			Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
			try {
				if (account.getUserType() == UserType.User)
					openNewForm("User", stage);
				else
					openNewForm("Librarian", stage);
			} catch (Exception exc) {

			}

			System.out.println(account.getFirstName());
		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING, "Incorrect Username or Password!!!\n\n Please Try Agian.");
			alert.setHeaderText("Login Failed");
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
