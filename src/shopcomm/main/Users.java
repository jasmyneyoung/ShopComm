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
public class Users {
    private String firstName;
    private String lastName;
    private String username;
    private String type;

    

    public Users(String userName, String firstName, String lastName, String type) {
        this.username = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }
}
