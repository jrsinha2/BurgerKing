package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class ManageItemController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_LogOut;

    @FXML
    private JFXButton btn_MainMenu;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemDesc;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXTextField txtImage;

    @FXML
    private JFXButton btnSearchtem;

    @FXML
    private JFXButton btn_Delete;

    @FXML
    private JFXComboBox<String> cbxType;

    

    @FXML
    private TableColumn<ItemTable,String> tblCode;

    @FXML
    private TableColumn<ItemTable,String> tblDescription;

    @FXML
    private TableColumn<ItemTable,Double> tblPrice;


    
    @FXML
    private JFXButton btnItems;
    @FXML
    private TableColumn<ItemTable,String> tblType;
    @FXML
    private TableColumn<ItemTable,String> tblImage;
    @FXML
    private TableView<ItemTable> tblItems;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbxType.getItems().addAll("Burger","Shakes");
        genItemCode();
        getItems();
        
    
    }
    @FXML
    void SearchItem(ActionEvent event) {
        try {
            String ICode = txtItemCode.getText();
            String IDes = txtItemDesc.getText();
            
            //String sql = "SELECT * FROM item WHERE ItemDes LIKE ? or ItemID = ?";
            String sql = "SELECT * FROM item WHERE ItemID = ?";
            Connection conn = ConnectionUtil.conDB();
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            //pst.setString(1,"%"+IDes+"%");
            //pst.setString(2,ICode);
            pst.setString(1,ICode);
            rs = pst.executeQuery();
            /*ObservableList<ItemTable> dynamicList = FXCollections.observableArrayList();
            
            tblCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
            tblDescription.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
            tblPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
            tblType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
            tblImage.setCellValueFactory(new PropertyValueFactory<>("ItemImage"));
        
            tblItems.setItems(dynamicList);
            
            while(rs.next()) {
                ItemTable i = new ItemTable(rs.getString(1),rs.getString(2),Double.parseDouble(rs.getString(3)),rs.getString(4),rs.getString(5));
                
                dynamicList.add(i);
            }
            */
            if(rs.next()) {
                txtItemCode.setText(rs.getString(1));
                txtItemDesc.setText(rs.getString(2));
                txtPrice.setText(rs.getString(3));
                txtImage.setText(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void UpdateItem(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {
        
        
        Item i = new Item(txtItemCode.getText(),txtItemDesc.getText(),Double.parseDouble(txtPrice.getText()),(String)cbxType.getValue(),txtImage.getText());
        if(Item.addDB(i)) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Added!");
                alert.setHeaderText("Item added successfully");
                alert.setContentText("Details added!");

                alert.showAndWait();
                System.out.println("item added");
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
            AnchorPane login = FXMLLoader.load(getClass().getResource("MainMenuAdmin.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            Logger.getLogger(ManageItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void deleteItem(ActionEvent event) {

    }

    public void genItemCode() {
        Connection conn = ConnectionUtil.conDB();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String nID;
        String oID = null;
        String sqlSearch = "SELECT ItemID FROM item ORDER BY ItemID DESC LIMIT 1";
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
                nID = "I"+"00"+(numberID+1);
            }
            else if(numberID<99) {
                nID = "I" +"0" +(numberID+1);
            }
            else if(numberID < 999) {
                nID = "I" +(numberID +1);
            }
            else {
                nID = "I001";
            }
        }
        else
        {
            nID = "I001";
        }
        txtItemCode.setText(nID);
    }

    


    @FXML
    public void getAllItems(ActionEvent event) {
        getItems();
    }
    
    private void getItems() {
        ObservableList<ItemTable> list = FXCollections.observableArrayList();
        tblCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        tblDescription.setCellValueFactory(new PropertyValueFactory<>("ItemDescription"));
        tblPrice.setCellValueFactory(new PropertyValueFactory<>("ItemPrice"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("ItemType"));
        tblImage.setCellValueFactory(new PropertyValueFactory<>("ItemImage"));
        tblItems.setItems(list);
        
        ArrayList<Item> items = null;
        items = Item.getItems();
        for (Item i : items) {
            ItemTable itm = new ItemTable(i.getCode(),i.getDescription(),i.getPrice(),i.getType(),i.getType());
            list.add(itm);
        }
    }

}
