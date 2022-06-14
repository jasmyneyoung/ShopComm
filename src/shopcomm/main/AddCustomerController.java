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
public class AddCustomerController implements Initializable {
    Parent root;
    Scene fxmlFile;
    Stage stage;

    JDBCConnector jdbc;
    
    String company;
    String firstName;
    String lastName;
    String phone;
        
    @FXML
    private TextField firstNameTxt;
    @FXML
    private Button enterBtn;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField companyTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void enterCustomer(ActionEvent event) {
        company = companyTxt.getText();
        firstName = firstNameTxt.getText();
        lastName = lastNameTxt.getText();
        phone = phoneTxt.getText();
        
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        String query = "INSERT INTO Customer (company,firstName,lastName,phone) VALUES(?,?,?,?)";
        
        
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, company);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phone);
            
            statement.executeUpdate();
            
        }catch(SQLException e){}
        
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
