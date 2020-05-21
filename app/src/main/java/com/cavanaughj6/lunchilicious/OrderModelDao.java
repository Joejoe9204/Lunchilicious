package com.cavanaughj6.lunchilicious;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface OrderModelDao {

    @Insert
    void insertItem(OrderModel completeOrder);

    @Insert
    void insertListMenuItems(List<OrderModel> completeOrder);

    @Query("DELETE FROM completeOrder")
    void deleteAllItems();

    @Query("SELECT * " +
            "FROM completeOrder " +
            "ORDER BY orderId, orderDate")
    LiveData<List<OrderModel>> getAllOrderItems();


    @Query("SELECT orderId FROM completeOrder")
    int findMaxMenuId();


}
