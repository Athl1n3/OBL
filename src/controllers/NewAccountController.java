package controllers;

import java.net.URL;
import java.util.ResourceBundle;

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

    }

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCreateAccount != null : "fx:id=\"btnCreateAccount\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert imgBack != null : "fx:id=\"imgBack\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtMobileNum != null : "fx:id=\"txtMobileNum\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert lblUserID != null : "fx:id=\"lblUserID\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtConPassword != null : "fx:id=\"txtConPassword\" was not injected: check your FXML file 'NewAccountForm.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'NewAccountForm.fxml'.";

    }
}
