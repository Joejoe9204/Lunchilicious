package com.cavanaughj6.lunchilicious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MenuViewModel menuViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, MenuFragment.newInstance()).commit();
            menuViewModel.setItems(setMenuItems());
        }
    }

    public ArrayList<MenuItem> setMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(3, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(4, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(5, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(6, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
        items.add(new MenuItem(7, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
        items.add(new MenuItem(8, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
        items.add(new MenuItem(9, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
        items.add(new MenuItem(10, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
        items.add(new MenuItem(11, "Side", "Fries", "large hot fries", (float) 2.95));
        items.add(new MenuItem(12, "Side", "Gravy Fries", "Fries with gravy on top", (float) 3.95));
        items.add(new MenuItem(13, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
        items.add(new MenuItem(14, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
        items.add(new MenuItem(15, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));

        return items;
    }
}