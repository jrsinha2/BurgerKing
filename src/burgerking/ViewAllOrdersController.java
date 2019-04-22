package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.UserSession;

public class ViewAllOrdersController implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_LogOut;

    @FXML
    private JFXButton btn_MainMenu;

    @FXML
    private JFXDatePicker txtOrderDate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private TableView<ViewAllOrdersTable> tblOrder;

    @FXML
    private TableColumn<ViewAllOrdersTable,String> tblOrderID;

    @FXML
    private TableColumn<ViewAllOrdersTable,String> tblCustID;

    @FXML
    private TableColumn<ViewAllOrdersTable,String> tblCustName;

    @FXML
    private TableColumn<ViewAllOrdersTable,LocalDate> tblDate;

    @FXML
    private TableColumn<ViewAllOrdersTable,Double> tblAmount;

    @FXML
    private TableColumn<ViewAllOrdersTable,String> tblStatus;

    @FXML
    void btnLogOut(ActionEvent event) {
        UserSession.cleanUserSession();
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            System.err.println("Cannot load login.fxml");
        }
    }

    @FXML
    void btnMainMenu(ActionEvent event) {
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            root.getChildren().setAll(login);
        } catch (IOException ex) {
            System.err.println("Cannot load login.fxml");
        }
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        ObservableList<ViewAllOrdersTable> list = FXCollections.observableArrayList();
        tblOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tblCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        
        tblOrder.setItems(list);
        
        ArrayList<ViewAllOrdersTable> viewList = null;
        viewList = ViewAllOrdersTable.getAllOrders();
        
        for(ViewAllOrdersTable all : viewList){
            list.add(all);
        }
        
    }

    @FXML
    private void viewOrders(ActionEvent event) {
        
        ObservableList<ViewAllOrdersTable> list = FXCollections.observableArrayList();
        LocalDate date = txtOrderDate.getValue();
        tblOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        tblCustID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tblCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        tblAmount.setCellValueFactory(new PropertyValueFactory<>("orderAmount"));
        tblStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        
        tblOrder.setItems(list);
        
        ArrayList<ViewAllOrdersTable> viewList = null;
        viewList = ViewAllOrdersTable.getOrdersatDate(date);
        
        for(ViewAllOrdersTable all : viewList){
            list.add(all);
        }
    
    }

}
