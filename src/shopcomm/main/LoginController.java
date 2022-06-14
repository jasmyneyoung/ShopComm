/*
 * LoginController.java
 * Jasmyne Young
 * PROJECT: ShopComm
 * last updated: 05/06/2022
 */
package shopcomm.main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Jasmyne Young
 */
public class LoginController implements Initializable {
    int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
    int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
    
    //the following are objects used in the FXML to send data to the program
    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField passwordInput;
    
    @FXML
    private ImageView logo;
    
    @FXML
    private Text incorrect;
    
    @FXML
    private Button loginBtn;
    
    JDBCConnector jdbc;
    int userCount;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("LoginController.java opened"); //prints name of file for tracking4
        jdbc = new JDBCConnector();
        
        //this portion of code creates the connection to the database
        String DATABASE_URL = "jdbc:mysql://localhost/shopcomm?autoReconnect=true&useSSL=false";
        try{
           Connection conn = DriverManager.getConnection(DATABASE_URL,"root", "Jazz2016");
        }catch(SQLException e){
           System.out.println("caught SQLException in LoginController.java"); 
            //print to indicate where the error was thrown
       }
    }
    
    @FXML
    private void handleLogin(ActionEvent event) throws IOException{
       
        if(event.getSource()== loginBtn){
            String usernameIn = usernameInput.getText();
            String passwordIn = passwordInput.getText();
            System.out.println(usernameIn);
            System.out.println(passwordIn);
            
            /*this block of code performs a select query to check for 
            login information in the database */
            Connection conn = jdbc.getConnection();
            String userQuery = "SELECT COUNT(distinct username, password) as total FROM user WHERE username = '" + usernameIn + "' and password = '" + passwordIn + "';";
            Statement statement;
            ResultSet rs;
            try {
            statement = conn.createStatement();
            rs = statement.executeQuery(userQuery);
                while(rs.next()){
                    userCount = rs.getInt("total");
                    System.out.println("got the result set");  
                }
            }catch(Exception e){
                System.out.println(e);
            }
            
            if(userCount > 0) {
                System.out.println("logged in!");
                Stage loginStage = (Stage) loginBtn.getScene().getWindow();
                loginStage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.setTitle("ShopComm");
                primaryStage.setMaximized(true);
                primaryStage.show();
            } else {
                usernameInput.clear();
                passwordInput.clear();
                incorrect.setText("incorrect username or password");
                System.out.println("incorrect username or password");
            }
        }
    }     
}
