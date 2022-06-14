/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.io.IOException;
import java.lang.Integer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
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
public class OpenJobsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Scene fxmlFile;
    Parent root;
    Stage window;
    
    @FXML
    private AnchorPane userAnchorPane;
    
    @FXML
    private Button homeBtn;
    
    @FXML
    private TableView<Jobs> jobs;
    
    JDBCConnector jdbc;
    @FXML
    private TableColumn<Jobs, Integer> roCOL;
    @FXML
    private TableColumn<Jobs, String> nameCOL;
    @FXML
    private TableColumn<Jobs, String> vehicleCOL;
    @FXML
    private TableColumn<Jobs, String> dateCOL;
    
    @FXML
    private Button addBtn;
   
    //RO View Table
    @FXML
    private Button selectJob;
    @FXML
    private TableView<RO> jobView;
    @FXML
    private TableColumn<RO, Integer> partCOL;
    @FXML
    private TableColumn<RO, String> descCOL;
    @FXML
    private TableColumn<RO, Integer> qtyCOL;
    @FXML
    private TableColumn<RO, Float> priceCOL;
    @FXML
    private Button addLaborBTN;
    @FXML
    private Button addPartBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       jdbc = new JDBCConnector();
       showJobs();
       
        
    }    
                        
    @FXML
    private void goHome(ActionEvent event) throws IOException {
        System.out.println("going back to home page");
        Pane pane = FXMLLoader.load(getClass().getResource("Home.fxml"));
        userAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void addJob(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddJob.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add A New Job");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }
    
    public void showJobs(){
        ObservableList<Jobs> list = getJobsList();
        
        roCOL.setCellValueFactory(new PropertyValueFactory<>("RO"));
        nameCOL.setCellValueFactory(new PropertyValueFactory<>("Name"));
        vehicleCOL.setCellValueFactory(new PropertyValueFactory<>("Vehicle"));
        dateCOL.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        jobs.setItems(list);
    }
    
    private ObservableList<Jobs> getJobsList(){
        ObservableList<Jobs> jobsList = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        String query = "SELECT orders.id, customer.firstName, vehicle.make, orders.date FROM orders INNER JOIN customer on orders.customerID=customer.customerID INNER JOIN vehicle on orders.vehicleID=vehicle.vehicleID;";
        Statement statement;
        ResultSet rs;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            Jobs jobs;
            while(rs.next()){
                int orderID = rs.getInt("orders.id");
                jobs = new Jobs(rs.getInt("orders.id"), rs.getString("customer.firstName"),rs.getString("vehicle.make"),rs.getDate("orders.date"));
                jobsList.add(jobs);
                
            }
        }catch(Exception e){
            e.getMessage();
        }
        return jobsList;
    }    

    @FXML
    private void initialize(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("OpenJobs.fxml"));
        userAnchorPane.getChildren().setAll(pane);
    }

    @FXML
    private void selectJob(ActionEvent event) throws IOException {
        
        TableViewSelectionModel selectionModel = jobs.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Integer> selectedIndices = selectionModel.getSelectedIndices();
        Jobs selectedJob = (Jobs) selectionModel.getSelectedItem();
        int orderID = selectedJob.getRO();
        System.out.println(orderID);
        ObservableList<RO> list = getOrderLines(orderID);
        
        partCOL.setCellValueFactory(new PropertyValueFactory<>("partNumber"));
        descCOL.setCellValueFactory(new PropertyValueFactory<>("description"));
        qtyCOL.setCellValueFactory(new PropertyValueFactory<>("qty"));
        priceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        jobView.setItems(list);
    }
     private ObservableList<RO> getOrderLines(int orderID){
        ObservableList<RO> orderLines = FXCollections.observableArrayList();
        Connection conn = jdbc.getConnection();
        String partsQuery = "SELECT partNumber, description, quantity, price FROM parts WHERE orderNumber = " + orderID;
        Statement statement;
        ResultSet rs;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(partsQuery);
            RO line;
            while(rs.next()){
                line = new RO(rs.getString("partNumber"), rs.getString("description"),rs.getInt("quantity"),rs.getFloat("price"));
                orderLines.add(line);
            }
        }catch(Exception e){
            e.getMessage();
        }
        System.out.println("whoop here we are");
        String laborQuery = "SELECT description, hours, price FROM labor WHERE orderNumber = " + orderID;
        Statement statement2;
        ResultSet rs2;
        try {
            statement2 = conn.createStatement();
            rs2 = statement2.executeQuery(laborQuery);
            RO line;
            while(rs2.next()){
                line = new RO("LABOR", rs2.getString("description"),rs2.getInt("hours"),rs2.getFloat("price"));
                orderLines.add(line);
            } 
        }catch(Exception e){
           System.out.println("caught an exception");
           e.getMessage();
        }
        return orderLines;
    }    

    @FXML
    private void addLabor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddLabor.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Labor");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }

    @FXML
    private void addPart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Part");
        window.setResizable(false);
        window.setAlwaysOnTop(true);
        window.show();
    }
}
