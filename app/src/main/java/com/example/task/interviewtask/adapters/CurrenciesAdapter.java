package com.example.task.interviewtask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.viewholders.CurrencyViewHolder;
import com.example.task.interviewtask.data.entities.CurrencyEntity;

import java.util.List;

public class CurrenciesAdapter extends RecyclerView.Adapter<CurrencyViewHolder> {

    private List<CurrencyEntity> currencies;
    private CurrenciesAdapterListener listener;
    private Context context;

    public CurrenciesAdapter(CurrenciesAdapterListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_currency, parent, false);

        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        if(currencies != null) {
            holder.currency.setText(currencies.get(position).getName());
            holder.currency.setOnClickListener(v -> {
                if(listener != null) {
                    listener.onCurrencyClicked(currencies.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(currencies != null) {
            return currencies.size();
        } else {
            return 0;
        }
    }

    public void setCurrencies(List<CurrencyEntity> currencies) {
        this.currencies = currencies;
        notifyDataSetChanged();
    }

    public interface CurrenciesAdapterListener {
        void onCurrencyClicked(CurrencyEntity currencyEntity);
    }
}
