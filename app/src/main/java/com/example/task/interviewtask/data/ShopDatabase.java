package com.example.task.interviewtask.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.task.interviewtask.data.dao.BasketProductDao;
import com.example.task.interviewtask.data.dao.CurrencyDao;
import com.example.task.interviewtask.data.dao.ProductDao;
import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.data.entities.CurrencyEntity;
import com.example.task.interviewtask.data.entities.ProductEntity;

@Database(entities = {ProductEntity.class, BasketProductEntity.class, CurrencyEntity.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract BasketProductDao basketProductDao();
    public abstract CurrencyDao currencyDao();

    private static ShopDatabase INSTANCE;

    public static ShopDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShopDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShopDatabase.class, "shop_database")
                            .addCallback(shopDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback shopDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ProductDao productDao;

        PopulateDbAsync(ShopDatabase db) {
            productDao = db.productDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productDao.deleteAllProducts();

            ProductEntity productEntity = new ProductEntity("Coffee (240g)", 2.30);
            productDao.insert(productEntity);
            productEntity = new ProductEntity("Tea (box of 60)", 3.10);
            productDao.insert(productEntity);
            productEntity = new ProductEntity("Sugar (1kg)", 2.00);
            productDao.insert(productEntity);
            productEntity = new ProductEntity("Milk (bottle)", 1.20);
            productDao.insert(productEntity);
            productEntity = new ProductEntity("Cup", 0.50);
            productDao.insert(productEntity);
            return null;
        }
    }
}
