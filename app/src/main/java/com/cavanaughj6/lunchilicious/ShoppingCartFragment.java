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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ShoppingCartFragment extends Fragment {
    private RecyclerView recyclerViewCart;
    private CartAdapter cartAdapter;
    CartViewModel cartViewModel;
    OrderViewModel orderViewModel;

    TextView tv_totalPrice, tv_quantity;
    Button b_confirm, b_back_cart;
    String orderId, orderDate;
    double itemPrice, costPerItem, totalPrice;
    int itemQuantity;

    private ShoppingCartFragmentInterface shoppingCartFragmentInterface;

    public interface ShoppingCartFragmentInterface {
        void startMainMenuFragment();
    }

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        tv_totalPrice = view.findViewById(R.id.tv_totalPrice);
        tv_quantity = view.findViewById(R.id.tv_quantity);
        b_back_cart = view.findViewById(R.id.b_back_cart);
        b_confirm = view.findViewById(R.id.b_confirm);

        recyclerViewCart = view.findViewById(R.id.cartrecycler_view);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewCart.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        displayCartItems(null);

        b_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartFragmentInterface.startMainMenuFragment();
            }
        });


        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderId = getOrderId();
                orderDate = getOrderDate();

                orderViewModel = new ViewModelProvider(getActivity()).get(OrderViewModel.class);
                final OrderModel orderModel = new OrderModel(orderId, orderDate, totalPrice);
                orderViewModel.addNewOrderItem(orderModel);

                cartViewModel.deleteCart(cartViewModel.cartList);
            }
        });

        return view;
    }

    public void getTotalPrice(List<CartItem> cartList) {
        int maxCartSize = cartList.size();
        totalPrice = 0;
        for (int i = 0; i < maxCartSize; i++) {
            itemQuantity = cartList.get(i).quantity;
            itemPrice = cartList.get(i).unitPrice;
            costPerItem = itemPrice * itemQuantity;
            totalPrice = costPerItem + totalPrice;
        }
        tv_totalPrice.setText(String.format(Locale.US, "Total Price: %.2f", totalPrice));
    }

    public void displayCartItems(View v) {
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        cartAdapter = new CartAdapter(getContext(), cartViewModel.cartList);
        recyclerViewCart.setAdapter(cartAdapter);
        cartViewModel.getCartLiveData().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartAdapter.setCartItems(cartItems);
                cartViewModel.cartList = cartItems;
                getTotalPrice(cartItems);
            }
        });
    }

    String getOrderId() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String orderId = simpleDateFormat.format(new Date());
        return orderId;
    }

    String getOrderDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = simpleDateFormat.format(new Date());
        return orderDate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        shoppingCartFragmentInterface = (ShoppingCartFragment.ShoppingCartFragmentInterface) context;
    }

}
