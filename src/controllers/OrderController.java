package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtBookType;

    @FXML
    private TextField txtBookID;

    @FXML
    private DatePicker dtOrderDate;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {

    }

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert imgBack != null : "fx:id=\"imgBack\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert txtBookType != null : "fx:id=\"txtBookType\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert txtBookID != null : "fx:id=\"txtBookID\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert dtOrderDate != null : "fx:id=\"dtOrderDate\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert txtUserID != null : "fx:id=\"txtUserID\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'OrderForm.fxml'.";
        assert btnPlaceOrder != null : "fx:id=\"btnPlaceOrder\" was not injected: check your FXML file 'OrderForm.fxml'.";

    }
}

