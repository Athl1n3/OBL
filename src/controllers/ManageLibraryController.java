package controllers;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;

import entities.Book;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageLibraryController {

	@FXML
	private TextField txtSearch;

	@FXML
	private ImageView imgRef;

	@FXML
	private ImageView imgBack;

	@FXML
	private TableView<Book> tableView;

	@FXML
	private TableColumn<Book, String> bookID;

	@FXML
	private TableColumn<Book, String> name;

	@FXML
	private TableColumn<Book, String> author;

	@FXML
	private TableColumn<Book, Double> edition;

	@FXML
	private TableColumn<Book, Year> year;

	@FXML
	private TableColumn<Book, Integer> copies;

	@FXML
	private TableColumn<Book, Integer> availableCopies;

	@FXML
	private Button btnAddBook;

	@FXML
	private Button btnDeleteBook;

	@FXML
	private Button btnEditBook;

	/**
	 * This method called when "Add book" button clicked and open a form to fill
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnAddBookPressed(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		AddBookController AddBookForm = new AddBookController();
		try {
			AddBookForm.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method called when "Delete book" button clicked and deletes this
	 * specific book after the user confirmation
	 * 
	 * @param event
	 */
	@FXML
	void btnDeleteBookPressed(ActionEvent event) {

		Book selectedForDelete = (Book) tableView.getSelectionModel().getSelectedItem();
		try {

			if (selectedForDelete == null)
				throw new Exception();
			else {
				Alert confirmation = new Alert(AlertType.CONFIRMATION);
				confirmation.setTitle("Confirmation");
				confirmation.setHeaderText("Are you sure want to delete this book");
				// confirmation.setContentText("Select a book for delete!");
				confirmation.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						DatabaseController.deleteBook(selectedForDelete.getBookID());
						initialize();

					}
				});

			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No book has selected");
			alert.setContentText("Select a book for delete!");
			alert.showAndWait();
		}

	}

	/**
	 * Open "edit book" form to edit a specific book
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void btnEditBookPressed(ActionEvent event) throws Exception {
		Book selectedForEdit = (Book) tableView.getSelectionModel().getSelectedItem();

		try {
			if (selectedForEdit == null)
				throw new Exception();
			else {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				EditBookController EditBookForm = new EditBookController();
				try {
					EditBookForm.start(stage, selectedForEdit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No book has selected");
			alert.setContentText("Select a book for edit!");
			alert.showAndWait();
		}

	}

	/**
	 * Back to the previous scene
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Library Management");
	}

	/**
	 * Initialize GUI layout and tableview initialize gui depending on books
	 */
	@FXML
	public void initialize() {

		// ObservableList<Book> search = FXCollections.observableArrayList();
		// add all the relevant fields to the view table
		bookID.setCellValueFactory(new PropertyValueFactory<Book, String>("bookID"));
		name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		edition.setCellValueFactory(new PropertyValueFactory<Book, Double>("edition"));
		year.setCellValueFactory(new PropertyValueFactory<Book, Year>("printYear"));
		copies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("copiesNumber"));
		availableCopies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("availableCopies"));
		tableView.setItems(getBooks());

		txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			ArrayList<Book> temp;
			ArrayList<Book> arr = new ArrayList<Book>();
			ObservableList<Book> search = FXCollections.observableArrayList();
			if (DatabaseController.bookSearch(txtSearch.getText(), "name") != null)
				arr.addAll(DatabaseController.bookSearch(txtSearch.getText(), "name"));

			if (DatabaseController.bookSearch(txtSearch.getText(), "author") != null)
				arr.addAll(DatabaseController.bookSearch(txtSearch.getText(), "author"));

			if (DatabaseController.bookSearch(txtSearch.getText(), "subject") != null)
				arr.addAll(DatabaseController.bookSearch(txtSearch.getText(), "subject"));

			if (DatabaseController.bookSearch(txtSearch.getText(), "description") != null)
				arr.addAll(DatabaseController.bookSearch(txtSearch.getText(), "description"));
			if (arr != null)
				for (int i = 0; i < arr.size(); i++)
					if (!contain(search, arr.get(i)))
						search.add(arr.get(i));
			tableView.setItems(search);

		});

	}

	/**
	 * this method returns the current books in the DB
	 * 
	 * @return ObservableList<Book> with all the books
	 */
	public ObservableList<Book> getBooks() {
		ArrayList<Book> allBooks;
		allBooks = DatabaseController.getAllBooks();
		ObservableList<Book> books = FXCollections.observableArrayList();
		for (int i = 0; i < allBooks.size(); i++)
			books.add(allBooks.get(i));
		return books;
	}

	/**
	 * refresh the data in the table view
	 * 
	 * @param event
	 */
	@FXML
	void imgRefreshClicked(MouseEvent event) {
		initialize();
	}

	/**
	 * Load the relevant fxml file and show it
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Manage");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * checks if the observable list 'search' contains the book
	 * 
	 * @param search
	 * @param book
	 * @return true if the observable list 'search' contains the book
	 */
	boolean contain(ObservableList<Book> search, Book book) {
		for (int i = 0; i < search.size(); i++)
			if (search.get(i).getBookID() == book.getBookID())
				return true;
		return false;

	}

	@FXML
	void btnManageCopiesPressed(ActionEvent event) {
	
	Book selectedForEdit = (Book) tableView.getSelectionModel().getSelectedItem();

	try {
		if (selectedForEdit == null)
			throw new Exception();
		else {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			ManageCopiesController manageCopiesForm = new ManageCopiesController();
			try {
				manageCopiesForm.start(stage, selectedForEdit);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	} catch (Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("No book has selected");
		alert.setContentText("Select a book for manage copies!");
		alert.showAndWait();
	}

}
	

}
