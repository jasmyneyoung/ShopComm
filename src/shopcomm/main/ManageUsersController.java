/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class ManageUsersController implements Initializable {

    Scene fxmlFile;
    Parent root;
    Stage window;
    
    @FXML
    private AnchorPane userAnchorPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private TableView<Users> users;
    
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private TableColumn<Users, String> firstNameCOL;
    @FXML
    private TableColumn<Users, String> lastNameCOL;
    @FXML
    private TableColumn<Users, String> usernameCOL;
    @FXML
    private TableColumn<Users, String> typeCOL;
    
    JDBCConnector jdbc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       jdbc = new JDBCConnector();
       showUsers();
    }    

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        System.out.println("going back to home page");
        Pane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        userAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void addUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add A User");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }

    @FXML
    private void editUser(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EditUser.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }

    @FXML
    private void removeUser(ActionEvent event) {
    }
    
    public void showUsers(){
        
        ObservableList<Users> list = getUsersList();
        
        firstNameCOL.setCellValueFactory(new PropertyValueFactory<>("username"));
        lastNameCOL.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        usernameCOL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        typeCOL.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        users.setItems(list);
        
    }

    private ObservableList<Users> getUsersList(){
        ObservableList<Users> userList = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        
        String query = "SELECT username,firstName,lastName,type FROM user";
        Statement statement;
        ResultSet rs;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Users users;
            while(rs.next()){
                users = new Users(rs.getString("username"), rs.getString("firstName"),rs.getString("lastName"),rs.getString("type"));
                userList.add(users);
            }
        }catch(Exception e){
            System.out.println("exception caught");
        }
        return userList;
    }

    @FXML
    private void initialize(ActionEvent event) throws IOException{
        Pane pane = FXMLLoader.load(getClass().getResource("ManageUsers.fxml"));
        userAnchorPane.getChildren().setAll(pane);
    }
    
}
