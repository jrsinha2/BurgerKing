package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomizeOrderController implements Initializable{

    private Text lbl_itemdescription;

    @FXML
    private Text lblCustPrice;

    @FXML
    private JFXButton btnMainMenu1;

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private ImageView imgItem;

    @FXML
    private Label lbl_item;

    @FXML
    private Label lbl_pattyprice;

    @FXML
    private JFXRadioButton rbMedium;

    @FXML
    private JFXRadioButton rbRegular;

    @FXML
    private JFXRadioButton rbLarge;

    private String txt;
    private int qty;
    private String item_code;
    private double customizedPrice;
    @FXML
    private ToggleGroup fries;
    @FXML
    private Label itm_description;
    private double extraPrice;
    public void setTxt(String txt, String itemQty, String itemCode) {
        this.txt = txt;
        lblCustPrice.setText(txt);
        this.qty = Integer.parseInt(itemQty);
        this.customizedPrice = Double.parseDouble(txt);
        this.item_code = itemCode;
        
    }
    
    
    public void setItemCode(String ItemCode) {
        this.item_code = ItemCode;
        try{    
            Item i = Item.searchDB(ItemCode);
            if(i!=null) {
            itm_description.setText(i.getDescription());
            String imgURL = i.getImage();
            imgItem.setImage(new Image(String.format("file:/C:/Users/Aditya Sinha/Documents/NetBeansProjects/BurgerKing/src/images/%s",imgURL)));
            }    
            else
            {
                System.err.println("Failed to load content");
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void setCustomizedPrice() {
        lblCustPrice.setText(String.valueOf(customizedPrice));
    }
    @FXML
    void btnAddtoOrder(ActionEvent event) {
        customizedPrice+=extraPrice;
        setCustomizedPrice();
        String newAmount=lblCustPrice.getText();
        
        System.out.println(newAmount);
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MakeOrder.fxml"));
        Parent root1=(Parent) fxmlLoader.load();
        Stage stage=new Stage();
        stage.setScene(new Scene(root1));
        
        
        MakeOrderController mkc=fxmlLoader.getController();
        mkc.setCustomizedAmnt(newAmount);
        ((Node)(event.getSource())).getScene().getWindow().hide();
        //stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void setFries(ActionEvent event) {
        this.extraPrice = 0;
        if(rbRegular.isSelected()) {
            rbRegular.setSelected(true);
            rbMedium.setSelected(false);
            rbLarge.setSelected(false);
            extraPrice = 99;
            //customizedPrice+=regularSize;
            //setCustomizedPrice();
        }
        if(rbMedium.isSelected()) {
            rbMedium.setSelected(true);
            rbRegular.setSelected(false);
            rbLarge.setSelected(false);
            extraPrice = 119;
            //customizedPrice+=mediumSize;
            //setCustomizedPrice();
        }
        if(rbLarge.isSelected()) {
            rbMedium.setSelected(false);
            rbRegular.setSelected(false);
            rbLarge.setSelected(true);
            extraPrice = 129;
            //customizedPrice+=largeSize;
            //setCustomizedPrice();
        }
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        lblCustPrice.setText(txt);
    }

}
