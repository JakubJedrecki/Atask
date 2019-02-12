package com.example.task.interviewtask.ui.basket;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.interviewtask.MainActivity;
import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.BasketAdapter;
import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.ui.checkout.CheckoutFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BasketFragment extends Fragment implements BasketAdapter.BasketAdapterListener {

    @BindView(R.id.basketRecyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private BasketViewModel basketViewModel;
    private BasketAdapter recyclerViewAdapter;

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        unbinder = ButterKnife.bind(this, view);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        basketViewModel = ViewModelProviders.of(this).get(BasketViewModel.class);
        basketViewModel.getProductsInBasket().observe(this, basketProductEntities -> recyclerViewAdapter.setEntities(basketProductEntities));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
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
        recyclerViewAdapter = new BasketAdapter(this, getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onRemoveProductClicked(BasketProductEntity entity) {
        basketViewModel.removeFromBasket(entity);
    }

    @OnClick(R.id.btnCheckout)
    public void onCheckoutClicked() {
        ((MainActivity) getActivity()).replaceFragment(CheckoutFragment.newInstance());
    }
}
