package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.Account;
import entities.LibrarianAccount;
import entities.ManagerAccount;
import entities.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NotificationsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgBack;

    @FXML
    private Label txtUsername;

    @FXML
    private Label txtUserID;

    @FXML
    private TableView<Notification> tableView;

    @FXML
    private TableColumn<Notification, Timestamp> dateCol;

    @FXML
    private TableColumn<Notification, String> messageCol;

    @FXML
    private Button btnExtendBook;

    @FXML
    private Button btnExtendBook1;

    @FXML
    private Button btnExtendBook11;

    private static Account loggedAccount;
    private ObservableList<Notification> notificationsOlist;
    
    @FXML
    void btnExtendBookPressed(ActionEvent event) {

    }

    @FXML
    void imgBackClicked(MouseEvent event) {
    	Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        // get the previous scene
        Scene scene = SceneController.pop();
        stage.setScene(scene);
        stage.setTitle("Main");
    }

    @FXML
    void initialize() {
    	txtUserID.setText(String.valueOf(loggedAccount.getAccountID()));
    	txtUsername.setText(loggedAccount.getFirstName() + " " + loggedAccount.getLastName());
    
    	ArrayList<Notification> notificationsList;
    	if(loggedAccount instanceof ManagerAccount)
    		notificationsList = DatabaseController.getNotifications(1);//get data for manager
    	else if(loggedAccount instanceof LibrarianAccount)
    		notificationsList = DatabaseController.getNotifications(2);// get data for librarian
    	else
    		notificationsList = DatabaseController.getNotifications(loggedAccount.getAccountID());//get data for user
    	notificationsOlist = FXCollections.observableArrayList(notificationsList);// notifications List
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
		tableView.setItems(notificationsOlist);
    }
    
    public void start(Stage stage, Account loggedAccount) {
		try {
			NotificationsController.loggedAccount = loggedAccount;
			Parent root = FXMLLoader.load(getClass().getResource("../gui/NotificationsForm.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Notifications");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
