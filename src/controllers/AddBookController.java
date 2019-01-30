package controllers;

import java.io.File;
import java.io.IOException;

import entities.Book;
import entities.Book.bookType;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddBookController {

	@FXML
	private TextField txtBookName;

	@FXML
	private TextField txtAuthor;

	@FXML
	private TextField txtBookID;

	@FXML
	private TextField txtEdition;

//	@FXML
	//private TextArea txtTableO33333fContents;

	@FXML
	private TextField txtPrintYear;

	@FXML
	private TextField txtBookSubject;

	@FXML
	private ImageView imgBack;

	@FXML
	private Button btnAddBook;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtCatalog;

	@FXML
	private TextField txtCopies;

	@FXML
	private TextField txtShelf;

	@FXML
	private TextArea txtDescirption;

	@FXML
	private Button btnBrowsePath;

	@FXML
	private TextField txtTableOfContents;

	@FXML
	private ComboBox<String> bookTypeCB;

	private Book newBook;

	/**
	 *
	 * checks input validity and add this new book to the DB 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnAddBookPressed(ActionEvent event) throws IOException {
		boolean input;
		input = validateInput();
		
		if (input == true) {
			newBook = new Book(Integer.parseInt(txtBookID.getText()), txtBookName.getText(), txtAuthor.getText(),
					txtEdition.getText(), Integer.parseInt(txtPrintYear.getText()), txtBookSubject.getText(),
					txtDescirption.getText(), Integer.parseInt(txtCatalog.getText()), txtTableOfContents.getText(),
					txtShelf.getText(), Integer.parseInt(txtCopies.getText()),
					bookType.valueOf(bookTypeCB.getSelectionModel().getSelectedItem().toString()),
					Integer.parseInt(txtCopies.getText()));
			System.out.println(newBook.getBookID());

			//check if this id exist 
			if ((DatabaseController.getBook(newBook.getBookID())) == null) {
				DatabaseController.addBook(newBook);
				System.out.println("Ddd");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Succsess");
				alert.setHeaderText("The book has been added successfully");
				alert.showAndWait();
				((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setHeaderText("This book ID is exist");
				alert.showAndWait();

			}

		}

	}

	/**
	 * when browse button clicked this method open the window to get the path of the
	 * file
	 * @param event
	 */
	@FXML
	void btnBrowsePathPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File SelectedFile = fc.showOpenDialog(null);

		if (SelectedFile != null) {
			txtTableOfContents.setText(SelectedFile.getAbsolutePath());
		}
	}

	/**
	 * this method clears all the data in the fields when clear button clicked
	 * @param event
	 */
	
	
	@FXML
	void btnClearPressed(ActionEvent event) {
		txtBookName.clear();
		txtBookID.clear();
		txtEdition.clear();
		txtAuthor.clear();
		txtPrintYear.clear();
		txtBookSubject.clear();
		txtCatalog.clear();
		txtCopies.clear();
		txtShelf.clear();
		txtDescirption.clear();
		txtTableOfContents.clear();
	}

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage
	}

	
	/**
	 * initialize the relevant fields and disable "Add book button" until fill all
	 * the fields
	 */
	@FXML
	void initialize() {

		btnAddBook.setDisable(true);
		ObservableList<String> options = FXCollections.observableArrayList("Wanted", "Regular");
		bookTypeCB.getItems().addAll(options);

		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(txtBookName.textProperty(), txtAuthor.textProperty(), txtBookID.textProperty(),
						txtEdition.textProperty(), txtPrintYear.textProperty(),
						txtBookSubject.textProperty(), txtCatalog.textProperty(), txtCopies.textProperty(),
						txtShelf.textProperty(), txtDescirption.textProperty(), txtTableOfContents.textProperty());
			}

			// this function return true if at least one field not filled
			@Override
			protected boolean computeValue() {
				return (txtBookName.getText().isEmpty() || txtAuthor.getText().isEmpty()
						|| txtBookID.getText().isEmpty() || txtEdition.getText().isEmpty()
						|| txtPrintYear.getText().isEmpty()
						|| txtBookSubject.getText().isEmpty() || txtCatalog.getText().isEmpty()
						|| txtCopies.getText().isEmpty() || txtShelf.getText().isEmpty()
						|| txtDescirption.getText().isEmpty() || txtTableOfContents.getText().isEmpty());
			}
		};

		// Enable "add book button" after fill all the fields
		btnAddBook.disableProperty().bind(bb);
		bookTypeCB.getSelectionModel().select(1);

	}

	/**
	 * loads "add book form" and show it  
	 * @param primaryStage
	 */
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../gui/AddBookForm.fxml"));
			Stage stage = new Stage();
			stage.initOwner(primaryStage);
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Add Book Form");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Validate updated user data
	 * 
	 * @return true in case of a valid input
	 */
	@FXML
	public boolean validateInput() {

		// initialize the text fields to the original color
		txtBookName.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtAuthor.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtBookID.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtEdition.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtPrintYear.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtBookSubject.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtCatalog.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtCopies.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtShelf.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtDescirption.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

		Alert msg = new Alert(AlertType.ERROR, "", ButtonType.OK);// Prepare alert box
		msg.setHeaderText("Input Error");
		String errorMsg = "";

		/**
		 * validate input for all relevant text fields
		 */

		for (char c : txtBookID.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Book ID number must contain numbers only!\n";
				txtBookID.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}

		for (char c : txtAuthor.getText().toCharArray())// Parse text field into chars array and validate
			if (Character.isDigit(c)) {
				errorMsg += "Author name must contain letters only!\n";
				txtAuthor.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}


		for (char c : txtCatalog.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Book Catalog number must contain numbers only!\n";
				txtCatalog.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}

		for (char c : txtCopies.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Copies number must contain numbers only!\n";
				txtCopies.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}
		for (char c : txtPrintYear.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Year must contain numbers only!\n";
				txtPrintYear.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}

		// if errorMsg is empty all the text fields input is correct
		if (!(errorMsg.equals(""))) {
			msg.setContentText(errorMsg);
			msg.show();
			return false;
		}

		return true;

	}
}
