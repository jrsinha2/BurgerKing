package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.ConnectionUtil;

public class PaymentController implements Initializable{

    @FXML
    private JFXButton btnMainMenu1;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Label lbl_payment;

    @FXML
    private JFXTextField txt_paymentno;

    @FXML
    private JFXTextField txt_subtotal;

    

    @FXML
    private JFXTextField txt_cash;

    @FXML
    private Label lbl_balance;
    
    private double subtotal;
    private String OrderID;
    private String CustID;
    private LocalDate OrderDate;
    private ArrayList<OrderDetails> orderlist;
    private String OrderStatus;
    
    @FXML
    void saveOrder(ActionEvent event) {
        Order o = new Order(OrderID,CustID,OrderDate,OrderStatus,orderlist);
        Payment p = new Payment(txt_paymentno.getText(),OrderID,Double.parseDouble(txt_subtotal.getText()));
        boolean ordersuccess = Order.addDB(o);
        boolean paymentsuccess = Payment.addDB(p);
        System.out.println(ordersuccess+" " + paymentsuccess);
        if(ordersuccess && paymentsuccess) {
                
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Saved!");
                alert.setHeaderText("Order added successfully");
                alert.setContentText("Order Details saved!");

                alert.showAndWait();
                System.out.println("Order added");
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MakeOrder.fxml"));
                    Node source = (Node)event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                    stage = new Stage();
                    Parent root = loader.load();
                    stage.setScene(new Scene(root));
                    stage.show();
                }catch(IOException e){
                    e.printStackTrace();
                }
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Addition Error!");
                alert.setHeaderText("addition Failed!");
                alert.setContentText("Fill the correct details!");

                alert.showAndWait();
                System.out.println("Wrong details");
        }
    }

    @FXML
    void setBalance(ActionEvent event) {
        double cash = Double.parseDouble(txt_cash.getText());
        double balance = subtotal - cash;
        lbl_balance.setText(String.valueOf(balance));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPaymentID();
        OrderStatus  = "PAID";
    }
    private void setPaymentID() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nID;
        String oID = null;
        String sqlSearch = "SELECT PaymentID FROM payment ORDER BY PaymentID DESC LIMIT 1";
        try {
            pst = conn.prepareStatement(sqlSearch);
            rs = pst.executeQuery();
            if(rs.next()) {
                oID = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(oID!=null) {
            String txtID = "";
            for(int i =1;i<oID.length();i++) {
                txtID+=oID.charAt(i);
            }
            int numberID = Integer.parseInt(txtID);
            if(numberID<9) {
                nID = "P"+"00"+(numberID+1);
            }
            else if(numberID<99) {
                nID = "P" +"0" +(numberID+1);
            }
            else if(numberID < 999) {
                nID = "P" +(numberID +1);
            }
            else {
                nID = "P001";
            }
        }
        else
        {
            nID = "P001";
        }
        txt_paymentno.setText(nID);
    }
    
    public void setSubTotal(String Total) {
        this.subtotal = Double.parseDouble(Total);
        txt_subtotal.setText(Total);
    }
    void setTxt(String OID, double price,String CID,LocalDate date,ArrayList<OrderDetails> list) {
        this.OrderID = OID;
        this.CustID = CID;
        this.subtotal = price;
        this.OrderDate = date;
        this.orderlist = list;
        
    }
}
