package com.cavanaughj6.lunchilicious;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;


public class CartViewModel extends AndroidViewModel {

    LiveData<List<CartItem>> cartLiveData = null;
    public List<CartItem> cartList = new ArrayList<>();

    public CartViewModel(@NonNull Application application){
        super(application);
    }

    public LiveData<List<CartItem>> getCartLiveData() {
        if(cartLiveData == null) {
            cartLiveData = LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).cartItemDao().getAllCartItems();
        }
        return cartLiveData;
    }

    public void addNewCartItem(CartItem cartItem){
        LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
            int maxCartSize = cartList.size();
            boolean exists = false;
            if(maxCartSize == 0){
                LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).cartItemDao().insertItem(cartItem);
            }
            else{
                for(int i = 0; i < maxCartSize; i++) {
                    if(cartList.get(i).id == cartItem.id){
                        exists = true;
                        cartItem.quantity = cartList.get(i).quantity + cartItem.quantity;
                    }
                }
                if(!exists){
                    LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).cartItemDao().insertItem(cartItem);
                }
            }
        });
    }

    public void deleteCart(List<CartItem> cartItems){
        LunchiliciousDatabase.databaseWriteExecutor.execute(() -> {
            LunchiliciousDatabase.getDatabase(getApplication().getApplicationContext()).cartItemDao().deleteAllItems();
        });
    }

}
