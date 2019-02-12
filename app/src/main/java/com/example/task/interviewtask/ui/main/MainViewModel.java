package com.example.task.interviewtask.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.data.repositories.BasketProductRepository;
import com.example.task.interviewtask.data.repositories.ProductRepository;
import com.example.task.interviewtask.data.entities.ProductEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<ProductEntity>> productEntities;
    private ProductRepository productRepository;
    private BasketProductRepository basketProductRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        productRepository = new ProductRepository(application);
        basketProductRepository = new BasketProductRepository(application);
        productEntities = productRepository.getProductEntities();
    }

    /**
     * Returns all products
     * @return
     */
    public LiveData<List<ProductEntity>> getProductEntities() {
        return productEntities;
    }

    /**
     * insert product into database
     * @param productEntity
     */
    public void insertProduct(ProductEntity productEntity) {
        productRepository.addProduct(productEntity);
    }

    /**
     * Adds given product to basket db table
     * @param productEntity
     */
    public void addToBasket(ProductEntity productEntity) {
        basketProductRepository.addProductToBasket(new BasketProductEntity(productEntity.getProductName(), productEntity.getPrice()));
    }
}
