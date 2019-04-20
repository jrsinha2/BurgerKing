/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgerking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class Item {

    
    private String ItemCode;
    private String ItemDescription;
    private String ItemType;
    private double ItemPrice;
    private String ItemImage;
    public Item(String ID,String Des,double Price,String Type,String image) {
        this.ItemCode = ID;
        this.ItemDescription = Des;
        this.ItemImage = image;
        this.ItemPrice = Price;
        this.ItemType = Type;
    }
    
    public String getCode() {
        return this.ItemCode;
    }
    
    public String getDescription() {
        return this.ItemDescription;
    }
    
    public String getType() {
        return this.ItemType;
    }
    
    public double getPrice() {
        return this.ItemPrice;
    }
    
    public String getImage() {
        return this.ItemImage;
    }
    
    public static Item searchDB(String ItemCode) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        Item i = null;
        String searchSql = "SELECT * FROM item WHERE ItemID = ?";
        ResultSet rs = null;
        try {
            
            
            pst = conn.prepareStatement(searchSql);
            pst.setString(1, ItemCode);
            rs = pst.executeQuery();
            if(rs.next()) {
               i = new Item(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5));
               return i;
            }
        } catch (SQLException ex) {
            return i;
        }
        return i;
    }
    
    public static boolean addDB(Item i) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        String insertSql = "INSERT into item VALUES(?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(insertSql);
            pst.setString(1,i.ItemCode);
            pst.setString(2,i.ItemDescription);
            pst.setDouble(3,i.ItemPrice);
            pst.setString(4,i.ItemType);
            pst.setString(5, i.ItemImage);
            
            if(pst.executeUpdate()>0)
                return true;
            else
                return false;
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static ArrayList<Item> getItems() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        Item i = null;
        String searchSql = "SELECT * FROM item";
        ArrayList<Item> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            
            
            pst = conn.prepareStatement(searchSql);
            
            rs = pst.executeQuery();
            while(rs.next()) {
               i = new Item(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5));
               list.add(i);
            }
            return list;
        } catch (SQLException ex) {
            return list;
        }
        
    }
}
