package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


//This fragment simply sends you to other fragments. Nothing else

public class MainMenuFragment extends Fragment {

    Button b_toMenu, b_AddNew, b_toCart, b_toOrder;

    private MainMenuFragmentInterface mainMenuFragmentInterface;


    public interface MainMenuFragmentInterface {
        void startMenuFragment();
        void startNewItemFragment();
        void startShoppingCartFragment();
        void startOrderFragment();
    }

    public static MainMenuFragment newInstance() { return new MainMenuFragment();}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_type_and_cart, container, false);

        b_AddNew = view.findViewById(R.id.b_toAddNew);
        b_toMenu = view.findViewById(R.id.b_toMenu);
        b_toCart = view.findViewById(R.id.b_toCart);
        b_toOrder = view.findViewById(R.id.b_toOrder);

        b_toOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuFragmentInterface.startOrderFragment();
            }
        });

        b_AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mainMenuFragmentInterface.startNewItemFragment();
            }
        });

        b_toCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuFragmentInterface.startShoppingCartFragment();
            }
        });

        b_toMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenuFragmentInterface.startMenuFragment();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mainMenuFragmentInterface = (MainMenuFragment.MainMenuFragmentInterface)context;
    }

}
