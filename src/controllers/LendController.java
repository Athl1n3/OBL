package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.beans.binding.BooleanBinding;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Alaa Grable
 * @version 1.0 [17.1.2019]
 * 
 */

public class LendController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TableView<LentBook> tableView;

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtBookID;

	@FXML
	private TextField txtBookName;

	@FXML
	private TextField txtBookType;

	@FXML
	private TextField txtAvailableCopies;

	@FXML
	private TextField txtUserID;

	@FXML
	private DatePicker dtDueDate;

	@FXML
	private TextField txtName;

	@FXML
	private Button btnBookLookup;

	@FXML
	private DatePicker dtIssueDate;

	@FXML
	private Button btnLendBook;

	@FXML
	private Button btnClear;

	UserAccount lenderAccount = new UserAccount(316544345, "ALAA", "Grable", "alaatg.7@gmail.com", "0522985313", 111,
			"Zerox", "asd123", accountStatus.Active, 0, 0);
	Book lentBook = new Book(123, "Aces", "Zbe", "1st", 1992, "Fucking adam", "Fuck", 1, "Sex", "7", 15, "Wanted", 10);

	private static UserAccount loggedAccount;

	/**
	 * When BookLookUp button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnBookLookupPressed(ActionEvent event) {

		/*
		 * lentBook = DataBaseController.getBook(txtBookID.getText());
		 */
		// validate if there is such a book with the inputed book ID
		if (lentBook == null) {
			// if not , then let the user know
			alertWarningMessage("There is no such book in the library");
		} else {
			/*
			 * lenderBook = DatabaseController.getAccount(txtUserID.getText());
			 */
			// validate if there is such an account with the inputed ID
			if (lenderAccount == null) {
				// if not , then let the user know
				alertWarningMessage("There is no such user");
			} else {
				// if the book ID & the user ID is found in the DB , then display the details
				// about the book and the user
				txtBookName.setText(lentBook.getName());
				txtBookName.setEditable(false);

				txtBookType.setText(lentBook.getBookType());// needs to be added //
				txtBookType.setEditable(false);

				txtAvailableCopies.setText(String.valueOf(lentBook.getCopiesNumber()));
				txtAvailableCopies.setEditable(false);

				txtName.setText(lenderAccount.getFirstName());
				txtName.setEditable(false);

				// validate if the user account status is not active
				if (!lenderAccount.getStatus().equals(accountStatus.Active)) {
					// if the user account status is not active then let the user know that
					alertWarningMessage("This account is " + lenderAccount.getStatus());
				} else {
					// the user account status is active , then validate if there is copies of that
					// book
					if (lentBook.getCopiesNumber() == 0) {
						// if there is no copies of that book then let the user know that
						alertWarningMessage("There is no copies of the book " + lentBook.getName());
					} else {
						// if everything is okay then enable the button to let the user be able to lent
						// the book
						btnLendBook.setDisable(false);
					}
				}
			}
		}
	}

	/*
	 * Clear all textFields
	 */
	@FXML
	void btnClearPressed(ActionEvent event) {
		txtBookID.clear();
		txtBookName.clear();
		txtBookType.clear();
		txtAvailableCopies.clear();
		txtUserID.clear();
		txtName.clear();
		dtDueDate.getEditor().clear();
	}

	/**
	 * When LendBook button is pressed , this method will be called
	 * 
	 * @param event
	 */
	@FXML
	void btnLendBookPressed(ActionEvent event) {

		// get the date of today
		LocalDate date = LocalDate.now();
		// validate if the book type is wanted or not
		if (lentBook.getBookType().equals("Wanted"))
			// the book is "wanted" so lent the book for 3 days only
			date = date.plusDays(3);
		else
			// the books is "regular" , then lent the book for 2 weeks
			date = date.plusWeeks(2);
		// set the returning time
		dtDueDate.setValue(date);

		// create the lent book request with the appropriate returning time
		LentBook lntbook = new LentBook(lenderAccount.getID(), lentBook.getBookID(), LocalDate.now(), date, false,
				lentBook.getName(), lentBook.getEdition(), lentBook.getAuthor(), lentBook.getSubject(),
				lentBook.getBookType());
		/*
		 * DataBaseController.setLentBook(lntbook); needs to be added //
		 */

		// let the user know that the lent process has been cone successfully
		Alert alert = new Alert(AlertType.INFORMATION, "Book has been lent successfully", ButtonType.OK);
		alert.show();

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
		stage.setTitle("User Main");
	}

	/**
	 * Initialise the current screen
	 */
	@FXML
	void initialize() {
		// display the date of today
		dtIssueDate.setValue(LocalDate.now());
		// make this field inedible
		dtIssueDate.setEditable(false);
		// disable LendBook button
		btnLendBook.setDisable(true);

		// Enable BookLookUp button only when the book ID textfield & user ID textField
		// is not empty
		BooleanBinding booleanBind = txtBookID.textProperty().isEmpty().or(txtUserID.textProperty().isEmpty());
		btnBookLookup.disableProperty().bind(booleanBind);
	}

	void start(Stage stage, Account loggedAccount) throws Exception {
		this.loggedAccount = (UserAccount) loggedAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/LendForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Lend book");
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
