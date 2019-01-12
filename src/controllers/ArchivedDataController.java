package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Archive;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ArchivedDataController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtFirstName;

	@FXML
	private TextField txtLastName;

	@FXML
	private TextField txtMobileNum;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtUserID;

	@FXML
	private TextField txtUsername;

	private static Archive userArcData;
	private int ID;

	@FXML
	void imgBackClicked(MouseEvent event) throws IOException {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
	}

	@FXML
	void initialize() {
		// DatabaseController.getArchived(ID);

		/*
		 * txtUserID.setText(String.valueOf(userArcData.getUserId()));
		 * txtID.setText(String.valueOf(userArcData.getId()));
		 * txtUsername.setText(userArcData.getUsername());
		 * txtFirstName.setText(userArcData.getFirstname());
		 * txtLastName.setText(userArcData.getLastname());
		 * txtMobileNum.setText(userArcData.getMobileNum());
		 * txtEmail.setText(userArcData.getEmail());
		 */

	}

	void start(Stage primaryStage, int ID) throws Exception {
		this.ID = ID;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ArchivedDataForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("User Archived Data");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
	}
}
