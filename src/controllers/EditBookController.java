package controllers;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EditBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtEdition;

    @FXML
    private TextField txtPrintYear;

    @FXML
    private TextField txtSubject;

    @FXML
    private ImageView imgBack;

    @FXML
    private Button btnEditBook;

    @FXML
    private TextField txtCatalog;

    @FXML
    private TextField txtCopies;

    @FXML
    private TextField txtShelf;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Button btnBrowsePath;

    @FXML
    private TextField txtPath;

    @FXML
    void btnBrowsePathPressed(ActionEvent event) {

    }

    @FXML
    void btnEditBookPressed(ActionEvent event) {

    }

    @FXML
    void imgBackClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}

