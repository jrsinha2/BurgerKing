package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

public class RegisterController {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView wallpaper;

    @FXML
    private ImageView logo;

    @FXML
    private Label lbl;

    @FXML
    private AnchorPane loginpane;

    @FXML
    private ImageView username_icon;

    @FXML
    private ImageView password_icon;

    @FXML
    private JFXButton btn_register;

    @FXML
    private JFXTextField txt_username;

    @FXML
    private JFXPasswordField txt_password;

    @FXML
    private JFXTextField txt_contact;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private ImageView username_icon1;

    @FXML
    private JFXButton btn_login;

    Connection conn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public RegisterController() {
        conn = ConnectionUtil.conDB();
    }
    @FXML
    private void btnRegister(ActionEvent event) {
        if(signUp()) {
            try {
                AnchorPane mainmenu = FXMLLoader.load(getClass().getResource("Login.fxml"));
                root.getChildren().setAll(mainmenu);
            } catch (IOException ex) {
                Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Register Error!");
                alert.setHeaderText("Resgistration Failed!");
                alert.setContentText("Fill the correct details!");

                alert.showAndWait();
                System.out.println("Wrong details");
            }
    
    }

    @FXML
    private void btnLogin(ActionEvent event) {
        try {
            AnchorPane register = FXMLLoader.load(getClass().getResource("Login.fxml"));
            root.getChildren().setAll(register);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean signUp() {
        String enteredUsername = txt_username.getText();
        String enteredName = txt_name.getText();
        String enteredPassword = txt_password.getText();
        String contactno = txt_contact.getText();
        
        //query
        String findSql = "SELECT * FROM users WHERE username = ?";
        
        try{
            preparedStatement = conn.prepareStatement(findSql);
            preparedStatement.setString(1,enteredUsername);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next() && !enteredUsername.equals("")  && !enteredPassword.equals("") && !enteredName.equals(""))
            {
                String insertSql = "INSERT INTO users VALUES(?,?,?,?)";
                try
                {   PreparedStatement preparedStatement1;
                    preparedStatement1 = conn.prepareStatement(insertSql);
                    preparedStatement1.setString(2,enteredUsername);
                    preparedStatement1.setString(1,enteredName);
                    //int hash = enteredPassword.hashCode();
                    //enteredPassword = String.valueOf(hash);
                    preparedStatement1.setString(3,enteredPassword);
                    preparedStatement1.setString(4,contactno);
                    preparedStatement1.executeUpdate();  
                    return true;
                }
                catch(Exception ex)
                {   System.err.println("query wrong" + ex.getMessage());
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        catch(Exception ex) {
            return false;
        }
    }

}
