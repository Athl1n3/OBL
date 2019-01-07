package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UserLookupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLock;

    @FXML
    private Button btnSuspend;

    @FXML
    private Label lblStatus;

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
    private ImageView imgBack;

    @FXML
    private Button btnEditData;

    @FXML
    void btnArchivePressed(ActionEvent event) {

    }

    @FXML
    void btnClearPressed(ActionEvent event) {

    }

    @FXML
    void btnEditDataPressed(ActionEvent event) {

    }

    @FXML
    void btnLockPressed(ActionEvent event) {

    }

    @FXML
    void btnSuspendPressed(ActionEvent event) {

    }

    @FXML
    void btnView(ActionEvent event) {

    }

    @FXML
    void btnViewHistoryPressed(ActionEvent event) {

    }

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert btnLock != null : "fx:id=\"btnLock\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnSuspend != null : "fx:id=\"btnSuspend\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert cbEditUser != null : "fx:id=\"cbEditUser\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnView != null : "fx:id=\"btnView\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnViewHistory != null : "fx:id=\"btnViewHistory\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnArchive != null : "fx:id=\"btnArchive\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert txtUserID != null : "fx:id=\"txtUserID\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert txtFirstName != null : "fx:id=\"txtFirstName\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert txtLastName != null : "fx:id=\"txtLastName\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert txtMobileNum != null : "fx:id=\"txtMobileNum\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert txtEmail != null : "fx:id=\"txtEmail\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert imgBack != null : "fx:id=\"imgBack\" was not injected: check your FXML file 'UserLookupForm.fxml'.";
        assert btnEditData != null : "fx:id=\"btnEditData\" was not injected: check your FXML file 'UserLookupForm.fxml'.";

    }
}

