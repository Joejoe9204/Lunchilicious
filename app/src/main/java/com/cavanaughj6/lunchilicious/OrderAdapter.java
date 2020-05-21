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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    public Context context;
    List<OrderModel> oList;

    public OrderAdapter(Context context, List<OrderModel> orderList){
        oList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(oList.get(position));
    }

    @Override
    public int getItemCount() {return oList.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_orderId, tv_orderDate, tv_orderTotal;
        ViewHolder(@NonNull View view) {
            super(view);
            tv_orderTotal = view.findViewById(R.id.tv_orderTotal);
            tv_orderDate = view.findViewById(R.id.tv_orderDate);
            tv_orderId = view.findViewById(R.id.tv_orderId);
        }

        public void bind(OrderModel order) {
            tv_orderId.setText(order.getOrderId());
            tv_orderDate.setText(order.getOrderDate());
            tv_orderTotal.setText(String.format(Locale.US, "%.2f", order.getTotalCost()));
        }

    }

    public void setOrderItems(List<OrderModel> orders){
        this.oList = orders;
        notifyDataSetChanged();
    }
}
