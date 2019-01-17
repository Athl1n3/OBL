package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import entities.Account;
import entities.Book;
import entities.LentBook;
import entities.UserAccount;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ExtendLendController {

	LentBook lntBook;
	Book tmpBook;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
    @FXML
    private ImageView imgBack;

	@FXML
	private TableView<LentBook> tableView;

	@FXML
	private TableColumn<LentBook, String> bookNameCol;

	@FXML
	private TableColumn<LentBook, String> bookEditionCol;

	@FXML
	private TableColumn<LentBook, String> bookAuthorCol;

	@FXML
	private TableColumn<LentBook, String> bookTopicCol;

	@FXML
	private TableColumn<LentBook, LocalDate> bookIssuedDate;

	@FXML
	private TableColumn<LentBook, LocalDate> bookDueDate;
	
	@FXML
	private TableColumn<LentBook , String> bookDetails;

	@FXML
	private Label txtUsername;

	@FXML
	private Label txtUserID;

	@FXML
	private Button btnExtendBook;

	@FXML
	void btnExtendBookPressed(ActionEvent event) {
		LentBook selectedBook = tableView.getSelectionModel().getSelectedItem();

		// check account status if suspended ..
		if (selectedBook.getBookType().equals("Wanted")) {
			Alert alert = new Alert(AlertType.WARNING, "This book "  + tmpBook.getName() + " is a 'Wanted' book and cannot be extended.",ButtonType.OK);
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
	void initialize() {
		bookNameCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookName"));
		bookTopicCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookTopic"));
		bookEditionCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookEdition"));
		bookAuthorCol.setCellValueFactory(new PropertyValueFactory<LentBook,String>("bookAuthor"));
		bookIssuedDate.setCellValueFactory(new PropertyValueFactory<LentBook,LocalDate>("IssueDate"));
		bookDueDate.setCellValueFactory(new PropertyValueFactory<LentBook,LocalDate>("DueDate"));
		ObservableList<LentBook> list = getLentBookList();
		tableView.setItems(list);
		btnExtendBook.disableProperty().bind(Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));
	}

	private ObservableList<LentBook> getLentBookList() {

		// DatabaseController.getExtendBookList(account.getID()) ** & send it to the
		// next code line
		LentBook LntBK1 = new LentBook(123,111,LocalDate.now(),LocalDate.now().plusWeeks(2), false,"Marshood","2st", "ALAA", "Calculus","Math" );
		LentBook LntBK2 = new LentBook(777,999,LocalDate.now(),LocalDate.now().plusWeeks(2), false,"Fucker","7st", "ahmad", "notur","prog" );
		ObservableList<LentBook> list = FXCollections.observableArrayList(LntBK1,LntBK2);
		return list;
	}
	
    @FXML
    void imgBackClicked(MouseEvent event) {
    	
    }
    
    void start(Stage stage) throws Exception {
		//this.loggedAccount = (UserAccount)loggedAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ExtendLendForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Extend Lend book");
		stage.sizeToScene();
		stage.setScene(scene);
		stage.show();
	}
}
