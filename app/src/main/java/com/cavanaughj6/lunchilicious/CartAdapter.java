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


//setting a quantity clicking to remove or add that amount

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public Context context;
    List<CartItem> cList;

    public CartAdapter(Context context, List<CartItem> cartList) {
        cList = new ArrayList<>();
        this.context = context;
        //this.cList = cartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(cList.get(position));
    }

    @Override
    public int getItemCount() {return cList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_addToType, tv_addToName, tv_addToPrice, tv_quantity;
        ViewHolder(@NonNull View view) {
            super(view);
            tv_addToName = view.findViewById(R.id.tv_addToName);
            tv_addToType = view.findViewById(R.id.tv_addToType);
            tv_addToPrice = view.findViewById(R.id.tv_addToPrice);
            tv_quantity = view.findViewById(R.id.tv_quantity);

        }

        public void bind(CartItem cartItem) {
            tv_addToType.setText(cartItem.getCartType());
            tv_addToName.setText(cartItem.getCartName());
            tv_addToPrice.setText(String.format(Locale.US, "%.2f", cartItem.getCartPrice()));
            tv_quantity.setText(String.format(Locale.US, "%d", cartItem.getCartQuantity()));
        }

    }

    public void setCartItems(List<CartItem> items){
        this.cList = items;
        notifyDataSetChanged();
    }
}