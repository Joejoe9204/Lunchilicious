package com.cavanaughj6.lunchilicious;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LunchiliciousRepository {
    private MenuItemDao menuItemDao;
    private LiveData<List<MenuItem>> itemsLiveData;
    MenuItemClient client;
    String POST_URL = "http://aristotle.cs.scranton.edu/";
    Application application;




    public LunchiliciousRepository(Application application){
        this.application = application;
        LunchiliciousDatabase db = LunchiliciousDatabase.getDatabase(application);
        menuItemDao = db.menuItemDao();
        itemsLiveData = menuItemDao.getAllMenuItems();

        OkHttpClient httpclient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient).build();

        client = retrofit.create(MenuItemClient.class);
    }

    public LiveData<List<MenuItem>> getMenuItemsRepo() { return itemsLiveData; }


    public void addMenuItem(MenuItem menuItem) {
        Call<MenuItem> call = client.addMenuItem(menuItem);
        call.enqueue(new Callback<MenuItem>() {
            @Override
            public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                MenuItem items = response.body();
                    int newId = items.getMenuId();
                    Toast.makeText(application, "PASSED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MenuItem> call, Throwable t) {
                Toast.makeText(application, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMenuItems(){
        Call<List<MenuItem>> call = client.getAllMenuItems();
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
                    List<MenuItem> itemList = response.body();
                    int maxID = menuItemDao.findMaxMenuId();
                    for (int i = 0; i < itemList.size() ; i++) {
                        if (itemList.get(i).id > maxID) {
                            menuItemDao.insertItem(itemList.get(i));
                        }
                    }
                });
                Toast.makeText(application, "PASSED", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(application, "FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



