package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Book;
import entities.LentBook;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class ExtendLendController {
	
	LentBook lntBook;
	Book tmpBook;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TreeTableView<?> treeTableView;
    
    @FXML
    private TreeTableColumn<Book,String> bookNameCol;

    @FXML
    private Label txtUsername;

    @FXML
    private Label txtUserID;

    @FXML
    private Button btnExtendBook;

    @FXML
    void btnExtendBookPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	btnExtendBook.setDisable(true);
    	
    	
    	
    }
}
