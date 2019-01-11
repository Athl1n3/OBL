package controllers;

import java.io.IOException;
import java.sql.Date;

import entities.Book;
import javafx.event.ActionEvent;
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
    	//adam will make it 
    }

    @FXML
    void btnEditBookPressed(ActionEvent event) {
 /*
  * update the edited data in the DB    	
  */
    	editedBook.setName(txtBookName.getText());
    	editedBook.setAuthor(txtAuthor.getText());
    	editedBook.setBookID(Integer.parseInt(txtBookID.getText()));
    	editedBook.setEdition(Double.parseDouble(txtEdition.getText()));
    	editedBook.setPrintYear(Integer.parseInt(txtPrintYear.getText()));
    	editedBook.setSubject(txtSubject.getText());
    	editedBook.setCatalog(Integer.parseInt(txtCatalog.getText()));
    	editedBook.setCopiesNumber(Integer.parseInt(txtCopies.getText()));
    	editedBook.setShelf(txtShelf.getText());
    	//Updated edited book in DB
    	//add success message 
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
     * this function initialize the fields in the new window previous window selected book  
     */
    public void initFields(Book selectedBook) {
    	
    	editedBook = selectedBook; 
    	txtBookName.setText(selectedBook.getName());	
    	txtAuthor.setText(selectedBook.getAuthor());
    	txtBookID.setText(Integer.toString(selectedBook.getBookID()));
    	txtEdition.setText(Double.toString(selectedBook.getEdition()));
    	//txtPrintYear.setText(Date.selectedBook.getPrintYear());
    	txtSubject.setText(selectedBook.getSubject());
    	txtCatalog.setText(Integer.toString(selectedBook.getCatalog()));
    	txtCopies.setText(Integer.toString(selectedBook.getCopiesNumber()));
    	txtShelf.setText(selectedBook.getShelf());
    	
    }
}

