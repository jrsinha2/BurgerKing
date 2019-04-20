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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class Customer {
    private String name;
    private String id;
    private String contactno;
    private String address;
    
    public Customer(String i,String n,String c,String a) {
        this.id = i;
        this.name = n;
        this.contactno = c;
        this.address = a;
    }
    
    public static boolean addinDB(Customer c) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        String insertSql = "INSERT into customer VALUES(?,?,?,?)";
        try {
            pst = conn.prepareStatement(insertSql);
            pst.setString(1,c.id);
            pst.setString(2,c.name);
            pst.setString(3,c.contactno);
            pst.setString(4,c.address);
            
            if(pst.executeUpdate()>0)
                return true;
            else
                return false;
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static Customer searchDB(String contact)
    {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        Customer c = null;
        String searchSql = "SELECT * FROM customer WHERE CustContact = ?";
        ResultSet rs = null;
        try {
            
            
            pst = conn.prepareStatement(searchSql);
            pst.setString(1, contact);
            rs = pst.executeQuery();
            if(rs.next()) {
               c = new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
               return c;
            }
        } catch (SQLException ex) {
            return c;
        }
        return c;
   }
    
    public String getName(){
        return this.name;
    }
    public String getID() {
        return this.id;
    }
    public String getAddress() {
        return this.address;
    }
    public String getContact() {
        return this.contactno;
    }

}
