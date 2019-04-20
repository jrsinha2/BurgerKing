/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burgerking;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Aditya Sinha
 */
public class ItemTable {
    private SimpleStringProperty ItemCode = new SimpleStringProperty("");
    private SimpleStringProperty ItemDescription = new SimpleStringProperty("");
    private SimpleDoubleProperty ItemPrice = new SimpleDoubleProperty(0.0);
    private SimpleStringProperty ItemType = new SimpleStringProperty("");
    private SimpleStringProperty ItemImage = new SimpleStringProperty("");
    
    public ItemTable(String ID,String Des,double Price,String Type,String image) {
        this.ItemCode.set(ID);
        this.ItemDescription.set(Des);
        this.ItemImage.set(image);
        this.ItemPrice.set(Price);
        this.ItemType.set(Type);
    }
    
    public String getCode() {
        return this.ItemCode.get();
    }
    
    public String getDescription() {
        return this.ItemDescription.get();
    }
    
    public String getType() {
        return this.ItemType.get();
    }
    
    public double getPrice() {
        return this.ItemPrice.get();
    }
    
    public String getImage() {
        return this.ItemImage.get();
    }



}
