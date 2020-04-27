package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    LiveData<List<MenuItem>> itemsLiveData = null;

    public MenuViewModel(@NonNull Application application) {super(application);}

    public LiveData<List<MenuItem>> getMenuItemsLiveData() {
        if (itemsLiveData == null) {
            Context context = getApplication().getApplicationContext();
            itemsLiveData = LunchiliciousDatabase.getDatabase(context).menuItemDao().getAllMenuItems();
        }
        return itemsLiveData;
    }

    public void addNewMenuItem(MenuItem menuItem) {
        LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
            Context context = getApplication().getApplicationContext();
            int maxMenuId = LunchiliciousDatabase.getDatabase(context).menuItemDao().findMaxMenuId();
            menuItem.MenuId = maxMenuId + 1;
            LunchiliciousDatabase.getDatabase(context).menuItemDao().insertItem(menuItem);
        });
    }
}


