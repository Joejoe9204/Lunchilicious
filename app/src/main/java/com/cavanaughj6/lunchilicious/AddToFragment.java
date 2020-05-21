package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class AddToFragment extends Fragment {

    TextView tv_addToName, tv_addToType, tv_addToDescription, tv_addToPrice, tv_curNum;
    Button b_addToCart, b_plus, b_minus, b_back;

    String itemName, itemType, itemDesc;
    int itemId;
    int quantity = 0;
    double itemPrice;
    int count = 0;

    CartViewModel viewModel;

    private AddToFragmentInterface addToFragmentInterface;
    public interface AddToFragmentInterface{
       void startMenuFragment();
    }

    public static AddToFragment newInstance() { return new AddToFragment(); }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemName = getArguments().getString("item_name");
            itemId = getArguments().getInt("item_id");
            itemType = getArguments().getString("item_type");
            itemDesc = getArguments().getString("item_desc");
            itemPrice = getArguments().getDouble("item_price");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_to, container, false);

        tv_curNum = view.findViewById(R.id.tv_curNum);
        tv_addToName = view.findViewById(R.id.tv_addToName);
        tv_addToDescription = view.findViewById(R.id.tv_addToDesc);
        tv_addToPrice = view.findViewById(R.id.tv_addToPrice);
        tv_addToType = view.findViewById(R.id.tv_addToType);
        b_addToCart = view.findViewById(R.id.b_addtocart);
        b_back = view.findViewById(R.id.b_back_addto);
        b_plus = view.findViewById(R.id.b_add_addto);
        b_minus = view.findViewById(R.id.b_minus_addto);


        tv_addToType.setText(itemType);
        tv_addToName.setText(itemName);
        tv_addToDescription.setText(itemDesc);
        tv_addToPrice.setText(String.format(Locale.US, "$%.2f", itemPrice));


        b_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity == 0){
                    Toast.makeText(getContext(), "Can't add nothing", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(count == 0) {
                        viewModel = new ViewModelProvider(getActivity()).get(CartViewModel.class);
                        final CartItem cartItem = new CartItem(itemId, itemName, itemType, itemDesc, itemPrice, quantity);
                        viewModel.addNewCartItem(cartItem);
                        count++;
                    }
                    else{
                        //do nothing
                    }
                }
            }
        });

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFragmentInterface.startMenuFragment();
                count = 0;
            }
        });

        b_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity < 10){
                    quantity += 1;
                }
                else{
                    Toast.makeText(getContext(), "Don't be greedy", Toast.LENGTH_SHORT).show();
                }
                tv_curNum.setText(String.format(Locale.US, "Number\n" +"%d", quantity));
            }
        });

        b_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity > 0){
                    quantity -= 1;
                }
                else {
                    Toast.makeText(getContext(), "Not possible", Toast.LENGTH_SHORT).show();
                }
                tv_curNum.setText(String.format(Locale.US, "Number\n" + "%d", quantity));
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        addToFragmentInterface = (AddToFragment.AddToFragmentInterface)context;
    }

}
