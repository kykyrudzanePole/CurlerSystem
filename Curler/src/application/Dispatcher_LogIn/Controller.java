package application.Dispatcher_LogIn;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.*;

public class Controller {
    @FXML
    private TextField TextField1;
    @FXML
    private TextField TextField2;
    @FXML
    private Button button;
    @FXML
    void initialize() {
        button.setOnAction(event -> {
            String id = TextField1.getText().trim();
            String pass = TextField2.getText().trim();
            if(!id.equals("") && !pass.equals(""))
            {
                loginUser(id,pass);
            }
            else{
                System.out.println("ID and password is Empty");
                button.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/application/dispatcher_office/OfficeView.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        });
    }
public void loginUser(String id, String pass){
       Database db = new Database();
       Dispatcher dispatcher = new Dispatcher();
       dispatcher.setID_worker(id);
       dispatcher.setPassword(pass);
       ResultSet rs = db.getUser(dispatcher);
       int counter = 0;
       try {
           while (rs.next()) {
               counter++;
           }
       }
       catch (SQLException e){
           e.printStackTrace();
       }
       if(counter>=1){
           System.out.println("Well Done!");

       }
       else{
           System.out.println("Bad((!");
       }
}
}
