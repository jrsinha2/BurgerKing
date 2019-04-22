/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgerking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class Order {
    private String orderID;
    private String customerID;
    private LocalDate orderDate;
    private String orderStatus;
    private ArrayList<OrderDetails> orderList;
    
    public Order(String oid,String cid,LocalDate date,String status,ArrayList<OrderDetails> list) {
        this.orderID = oid;
        this.customerID = cid;
        this.orderDate = date;
        this.orderStatus = status;
        this.orderList = list;
    }
    
    public static boolean addDB(Order o) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        String insertSql = "INSERT into orders VALUES(?,?,?,?)";
        try {
            pst = conn.prepareStatement(insertSql);
            pst.setString(1,o.orderID);
            pst.setString(2,o.customerID);
            pst.setString(3,String.valueOf(o.orderDate));
            pst.setString(4,o.orderStatus);
            
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
