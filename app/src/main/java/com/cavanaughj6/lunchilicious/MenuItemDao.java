package com.cavanaughj6.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface MenuItemDao {

    @Insert
    void insertItem(MenuItem items);

    @Insert
    void insertListMenuItems(List<MenuItem> items);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query("SELECT * " +
            "FROM item " +
            "ORDER BY MenuType, MenuName")
    LiveData<List<MenuItem>> getAllMenuItems();


    @Query("SELECT max(MenuId) FROM item")
    int findMaxMenuId();

}
