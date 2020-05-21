package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.List;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateWorker extends Worker {

    private MenuItemDao menuItemDao;
    MenuItemClient client;
    String POST_URL = "http://aristotle.cs.scranton.edu/";
    //Application application;

    public UpdateWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        LunchiliciousDatabase db = LunchiliciousDatabase.getDatabase(getApplicationContext());
        menuItemDao = db.menuItemDao();

        OkHttpClient httpclient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpclient).build();

        client = retrofit.create(MenuItemClient.class);

        updateMenuItems();

        return Result.success();
    }

    public void updateMenuItems() {
        Call<List<MenuItem>> call = client.getAllMenuItems();
        call.enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
                    List<MenuItem> itemList = response.body();
                    int maxID = menuItemDao.findMaxMenuId();
                    for (int i = 0; i < itemList.size(); i++) {
                        if (itemList.get(i).id > maxID) {
                            menuItemDao.insertItem(itemList.get(i));
                        }
                    }
                });
                Toast.makeText(getApplicationContext(), "Menu Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Menu Failed to Update", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
