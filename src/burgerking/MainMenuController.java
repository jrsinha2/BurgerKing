package burgerking;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import utils.UserSession;

public class MainMenuController implements Initializable{

    


    @FXML
    private JFXButton btn_LogOut;
    @FXML
    private JFXButton btn_MakeOrder;
    @FXML
    private AnchorPane rootMainMenu;
    @FXML
    private JFXButton btnAllOrders;
    @FXML
    private JFXButton btn_Customer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    void btnLogOut(ActionEvent event) {
        UserSession.cleanUserSession();
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            rootMainMenu.getChildren().setAll(login);
        } catch (IOException ex) {
            System.err.println("Cannot load login.fxml");
        }
    }

    @FXML
    void btnMakeOrder(ActionEvent event) {
        try {
            AnchorPane makeOrder  = FXMLLoader.load(getClass().getResource("MakeOrder.fxml"));
            rootMainMenu.getChildren().setAll(makeOrder);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void btnPendingOrders(ActionEvent event) {
        
    }

    @FXML
    void setBtnViewAllOrders(ActionEvent event) {
        try {
            AnchorPane makeOrder  = FXMLLoader.load(getClass().getResource("ViewAllOrders.fxml"));
            rootMainMenu.getChildren().setAll(makeOrder);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void manageCustomer(ActionEvent event) {
        try {
            AnchorPane makeOrder  = FXMLLoader.load(getClass().getResource("ManageCustomers.fxml"));
            rootMainMenu.getChildren().setAll(makeOrder);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }

    



}
