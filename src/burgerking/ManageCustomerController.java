package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ConnectionUtil;
import utils.UserSession;

public class ManageCustomerController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_LogOut;

    @FXML
    private JFXButton btn_MainMenu;

    @FXML
    private JFXTextField txtCustID;

    @FXML
    private JFXTextField txtCustName;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btn_Delete;

    private TableView<Customer> tblItems;

    @FXML
    private TableColumn<Customer,String> tblID;

    @FXML
    private TableColumn<Customer,String> tblName;

    @FXML
    private TableColumn<Customer,String> tblContact;

    @FXML
    private TableColumn<Customer,String> tblAddress;

    @FXML
    private JFXButton btnCustomers;
    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    void SearchCustomer(ActionEvent event) {

    }

    @FXML
    void UpdateCustomer(ActionEvent event) {

    }

    @FXML
    void addCustomer(ActionEvent event) {
        Customer c = new Customer(txtCustID.getText(),txtCustName.getText(),txtContact.getText(),txtAddress.getText());
        if(Customer.addinDB(c)) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Added!");
                alert.setHeaderText("added successfully");
                alert.setContentText("Details Added!");

                alert.showAndWait();
                System.out.println("customer added");
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
    void btnLogOut(ActionEvent event) {
        UserSession.cleanUserSession();
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnMainMenu(ActionEvent event) {
        
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteCustomer(ActionEvent event) {

    }

    @FXML
    void getAllCustomers(ActionEvent event) {
        getCustomerslist();
    }

        public void genCustomerCode() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nID;
        String oID = null;
        String sqlSearch = "SELECT CustID FROM customer ORDER BY CustID DESC LIMIT 1";
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
                nID = "C"+"00"+(numberID+1);
            }
            else if(numberID<99) {
                nID = "C" +"0" +(numberID+1);
            }
            else if(numberID < 999) {
                nID = "C" +(numberID +1);
            }
            else {
                nID = "C001";
            }
        }
        else
        {
            nID = "C001";
        }
        txtCustID.setText(nID);
    }
    private void getCustomerslist() {
        ObservableList<Customer> list = FXCollections.observableArrayList();
        tblID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblContact.setCellValueFactory(new PropertyValueFactory<>("contactno"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomers.setItems(list);
        
        ArrayList<Customer> customers = null;
        customers = Customer.getCustomers();
        for (Customer c : customers) {
            Customer cus = new Customer(c.getID(),c.getName(),c.getContact(),c.getAddress());
            list.add(cus);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genCustomerCode();
    }
}
