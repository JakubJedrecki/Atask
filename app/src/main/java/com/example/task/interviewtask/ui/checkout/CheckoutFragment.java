package com.example.task.interviewtask.ui.checkout;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.CurrenciesAdapter;
import com.example.task.interviewtask.data.entities.CurrencyEntity;
import com.example.task.interviewtask.util.InetConnectionHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CheckoutFragment extends Fragment implements CurrenciesAdapter.CurrenciesAdapterListener {

    @BindView(R.id.txtTotalPrice)
    TextView totalPrice;

    @BindView(R.id.txtSelectedCurrency)
    TextView selectedCurrency;

    @BindView(R.id.currenciesRecyclerView)
    RecyclerView currenciesRecyclerView;

    private Unbinder unbinder;
    private CheckoutViewModel checkoutViewModel;
    private CurrenciesAdapter recyclerViewAdapter;

    public static CheckoutFragment newInstance() { return new CheckoutFragment(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkoutViewModel = ViewModelProviders.of(this).get(CheckoutViewModel.class);
        checkoutViewModel.getProductsInBasket().observe(this, basketProductEntities ->
                totalPrice.setText(String.valueOf(checkoutViewModel.getTotalPrice(basketProductEntities))));
        if(checkInternetConnection()) {
            checkoutViewModel.getCurrencyList().observe(this, currencyEntities -> {
                recyclerViewAdapter.setCurrencies(currencyEntities);
                checkoutViewModel.calculatePriceInEuro(currencyEntities);
            });
        }
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
        currenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewAdapter = new CurrenciesAdapter(this, getActivity());
        currenciesRecyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onCurrencyClicked(CurrencyEntity currencyEntity) {
        selectedCurrency.setText(currencyEntity.getName());
        totalPrice.setText(String.valueOf(checkoutViewModel.currencyChanged(currencyEntity)));
    }

    @OnClick(R.id.refreshBtn)
    public void onRefreshCurrenciesClicked() {
        if (checkInternetConnection()) {
            checkoutViewModel.refreshCurrencies();
        }
    }

    private boolean checkInternetConnection() {
        InetConnectionHelper inetConnectionHelper = new InetConnectionHelper();

        if(inetConnectionHelper.isNetworkAvailable(getActivity())) {
            return true;
        }
        Toast.makeText(getActivity(), "Please check your internet connection.", Toast.LENGTH_LONG).show();
        return false;
    }
}
