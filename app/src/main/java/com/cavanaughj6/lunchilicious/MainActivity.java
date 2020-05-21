package com.cavanaughj6.lunchilicious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
    implements MenuFragment.MenuFragmentInterface, MainMenuFragment.MainMenuFragmentInterface, AddToFragment.AddToFragmentInterface,
                NewItemFragment.NewItemFragmentInterface, ShoppingCartFragment.ShoppingCartFragmentInterface, OrderFragment.OrderFragmentInterface {

    MenuViewModel menuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, MainMenuFragment.newInstance()).commit();
        }

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
            UpdateWorker.class, 30, TimeUnit.SECONDS).build();

        WorkManager.getInstance(getApplication().getApplicationContext()).enqueue(periodicWorkRequest);

    }

    @Override
    public void startMenuFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, MenuFragment.newInstance()).commit();
    }


    @Override
    public void startMainMenuFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, MainMenuFragment.newInstance()).commit();
    }

    @Override
    public void startNewItemFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, NewItemFragment.newInstance()).commit();
    }

    @Override
    public void startShoppingCartFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, ShoppingCartFragment.newInstance()).commit();
    }

    @Override
    public void startOrderFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, OrderFragment.newInstance()).commit();
    }

}