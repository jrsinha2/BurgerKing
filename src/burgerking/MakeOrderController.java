package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.ConnectionUtil;
import utils.UserSession;

public class MakeOrderController implements Initializable{

    


    @FXML
    private JFXTextField txtOID;

    @FXML
    private JFXDatePicker txtOrderDate;

    @FXML
    private JFXTextField txtCID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtTPNo;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnCustomize;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private Text lbl_amt;

    @FXML
    private JFXButton btnRemoveItem;

    @FXML
    private JFXButton btnPayNow;

    @FXML
    private JFXButton btnDeliver;

    @FXML
    private TableView<OrderTable> tblOrder;

    @FXML
    private TableColumn<OrderTable, String> tblCode;

    @FXML
    private TableColumn<OrderTable,String> tblDescription;

    @FXML
    private TableColumn<OrderTable,Double> tblPrice;

    @FXML
    private TableColumn<OrderTable,Integer> tblQty;

    @FXML
    private TableColumn<OrderTable,Double> tblAmount;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btn_LogOut;
    @FXML
    private JFXButton btn_MainMenu;
    
    private final ObservableList<OrderTable> list = FXCollections.observableArrayList();
    private ArrayList<OrderDetails> orderDetailList = new ArrayList<>();
    @FXML
    private Text lbl_total;
    private double totalAmount = 0;
    
    @FXML
    private JFXButton btnSearchtem;
    
    /**
     * 
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        setOrderID();
        
    }

    @FXML
    void addCustomer(ActionEvent event) {
        String cid = txtCID.getText();
        String cname = txtName.getText();
        String contact = txtTPNo.getText();
        String address = txtAddress.getText();
        Customer c = new Customer(cid, cname, contact, address);
        if(Customer.addinDB(c)){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added!");
                alert.setHeaderText("Customer added successfully");
                alert.setContentText("Details added!");

                alert.showAndWait();
                System.out.println("Customer added");
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
    void addItem(ActionEvent event) {
        tblCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("itemDes"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        tblQty.setCellValueFactory(new PropertyValueFactory<>("itemQty"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("itemAmt"));
        tblOrder.setItems(list);
        
        OrderTable ot;
        String iCode = txtItemCode.getText();
        String iDes = txtItemDesc.getText();
        double price = Double.parseDouble(txtPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double amt = Double.parseDouble(lbl_amt.getText());
        ot = new OrderTable(iCode,iDes,price,qty,amt);
        ot.printContent();
        list.add(ot);
        
        OrderDetails od = new OrderDetails(txtOID.getText(),iCode,price,qty);
        orderDetailList.add(od);
        
        totalAmount+=amt;
        lbl_total.setText(String.valueOf(totalAmount));
        
        txtItemCode.setText(null);
        txtItemDesc.setText(null);
        txtPrice.setText(null);
        txtQty.setText(null);
        lbl_amt.setText(null);
        
        btnCustomize.setDisable(false);
        txtItemCode.requestFocus();
        
        
    }

    @FXML
    void btnDelivery(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Delivery.fxml"));
            Node source = (Node)event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            DeliveryController cont = loader.getController();
            cont.setTxt(txtOID.getText(),Double.parseDouble(lbl_total.getText()),txtCID.getText(),txtOrderDate.getValue(),orderDetailList );
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnLogOut(ActionEvent event) {
        UserSession.cleanUserSession();
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void btnMainMenu(ActionEvent event) {
        try {
            AnchorPane mainmenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            root.getChildren().setAll(mainmenu);
        } catch (IOException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnPayment(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            Node source = (Node)event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            PaymentController cont = loader.getController();
            cont.setTxt(txtOID.getText(),Double.parseDouble(lbl_total.getText()),txtCID.getText(),txtOrderDate.getValue(),orderDetailList );
            cont.setSubTotal(lbl_total.getText());
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @FXML
    void btnRemoveItem(ActionEvent event) {
        int removeIndex=tblOrder.getSelectionModel().getSelectedIndex();
        tblOrder.getItems().remove(removeIndex);
        orderDetailList.remove(removeIndex);
        Double removePrice =orderDetailList.get(removeIndex).getItemPrice();
        int remove_Qty=orderDetailList.get(removeIndex).getItemQty();
        Double removeAmount=removePrice*remove_Qty;
        totalAmount-=removeAmount;
        if ((totalAmount !=0)) {
            lbl_total.setText(String.valueOf(totalAmount));
        } else {
            lbl_total.setText(String.valueOf(0));

        }
    }

    @FXML
    void customizeOrder(ActionEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomizeOrder.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            String amountPrice = lbl_amt.getText();
            String itemQty = txtQty.getText();
            String itemCode = txtItemCode.getText();
            
            CustomizeOrderController cont = loader.getController();
            cont.setTxt(amountPrice,itemQty,itemCode);
            
            cont.setItemCode(itemCode);
            
            
            
            ((Node)(event.getSource())).getScene().getWindow();
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(MakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void searchCustomer(ActionEvent event) {
        String searchcontact = txtTPNo.getText();
        Customer c = Customer.searchDB(searchcontact);
        if(c==null) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Addition Error!");
            alert.setHeaderText("addition Failed!");
            alert.setContentText("Fill the correct details!");

            alert.showAndWait();
            System.out.println("Wrong details");
        }
        else {
            txtAddress.setText(c.getAddress());
            txtName.setText(c.getName());
            txtTPNo.setText(c.getContact());
            txtCID.setText(c.getID());
            txtItemCode.requestFocus();
        }
    }
    private void setDate() {
        txtOrderDate.setValue(LocalDate.now());
        
    }
    private void setOrderID() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nID;
        String oID = null;
        String sqlSearch = "SELECT OrderID FROM orders ORDER BY OrderID DESC LIMIT 1";
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
                nID = "O"+"00"+(numberID+1);
            }
            else if(numberID<99) {
                nID = "O" +"0" +(numberID+1);
            }
            else if(numberID < 999) {
                nID = "O" +(numberID +1);
            }
            else {
                nID = "O001";
            }
        }
        else
        {
            nID = "O001";
        }
        txtOID.setText(nID);
        
    }

    @FXML
    private void setAmount(ActionEvent event) {
        double itemPrice = Double.parseDouble(txtPrice.getText());
        int qty  = Integer.parseInt(txtQty.getText());
        double amt = itemPrice * qty;
        lbl_amt.setText(String.valueOf(amt));
    
    }

    @FXML
    private void SearchItem(ActionEvent event) {
        String icode = txtItemCode.getText();
        Item i = Item.searchDB(icode);
        txtItemCode.setText(icode);
        txtItemDesc.setText(i.getDescription());
        txtPrice.setText(String.valueOf(i.getPrice()));
        
    
    }
    public void setCustomizedAmnt(String amt) {
        lbl_amt.setText(amt);
    } 

    
    
    
}
