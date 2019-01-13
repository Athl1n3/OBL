package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;

import entities.Account;
import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LendController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgBack;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtBookType;

    @FXML
    private TextField txtAvailableCopies;

    @FXML
    private TextField txtUserID;

    @FXML
    private DatePicker dtDueDate;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnBookLookup;

    @FXML
    private DatePicker dtIssueDate;

    @FXML
    private Button btnLendBook;

    @FXML
    private Button btnClear;

    UserAccount lenderAccount;
    Book lentBook;
    
    @FXML
    void btnBookLookupPressed(ActionEvent event) {
    	lentBook= new Book(123,"alaa","allua",12,"1996","sub","desc",1,"table of c","alala",11,"alalalala");
    	// lentBook = DataBaseController.getBook(txtBookID.getText());
    	if(lentBook == null){
    		Alert alert = new Alert(AlertType.WARNING,"There is no such book in the library", ButtonType.OK);
    		alert.show();
    	}
    	else {
    		//lenderBook = DatabaseController.getAccount(txtUserID.getText());
    		lenderAccount = new UserAccount(111,"asus", "laptop", "alaatg.7", 9851,111,"Zerox","a123", "Member", "Active", 0 , 0, true);
    		if(lenderAccount == null) {
        		Alert alert = new Alert(AlertType.WARNING,"There is no such user", ButtonType.OK);
        		alert.show();
    		}
    		else {
    			txtBookName.setText(lentBook.getName());
    			txtBookName.setEditable(false);
    			
    			txtBookType.setText(lentBook.getBookType());// needs to be added //
    			txtBookType.setEditable(false);
    			
    			txtAvailableCopies.setText(String.valueOf(lentBook.getCopiesNumber()));
    			txtAvailableCopies.setEditable(false);
    			
    			txtName.setText(lenderAccount.getFirstName());
    			txtName.setEditable(false);
    			
    			if(!lenderAccount.getStatus().equals("Active")) {
    				Alert alert = new Alert(AlertType.WARNING , "This account is " + lenderAccount.getStatus(), ButtonType.OK);
					alert.show();
    			}
    			else {
    				if(lentBook.getCopiesNumber() == 0) {
    					Alert alert = new Alert(AlertType.WARNING, "There is no copies of the book " + lentBook.getName());
    					alert.show();
    				}
    				else {
    					btnLendBook.setDisable(false);
    				}
    			}
    		}
    	}		
    }
    
    @FXML
    void btnClearPressed(ActionEvent event) {
    	txtBookID.clear();
    	txtBookName.clear();
    	txtBookType.clear();
    	txtAvailableCopies.clear();
    	txtUserID.clear();
    	txtName.clear();
    	dtDueDate.getEditor().clear();
    }

    @FXML
    void btnLendBookPressed(ActionEvent event) {
    	
    	LocalDate date = LocalDate.now();
    	if(lentBook.getBookType().equals("Wanted") )    /// the book is wanted
    		date.plusDays(3);
    	else      // the books is regular
    		date.plusWeeks(2);
    	dtDueDate.setValue(date);
		
    	LentBook lntbook = new LentBook(lenderAccount.getID(),lentBook.getBookID(), LocalDate.now().toString(),date.toString(),false);
    	//DataBaseController.setLentBook(lntbook);     needs to be added //
		
    	Alert alert = new Alert(AlertType.INFORMATION, "Book has been lent successfully",ButtonType.OK);
    	alert.show();

    }

    @FXML
    void imgBackClicked(MouseEvent event) {

    }
    
    @FXML
    void initialize() {
    	dtIssueDate.setValue(LocalDate.now());
    	dtIssueDate.setEditable(false);
    	btnLendBook.setDisable(true);
    	
    	BooleanBinding booleanBind = txtBookID.textProperty().isEmpty().and(txtUserID.textProperty().isEmpty());
    	btnBookLookup.disableProperty().bind(booleanBind);
    	
    	
    }
    
  /*  @FXML
    void ChecktxtBookID() {
    	if(!txtBookID.getText().trim().isEmpty() && !txtUserID.getText().trim().isEmpty())
    		btnLendBook.setDisable(false);
    }
    
    @FXML
    void ChecktxtUserID() {
    	if(!txtBookID.getText().trim().isEmpty() && !txtUserID.getText().trim().isEmpty())
    		btnLendBook.setDisable(false);
    }*/
    
    void start(Stage primaryStage) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("../gui/LendForm.fxml"));
    	Scene scene = new Scene(root);
    	primaryStage.setTitle("lend book");
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
    }
}

