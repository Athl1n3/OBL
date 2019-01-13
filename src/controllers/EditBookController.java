package controllers;

import java.io.File;
import java.io.IOException;

import entities.Book;
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

public class EditBookController {

	Book editedBook;

	@FXML
	private TextField txtBookName;

	@FXML
	private TextField txtAuthor;

	@FXML
	private TextField txtBookID;

	@FXML
	private TextField txtEdition;

	@FXML
	private TextField txtPrintYear;

	@FXML
	private TextField txtSubject;

	@FXML
	private ImageView imgBack;

	@FXML
	private Button btnEditBook;

	@FXML
	private TextField txtCatalog;

	@FXML
	private TextField txtCopies;

	@FXML
	private TextField txtShelf;

	@FXML
	private TextArea txtDescription;

	@FXML
	private Button btnBrowsePath;

	@FXML
	private TextField txtPath;

	@FXML
	void btnBrowsePathPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File SelectedFile = fc.showOpenDialog(null);
		if (SelectedFile != null) {
			txtPath.setText(SelectedFile.getAbsolutePath());
		}
	}

	@FXML
	void btnEditBookPressed(ActionEvent event) {
		/*
		 * update the edited data in the DB
		 */
		editedBook.setName(txtBookName.getText());
		editedBook.setAuthor(txtAuthor.getText());
		editedBook.setBookID(Integer.parseInt(txtBookID.getText()));
		editedBook.setEdition(txtEdition.getText());
		editedBook.setPrintYear(Integer.parseInt(txtPrintYear.getText()));
		editedBook.setSubject(txtSubject.getText());
		editedBook.setCatalog(Integer.parseInt(txtCatalog.getText()));
		editedBook.setCopiesNumber(Integer.parseInt(txtCopies.getText()));
		editedBook.setShelf(txtShelf.getText());
		// Updated edited book in DB (DBController.updateBook(editedBook));
		// add success message
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setContentText("this changes has updated successfully");
		alert.showAndWait();

	}

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		/*
		 * back to the previous screen
		 */
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ManageLibraryForm.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene((Parent) loader.load()));
		stage.show();
		((Node) event.getSource()).getScene().getWindow().hide();

	}

	@FXML
	void initialize() {

	}

	/*
	 * this function initialize the fields in the new window previous window
	 * selected book
	 */
	public void initFields(Book selectedBook) {

		editedBook = selectedBook;
		txtBookName.setText(selectedBook.getName());
		txtAuthor.setText(selectedBook.getAuthor());
		txtBookID.setText(Integer.toString(selectedBook.getBookID()));
		txtEdition.setText(selectedBook.getEdition());
		txtPrintYear.setText(Integer.toString(selectedBook.getPrintYear()));
		txtSubject.setText(selectedBook.getSubject());
		txtCatalog.setText(Integer.toString(selectedBook.getCatalog()));
		txtCopies.setText(Integer.toString(selectedBook.getCopiesNumber()));
		txtShelf.setText(selectedBook.getShelf());

	}
}
