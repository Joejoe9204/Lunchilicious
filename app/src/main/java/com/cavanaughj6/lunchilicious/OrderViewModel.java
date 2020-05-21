package com.cavanaughj6.lunchilicious;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    LiveData<List<OrderModel>> orderLiveData = null;
    public List<OrderModel> orderList = new ArrayList<>();

    public OrderViewModel(@NonNull Application application) { super(application); }

    public LiveData<List<OrderModel>> getOrderLiveData() {
        if(orderLiveData == null){
            orderLiveData = LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).orderModelDao().getAllOrderItems();
        }
        return orderLiveData;
    }


    //if Id is the same as any other dont insert
    public void addNewOrderItem(OrderModel orderModel){
        LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
            LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).orderModelDao().insertItem(orderModel);

        });
    }

}
