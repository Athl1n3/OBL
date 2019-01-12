package controllers;

import java.util.ArrayList;
import java.util.Date;

import entities.UserAccount;
import entities.UserActivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 
 * @author Adam Mahameed
 * @version 1.2 [12.1.2019]
 * 
 */
public class ActivityController {

	@FXML
	private ImageView imgBack;

	@FXML
	private TableView<UserActivity> tableView;

	@FXML
	private TableColumn<UserActivity, Date> dateColumn;

	@FXML
	private TableColumn<UserActivity, String> activityColumn;

	@FXML
	private Label lblUsername;

	@FXML
	private Label lblUserID;

	private static UserAccount lookedupAccount;
	private ArrayList<UserActivity> userActivityList;
	private ObservableList<UserActivity> userActivityOlist;

	@FXML
	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("User Lookup");
	}

	@FXML
	void initialize() {
		// userActivityList =
		// DatabaseController.getActivityList(lookedupAccount.getAccountID());

		lblUserID.setText(String.valueOf(lookedupAccount.getAccountID()));
		lblUsername.setText(lookedupAccount.getUserName());

		userActivityOlist = FXCollections.observableArrayList(new UserActivity(5, "hi", new Date()));// userActivityList
																										// MUST be used
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		activityColumn.setCellValueFactory(new PropertyValueFactory<>("activityName"));
		tableView.setItems(userActivityOlist);
	}

	void start(Stage stage, UserAccount lookedupAccount) throws Exception {
		this.lookedupAccount = lookedupAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ActivityForm.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("User activity");
		stage.sizeToScene();
		stage.setScene(scene);
	}
}
