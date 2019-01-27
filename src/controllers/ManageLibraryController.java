package controllers;

import java.io.IOException;
import java.time.Year;

import entities.Book;
import entities.Book.bookType;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManageLibraryController {

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
						// delete this book from DB
						// DBController.deleteBook(selectedForDelete);
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

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Library Management");
	}

	@FXML
	void initialize() {

		// add all the relevant fields to the view table
		bookID.setCellValueFactory(new PropertyValueFactory<Book, String>("bookID"));
		name.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
		author.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		edition.setCellValueFactory(new PropertyValueFactory<Book, Double>("edition"));
		year.setCellValueFactory(new PropertyValueFactory<Book, Year>("printYear"));
		copies.setCellValueFactory(new PropertyValueFactory<Book, Integer>("copiesNumber"));
		// availableCopies.setCellValueFactory(new PropertyValueFactory<Book,
		// Integer>("availableCopies"));
		tableView.setItems(getBooks());

	}

	public ObservableList<Book> getBooks() {

		ObservableList<Book> books = FXCollections.observableArrayList();
		// need to add the books from DB
		books.add(new Book(123, "book1", "fadi", "1", 6, "action", "anananana", 1, "annon", "shelf", 12, bookType.Regular, 1));

		return books;
	}

	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Manage");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
