package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Alaa Grable
 * @version 1.0 [17.1.2019]
 * 
 */

public class ManualExtendController {

	UserAccount acc;
	Book tmpBook;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

	@FXML
	private TableView<LentBook> tableView;

	@FXML
	private TableColumn<LentBook, String> bookNameCol;

	@FXML
	private TableColumn<LentBook, String> bookEditionCol;

	@FXML
	private TableColumn<LentBook, String> bookAuthorCol;

	@FXML
	private TableColumn<LentBook, String> bookTypeCol;

	@FXML
	private TableColumn<LentBook, LocalDate> issuedDateCol;

	@FXML
	private TableColumn<LentBook, LocalDate> dueDateCol;

	@FXML
	private TableColumn<LentBook, String> bookDetails;

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

	/**
	 * When ExtendLend button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnExtendLendPressed(ActionEvent event) {
		// get the selected book from the tableView
		LentBook selectedBook = tableView.getSelectionModel().getSelectedItem();
		// validate if the book type is equal to "wanted" or not
		if (selectedBook.getBook().getBookType().equals("Wanted"))
			// if the book type is "Wanted" then let the user know that he can't extend the
			// book return time
			alertWarningMessage(
					"This book " + selectedBook.getBook().getName() + " is a 'Wanted' book and cannot be extended.");
		else {
			/*
			 * tmpBook = DatabaseController.getBook(selectedBook.getBookID());
			 */
			// validate if the orders on that book is lesser than the actual available
			// copies in the library
			if (tmpBook.getAvailableCopies() <= tmpBook.getBookOrders()) {
				// if not , then let the user know that he can't extend the book return time
				alertWarningMessage("There is a lot of orders on that book , \nTherefore the book " + tmpBook.getName()
						+ " cannot be extended.");
			} else {
				// extend the book return time to 1 more weeks
				selectedBook.setDueDate(selectedBook.getDueDate().plusWeeks(1));

				/*
				 * DatabaseController.updateLentBook(selectedBook);
				 */
				// let the user know that the return time for the his book has been extended
				// successfully
				Alert alert = new Alert(AlertType.INFORMATION,
						"The book" + tmpBook.getName() + " Due time has been extended successfully.", ButtonType.OK);
				alert.show();
			}

		}

	}

	/**
	 * When UserLookUp button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnUserLookupPressed(ActionEvent event) {

		// get the inputed ID
		String usrID = txtID.getText();
		/*
		 * acc = DatabaseController.getAccount(usrID);
		 */
		acc = new UserAccount(316544345, "ALAA", "Grable", "alaatg.7@gmail.com", "0522985313", 123, "Zerox", "asd123",
				accountStatus.Active, 0, false);
		// display the user details according to the inserted ID
		txtUserID.setText(String.valueOf(acc.getID()));
		txtUsername.setText(acc.getUserName());
		txtName.setText(acc.getFirstName() + " " + acc.getLastName());
		lblStatus.setText(String.valueOf(acc.getStatus()));
		// get the books of the user as an observableList to display it in the table
		ObservableList<LentBook> list = getLentBookList(usrID);
		// display the data in the tableView
		tableView.setItems(list);
	}

	@FXML
	/**
	 * Initialise the current screen
	 */
	void initialize() {

		// a listener to validate if the ID length is not greater than 9 digits and if
		// it's only contain numbers
		txtID.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				txtID.setText(newValue.replaceAll("[^\\d]", ""));
				alertWarningMessage("The ID must contain only numbers");
			}
			if (txtID.getLength() > 9) {
				txtID.setText(oldValue);
				alertWarningMessage("The ID must be 9 numbers");
			}
		});

		// Defines how to fill data for each cell
		bookNameCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookName"));
		bookEditionCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookEdition"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookAuthor"));
		bookTypeCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookType"));
		issuedDateCol.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("IssueDate"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("DueDate"));
		btnExtendLend.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));

		// Enable UserLookUp button only when the user ID textfield is not empty
		BooleanBinding booleanBind = txtID.textProperty().isEmpty();
		btnUserLookup.disableProperty().bind(booleanBind);
	}

	/*
	 * Create an ObservableList that contains the lent books for that user
	 */
	private ObservableList<LentBook> getLentBookList(String userID) {

		/*
		 * DatabaseController.getExtendBookList(userID) ** & send it
		 */
		/*
		LentBook LntBK1 = new LentBook(123, 111, LocalDate.now(), LocalDate.now().plusWeeks(2), false, "Marshood",
				"2st", "ALAA", "Calculus", "Wanted");
		LentBook LntBK2 = new LentBook(777, 999, LocalDate.now(), LocalDate.now().plusWeeks(2), false, "Fucker", "7st",
				"ahmad", "notur", "Regular");
		// create an observablelist that contains the user let books
		ObservableList<LentBook> list = FXCollections.observableArrayList(LntBK1, LntBK2);
		// return the observablelist
		
		return list;
		*/
		return null;
	}

	void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ManualExtendForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Manual Extend Lend book");
		stage.sizeToScene();
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Show an appropriate alert to the user when an error occur
	 * 
	 * @param msg
	 */
	private void alertWarningMessage(String msg) {
		new Alert(AlertType.WARNING, msg, ButtonType.OK).show();
	}

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Main");
	}
}
