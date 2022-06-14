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
public class RO {
    private String partNumber;
    private String description;
    private int qty;
    private float price;

    public RO(String partNumber, String description, int qty, float price) {
        this.partNumber = partNumber;
        this.description = description;
        this.qty = qty;
        this.price = price;
    }
    
     public String getPartNumber() {
        return partNumber;
    }

    public String getDescription() {
        return description;
    }

    public int getQty() {
        return qty;
    }
    
    public float getPrice(){
        return price;
    }

}
