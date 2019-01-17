package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.*;
import entities.Account.UserType;
import entities.UserAccount.accountStatus;
import client.ClientConnection;
import entities.Account;
import entities.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private TextField txtUsername;
	@FXML
	private Label lblUserID;

	@FXML
	private TextField txtConPassword;

	@FXML
	private TextField txtPassword;


	public ClientConnection cc;

    @FXML
    void btnClearPressed(ActionEvent event) {
    }

    @FXML
    void btnCreateAccountPressed(ActionEvent event) {
    	UserAccount newAccount = new UserAccount();
    	newAccount.setID(Integer.parseInt(txtID.getText()));
    	newAccount.setFirstName(txtFirstName.getText());
    	newAccount.setLastName(txtLastName.getText());
    	newAccount.setMobileNum(txtMobileNum.getText());
    	newAccount.setEmail(txtEmail.getText());
    	newAccount.setAccountID(1);
    	newAccount.setUserName(txtUsername.getText());
    	newAccount.setPassword(txtPassword.getText());
    	newAccount.userType = UserType.User;
    	newAccount.status = accountStatus.Active;
    	DatabaseController.addAccount(newAccount);
    }

	@FXML
	void imgBackClicked(MouseEvent event) {
	}

    @FXML
    void initialize() {
       
    }
    public void start(Stage stage) {
  		try {
  			Parent root = FXMLLoader.load(getClass().getResource("../gui/NewAccountForm.fxml"));
  			Scene scene = new Scene(root);
  			stage.setScene(scene);
  			stage.setTitle("Student Frame");
  			stage.show();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  	}

}
