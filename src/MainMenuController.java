package burgerking;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JFXButton btn_PendingOrders;
    @FXML
    private AnchorPane rootMainMenu;
    @FXML
    private JFXButton btnAllOrders;

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

    @FXML
    void btnPendingOrders(ActionEvent event) {
        
    }

    @FXML
    void setBtnViewAllOrders(ActionEvent event) {

    }

    



}
