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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class AddPartController implements Initializable {
    
    Parent root;
    Scene fxmlFile;
    Stage stage;

    JDBCConnector jdbc;
    
    int orderNumber;
    String partNumber;
    String description;
    int quantity;
    float price;

    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private Button enterBtn;
    @FXML
    private TextField partNumberTxt;
    @FXML
    private TextField orderTxt;
    @FXML
    private TextField qtyTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enterPart(ActionEvent event) {
        orderNumber = Integer.valueOf(orderTxt.getText());
        partNumber = partNumberTxt.getText();
        description = descriptionTxt.getText();
        quantity = Integer.valueOf(qtyTxt.getText());
        price = Float.valueOf(priceTxt.getText());
        
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        String query = "INSERT INTO parts (orderNumber,partNumber,description, quantity, price) VALUES(?,?,?,?,?)";
        
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderNumber);
            statement.setString(2, partNumber);
            statement.setString(3, description);
            statement.setInt(4, quantity);
            statement.setFloat(5, price);
            
            statement.executeUpdate();
            
        }catch(SQLException e){
            System.out.println("caught exception in labor input");
        }
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
