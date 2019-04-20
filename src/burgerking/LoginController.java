package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import utils.UserSession;
import utils.ConnectionUtil;

public class LoginController implements Initializable{

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView wallpaper;

    @FXML
    private ImageView logo;

    @FXML
    private Button btn_register;

    @FXML
    private Label lbl;

    @FXML
    private AnchorPane loginpane;

    @FXML
    private ImageView username_icon;

    @FXML
    private ImageView password_icon;

    @FXML
    private Button btn_login;

    @FXML
    private JFXButton forgot_btn;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXPasswordField txt_password;
    
    
    //
    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    
    public LoginController() {
        conn = ConnectionUtil.conDB();
   }
    
      @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @FXML
    private void btnLogin(ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();
        boolean validate = LogIn(username,password);
        
        try {
            AnchorPane mainmenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            AnchorPane adminmenu = FXMLLoader.load(getClass().getResource("MainMenuAdmin.fxml"));
            if(validate) {
                if(username.equals("bk99")) {
                    root.getChildren().setAll(adminmenu);
                }
                else
                {   
                    root.getChildren().setAll(mainmenu);
                }
            }
            else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error!");
                alert.setHeaderText("Login Failed!");
                alert.setContentText("Username/Password does not match");

                alert.showAndWait();
                System.out.println("Wrong login details");
            }
        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        }
    
    
    

    @FXML
    private void btnForgot(ActionEvent event) {
    }

    @FXML
    private void btnRegister(ActionEvent event) {
        try {
            AnchorPane register = FXMLLoader.load(getClass().getResource("Register.fxml"));
            root.getChildren().setAll(register);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    private boolean LogIn(String enteredUsername,String enteredPassword) {
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,enteredUsername);
            preparedStatement.setString(2,enteredPassword);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                System.out.println("User doesnot exist");
                return false;
            }
            else
            {   
                //showDialog("Login Successful",null,"Successful");
                System.out.println("Login Successful");
                UserSession.createUser(enteredUsername, null);
                return true;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

  
}
