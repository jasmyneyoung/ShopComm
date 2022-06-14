/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class EditUserController implements Initializable {
    
    JDBCConnector jdbc;
    
    ObservableList<String> users;
    @FXML
    private ComboBox<String> userSelectionBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        
        String select = "SELECT firstName,lastName FROM user";
        Statement statement;
        ResultSet rs;
        FXCollections usersList;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(select);
            while(rs.next()){
                if(rs.getString("firstName") != null){
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    users.add(firstName + " " + lastName);
                }  
            }
        }catch(Exception e){
            System.out.println("exception caught in select query");
        }
    }    

    @FXML
    private void selectUser(ActionEvent event) {
    }
    
}
