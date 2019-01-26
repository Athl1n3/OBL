package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import entities.Account;
import entities.LibrarianAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LibrarianMainController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblUsername;

	@FXML
	private ImageView imgLogout;

	@FXML
	private ImageView imgSearch;

	@FXML
	private ImageView imgUsersManagement;

	@FXML
	private ImageView imgExtendLend;

	@FXML
	private ImageView imgLibraryManagement;

	@FXML
	private ImageView imgLendBook;

	@FXML
	private ImageView imgReturnBook;

	private static Stage stage;
	private static Scene scene;
	private static LibrarianAccount loggedLibAccount;

	@FXML
	void imgExtendLendClicked(MouseEvent event) {
		SceneController.push(scene);
		ManualExtendController ManualExtendForm = new ManualExtendController();
		try {
			ManualExtendForm.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void imgLendBookClicked(MouseEvent event) {
		SceneController.push(scene);
		ManualExtendController ManualExtendForm = new ManualExtendController();
		try {
			ManualExtendForm.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void imgLibraryManagementClicked(MouseEvent event) {
		SceneController.push(scene);
		LibraryManagementController LibraryManagementForm = new LibraryManagementController();
		try {
			LibraryManagementForm.start(stage, loggedLibAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void imgLogoutClicked(MouseEvent event) {
		// DatabaseController Log user out ***********
		Alert confirmLogout = new Alert(AlertType.CONFIRMATION, "Are you sure you want to log out of this account?",
				ButtonType.YES, ButtonType.CANCEL);
		if (confirmLogout.showAndWait().get() == ButtonType.YES) {
			loggedLibAccount.setLogged(false);
			DatabaseController.logAccount(loggedLibAccount);
			DatabaseController.loggedAccount = null;
			Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
			// get the previous scene
			Scene scene = SceneController.pop();
			stage.setScene(scene);
			stage.setTitle("Main Form");
		}
	}

	@FXML
	void imgReturnBookClicked(MouseEvent event) {
		SceneController.push(scene);
		ReturnController ReturnForm = new ReturnController();
		try {
			ReturnForm.start(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void imgSearchClicked(MouseEvent event) {
		SceneController.push(scene);
		SearchController SearchForm = new SearchController();
		try {
			SearchForm.start(stage, loggedLibAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void imgUsersManagementClicked(MouseEvent event) {
		SceneController.push(scene);
		UsersManagementController UsersManagementForm = new UsersManagementController();
		try {
			UsersManagementForm.start(stage, loggedLibAccount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		lblUsername.setText(loggedLibAccount.getFirstName() + " " + loggedLibAccount.getLastName());
	}

	void start(Stage primaryStage, Account loggedLibAccount) throws Exception {
		this.loggedLibAccount = (LibrarianAccount) loggedLibAccount;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/LibrarianMainForm.fxml"));
		stage = primaryStage;
		scene = new Scene(root);
		primaryStage.setTitle("Librarian Main");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
