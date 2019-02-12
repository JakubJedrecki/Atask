package com.example.task.interviewtask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.viewholders.BasketProductViewHolder;
import com.example.task.interviewtask.data.entities.BasketProductEntity;

import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketProductViewHolder> {

    private List<BasketProductEntity> basketProductEntities;
    private BasketAdapterListener listener;
    private Context context;

    public BasketAdapter(BasketAdapterListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public BasketProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_basket_product, parent, false);
        return new BasketProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketProductViewHolder holder, int position) {
        if (basketProductEntities != null) {
            holder.productName.setText(basketProductEntities.get(position).getProductName());
            //holder.productQuantity.setText( "Quantity " + String.valueOf(basketProductEntities.get(position).getQuantity()));
            holder.productQuantity.setText( String.format(context.getString(R.string.txt_quantity), basketProductEntities.get(position).getQuantity()));
            holder.btnRemoveFromBasket.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRemoveProductClicked(basketProductEntities.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(basketProductEntities != null) {
            return basketProductEntities.size();
        } else {
            return 0;
        }
    }

    public void setEntities(List<BasketProductEntity> entities) {
        basketProductEntities = entities;
        notifyDataSetChanged();
    }

    public interface BasketAdapterListener {
        void onRemoveProductClicked(BasketProductEntity entity);
    }
}
