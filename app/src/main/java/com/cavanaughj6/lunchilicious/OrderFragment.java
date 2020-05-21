package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class OrderFragment extends Fragment {
    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    OrderViewModel orderViewModel;

    Button b_back_order;


    private OrderFragmentInterface orderFragmentInterface;
    public interface OrderFragmentInterface {
        void startMainMenuFragment();
    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {  super.onCreate(savedInstanceState);    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);
        b_back_order = view.findViewById(R.id.b_back_order);

        recyclerViewOrder = view.findViewById(R.id.recyclerorder_view);
        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewOrder.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        displayOrderItems(null);


        b_back_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderFragmentInterface.startMainMenuFragment();
            }
        });

        return view;
    }

    public void displayOrderItems(View v) {
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        orderAdapter = new OrderAdapter(getContext(), orderViewModel.orderList);
        recyclerViewOrder.setAdapter(orderAdapter);
        orderViewModel.getOrderLiveData().observe(getViewLifecycleOwner(), new Observer<List<OrderModel>>() {
            @Override
            public void onChanged(List<OrderModel> orderModels) {
                orderAdapter.setOrderItems(orderModels);
                orderViewModel.orderList = orderModels;
            }
        });
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        orderFragmentInterface = (OrderFragment.OrderFragmentInterface)context;
    }

}
