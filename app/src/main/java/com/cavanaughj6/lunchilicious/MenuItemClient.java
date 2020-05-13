package com.cavanaughj6.lunchilicious;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MenuItemClient {

    @GET("/lunchilicious/menuitems")
    Call<List<MenuItem>> getAllMenuItems();

    @POST("/lunchilicious/addmenuitem")
    Call<MenuItem> addMenuItem(@Body MenuItem menuItem);

}
