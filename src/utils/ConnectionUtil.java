/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aditya Sinha
 */
public class ConnectionUtil {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost/burgerking";
    static String user = "root";
    static String password = "";
    public static Connection conDB(){
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url,user,password);
            return con;
        }
        catch(ClassNotFoundException | SQLException ex) {
            System.err.println("ConnectionUtil: "+ex.getMessage());
            return null;
        }
    }
}
