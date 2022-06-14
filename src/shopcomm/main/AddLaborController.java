/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

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
public class AddLaborController implements Initializable {

    Parent root;
    Scene fxmlFile;
    Stage stage;

    JDBCConnector jdbc;
    
    int orderNumber;
    String description;
    int hours;
    float price;
    
    @FXML
    private Button enterBtn;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField hoursTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField orderTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enterLabor(ActionEvent event){
        orderNumber = Integer.valueOf(orderTxt.getText());
        description = descriptionTxt.getText();
        hours = Integer.valueOf(hoursTxt.getText());
        price = Float.valueOf(priceTxt.getText());
        
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        String query = "INSERT INTO labor (orderNumber,hours,description,price) VALUES(?,?,?,?)";
        
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderNumber);
            statement.setInt(2, hours);
            statement.setString(3, description);
            statement.setFloat(4, price);
            
            statement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("caught exception in labor input");
        }
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
