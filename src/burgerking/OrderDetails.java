/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgerking;

/**
 *
 * @author Aditya Sinha
 */
public class OrderDetails {
    private String OrderID;
    private String ItemCode;
    private double ItemPrice;
    private int qty;
    
    public OrderDetails(String OID,String IID,double p,int q) {
        this.OrderID = OID;
        this.ItemCode = IID;
        this.ItemPrice = p;
        this.qty = q;
    }
    
    public String getOrderID() {
        return this.OrderID;
    }
    
    public String getItemCode() {
        return this.ItemCode;
    }
    public double getItemPrice() {
        return this.ItemPrice;
    }
    public int getItemQty() {
        return this.qty;
    }
}
