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

public class MainMenuAdminController implements Initializable{

    @FXML
    private AnchorPane rootMainMenu;


    @FXML
    private JFXButton btn_Item;


    @FXML
    private JFXButton btn_LogOut;
    @FXML
    private JFXButton btn_users;

    @FXML
    void btnLogOut(ActionEvent event) {
        UserSession.cleanUserSession();
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("Login.fxml"));
            rootMainMenu.getChildren().setAll(login);
        }
        catch (IOException ex) {
            
            
        }
    }



    @FXML
    private void manageItem(ActionEvent event) {
        try {
            AnchorPane login = FXMLLoader.load(getClass().getResource("ManageItem.fxml"));
            rootMainMenu.getChildren().setAll(login);
        }
        catch (IOException ex) {
            
            
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
    }

    @FXML
    private void manageUsers(ActionEvent event) {
    }

}
