package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancel;

    @FXML
    void btnCancelPressed(ActionEvent event) {

    }

    @FXML
    void btnLoginPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert txtPassword != null : "fx:id=\"txtPassword\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'LoginForm.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'LoginForm.fxml'.";

    }
}

