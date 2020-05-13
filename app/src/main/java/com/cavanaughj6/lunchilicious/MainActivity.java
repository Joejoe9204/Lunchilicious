package com.cavanaughj6.lunchilicious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MenuViewModel menuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, MenuFragment.newInstance()).commit();
        }
    }


}