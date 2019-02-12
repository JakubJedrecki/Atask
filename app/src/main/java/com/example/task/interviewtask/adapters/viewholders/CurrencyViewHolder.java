package com.example.task.interviewtask.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.task.interviewtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvCurrency)
    public TextView currency;

    public CurrencyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
