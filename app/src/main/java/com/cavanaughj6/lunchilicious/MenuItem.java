package com.cavanaughj6.lunchilicious;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class MenuItem {
    @PrimaryKey
     int id;
     String type;
     String name;
     String description;
     double unitPrice;
    public MenuItem(int id, String type, String name, String description, double unitPrice) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public int getMenuId() {return id;}
    public String getMenuType() {return type;}
    public String getMenuName() {return name;}
    public String getMenuDescription() {return description;}
    public double getMenuPrice() {return unitPrice;}

}
