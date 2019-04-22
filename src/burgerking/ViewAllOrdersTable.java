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
import java.time.LocalDate;
import java.util.ArrayList;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class ViewAllOrdersTable {
    private String orderID;
    private String customerID;
    private String customerName;
    private LocalDate orderDate;
    private double orderAmount;
    private String orderStatus;
    
    public ViewAllOrdersTable(String oid,String cid,String cname,LocalDate date,double amt,String status) {
        this.orderID = oid;
        this.customerID = cid;
        this.customerName = cname;
        this.orderDate = date;
        this.orderAmount = amt;
        this.orderStatus = status;
    }
    
    public static ArrayList<ViewAllOrdersTable> getAllOrders() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ViewAllOrdersTable i = null;
        String searchSql = "SELECT o.OrderID,c.CustID,c.CustName,o.OrderDate,p.Price,o.OrderStatus\n" + "FROM orders o,customer c,payment p\n" + "WHERE o.CustID = c.CustID and p.OrderID = o.OrderID";
        ArrayList<ViewAllOrdersTable> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            
            
            pst = conn.prepareStatement(searchSql);
            
            rs = pst.executeQuery();
            while(rs.next()) {
               i = new ViewAllOrdersTable(rs.getString(1),rs.getString(2),rs.getString(3),LocalDate.parse(rs.getString(4)),rs.getDouble(5),rs.getString(6));
               list.add(i);
            }
            return list;
        } catch (SQLException ex) {
            return list;
        }
        
    }
    
    public static ArrayList<ViewAllOrdersTable> getOrdersatDate(LocalDate date) {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ViewAllOrdersTable i = null;
        String searchSql = "SELECT o.OrderID,c.CustID,c.CustName,o.OrderDate,p.Price,o.OrderStatus\n" + "FROM orders o,customer c,payment p\n" + "WHERE o.CustID = c.CustID and p.OrderID = o.OrderID and o.OrderDate = ?";
        ArrayList<ViewAllOrdersTable> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            
            
            pst = conn.prepareStatement(searchSql);
            pst.setString(1,String.valueOf(date));
            rs = pst.executeQuery();
            while(rs.next()) {
               i = new ViewAllOrdersTable(rs.getString(1),rs.getString(2),rs.getString(3),LocalDate.parse(rs.getString(4)),rs.getDouble(5),rs.getString(6));
               list.add(i);
            }
            return list;
        } catch (SQLException ex) {
            return list;
        }
        
    }
}

