/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jasmy
 */
public class CustomerInfoController implements Initializable {

    Scene fxmlFile;
    Parent root;
    Stage window;
    
    @FXML
    private TableColumn<Customers, Integer> custIDCOL;
    @FXML
    private TableColumn<Customers, String> firstNameCOL;
    @FXML
    private TableColumn<Customers, String> lastNameCOL;
    @FXML
    private TableColumn<Customers, String> compCOL;
    @FXML
    private TableColumn<Customers, String> phoneCOL;
    
    JDBCConnector jdbc;
    @FXML
    private TableView<Customers> customers;
    @FXML
    private Button addVehBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button HomeBtn;
    @FXML
    private AnchorPane customerAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.println("showing customers");
       jdbc = new JDBCConnector();
       showCustomers();
    }    
    
    public void showCustomers(){
        ObservableList<Customers> list = getCustomersList();
        
        custIDCOL.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        firstNameCOL.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCOL.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        compCOL.setCellValueFactory(new PropertyValueFactory<>("company"));
        phoneCOL.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        customers.setItems(list);
        
    }

    private ObservableList<Customers> getCustomersList(){
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        
        String query = "SELECT customerID,firstName,lastName,company,phone FROM customer";
        Statement statement;
        ResultSet rs;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Customers customers;
            while(rs.next()){
                customers = new Customers(rs.getInt("customerID"), rs.getString("firstName"),rs.getString("lastName"),rs.getString("company"),rs.getString("phone"));
                customersList.add(customers);
            }
        }catch(Exception e){
            System.out.println("exception caught");
        }
        return customersList;
    }

    @FXML
    private void addVehicle(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddVehicle.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add A Vehicle");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }

    @FXML
    private void addCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add A Customer");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        System.out.println("going back to home page");
        Pane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        customerAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void initialize(ActionEvent event) {
    }
}
