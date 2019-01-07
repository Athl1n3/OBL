package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;

public class ManualExtendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TreeTableView<?> treeTableView;

    @FXML
    private TextField txtUserID;

    @FXML
    private Button btnUserLookup;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnExtendLend;

    @FXML
    void btnExtendLendPressed(ActionEvent event) {

    }

    @FXML
    void btnUserLookupPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert treeTableView != null : "fx:id=\"treeTableView\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert txtUserID != null : "fx:id=\"txtUserID\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert btnUserLookup != null : "fx:id=\"btnUserLookup\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert txtUsername != null : "fx:id=\"txtUsername\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";
        assert btnExtendLend != null : "fx:id=\"btnExtendLend\" was not injected: check your FXML file 'ManualExtendForm.fxml'.";

    }
}
