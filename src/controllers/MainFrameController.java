package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import server.ClientConsole;

public class MainFrameController implements Initializable {

	@FXML
	private Button btnExit;

	@FXML
	private ComboBox<String> cmbStatusMembership;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtName;

	@FXML
	private Button btnShow;

	@FXML
	private Button btnUpdate;

	private boolean found = false;
	ObservableList<String> statusList;

	final public static int DEFAULT_PORT = 5555;

	String host = "localhost";
	static ClientConsole cc;

	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainFrameController.fxml"));aa
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Student Frame");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setStatusComboBox() {
		ArrayList<String> st = new ArrayList<String>();
		st.add("NotRegistered");
		st.add("Active");
		st.add("Frozen");
		st.add("Locked");
		statusList = FXCollections.observableArrayList(st);
		cmbStatusMembership.setItems(statusList);
	}

	@FXML
	void pressShowStudentNameButton(ActionEvent event) {
		if (!txtID.getText().isEmpty()) {
			System.out.println(txtID.getText());
			cc.executeQuery("SELECT studentName FROM student WHERE studentID = " + txtID.getText() + ";");
			
			ArrayList<String> arr = cc.getList();
			if (arr.size() == 0) {
				found = false;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Student ID doesn't exist!");
				alert.show();
			} else
			{
				txtName.setText(arr.get(0));
				found=true;
			}
		} else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Please insert a student ID!");
			alert.show();
			System.out.println("No student ID was inserted");
		}
	}

	@FXML
	void pressExitButton(ActionEvent event) {
		cc.terminate();
	}

	@FXML
	void pressUpdateButton(ActionEvent event) {
		String str = cmbStatusMembership.getValue();
		if (!txtID.getText().isEmpty() && found == true) {
			if(str == "Frozen" || str == "Locked")
				cc.executeQuery("UPDATE student SET StatusMembership = '" + str + "', Freeze = 1 WHERE StudentID = " + txtID.getText() +";");
			else	
				cc.executeQuery("UPDATE student SET StatusMembership = '" + str + "', Freeze = 0 WHERE StudentID = " + txtID.getText() +";");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Student '" + txtID.getText() + "' status were updated Successfully!");
			alert.show();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("Please look up a student ID first.");
			alert.show();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	//	cc = new ClientConsole(host, DEFAULT_PORT);
		setStatusComboBox();
		txtName.setEditable(false);
	}

}
