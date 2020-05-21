package com.cavanaughj6.lunchilicious;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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

public class MenuFragment extends Fragment implements MenuAdapter.OnItemClickListener
{
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    MenuViewModel viewModel;
    List<MenuItem> itemList;

    Button b_back;
    String msgMenuType;

    private MenuFragmentInterface menuFragmentInterface;
    public interface MenuFragmentInterface{
        void startMainMenuFragment();
    }


    public static MenuFragment newInstance() {
        return new MenuFragment();
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null)
        {
            msgMenuType = getArguments().getString("foodtype");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.myrecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        displayMenuItems(null);

        viewModel = new ViewModelProvider(this.getActivity()).get(MenuViewModel.class);
        mAdapter = new MenuAdapter(getActivity(), itemList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(MenuFragment.this);

        b_back = view.findViewById(R.id.b_back_menu);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuFragmentInterface.startMainMenuFragment();
        }
        });

        return view;
    }


    @Override
    public void onItemClick(int position) {
        Fragment addToFragment = new AddToFragment();
        Bundle bundle = new Bundle();
        MenuItem clickedItem = itemList.get(position);

        bundle.putString("item_name", clickedItem.name);
        bundle.putString("item_type", clickedItem.type);
        bundle.putString("item_desc", clickedItem.description);
        bundle.putDouble("item_price", clickedItem.unitPrice);
        bundle.putInt("item_id", clickedItem.id);

        addToFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, addToFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    public void displayMenuItems(View v) {
        viewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        mAdapter = new MenuAdapter(getContext(), itemList);
        recyclerView.setAdapter(mAdapter);
        viewModel.getMenuItemsLiveData().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                mAdapter.setMenuItems(menuItems);
                mAdapter.notifyDataSetChanged();
                itemList = menuItems;
            }
        });
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        menuFragmentInterface = (MenuFragment.MenuFragmentInterface)context;
    }

}
