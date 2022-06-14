/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.io.IOException;
import static java.lang.String.valueOf;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class AddVehicleController implements Initializable {
    Parent root;
    Scene fxmlFile;
    Stage stage;

    JDBCConnector jdbc;
    
    int customerID;
    String year;
    String make;
    String model;
    String vin;
    
    @FXML
    private TextField custIDTxt;
    @FXML
    private Button enterBtn;
    @FXML
    private TextField modelTxt;
    @FXML
    private TextField vinTxt;
    @FXML
    private TextField yearTxt;
    @FXML
    private TextField makeTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void enterCustomer(ActionEvent event) throws IOException {
         /*takes user input from AddUser window*/
        String custID = custIDTxt.getText();
        float custIDint = Integer.parseInt(custID);
        customerID = Math.round(custIDint);
        year = yearTxt.getText();
        make = makeTxt.getText();
        model = modelTxt.getText();
        vin = vinTxt.getText();
        
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        String query = "INSERT INTO vehicle (customerID,year,make,model,vin) VALUES(?,?,?,?,?)";
        
        
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerID);
            statement.setString(2, year);
            statement.setString(3, make);
            statement.setString(4, model);
            statement.setString(5, vin);
            
            statement.executeUpdate();
            
        }catch(SQLException e){}
        
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
                
    }

    @FXML
    private void enterVehicle(ActionEvent event) {
    }


    
    
    
}
