package com.example.task.interviewtask.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.task.interviewtask.data.ShopDatabase;
import com.example.task.interviewtask.data.dao.BasketProductDao;
import com.example.task.interviewtask.data.entities.BasketProductEntity;

import java.util.List;

public class BasketProductRepository {
    private BasketProductDao basketProductDao;
    private LiveData<List<BasketProductEntity>> basketProductEntities;

    public BasketProductRepository(Application application) {
        ShopDatabase shopDatabase = ShopDatabase.getDatabase(application);
        basketProductDao = shopDatabase.basketProductDao();
        basketProductEntities = basketProductDao.getBasketProducts();
    }

    /**
     * Inserts new basket product to database
     * @param basketProductEntity - product to be added
     */
    public void addProductToBasket(BasketProductEntity basketProductEntity) {
        new insertAsyncTask(basketProductDao).execute(basketProductEntity);
    }

    /**
     * Removes selected product from basket
     * @param basketProductEntity - product to be removed
     */
    public void removeProductFromBasket(BasketProductEntity basketProductEntity) {
        new removeAsyncTask(basketProductDao).execute(basketProductEntity);
    }

    /**
     * Retrieves basket product with given name from database
     * @param name - product name
     * @return
     */
    public BasketProductEntity getBasketProductByName(String name) {
        return basketProductDao.getBasketProduct(name);
    }

    /**
     * Retrieve all basket products
     * @return
     */
    public LiveData<List<BasketProductEntity>> getBasketProducts() {
        return basketProductEntities;
    }

    private static class insertAsyncTask extends AsyncTask<BasketProductEntity, Void, Void> {
        private BasketProductDao asyncTaskDao;

        insertAsyncTask(BasketProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final BasketProductEntity... basketProductEntities) {
            if (asyncTaskDao.getBasketProduct(basketProductEntities[0].getProductName()) != null) {
                BasketProductEntity  entity = asyncTaskDao.getBasketProduct(basketProductEntities[0].getProductName());
                entity.setQuantity(entity.getQuantity() + 1);
                asyncTaskDao.update(entity);
            } else {
                asyncTaskDao.insert(basketProductEntities[0]);
            }
            return null;
        }
    }

    private static class removeAsyncTask extends AsyncTask<BasketProductEntity, Void, Void> {
        private BasketProductDao asyncTaskDao;

        removeAsyncTask(BasketProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(BasketProductEntity... basketProductEntities) {

            asyncTaskDao.deleteBasketProduct(basketProductEntities[0]);

            return null;
        }
    }
}
