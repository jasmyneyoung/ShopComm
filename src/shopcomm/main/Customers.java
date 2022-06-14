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
public class Customers{
    private int customerID;
    private String firstName;
    private String lastName;
    private String username;
    private String company;
    private String phoneNumber;

    

    public Customers(int customerID, String firstName, String lastName, String company, String phoneNumber) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.phoneNumber = phoneNumber;
    }
    
    public int getCustomerID(){
        return customerID;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCompany() {
        return company;
    }
    
    public String getPhoneNumber(){
        return phoneNumber;
    }
}
