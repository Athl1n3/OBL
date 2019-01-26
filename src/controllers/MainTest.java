package controllers;
 
import client.ClientConnection;
import entities.Account;
import entities.LibrarianAccount;
import entities.UserAccount;
import entities.UserAccount.accountStatus;
import javafx.application.Application;
import javafx.stage.Stage;
 
public class MainTest extends Application {
 
  //  Account acc = new UserAccount(315022517, "Adam", "Mahameed", "adam.spark199@gmail.com", "0502612212", 1, "adam",
    //        "123456", accountStatus.Active, 0, 0);
    //Account lib = new LibrarianAccount(315022517, "Adam", "Mahameed", "adam.spark199@gmail.com", "0502612212", 2,
      //      "adam", "123456", 0);// TRY USING LibrarianAccount and look for result
 
    public static void main(String[] args) { // TODO Auto-generated method stub
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseController.InitiateClient(new ClientConnection());
        primaryStage.setOnCloseRequest(event -> DatabaseController.terminateClient());
 
        MainFormController aFrame = new MainFormController();
        aFrame.start(primaryStage);
 
        /*
         * UsersManagementController aFrame = new UsersManagementController(); //
         * aFrame.start(primaryStage, lib);
         */
 
    }
}