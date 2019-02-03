package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entities.LibrarianAccount;
import entities.ManagerAccount;
import entities.ManualExtend;
import entities.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ManualExtendNotificationController {

	@FXML
	private ImageView imgBack;

	@FXML
	private TableView<ManualExtend> tableView;

	@FXML
	private TableColumn<ManualExtend, Integer> colBookID;

	@FXML
	private TableColumn<ManualExtend, Integer> colUserID;

	@FXML
	private TableColumn<ManualExtend, String> colWorkerName;

	@FXML
	private TableColumn<ManualExtend, LocalDate> colExtendDate;

	@FXML
	private TableColumn<ManualExtend, LocalDate> colDueDate;

	private ObservableList<ManualExtend> extendOList;
	
	/**
	 * close current stage
	 * @param event
	 */
	@FXML
	void imgBackClicked(MouseEvent event) {
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close(); // Close stage

	}

	@FXML
	void initialize() {
		ArrayList<ManualExtend> extendList;
		extendList = DatabaseController.getManualExtendList();
		extendOList = FXCollections.observableArrayList(extendList);// notifications List
		colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
		colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
		colWorkerName.setCellValueFactory(new PropertyValueFactory<>("workerName"));
		colExtendDate.setCellValueFactory(new PropertyValueFactory<>("extendDate"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		tableView.setItems(extendOList);
	}
}
