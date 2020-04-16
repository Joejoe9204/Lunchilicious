package com.cavanaughj6.lunchilicious;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class MenuViewModel extends ViewModel {

    MutableLiveData<List<MenuItem>> itemsLiveData = new MutableLiveData<>();
    public List<MenuItem> items = new ArrayList<>();

    public MutableLiveData<List<MenuItem>> getMenuItemsLiveData() {
        return itemsLiveData;
    }

    public void setItems(List<MenuItem> items) {
        if (itemsLiveData == null) {
            itemsLiveData = new MutableLiveData<>(items);
        }
        this.items = items;
        itemsLiveData.setValue(items);
    }

    public void addMenuItem(MenuItem menuItem) {
        items.add(menuItem);
        itemsLiveData.setValue(items);
    }
}


