package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    LiveData<List<MenuItem>> itemsLiveData = null;
    private LunchiliciousRepository lunchiliciousRepository;

    public MenuViewModel(@NonNull Application application) {
        super(application);
        lunchiliciousRepository = new LunchiliciousRepository(application);
        itemsLiveData = lunchiliciousRepository.getMenuItems();
    }

    public LiveData<List<MenuItem>> getMenuItemsLiveData() {
        if (itemsLiveData == null) {
            Context context = getApplication().getApplicationContext();
            itemsLiveData = LunchiliciousDatabase.getDatabase(context).menuItemDao().getAllMenuItems();
        }
        return itemsLiveData;
    }

    public void addNewMenuItem(MenuItem menuItem) {
        if(lunchiliciousRepository == null){
            lunchiliciousRepository = new LunchiliciousRepository(getApplication());
        }

            int maxMenuId = -1;
            menuItem.MenuId = maxMenuId;
            lunchiliciousRepository.addMenuItem(menuItem);
    }

    public void updateMenuItems() {
        lunchiliciousRepository.updateMenuItems();
    }
}


