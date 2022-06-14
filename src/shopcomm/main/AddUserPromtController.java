/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasmy
 */

public class AddUserPromtController implements Initializable {
    Stage stage;
    Parent root;
    Scene fxmlFile;
    
    @FXML
    private Button addAnotherBtn;
    @FXML
    private Button doneBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        fxmlFile = new Scene(root);
        stage = new Stage();
        stage.setScene(fxmlFile);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add A User");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @FXML
    private void exit(ActionEvent event) throws IOException{
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
       
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
