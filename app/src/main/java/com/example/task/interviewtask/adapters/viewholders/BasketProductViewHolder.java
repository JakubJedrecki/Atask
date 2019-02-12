package com.example.task.interviewtask.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.interviewtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvProductName)
    public TextView productName;

    @BindView(R.id.tvQuantity)
    public TextView productQuantity;

    @BindView(R.id.btnRemoveFromBasket)
    public ImageView btnRemoveFromBasket;

    public BasketProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
