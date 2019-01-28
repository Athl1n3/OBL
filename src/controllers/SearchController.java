package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchController implements Initializable {

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtSearch;

	@FXML
	private Button btnSearch;

	@FXML
	private Button btnCheck;

	@FXML
	private ComboBox<String> cmbSearchBy;

	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, Integer> bookIDCol;

	@FXML
	private TableColumn<Book, String> bookNameCol;

	@FXML
	private TableColumn<Book, String> editionCol;

	@FXML
	private TableColumn<Book, String> printYearCol;

	@FXML
	private TableColumn<Book, String> bookAuthorCol;

	@FXML
	private TableColumn<Book, String> subjectCol;

	@FXML
	private TableColumn<Book, String> availableCopiesCol;

	@FXML
	private TableColumn<Book, String> descriptionCol;

	@FXML
	private TableColumn<Book, String> shelfCol;

	@FXML
	private Button btnViewInfo;

	@FXML
	private Button btnClear;

	@FXML
	private Button btnOrderBook;

	ObservableList<String> list;
	ObservableList<Book> bookList;

	/**
	 * clear the table contents
	 * 
	 * @param event
	 */
	@FXML
	void btnClearPressed(ActionEvent event) {
		// tableView.getItems().clear();
		// tableView.refresh();
		tableView.getItems().removeAll(bookList);
		txtSearch.clear();
		txtSearch.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");


	}

	/**
	 * make an order for book if there is no available copies
	 * 
	 * @param event
	 */
	@SuppressWarnings("static-access")
	@FXML
	void btnOrderBookPressed(ActionEvent event) {
		try {
			OrderController orderController = new OrderController();
			// passing the selected book data to order controller
			Book selectedBook = tableView.getSelectionModel().getSelectedItem();
			if (selectedBook.getAvailableCopies() == 0)
				orderController.start(selectedBook);
			else
				showAlert("",
						"There is already an avialable copy!!!\n You can find it on Shelf: " + selectedBook.getShelf());
		} catch (NullPointerException e) {
			showAlert("Error!!!", "Please Selecte Book First");
		}
	}

	@FXML
	void activateOrderBookButton(ContextMenuEvent event) {
		btnOrderBook.setDisable(false);
	}

	/**
	 * search for specific book by its (name,author,subject,description)
	 * 
	 * @param event
	 */
	@FXML
	void btnSearchPressed(ActionEvent event) {
		txtSearch.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		try {
			String searchBy = cmbSearchBy.getValue();
			String str = txtSearch.getText();
			// make a default search by name if the user didn't make a selection from Combo
			// Box
			if (searchBy == null) {
				searchBy = "name";
				cmbSearchBy.setValue("Name");
			}
			// check if the inserted Book id is valid input
			if (searchBy.equals("Book ID")) {
				for (char c : str.toCharArray())// Parse text field into chars array and validate
					if (!Character.isDigit(c)) {
						showAlert("Input Error!", "Book ID number must contain numbers only!\n");
						txtSearch.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
						break;
					}
			}
			ArrayList<Book> arr = DatabaseController.bookSearch(txtSearch.getText(), searchBy);
			bookList = FXCollections.observableArrayList(arr);
			tableView.setItems(bookList);
		} catch (NullPointerException e) {
			showAlert("No Match Result For " + txtSearch.getText(), "Please Enter New " + cmbSearchBy.getValue());
			tableView.getItems().clear();
			tableView.refresh();
		}

	}

	@FXML
	void btnViewInfoPressed(ActionEvent event) {

	}

	/**
	 * return to the previous window
	 * 
	 * @param event
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
	 * initialize the search form, prepare the search By combo box and the table
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnOrderBook.setVisible(false);
		bookIDCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookID"));
		bookNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		editionCol.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
		printYearCol.setCellValueFactory(new PropertyValueFactory<Book, String>("printYear"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		subjectCol.setCellValueFactory(new PropertyValueFactory<Book, String>("subject"));
		availableCopiesCol.setCellValueFactory(new PropertyValueFactory<Book, String>("availableCopies"));
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
		shelfCol.setCellValueFactory(new PropertyValueFactory<Book, String>("shelf"));

		setCmbSearchBy();
		// disable the search button if the search text field is empty!
		BooleanBinding booleanBind = txtSearch.textProperty().isEmpty();
		btnSearch.disableProperty().bind(booleanBind);
		btnOrderBook.setDisable(true);
		// disable btnOrderBook until selecting row from the table
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (tableView.getItems().isEmpty())
				btnOrderBook.setDisable(true);
			else if (tableView.getSelectionModel().isEmpty())
				btnOrderBook.setDisable(true);
			else
				btnOrderBook.setDisable(false);
		});
		if (DatabaseController.loggedAccount instanceof UserAccount && DatabaseController.loggedAccount != null)
			btnOrderBook.setVisible(true);
	}

	@FXML
	void checkClosestReturnDate(ActionEvent event) {
		try {
			Book selectedBook = tableView.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.INFORMATION);
			LocalDate date = DatabaseController.getClosestReturnDate(selectedBook.getBookID());
			if (selectedBook.getAvailableCopies() == 0) {
				alert.setContentText(
						"Book(" + selectedBook.getName() + ") Closest Return date is:\n" + date.toString());
			} else
				alert.setContentText("Theres alreardy an existing copy from: " + selectedBook.getName()
						+ "\nYou can find it on Shelf: " + selectedBook.getShelf());
			alert.show();
		} catch (NullPointerException e) {
			showAlert("Error!!!", "Please Selecte Book First");
		}
	}

	public void start(Stage primaryStage, Account account) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/SearchForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Search");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		// openNewForm("../gui/SearchForm.fxml", "Search Form");
	}

	/**
	 * initialize the Search By combo box
	 */
	private void setCmbSearchBy() {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Book ID");
		arr.add("Name");
		arr.add("Author");
		arr.add("Subject");
		arr.add("Description");
		list = FXCollections.observableArrayList(arr);
		cmbSearchBy.setItems(list);

	}

	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR, content);
		alert.setHeaderText(header);
		alert.show();
	}
	/*
	 * public void openNewForm(String resource, String title) { try { Parent root =
	 * FXMLLoader.load(getClass().getResource(resource)); Stage stage = new Stage();
	 * Scene scene = new Scene(root); stage.setScene(scene); stage.setTitle(title);
	 * stage.show(); } catch (IOException e) { e.printStackTrace(); } }
	 */
}
