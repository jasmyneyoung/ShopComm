/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopcomm.main;

import java.util.Date;

/**
 *
 * @author jasmy
 */
public class Jobs {
    private int RO;
    private String name;
    private String vehicle;
    private Date date;

    

    public Jobs(int RO, String name, String vehicle, Date date) {
        this.RO = RO;
        this.name = name;
        this.vehicle = vehicle;
        this.date = date;
    }
    
    public int getRO() {
        return RO;
    }

    public String getName() {
        return name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public Date getDate() {
        return date;
    }
   
    public String toString(){
       return "order: " + RO + " name: " + name;
    }
}
