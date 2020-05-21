package com.cavanaughj6.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CartItemDao {

    @Insert
    void insertItem(CartItem items);

    @Insert
    void insertListMenuItems(List<CartItem> items);

    @Query("DELETE FROM cart")
    void deleteAllItems();

    @Query("SELECT * " +
            "FROM cart " +
            "ORDER BY type, name")
    LiveData<List<CartItem>> getAllCartItems();


    @Query("SELECT max(id) FROM cart")
    int findMaxMenuId();

}