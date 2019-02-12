package com.example.task.interviewtask.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.task.interviewtask.data.ShopDatabase;
import com.example.task.interviewtask.data.dao.ProductDao;
import com.example.task.interviewtask.data.entities.ProductEntity;

import java.util.List;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<ProductEntity>> productEntities;

    public ProductRepository(Application application) {
        ShopDatabase shopDatabase = ShopDatabase.getDatabase(application);
        productDao = shopDatabase.productDao();
        productEntities = productDao.getProducts();
    }

    /**
     * Inserts new product to database
     * @param productEntity
     */
    public void addProduct(ProductEntity productEntity) {
        new insertAsyncTask(productDao).execute(productEntity);
    }

    /**
     * Retrieves all products from database
     * @return
     */
    public LiveData<List<ProductEntity>> getProductEntities() {
        return productEntities;
    }

    private static class insertAsyncTask extends AsyncTask<ProductEntity, Void, Void> {

        private ProductDao asyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ProductEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
