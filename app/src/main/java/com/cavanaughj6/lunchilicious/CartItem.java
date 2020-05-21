package com.cavanaughj6.lunchilicious;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "cart")
public class CartItem {

    @PrimaryKey
    int id;
    String name;
    String type;
    String description;
    double unitPrice;
    int quantity;
    public CartItem(int id, String name, String type, String description, double unitPrice, int quantity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getCartId() {return id;}
    public String getCartType() {return type;}
    public String getCartName() {return name;}
    public String getCartDescription() {return description;}
    public double getCartPrice() {return unitPrice;}
    public int getCartQuantity() {return quantity;}


}