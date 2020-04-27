package com.cavanaughj6.lunchilicious;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MenuItem.class}, version = 5, exportSchema = false)
public abstract class LunchiliciousDatabase extends RoomDatabase {
    public abstract MenuItemDao menuItemDao();

    private static volatile LunchiliciousDatabase INSTANCE;
    private static final int NUM_THREADS = 2;

    public static ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUM_THREADS);

    public static LunchiliciousDatabase getDatabase(final Context context) {
        synchronized (LunchiliciousDatabase.class){
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LunchiliciousDatabase.class, "menu_item_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(lunchiliciousDBCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback lunchiliciousDBCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute( () -> {
                MenuItemDao menuItemDao = INSTANCE.menuItemDao();
                menuItemDao.deleteAllItems();
                ArrayList<MenuItem> menuItems = createMenuItems();
                menuItemDao.insertListMenuItems(menuItems);
            });
        }
    };

    public static ArrayList<MenuItem> createMenuItems() {
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
