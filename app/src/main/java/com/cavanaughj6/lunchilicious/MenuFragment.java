package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class MenuFragment extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<MenuItem> menuItem;

    private TextView tv_menuType, tv_menuName, tv_menuPrice;

    public MenuFragment(Context context, ArrayList<MenuItem> MenuItem) {
        this.context = context;
        this.menuItem = MenuItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_menu, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        tv_menuType = holder.itemView.findViewById(R.id.tv_menuType);
        tv_menuType.setText(menuItem.get(position).getMenuType());
        tv_menuName = holder.itemView.findViewById(R.id.tv_menuName);
        tv_menuName.setText(menuItem.get(position).getMenuName());
        tv_menuPrice = holder.itemView.findViewById(R.id.tv_menuPrice);
        tv_menuPrice.setText(String.format(Locale.US, "%.2f", menuItem.get(position).getMenuPrice()));
    }

    @Override
    public int getItemCount() {return menuItem.size(); }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        MyViewHolder(View view) { super(view); }
    }


}