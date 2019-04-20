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
public class OrderTable {
    private String itemCode;
    private String itemDes;
    private double itemPrice;
    private int itemQty;
    private double itemAmt;
    
    public OrderTable(String iC,String iD,double iP,int iQ,double amt) {
        this.itemCode = iC;
        this.itemDes = iD;
        this.itemPrice = iP;
        this.itemQty = iQ;
        this.itemAmt = amt;
    }
    public String getItemCode() {
        return itemCode;
    }
    
    public String getItemDes() {
        return itemDes;
    }
    
    public double getItemPrice() {
        return itemPrice;
    }
    
    public int getItemQty() {
        return itemQty;
    }
    
    public double getAmt() {
        return itemAmt;
    }

    void printContent() {
        System.out.println(this.itemAmt);
    }
    
}
