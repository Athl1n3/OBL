package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ReturnController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtBookID;

	@FXML
	private TextField txtSerialNumber;

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
	private Button btnLookup;

	@FXML
	private Button btnReturnBook;

	@FXML
	private Button btnClear;

	private LentBook returnedBook;
	private UserAccount userAccount;

	@FXML
	void btnLookupPressed(ActionEvent event) {
		try {
			returnedBook = DatabaseController.getLentBook(Integer.parseInt(txtUserID.getText()),
					Integer.parseInt(txtBookID.getText()), txtSerialNumber.getText());

			userAccount = (UserAccount) DatabaseController.getAccount(returnedBook.getUserID());

			txtBookName.setText(returnedBook.getBook().getName());
			txtName.setText(userAccount.getFullName());
			dtIssueDate.setValue(returnedBook.getIssueDate());
			dtDueDate.setValue(returnedBook.getDueDate());
			dtReturnDate.setValue(LocalDate.now());

		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR, "Please Insert VAlid BookID and UserID.");
			alert.setHeaderText("Inpute Format Error!");
			alert.show();
		} catch (NullPointerException e) {
			showAlert("", "There is No lents");
		}
	}

	@FXML
	void btnClearPressed(ActionEvent event) {
		clear();
	}

	@FXML
	void btnReturnBookPressed(ActionEvent event) {
		try {
			// update Book AvailableCopies += 1
			DatabaseController.updateBookAvailableCopies(returnedBook.getBook(), 1);
			//update lent field to false in book copy table
			returnedBook.getBookCopy().setLent(false);
			DatabaseController.updateBookCopy(returnedBook.getBookCopy());
			
			returnedBook.setReturnDate(dtReturnDate.getValue());
			returnedBook.setReturned(true);
			// update lent Book in DB lentBook table
			DatabaseController.returnBook(returnedBook);
			//DatabaseController.addActivity(userAccount.getID(),
				//	"Returned Book [Book ID: " + returnedBook.getBook().getBookID() + "]");
			if(!DatabaseController.isLate(returnedBook)) {
				showAlert("","Book returned successfully");
			}
			else
				showAlert("","Book returned in late");
		} catch (NullPointerException e) {
			e.printStackTrace();
			showAlert("Warning, Empty Input!!!", "Please Insert User ID and Book ID First");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clear() {
		txtBookID.setText(null);
		txtUserID.setText(null);
		txtName.setText(null);
		txtBookName.setText(null);
		txtName.setText(null);
		dtIssueDate.setValue(null);
		dtDueDate.setValue(null);
		dtReturnDate.setValue(null);
		txtSerialNumber.setText(null);
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
		alert.show();
		clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// disable Book\User Lookup button until the user enters both the bookID and
		// UserID
		BooleanBinding booleanBind = txtBookID.textProperty().isEmpty().or(txtUserID.textProperty().isEmpty()).or(txtSerialNumber.textProperty().isEmpty());
		btnLookup.disableProperty().bind(booleanBind);
		
	}

}
