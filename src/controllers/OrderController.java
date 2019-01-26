package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Book;
import entities.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrderController implements Initializable {

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

	private static Book orderedBook;

	@FXML
	void btnPlaceOrderPressed(ActionEvent event) {
		
	}

	@FXML
	void imgBackClicked(MouseEvent event) {

	}

	public void start(Book orderedBook) {
		OrderController.orderedBook = orderedBook;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../gui/OrderForm.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Book Order Form");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtBookID.setDisable(true);
		txtBookName.setDisable(true);
		txtBookType.setDisable(true);
		txtBookName.setDisable(true);
		dtOrderDate.setDisable(true);
		txtUserID.setDisable(true);
		txtName.setDisable(true);
		txtBookID.setText(String.valueOf(orderedBook.getBookID()));
		txtBookName.setText(orderedBook.getName());
		txtBookType.setText(orderedBook.getBookType().toString());
		txtBookName.setText(orderedBook.getName());
		dtOrderDate.setValue(LocalDate.now());
		txtUserID.setText(String.valueOf(DatabaseController.loggedAccount.getID()));
		txtName.setText(DatabaseController.loggedAccount.getFullName());
	}
}
