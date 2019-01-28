package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BookDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField txtBookSubject;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txtCatalog;

    @FXML
    private TextField txtCopies;

    @FXML
    private TextField txtShelf;

    @FXML
    private TextArea txtDescirption;

    @FXML
    private TextField txtBookType;

    @FXML
    private Button btnTableOfContents;
    
    private static Book selectedBook;

    @FXML
    void imgBackClicked(MouseEvent event) {
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage
    }

    @FXML
    void initialize() {
        
    }
    
    public void start(Stage primaryStage, Book selectedBook) {
		try {
			BookDetailsController.selectedBook = selectedBook;
			Parent root = FXMLLoader.load(getClass().getResource("../gui/BookDetailsForm.fxml"));
			Stage stage = new Stage();
			stage.initOwner(primaryStage);
			stage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Book Details");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
