package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class UsersManagementController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgBack;

    @FXML
    private ImageView imgUserLookup;

    @FXML
    private ImageView imgCreateAccount;

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void imgCreateAccountClicked(MouseEvent event) {

    }

    @FXML
    void imgUserLookupClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert imgBack != null : "fx:id=\"imgBack\" was not injected: check your FXML file 'UsersManagementForm.fxml'.";
        assert imgUserLookup != null : "fx:id=\"imgUserLookup\" was not injected: check your FXML file 'UsersManagementForm.fxml'.";
        assert imgCreateAccount != null : "fx:id=\"imgCreateAccount\" was not injected: check your FXML file 'UsersManagementForm.fxml'.";

    }
}
