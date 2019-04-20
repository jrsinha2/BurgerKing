package burgerking;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CustomizeOrderController implements Initializable{

    @FXML
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
            lbl_itemdescription.setText(i.getDescription());
            String imgURL = i.getImage();
            imgItem.setImage(new Image(String.format("../images/%s",imgURL)));
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

    }

    @FXML
    private void setFries(ActionEvent event) {
        if(rbRegular.isSelected()) {
            rbRegular.setSelected(true);
            rbMedium.setSelected(false);
            rbLarge.setSelected(false);
            double regularSize = 99;
            customizedPrice+=regularSize;
            setCustomizedPrice();
        }
        if(rbMedium.isSelected()) {
            rbMedium.setSelected(true);
            rbRegular.setSelected(false);
            rbLarge.setSelected(false);
            double mediumSize = 119;
            customizedPrice+=mediumSize;
            setCustomizedPrice();
        }
        if(rbLarge.isSelected()) {
            rbMedium.setSelected(false);
            rbRegular.setSelected(false);
            rbLarge.setSelected(true);
            double largeSize = 129;
            customizedPrice+=largeSize;
            setCustomizedPrice();
        }
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        lblCustPrice.setText(txt);
    }

}
