package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    public Context context;
    List<MenuItem> itemList;
    private OnItemClickListener mListener;



    public MenuAdapter(Context context, List<MenuItem> iList) {
        itemList = new ArrayList<>();
        this.context = context;
        //this.itemList = iList;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }


    @Override
    public int getItemCount() {return itemList.size(); }

     class ViewHolder extends RecyclerView.ViewHolder {
         TextView tv_menuType, tv_menuName, tv_menuPrice;

        ViewHolder(@NonNull View view) {
            super(view);
            tv_menuName = view.findViewById(R.id.tv_menuName);
            tv_menuPrice = view.findViewById(R.id.tv_menuPrice);
            tv_menuType = view.findViewById(R.id.tv_menuType);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
         public void bind(MenuItem menuItem) {
             tv_menuType.setText(menuItem.getMenuType());
             tv_menuName.setText(menuItem.getMenuName());
             tv_menuPrice.setText(String.format(Locale.US, "%.2f", menuItem.getMenuPrice()));
         }

    }

    public void setMenuItems(List<MenuItem> items){
        this.itemList = items;
        notifyDataSetChanged();
    }



}
