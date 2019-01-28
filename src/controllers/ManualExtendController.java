package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.Book;
import entities.Book.bookType;
import entities.UserAccount.accountStatus;
import entities.LentBook;
import entities.UserAccount;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author Alaa Grable
 * @version 1.0 [17.1.2019]
 * 
 */

public class ManualExtendController {

	UserAccount acc;

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
		if (selectedBook.getBook().getBookType().equals(bookType.Wanted)) {
			// if the book type is "Wanted" then let the user know that he can't extend the
			// book return time
			alertWarningMessage(
					"The book " + selectedBook.getBook().getName() + " is a 'Wanted'\n book and cannot be extended.");
		} else {
			// validate if there is a week or less to return that book
			if (LocalDate.now().isAfter(selectedBook.getIssueDate().plusWeeks(1)) == false
					&& LocalDate.now().isEqual(selectedBook.getIssueDate().plusWeeks(1)) == false) {
				// if not then let the user know that he can't extend the book return time
				alertWarningMessage(
						"You have more than 1 week left to return this book, therefore you can extend this book returning time.");
			} else {

				// validate if the orders on that book is lesser than the actual available
				// copies in the library
				if (selectedBook.getBook().getAvailableCopies() <= selectedBook.getBook().getBookOrders()) {
					// if not , then let the user know that he can't extend the book return time
					alertWarningMessage("There is a lot of orders on that book , \nTherefore the book "
							+ "'"+selectedBook.getBook().getName()+"'" + " cannot be extended.");
				} else {
					// extend the book return time to 1 more weeks
					selectedBook.setDueDate(selectedBook.getDueDate().plusWeeks(1));

					//DatabaseController.updateLentBook(selectedBook);

					// let the user know that the return time for the his book has been extended
					// successfully
					Alert alert = new Alert(AlertType.INFORMATION,
							"The book" + selectedBook.getBook().getName() + " Due time has been extended successfully.",
							ButtonType.OK);
					alert.show();
				}
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
		
		 acc = (UserAccount) DatabaseController.getAccount(Integer.parseInt(usrID));
		// display the user details according to the inserted ID
		txtUserID.setText(String.valueOf(acc.getID()));
		txtUsername.setText(acc.getUserName());
		txtName.setText(acc.getFirstName() + " " + acc.getLastName());
		lblStatus.setText(String.valueOf(acc.getStatus()));
		// get the books of the user as an observableList to display it in the table
		ObservableList<LentBook> list = getLentBookList(usrID);
		// display the data in the tableView
		tableView.setItems(list);
		if(acc.getStatus().equals(accountStatus.Active)) {
			btnExtendLend.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
			btnExtendLend.setText("Extend Book Lend");
		}
		else {
			btnExtendLend.setText(acc.getStatus().toString() + " Account");
			btnExtendLend.setDisable(true);
		}
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
        bookNameCol.setCellValueFactory(new Callback<CellDataFeatures<LentBook, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<LentBook, String> c) {
                return new SimpleStringProperty(c.getValue().getBook().getName());                
            }
    });
        bookEditionCol.setCellValueFactory(new Callback<CellDataFeatures<LentBook, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<LentBook, String> c) {
                return new SimpleStringProperty(c.getValue().getBook().getEdition());                
            }
    });
        bookAuthorCol.setCellValueFactory(new Callback<CellDataFeatures<LentBook, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<LentBook, String> c) {
                return new SimpleStringProperty(c.getValue().getBook().getAuthor());                
            }
    });
        bookTypeCol.setCellValueFactory(new Callback<CellDataFeatures<LentBook, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<LentBook, String> c) {
                return new SimpleStringProperty(c.getValue().getBook().getBookType().toString());      
            }
    });
		issuedDateCol.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("IssueDate"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<LentBook, LocalDate>("DueDate"));
		btnExtendLend.setDisable(true);

		// Enable UserLookUp button only when the user ID textfield is not empty
		BooleanBinding booleanBind = txtID.textProperty().isEmpty();
		btnUserLookup.disableProperty().bind(booleanBind);

	}

	/**
	 * Create an ObservableList that contains the lent books for that user
	 * @param userID
	 * @return ObservableList<LentBook>
	 */
	private ObservableList<LentBook> getLentBookList(String userID) {

		// create an observablelist that contains the user let books
		
		ObservableList<LentBook> list = FXCollections.observableArrayList(DatabaseController.getLentBookList(acc.getAccountID()));
		// return the observablelist
		return list;
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