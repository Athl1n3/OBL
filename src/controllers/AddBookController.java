package controllers;

import java.io.IOException;

import entities.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

	@FXML
	private TextArea txtTableOfContents;

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
	private TextField txtPath;

	@FXML
	void btnAddBookPressed(ActionEvent event) throws IOException {
		Book newBook = new Book(
				Integer.parseInt(txtBookID.getText()), 
				txtBookName.getText(),
				txtAuthor.getText(),
				Double.parseDouble(txtEdition.getText()),
				Integer.parseInt(txtPrintYear.getText()),
				txtBookSubject.getText(),
				txtDescirption.getText(), 
				Integer.parseInt(txtCatalog.getText()),
				txtTableOfContents.getText(),
				txtShelf.getText(), 
				Integer.parseInt(txtCopies.getText()));

		/*
		 * needs DB try {
		 * 
		 * if(Integer.parseInt(txtBookID.getText()) is exist in db){ throw new
		 * Exception(); } catch(Exception e) { Alert alert = new
		 * Alert(AlertType.INFORMATION); alert.setTitle("Error");
		 * alert.setHeaderText("this id is exist");
		 * alert.setContentText("please enter a new id "); alert.showAndWait(); } }
		 */

		// write to this book to DB
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene((Parent) loader.load()));
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	@FXML
	void btnBrowsePathPressed(ActionEvent event) {

	}

	@FXML
	void btnClearPressed(ActionEvent event) {	
		txtBookName .clear();
	}

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene((Parent) loader.load()));
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	// disable the button until fill all the data
	/*
	 * @FXML void EnableAddBookButton(InputMethodEvent event) {
	 * System.out.println("dddd");
	 * if(txtBookName.getText()==null||txtAuthor.getText()==null||txtBookID.getText(
	 * )==null||txtEdition.getText()==null||txtPrintYear.getText()==null||
	 * txtBookSubject.getText()==null||txtCatalog.getText()==null||txtCopies.getText
	 * ()==null||txtShelf.getText()==null) btnAddBook.setDisable(true); else
	 * btnAddBook.setDisable(false); }
	 */

	@FXML
	void initialize() {
		btnAddBook.setDisable(true);

	}

}
