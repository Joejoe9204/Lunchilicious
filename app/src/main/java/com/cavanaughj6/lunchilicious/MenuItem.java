package com.cavanaughj6.lunchilicious;

public class MenuItem {
    int MenuId;
    String MenuType;
    String MenuName;
    String MenuDescription;
    double MenuPrice;
    public MenuItem(int id, String type, String name, String description, double unitPrice) {
        MenuId = id;
        MenuType = type;
        MenuName = name;
        MenuDescription = description;
        MenuPrice = unitPrice;
    }

    public int getMenuId() {return MenuId;}
    public String getMenuType() {return MenuType;}
    public String getMenuName() {return MenuName;}
    public String getMenuDescription() {return  MenuDescription;}
    public double getMenuPrice() {return MenuPrice;}

}
