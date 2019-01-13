package controllers;

import java.io.File;
import java.io.IOException;

import entities.Book;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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

		try {
			if (!(txtBookID.getText().matches("[0-9]+") && txtPrintYear.getText().matches("[0-9]+")
					&& txtCatalog.getText().matches("[0-9]+") && txtEdition.getText().matches("[0-9]+")
					&& txtCopies.getText().matches("[0-9]+"))) {

				throw new Exception();
			}

			Book newBook = new Book(Integer.parseInt(txtBookID.getText()), txtBookName.getText(), txtAuthor.getText(),
					txtEdition.getText(), Integer.parseInt(txtPrintYear.getText()), txtBookSubject.getText(),
					txtDescirption.getText(), Integer.parseInt(txtCatalog.getText()), txtTableOfContents.getText(),
					txtShelf.getText(), Integer.parseInt(txtCopies.getText()), null);

			// write this book to DB (DBController.addbook(newBook));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Succsess");
			alert.setHeaderText("The book has added successfully");
			alert.showAndWait();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageLibraryForm.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene((Parent) loader.load()));
			stage.show();
			((Node) event.getSource()).getScene().getWindow().hide();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("The following fields must contain Numbers only ");
			alert.setContentText("ID \nPrint year \nCatalog \nCopies");
			alert.showAndWait();
		}

		/*
		 * needs DB try {
		 * 
		 * if(Integer.parseInt(txtBookID.getText()) is exist in db){ throw new
		 * Exception(); } catch(Exception e) { Alert alert = new
		 * Alert(AlertType.INFORMATION); alert.setTitle("Error");
		 * alert.setHeaderText("this id is exist");
		 * alert.setContentText("please enter a new id "); alert.showAndWait(); } }
		 */

	}

	/*
	 * when browse button clicked this method open the window to get the path of the
	 * file
	 */
	@FXML
	void btnBrowsePathPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File SelectedFile = fc.showOpenDialog(null);

		if (SelectedFile != null) {
			txtPath.setText(SelectedFile.getAbsolutePath());
		}
	}

	/*
	 * this method clears all the data in the fields when clear button clicked
	 */
	@FXML
	void btnClearPressed(ActionEvent event) {
		txtBookName.clear();
		txtBookID.clear();
		txtEdition.clear();
		txtAuthor.clear();
		txtTableOfContents.clear();
		txtPrintYear.clear();
		txtBookSubject.clear();
		txtCatalog.clear();
		txtCopies.clear();
		txtShelf.clear();
		txtDescirption.clear();
		txtPath.clear();
	}

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene((Parent) loader.load()));
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();
	}

	/*
	 * initialize "add button to disable until fill all the fields in the form
	 */
	@FXML
	void initialize() {
		btnAddBook.setDisable(true);

		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(txtBookName.textProperty(), txtAuthor.textProperty(), txtBookID.textProperty(),
						txtEdition.textProperty(), txtTableOfContents.textProperty(), txtPrintYear.textProperty(),
						txtBookSubject.textProperty(), txtCatalog.textProperty(), txtCopies.textProperty(),
						txtShelf.textProperty(), txtDescirption.textProperty(), txtPath.textProperty());
			}

			// this function return true if at least one field not filled
			@Override
			protected boolean computeValue() {
				return (txtBookName.getText().isEmpty() || txtAuthor.getText().isEmpty()
						|| txtBookID.getText().isEmpty() || txtEdition.getText().isEmpty()
						|| txtTableOfContents.getText().isEmpty() || txtPrintYear.getText().isEmpty()
						|| txtBookSubject.getText().isEmpty() || txtCatalog.getText().isEmpty()
						|| txtCopies.getText().isEmpty() || txtShelf.getText().isEmpty()
						|| txtDescirption.getText().isEmpty() || txtPath.getText().isEmpty());
			}
		};

		// Enable "add book button" after fill all the fields
		btnAddBook.disableProperty().bind(bb);

	}

}
