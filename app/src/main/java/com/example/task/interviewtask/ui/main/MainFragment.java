package com.example.task.interviewtask.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task.interviewtask.MainActivity;
import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.ProductsAdapter;
import com.example.task.interviewtask.data.entities.ProductEntity;
import com.example.task.interviewtask.ui.basket.BasketFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment implements ProductsAdapter.ProductsAdapterListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MainViewModel viewModel;
    private ProductsAdapter recyclerViewAdapter;
    private Unbinder unbinder;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        initRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getProductEntities().observe(this, productEntities -> {
            recyclerViewAdapter.setProductEntities(productEntities);
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem goToBasket = menu.getItem(0);
        if (!goToBasket.isVisible()) {
            goToBasket.setVisible(true);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_basket:
                item.setVisible(false);
                ((MainActivity) getActivity()).replaceFragment(BasketFragment.newInstance());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * Initializes RecyclerView
     */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new ProductsAdapter(this, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onAddProductClicked(ProductEntity productEntity) {
        viewModel.addToBasket(productEntity);
        Toast.makeText(getActivity(), R.string.txt_added_to_basket, Toast.LENGTH_LONG).show();
    }


}
