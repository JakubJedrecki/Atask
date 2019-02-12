package com.example.task.interviewtask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task.interviewtask.R;
import com.example.task.interviewtask.adapters.viewholders.ProductViewHolder;
import com.example.task.interviewtask.data.entities.ProductEntity;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<ProductEntity> productEntities;
    private ProductsAdapterListener listener;
    private Context context;

    public ProductsAdapter(ProductsAdapterListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        if (productEntities != null) {
            holder.productName.setText(productEntities.get(position).getProductName());
            holder.productPrice.setText(String.format(context.getString(R.string.txt_price), productEntities.get(position).getPrice()));
            holder.productPrice.setText(String.format(context.getString(R.string.txt_price), productEntities.get(position).getPrice()));
            holder.btnAddToBasket.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAddProductClicked(productEntities.get(position));
                }
            });
        }
    }

    public void setProductEntities(List<ProductEntity> products) {
        productEntities = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(productEntities != null) {
            return productEntities.size();
        } else {
            return  0;
        }
    }

    public interface ProductsAdapterListener {
        void onAddProductClicked(ProductEntity productEntity);
    }
}
