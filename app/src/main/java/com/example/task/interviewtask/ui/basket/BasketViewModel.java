package com.example.task.interviewtask.ui.basket;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.data.entities.ProductEntity;
import com.example.task.interviewtask.data.repositories.BasketProductRepository;

import java.util.List;

public class BasketViewModel extends AndroidViewModel {
    private LiveData<List<BasketProductEntity>> basketProducts;
    private BasketProductRepository basketProductRepository;

    public BasketViewModel(@NonNull Application application) {
        super(application);
        basketProductRepository = new BasketProductRepository(application);
        basketProducts = basketProductRepository.getBasketProducts();
    }

    public BasketProductEntity getBasketProductByName(String name) {
        return basketProductRepository.getBasketProductByName(name);
    }

    /**
     * Returns all basket products
     * @return
     */
    public LiveData<List<BasketProductEntity>> getProductsInBasket() {
        return basketProducts;
    }

    public void removeFromBasket(BasketProductEntity entity) {
        basketProductRepository.removeProductFromBasket(entity);
    }
}
