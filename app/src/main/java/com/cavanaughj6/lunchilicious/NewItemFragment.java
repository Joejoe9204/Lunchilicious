package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class NewItemFragment extends Fragment {

    EditText et_name, et_type, et_price, et_desc;
    String addName, addPrice, addType, addDesc;
    Button b_back_newitem, b_addItem;

    boolean numeric;
    double menuPrice;

    MenuViewModel viewModel;

    private NewItemFragmentInterface newItemFragmentInterFace;
    public interface NewItemFragmentInterface {
        void startMainMenuFragment();
    }

    public static NewItemFragment newInstance() { return new NewItemFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
        {
            addName = savedInstanceState.getString("addName");
            addPrice = savedInstanceState.getString("addPrice");
            addType = savedInstanceState.getString("addType");
            addDesc = savedInstanceState.getString("addDesc");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("addName", addName);
        savedInstanceState.putString("addPrice", addPrice);
        savedInstanceState.putString("addType", addType);
        savedInstanceState.putString("addDesc" , addDesc);
        super.onSaveInstanceState(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_item, container, false);

        et_name = view.findViewById(R.id.et_name);
        et_desc = view.findViewById(R.id.et_desc);
        et_price = view.findViewById(R.id.et_price);
        et_type = view.findViewById(R.id.et_type);

                b_addItem = view.findViewById(R.id.b_addItem);
        b_back_newitem = view.findViewById(R.id.b_back_newitem);

        b_addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName = et_name.getText().toString();
                addType = et_type.getText().toString();
                addDesc = et_desc.getText().toString();
                addPrice = et_price.getText().toString();

                numeric = true;
                try {
                    menuPrice = Double.parseDouble(addPrice);
                } catch (NumberFormatException e) {
                    numeric = false;
                }

                if(numeric && addName != "" && addType != ""){
                    viewModel = new ViewModelProvider(getActivity()).get(MenuViewModel.class);
                    final MenuItem menuItem = new MenuItem(-1, addName, addType, addDesc, menuPrice);
                    viewModel.addNewMenuItem(menuItem);
                } else {
                    Toast.makeText(getContext(), "Input is Invalid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        b_back_newitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemFragmentInterFace.startMainMenuFragment();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        newItemFragmentInterFace = (NewItemFragment.NewItemFragmentInterface)context;
    }

}
