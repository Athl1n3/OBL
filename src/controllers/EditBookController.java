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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
	private TextField txtTableOfContents;

	@FXML
    private ChoiceBox<String> bookTypeCB;

    @FXML
    private TextArea txtTableOfCont333333333ents;
    
	private static Book selectedBook;


	/** 
	 * Browse the contents PDF file and write the path in the relevant text field
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
	 * checks input validity and update the DB when the new data
	 * @param event
	 */
	@FXML
	void btnEditBookPressed(ActionEvent event) {
		/*
		 * update the edited data in the DB
		 */
		if(validateInput()==true) {
		editedBook.setName(txtBookName.getText());
		editedBook.setAuthor(txtAuthor.getText());
		editedBook.setPrintYear(Integer.parseInt(txtPrintYear.getText()));
		editedBook.setSubject(txtSubject.getText());
		editedBook.setCatalog(Integer.parseInt(txtCatalog.getText()));
		editedBook.setCopiesNumber(Integer.parseInt(txtCopies.getText()));
		editedBook.setShelf(txtShelf.getText());
		editedBook.setEdition(txtEdition.getText());
		editedBook.setSubject(txtDescription.getText());
		editedBook.setBookType(bookType.valueOf(bookTypeCB.getSelectionModel().getSelectedItem().toString()));
		DatabaseController.editBook(editedBook);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setContentText("this changes has updated successfully");
		alert.showAndWait();
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage
		}
	}

	/**
	 * back to the previous screen when image "back" 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage
	}

	/**
	 * this function initialize the fields in the new window previous window
	 * selected book
	 */
	@FXML
	void initialize() {
		btnEditBook.setDisable(true);
		ObservableList<String> options = 
    		    FXCollections.observableArrayList(
    		        "Wanted",
    		        "Regular"
    		    );
		ObservableList<String> init = 
    		    FXCollections.observableArrayList(
    		    		selectedBook.getBookType().name()
    		    );
    	bookTypeCB.getItems().addAll(options);
		txtBookName.setText(selectedBook.getName());
		txtAuthor.setText(selectedBook.getAuthor());
		txtBookID.setText(Integer.toString(selectedBook.getBookID()));
		txtEdition.setText(selectedBook.getEdition());
		txtPrintYear.setText(Integer.toString(selectedBook.getPrintYear()));
		txtSubject.setText(selectedBook.getSubject());
		txtCatalog.setText(Integer.toString(selectedBook.getCatalog()));
		txtCopies.setText(Integer.toString(selectedBook.getCopiesNumber()));
		txtShelf.setText(selectedBook.getShelf());
		txtDescription.setText(selectedBook.getDescription());
		bookTypeCB.getItems().addAll(init);
		bookTypeCB.getSelectionModel().select(0	);
		txtTableOfContents.setText(selectedBook.getTableOfContents());
		editedBook=selectedBook;
		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(txtBookName.textProperty(), txtAuthor.textProperty(), txtBookID.textProperty(),
						txtEdition.textProperty(),  txtPrintYear.textProperty(),
						txtSubject.textProperty(), txtCatalog.textProperty(), txtCopies.textProperty(),
						txtShelf.textProperty(), txtDescription.textProperty(), txtTableOfContents.textProperty());
			}

			// this function return true if at least one field not filled
			@Override
			protected boolean computeValue() {
				return (txtBookName.getText().isEmpty() || txtAuthor.getText().isEmpty()
						|| txtBookID.getText().isEmpty() || txtEdition.getText().isEmpty()
					 || txtPrintYear.getText().isEmpty()
						|| txtSubject.getText().isEmpty() || txtCatalog.getText().isEmpty()
						|| txtCopies.getText().isEmpty() || txtShelf.getText().isEmpty()
						|| txtDescription.getText().isEmpty() || txtTableOfContents.getText().isEmpty());
			}
		};
		// Enable "add book button" after fill all the fields
				btnEditBook.disableProperty().bind(bb);
	}

	
	/**
	 * Open's "edit book" form and update the static selectedBook to the selected book from the previous window 
	 * @param primaryStage
	 * @param selectedBook
	 */
	public void start(Stage primaryStage, Book selectedBook) {
		try {
			this.selectedBook = selectedBook;
			Parent root = FXMLLoader.load(getClass().getResource("../gui/EditBookForm.fxml"));
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
	 * Validate the input in the text fields 
	 * @return true if the input valid 
	 */
	public boolean validateInput() {
		// initialize the text fields to the original color
		txtBookName.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtAuthor.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtBookID.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtEdition.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtPrintYear.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtSubject.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtCatalog.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtCopies.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtShelf.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");
		txtDescription.setStyle("-fx-border-color: white ; -fx-border-width: 2px ;");

		Alert msg = new Alert(AlertType.ERROR, "", ButtonType.OK);// Prepare alert box
		msg.setHeaderText("Input Error");
		String errorMsg = "";
	
		 
		
		 //validate input for all the text fields
		for (char c : txtAuthor.getText().toCharArray())// Parse text field into chars array and validate
			if (Character.isDigit(c)) {
				errorMsg += "Author name must contain letters only!\n";
				txtAuthor.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}



		for (char c : txtCopies.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Copies number must contain numbers only!\n";
				txtCopies.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
				break;
			}
		
		for (char c : txtCatalog.getText().toCharArray())// Parse text field into chars array and validate
			if (!Character.isDigit(c)) {
				errorMsg += "Copies number must contain numbers only!\n";
				txtCatalog.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
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
