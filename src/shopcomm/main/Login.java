/*
 * This .java file initializes the login screen. To see the login 
 * information, see the LoginController.java file. To see the UI layout
 * view the Login.fxml file. 
 * 
 * Last updated 05/02/2022
 */
package shopcomm.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jasmy
 */
public class Login extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Login.java opened"); //prints name of file for tracking
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ShopComm");
        stage.setResizable(false);
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}