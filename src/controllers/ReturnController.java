package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReturnController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView imgBack;

	@FXML
	private TextField txtBookID;

	@FXML
	private DatePicker dtIssueDate;

	@FXML
	private DatePicker dtReturnDate;

	@FXML
	private TextField txtBookName;

	@FXML
	private TextField txtUserID;

	@FXML
	private DatePicker dtDueDate;

	@FXML
	private TextField txtName;

	@FXML
	private Button btnBookLookup;

	@FXML
	private Button btnReturnBook;

	@FXML
	private Button btnClear;

	@FXML
	void btnBookLookup(ActionEvent event) {

	}

	@FXML
	void btnClearPressed(ActionEvent event) {

	}

	@FXML
	void btnReturnBookPressed(ActionEvent event) {

	}

	void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../gui/ReturnForm.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Return Book");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	void imgBackClicked(MouseEvent event) {
		Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
		Scene scene = SceneController.pop();
		stage.setScene(scene);
		stage.setTitle("User Main");
	}

	@FXML
	void initialize() {

	}
}
