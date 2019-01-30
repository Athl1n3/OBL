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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
	private Button btnProccessNotification;

	@FXML
	private Button btnClearSelected;

	private static Account loggedAccount;
	private ObservableList<Notification> notificationsOlist;

	/**
	 * Clear selected item from DataView
	 * @param event
	 */
	@FXML
	void btnClearSelectedPressed(ActionEvent event) {
		Alert deleteConfirmation = new Alert(AlertType.CONFIRMATION,
				"Are you sure you want to remove the selected notification?(Can't be undone)", ButtonType.YES,
				ButtonType.CANCEL);
		if (!(tableView.getSelectionModel().isEmpty())) {
			if (deleteConfirmation.showAndWait().get() == ButtonType.YES) {
				Notification selectedNotf = tableView.getSelectionModel().getSelectedItem();
				// DatabaseController.deleteNotfication(selectedNotf); // DONT FORGET TO
				// UNCOMMENT
				tableView.getItems().remove(selectedNotf);
			}
		} else
			new Alert(AlertType.WARNING, "No notification is selected!", ButtonType.OK).show();
	}
	
	
	/**
	 * Proccess selected notification (LOCK NOTIFICATIONS ONLY CAN BE PROCCESSED)
	 * @param event
	 */
	@FXML
	void btnProccessNotificationPressed(ActionEvent event) {
		Alert lockConfirmation = new Alert(AlertType.CONFIRMATION,
				"The system has detected 3 delays for this user and awaiting locking account approval\nDo you want to lock it?",
				ButtonType.YES, ButtonType.CANCEL);// Lock confirmation message

		Notification selectedNotf = tableView.getSelectionModel().getSelectedItem();

		// Parsing notification for accountID
		String accountID = (selectedNotf.getMessage().replaceAll("\\D+", ""));
		accountID = accountID.substring(0, accountID.length() - 1);
		/////

		ButtonType choice = lockConfirmation.showAndWait().get();
		if (choice == ButtonType.YES) {
			if (DatabaseController.lockAccount(Integer.parseInt(accountID))) {
				new Alert(AlertType.INFORMATION, "Account has been locked successfully!").show();
				// DatabaseController.deleteNotfication(selectedNotf); // DONT FORGET TO
				// UNCOMMENT
				tableView.getItems().remove(selectedNotf);
			} else
				new Alert(AlertType.ERROR, "An error has occured!").show();
		}
	}

	/**
	 * Return to the previous stage
	 * @param event
	 */
	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		// get the previous scene
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("Main");
	}

	/**
	 * Initialize notifications for this user
	 */
	@FXML
	void initialize() {
		txtUserID.setText(String.valueOf(loggedAccount.getAccountID()));
		txtUsername.setText(loggedAccount.getFirstName() + " " + loggedAccount.getLastName());

		ArrayList<Notification> notificationsList;
		if (loggedAccount instanceof ManagerAccount)
			notificationsList = DatabaseController.getNotifications(1);// get data for manager
		else if (loggedAccount instanceof LibrarianAccount) {
			btnProccessNotification.setVisible(false);
			notificationsList = DatabaseController.getNotifications(2);// get data for librarian
		} else {
			btnProccessNotification.setVisible(false);
			notificationsList = DatabaseController.getNotifications(loggedAccount.getAccountID());// get data for user
		}
		notificationsOlist = FXCollections.observableArrayList(notificationsList);// notifications List
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		messageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
		tableView.setItems(notificationsOlist);

		// Set listener to tableView to enable Proccess Notification button only when
		// the message is a lock message
		btnProccessNotification.setDisable(true);
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (!tableView.getItems().isEmpty())
				if (tableView.getSelectionModel().getSelectedItem().isLock())
					btnProccessNotification.setDisable(false);
				else
					btnProccessNotification.setDisable(true);
			else
				btnProccessNotification.setDisable(true);
		});
		
		
		tableView.setRowFactory( tableView -> {
		    TableRow<Notification> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (!row.isEmpty()) ) {
		        	Notification rowData = row.getItem();
		            new Alert(AlertType.INFORMATION, rowData.getMessage(), ButtonType.OK).show();
		        }
		    });
		    return row ;
		});
	}

	/**
	 * Startup GUI
	 * @param stage
	 * @param loggedAccount
	 */
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
