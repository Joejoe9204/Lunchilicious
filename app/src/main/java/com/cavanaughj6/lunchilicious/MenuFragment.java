package com.cavanaughj6.lunchilicious;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


public class MenuFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;
    MenuViewModel viewModel;
    List<MenuItem> items;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);

        viewModel.getMenuItemsLiveData().observe(this, new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                mAdapter.setMenuItems(menuItems);
                items = menuItems;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.myrecycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new MenuAdapter(getContext(), items);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}