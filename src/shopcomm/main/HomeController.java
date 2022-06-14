/*
 * HomeController.java
 * 
 * This file initializes the Home screen of ShopComm. It is opened after the 
 * initial login screen. 
 *
 * Last updated 05/02/2022
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jasmyne Young
 */
public class HomeController implements Initializable {

    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    
    @FXML
    private Label usernameLBL;
    
    @FXML
    private Button userBtn;
    
    @FXML
    private AnchorPane rootPane;
    
    public Stage stage;
    @FXML
    private Button openJobs;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("HomeController.java opened"); //print line for tracking
    }    
    
    public void setUsernameLabel(String username){
        usernameLBL.setText(username);
    }
    
    @FXML
    private void manageUsers(ActionEvent event){
        try{
            openSelectedScene("ManageUsers.fxml");
        }catch(Exception e){
        }
    }
    
    private void openSelectedScene(String resource) throws IOException{
        System.out.println("opening window");
        Pane pane = FXMLLoader.load(getClass().getResource(resource));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void logOut (ActionEvent event){
        stage = (Stage) rootPane.getScene().getWindow();
        System.out.println("you wish this button logged you out, huh?");
        stage.close();
    }

    @FXML
    private void infoScreen(ActionEvent event) {
        
        System.out.println("customer info button pressed");
        try{
            openSelectedScene("CustomerInfo.fxml");
        }catch(Exception e){
        }
    }

    @FXML
    private void openJobs(ActionEvent event) {
        try {
            //try{
            openSelectedScene("OpenJobs.fxml");
            //}catch(Exception e){
            // e.getErrorCode();
            //}
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
