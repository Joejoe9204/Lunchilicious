package com.cavanaughj6.lunchilicious;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.List;

public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    MenuViewModel viewModel;
    List<MenuItem> itemList;

    boolean numeric;
    double menuPrice;

    EditText et_MenuName, et_MenuType, et_MenuPrice;
    String msgMenuName, msgMenuPrice, msgMenuType;
    Button b_update;
    ImageButton imgb_add;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            msgMenuName = savedInstanceState.getString("Menu_Name");
            msgMenuPrice = savedInstanceState.getString("Menu_Price");
            msgMenuType = savedInstanceState.getString("Menu_Type");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("Menu_Name", msgMenuName);
        savedInstanceState.putString("Menu_Price", msgMenuPrice);
        savedInstanceState.putString("Menu_Type", msgMenuType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.myrecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        displayMenuItems(null);

        et_MenuName = view.findViewById(R.id.et_MenuName);
        et_MenuPrice = view.findViewById(R.id.et_MenuPrice);
        et_MenuType = view.findViewById(R.id.et_MenuType);

        imgb_add = view.findViewById(R.id.imgb_add);
        imgb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgMenuName = et_MenuName.getText().toString();
                msgMenuType = et_MenuType.getText().toString();
                msgMenuPrice = et_MenuPrice.getText().toString();

                numeric = true;
                try {
                    menuPrice = Double.parseDouble(msgMenuPrice);
                } catch (NumberFormatException e) {
                    numeric = false;
                }

                if (numeric && msgMenuName != "" && msgMenuType != "") {
                    viewModel = new ViewModelProvider(getActivity()).get(MenuViewModel.class);
                    final MenuItem menuItem = new MenuItem(-1 , msgMenuType, msgMenuName, "", menuPrice);
                    viewModel.addNewMenuItem(menuItem);
                } else {
                    Toast.makeText(getContext(), "Input is Invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_update = view.findViewById(R.id.b_update);
        b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateButton();
            }
        });


        return view;
    }



    public void displayMenuItems(View v) {
        viewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        mAdapter = new MenuAdapter(getContext(), itemList);
        recyclerView.setAdapter(mAdapter);
        viewModel.getMenuItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                mAdapter.setMenuItems(menuItems);
                itemList = menuItems;
            }
        });
    }

    public void updateButton() {
        viewModel.updateMenuItems();
    }

}
