package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.LentBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReturnController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

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

	private LentBook returnedBook;
	private Account userAccount;

	@FXML
	void btnBookLookup(ActionEvent event) {
		returnedBook = DatabaseController.getLentBook(Integer.parseInt(txtUserID.getText()),
				Integer.parseInt(txtBookID.getText()));
		if (returnedBook != null) {
			userAccount = DatabaseController.getAccountByAccountID(returnedBook.getUserID());
			txtBookName.setText(returnedBook.getBook().getName());
			txtName.setText(userAccount.getFullName());
			dtIssueDate.setValue(returnedBook.getIssueDate());
			dtDueDate.setValue(returnedBook.getDueDate());
		} else {
			showAlert("Warning!!!", "there is No lents");
		}
	}

	@FXML
	void btnClearPressed(ActionEvent event) {
		clear();
	}

	@FXML
	void btnReturnBookPressed(ActionEvent event) {
		if (returnedBook != null) {
			DatabaseController.updateBookAvailableCopies(returnedBook.getBook(), 1);
			if (dtReturnDate.getValue().isBefore(returnedBook.getDueDate())
					|| dtReturnDate.getValue().isEqual(returnedBook.getDueDate())) {
				showAlert("Book returned successfully!!!", null);
			} else {
				showAlert("Warning!!!", "The book returned in late");
				// need to check the late times number and creating a new field in account table
				// called lateCount
			}
			DatabaseController.deleteLendBook(returnedBook.getUserID(), returnedBook.getBook().getBookID());
		}
		else{
			showAlert("Warning, Empty Input!!!", "Please Insert User ID and Book ID First");
			//also we need to check if he already pressed bookLokeUp btn
		}
	}

	private void clear() {
		txtBookID.setText(null);
		txtName.setText(null);
		txtBookName.setText(null);
		txtName.setText(null);
		dtIssueDate.setValue(null);
		dtDueDate.setValue(null);
		returnedBook = null;
	}

	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ReturnForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Return Book");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("User Main");
	}

	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION, content, ButtonType.OK);
		alert.setHeaderText(header);
		if (alert.showAndWait().get() == ButtonType.OK)
			clear();
	}

}
