package com.example.task.interviewtask.database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.task.interviewtask.data.ShopDatabase;
import com.example.task.interviewtask.data.dao.ProductDao;
import com.example.task.interviewtask.data.entities.ProductEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ProductTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    private ProductDao productDao;
    private ShopDatabase shopDatabase;

    @Before
    public void setUp() {

        Context context = InstrumentationRegistry.getTargetContext();
        shopDatabase = Room.inMemoryDatabaseBuilder(context, ShopDatabase.class)
                        .allowMainThreadQueries()
                        .build();
        productDao = shopDatabase.productDao();
    }

    @After
    public void cleanUp() {
        shopDatabase.close();
    }

    @Test
    public void addAndRetrieveProductFromDb() {
        ProductEntity productEntity = new ProductEntity("Test Product", 3.00);
        productDao.insert(productEntity);

        ProductEntity result = productDao.getProduct(1);
        assertThat(result.getProductName(), is(equalTo(productEntity.getProductName())));
        assertThat(result.getPrice(), is(equalTo(productEntity.getPrice())));
    }

    @Test
    public void retrieveListOfProducts() {

        ProductEntity productEntity = new ProductEntity("Sugar (1kg)", 2.00);
        productDao.insert(productEntity);
        productEntity = new ProductEntity("Tea (box of 60)", 3.10);
        productDao.insert(productEntity);
        productEntity = new ProductEntity("Sugar (1kg)", 2.00);
        productDao.insert(productEntity);

        productDao.getProducts().observeForever(productEntities -> {
            assertThat(productEntities.size(), is(equalTo(3)));
        });
    }

    @Test
    public void removeAllProducts() {

        ProductEntity productEntity = new ProductEntity("Sugar (1kg)", 2.00);
        productDao.insert(productEntity);
        productEntity = new ProductEntity("Tea (box of 60)", 3.10);
        productDao.insert(productEntity);
        productEntity = new ProductEntity("Sugar (1kg)", 2.00);
        productDao.insert(productEntity);

        productDao.deleteAllProducts();

        productDao.getProducts().observeForever(productEntities -> {
            assertThat(productEntities.size(), is(equalTo(0)));
        });
    }
}
