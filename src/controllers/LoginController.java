package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientConnection;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable{

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
    

	public ClientConnection cc;

    @FXML
    void btnCancelPressed(ActionEvent event) {
    	((Node) event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    void btnLoginPressed(ActionEvent event) {
    	Account account;
    	try {
    		account = DatabaseController.getAccount(txtUsername.getText(), txtPassword.getText());
    		DatabaseController.initLoggedAccount(account);
    		if(account.getUserType() == UserType.User) 
    			openNewForm("../gui/UserMainForm.fxml", "User Main Form");
    		else
    			openNewForm("../gui/LibrarianMainForm.fxml", "Librarian Main Form");
    		((Node) event.getSource()).getScene().getWindow().hide();

    		System.out.println(account.getFirstName());
    	}
    	catch(NullPointerException e) {

    		Alert alert = new Alert(AlertType.WARNING, "Incorrect Username or Password!!!\n\n Please Try Agian.");
    		alert.setHeaderText("Login Faild");
    		alert.show();
    		lblUsername.setText("*"+ lblUsername.getText());
    		lblPassword.setText("*"+lblPassword.getText());
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
  			stage.setTitle("Login Form");
  			stage.show();
  		} catch (Exception e) {
  			e.printStackTrace();
  		}		
	}
	public void openNewForm(String resource, String title) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(resource));
			Stage stage = new Stage();
  			Scene scene = new Scene(root);
  			stage.setScene(scene);
  			stage.setTitle(title);
  			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

