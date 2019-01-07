package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ArchivedDataController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField txtUserID;

    @FXML
    private TextField txtUsername;

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert imgBack != null : "fx:id=\"imgBack\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtMobileNum != null : "fx:id=\"txtMobileNum\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtUserID != null : "fx:id=\"txtUserID\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'ArchivedDataForm.fxml'.";

    }
}

