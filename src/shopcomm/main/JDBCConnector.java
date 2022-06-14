/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

/**
 *
 * @author jasmy
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCConnector {
    private static final String user = "root";
    private static final String pass = "Jazz2016";
    private static final String DATABASE_URL = "jdbc:mysql://localhost/shopcomm?autoReconnect=true&useSSL=false";
    private static final String LOGIN_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
    
   /* public boolean validate(String username, String password){
       try{
           Connection conn = DriverManager.getConnection(DATABASE_URL, user, pass);
           
           PreparedStatment preparedStatement = connection.prepareStatement(LOGIN_QUERY);
           preparedStatement.setString(1, username);
           preparedStatement.setString(2, password);
           
           System.out.println(preparedStatement);
           
           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
               return true;
           }
       }catch(SQLException e){
           System.out.println("caught SQLException");
       }
       
       return false;
    }*/
    
    public Connection getConnection(){
        try{
           Connection connection = DriverManager.getConnection(DATABASE_URL, user, pass); 
           return connection;
        }catch(SQLException e){
           System.out.println("SQLException caught");
        }
        return null;
    }
}
