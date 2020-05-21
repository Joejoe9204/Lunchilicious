package com.cavanaughj6.lunchilicious;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "completeOrder")
public class OrderModel {
    @PrimaryKey
    @NonNull
    String orderId;
    String orderDate;
    double totalCost;
    public OrderModel(String orderId, String orderDate, double totalCost){
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public double getTotalCost() { return totalCost; }

}
