package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UserMainController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblStatus;

    @FXML
    private ImageView imgLogout;

    @FXML
    private ImageView imgSearch;

    @FXML
    private ImageView imgHistory;

    @FXML
    private ImageView imgSettings;

    @FXML
    private ImageView imgExtendBook;

    @FXML
    void imgExtendBookClicked(MouseEvent event) {

    }

    @FXML
    void imgHistoryClicked(MouseEvent event) {

    }

    @FXML
    void imgLogoutClicked(MouseEvent event) {

    }

    @FXML
    void imgSearchClicked(MouseEvent event) {

    }

    @FXML
    void imgSettingsClicked(MouseEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblUsername.setText(DatabaseController.loggedAccount.getAccount().getFirstName());
	}
}
