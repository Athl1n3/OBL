package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import javafx.beans.binding.Bindings;
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

public class ExtendLendController {

	LentBook lntBook;
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
	private TableColumn<LentBook, String> bookTopicCol;

	@FXML
	private TableColumn<LentBook, LocalDate> bookIssuedDate;

	@FXML
	private TableColumn<LentBook, LocalDate> bookDueDate;

	@FXML
	private TableColumn<LentBook, String> bookDetails;

	@FXML
	private Label txtUsername;

	@FXML
	private Label txtUserID;

	@FXML
	private Button btnExtendBook;

	private static UserAccount loggedAccount;

	/**
	 * When ExtendBook button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnExtendBookPressed(ActionEvent event) {
		// get the selected book from the tableView
		LentBook selectedBook = tableView.getSelectionModel().getSelectedItem();

		// validate if the book type is equal to "wanted" or not
		if (selectedBook.getBook().getBookType().equals("Wanted")) {
			// if the book type is "Wanted" then let the user know that he can't extend the
			// book return time
			alertWarningMessage(
					"This book " + selectedBook.getBook().getName() + " is a 'Wanted' book and cannot be extended.");
		} else {
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
	 * Initialise the current screen
	 */
	@FXML
	void initialize() {

		// Defines how to fill data for each cell
		bookNameCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookName"));
		bookTopicCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookTopic"));
		bookEditionCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookEdition"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<LentBook, String>("bookAuthor"));
		bookIssuedDate.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("IssueDate"));
		bookDueDate.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("DueDate"));

		// get the books of the user as an observableList to display it in the table
		ObservableList<LentBook> list = getLentBookList();
		// display the data in the tableView
		tableView.setItems(list);
		// Enable the ExtendBook button only when an item is selected from the tableView
		btnExtendBook.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
	}

	/*
	 * Create an ObservableList that contains the lent books for that user
	 */
	private ObservableList<LentBook> getLentBookList() {

		/*
		 * DatabaseController.getExtendBookList(account.getID()) ** & send it
		 */
/*
		LentBook LntBK1 = new LentBook(123, 111, LocalDate.now(), LocalDate.now().plusWeeks(2), false, "Marshood",
				"2st", "ALAA", "Calculus", "Math");
		LentBook LntBK2 = new LentBook(777, 999, LocalDate.now(), LocalDate.now().plusWeeks(2), false, "Fucker", "7st",
				"ahmad", "notur", "prog");

		// create an observablelist that contains the user let books
		ObservableList<LentBook> list = FXCollections.observableArrayList(LntBK1, LntBK2);

		// return the observablelist
		return list;
*/
		return null;
	}

	/**
	 * Back to the previous screen
	 */
	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		// get the previous scene
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Main");
	}

	void start(Stage stage, Account loggedAccount) throws Exception {
		this.loggedAccount = (UserAccount) loggedAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ExtendLendForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Extend Lend book");
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
}
