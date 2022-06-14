/**
 * AddUserController.Java
 * Jasmyne Young
 * This controller contains the code to add a user to the ShopComm database.'
 * Last updated: 05/06/2022
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class AddUserController implements Initializable {
    
    Parent root;
    Scene fxmlFile;
    Stage stage;

    JDBCConnector jdbc;
    
    String username;
    String firstName;
    String lastName;
    String password;
    String type;
    
    @FXML
    private TextField usernameTxt;
    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private ComboBox<String> userTypeBox;
    ObservableList<String> options = FXCollections.observableArrayList("Manager","Tech","Parts");
    @FXML
    private Button enterBtn;
    @FXML
    private Button enterBtn1;
    @FXML
    private TextField passwordTxt;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userTypeBox.setItems(options);
    }   

    /**the enterUser function takes user input from textfields and inserts
     * them into the database using an INSERT query.
     */ 
    @FXML
    private void enterUser(ActionEvent event) throws IOException {
        /*takes user input from AddUser window*/
        username = usernameTxt.getText();
        firstName = firstNameTxt.getText();
        lastName = lastNameTxt.getText();
        password = passwordTxt.getText();
        type = userTypeBox.getValue();
        
        //creates jdbc connection (allows communication between java and sql)
        jdbc = new JDBCConnector();
        Connection connection = jdbc.getConnection();
        
        //creates the query for the INSERT. parses the result set and sends the values
        String query = "INSERT INTO user (username,firstName,lastName,password,type) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, password);
            statement.setString(5, type);
            
            statement.executeUpdate();
            
        }catch(SQLException e){}
        
        //closes the window after the insert is completed
        Node source = (Node) event.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        //reloads the AddUser screen
        root = FXMLLoader.load(getClass().getResource("AddUserPromt.fxml"));
        fxmlFile = new Scene(root);
        stage = new Stage();
        stage.setScene(fxmlFile);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add A User");
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }
}
 