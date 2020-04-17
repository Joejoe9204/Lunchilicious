package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class MenuAdapter extends RecyclerView.Adapter {

    public Context context;
    List<MenuItem> itemList;

    public MenuAdapter(Context context,List<MenuItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        MyViewHolder RVH = new MyViewHolder(view);
        return RVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tv_menuType = holder.itemView.findViewById(R.id.tv_menuType);
        tv_menuType.setText(itemList.get(position).getMenuType());
        TextView tv_menuName = holder.itemView.findViewById(R.id.tv_menuName);
        tv_menuName.setText(itemList.get(position).getMenuName());
        TextView tv_menuPrice = holder.itemView.findViewById(R.id.tv_menuPrice);
        tv_menuPrice.setText(String.format(Locale.US, "%.2f", itemList.get(position).getMenuPrice()));
    }

    @Override
    public int getItemCount() {return itemList.size(); }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        MyViewHolder(View view) { super(view);}
    }

    public void setMenuItems(List<MenuItem> items){
        this.itemList = items;
        notifyDataSetChanged();
    }
}
