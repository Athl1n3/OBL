package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManualExtendController {
	
	UserAccount acc;
	Book tmpBook;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

	@FXML
	private TableView<LentBook> tableView;
    
	@FXML
	private TableColumn<LentBook, String> bookNameCol;

	@FXML
	private TableColumn<LentBook, String> bookEditionCol;

	@FXML
	private TableColumn<LentBook, String> bookAuthorCol;

	@FXML
	private TableColumn<LentBook, String> bookTypeCol;

	@FXML
	private TableColumn<LentBook, LocalDate> issuedDateCol;

	@FXML
	private TableColumn<LentBook, LocalDate> dueDateCol;
	
	@FXML
	private TableColumn<LentBook , String> bookDetails;

    @FXML
    private TextField txtUserID;

    @FXML
    private Button btnUserLookup;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtName;

    @FXML
    private Label lblStatus;

    @FXML
    private Button btnExtendLend;

    @FXML
    void btnExtendLendPressed(ActionEvent event) {
    	LentBook selectedBook = tableView.getSelectionModel().getSelectedItem();
    	
		// check account status if suspended ..
		if (selectedBook.getBookType().equals("Wanted")) {
			Alert alert = new Alert(AlertType.WARNING, "This book "  + selectedBook.getBookName() + " is a 'Wanted' book and cannot be extended.",ButtonType.OK);
			alert.show();
		}
		else {
			//tmpBook = DatabaseController.getBook(selectedBook.getBookID());
			if(tmpBook.getAvailableCopies() <= tmpBook.getBookOrders()) {
				Alert alert = new Alert(AlertType.WARNING , "There is a lot of orders on that book , \nTherefore the book " + tmpBook.getName() +" cannot be extended.",ButtonType.OK);
				alert.show();
			}
			else {
				selectedBook.setDueDate(selectedBook.getDueDate().plusWeeks(1));
				
				//DatabaseController.updateLentBook(selectedBook);
				Alert alert = new Alert(AlertType.INFORMATION, "The book"  + tmpBook.getName() + " Due time has been extended successfully.", ButtonType.OK);
				alert.show();
			}
			
		}

    }

    @FXML
    void btnUserLookupPressed(ActionEvent event) {
    	
    	  try {
    		  String usrID = txtID.getText();
    	      Integer num = Integer.valueOf(usrID);
    	      //acc = DatabaseController.getAccount(usrID);
    	      acc = new UserAccount(316544345,"ALAA", "Grable", "alaatg.7@gmail.com", "0522985313", 123,"Zerox", "asd123", accountStatus.Active, 0,0,true);
    	      txtUserID.setText(String.valueOf(acc.getID()));
    	      txtUsername.setText(acc.getUserName());
    	      txtName.setText(acc.getFirstName()+" " +acc.getLastName() );
    	      lblStatus.setText(String.valueOf(acc.getStatus()));
    	      ObservableList<LentBook> list = getLentBookList(usrID);
    	      tableView.setItems(list);
    	       
    	    } catch (NumberFormatException e) {
    	        Alert alert = new Alert(AlertType.WARNING,"The user ID must contain only numbers", ButtonType.OK);
    	        alert.show();
    	    }
    }

    @FXML
    void initialize() {
        
		bookNameCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookName"));
		bookEditionCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookEdition"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookAuthor"));
		bookTypeCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookType"));
		issuedDateCol.setCellValueFactory(new PropertyValueFactory<LentBook,LocalDate>("IssueDate"));
		dueDateCol.setCellValueFactory(new PropertyValueFactory<LentBook,LocalDate>("DueDate"));
		btnExtendLend.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
        
        BooleanBinding booleanBind = txtID.textProperty().isEmpty();
        btnUserLookup.disableProperty().bind(booleanBind);

    }
    
	private ObservableList<LentBook> getLentBookList(String userID) {

		// DatabaseController.getExtendBookList(userID) ** & send it to the
		// next code line
		LentBook LntBK1 = new LentBook(123,111,LocalDate.now(),LocalDate.now().plusWeeks(2), false,"Marshood","2st", "ALAA", "Calculus","Wanted" );
		LentBook LntBK2 = new LentBook(777,999,LocalDate.now(),LocalDate.now().plusWeeks(2), false,"Fucker","7st", "ahmad", "notur","Regular" );
		ObservableList<LentBook> list = FXCollections.observableArrayList(LntBK1,LntBK2);
		return list;
	}
	
    void start(Stage stage) throws Exception {
		//this.loggedAccount = (UserAccount)loggedAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ManualExtendForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Manual Extend Lend book");
		stage.sizeToScene();
		stage.setScene(scene);
		stage.show();
	}
}
