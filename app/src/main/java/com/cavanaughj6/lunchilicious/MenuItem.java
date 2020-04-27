package com.cavanaughj6.lunchilicious;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class MenuItem {
    @PrimaryKey
     int MenuId;
     String MenuType;
     String MenuName;
     String MenuDescription;
     double price;
    public MenuItem(int MenuId, String MenuType, String MenuName, String MenuDescription, double price) {
        this.MenuId = MenuId;
        this.MenuType = MenuType;
        this.MenuName = MenuName;
        this.MenuDescription = MenuDescription;
        this.price = price;
    }

    public int getMenuId() {return MenuId;}
    public String getMenuType() {return MenuType;}
    public String getMenuName() {return MenuName;}
    public String getMenuDescription() {return  MenuDescription;}
    public double getMenuPrice() {return price;}

}
