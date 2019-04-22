/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgerking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class Payment {
    private String PID;
    private double Price;
    private String orderID;
    
    public Payment(String pid,String oid,double price) {
        this.PID = pid;
        this.orderID = oid;
        this.Price = price;
    }
    
    public static boolean addDB(Payment p) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        String insertSql = "INSERT into payment VALUES(?,?,?)";
        try {
            pst = conn.prepareStatement(insertSql);
            pst.setString(1,p.PID);
            pst.setString(2,p.orderID);
            pst.setDouble(3,p.Price);
            
            if(pst.executeUpdate()>0)
                return true;
            else
                return false;
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
