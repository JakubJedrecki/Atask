package com.example.task.interviewtask.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.interviewtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvProductName)
    public TextView productName;

    @BindView(R.id.tvProductPrice)
    public TextView productPrice;

    @BindView(R.id.btnAddToBasket)
    public ImageView btnAddToBasket;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
