package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ReturnController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBookID;

    @FXML
    private DatePicker dtIssueDate;

    @FXML
    private DatePicker dtReturnDate;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtUserID;

    @FXML
    private DatePicker dtDueDate;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnBookLookup;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnClear;

    @FXML
    void btnBookLookup(ActionEvent event) {

    }

    @FXML
    void btnClearPressed(ActionEvent event) {

    }

    @FXML
    void btnReturnBookPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtBookID != null : "fx:id=\"txtBookID\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert dtIssueDate != null : "fx:id=\"dtIssueDate\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert dtReturnDate != null : "fx:id=\"dtReturnDate\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert txtUserID != null : "fx:id=\"txtUserID\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert dtDueDate != null : "fx:id=\"dtDueDate\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert btnBookLookup != null : "fx:id=\"btnBookLookup\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert btnReturnBook != null : "fx:id=\"btnReturnBook\" was not injected: check your FXML file 'ReturnForm.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'ReturnForm.fxml'.";

    }
}

