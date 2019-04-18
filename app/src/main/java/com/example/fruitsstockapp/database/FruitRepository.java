package com.example.fruitsstockapp.database;


import android.app.Application;
import android.os.AsyncTask;

import com.example.fruitsstockapp.model.Fruit;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FruitRepository {
    private FruitDao fruitDao;
    private LiveData<List<Fruit>> allFruitsInCart;
    private LiveData<List<Fruit>> allFruitsInStock;

    public FruitRepository(Application application) {
        FruitDatabase fruitDatabase = FruitDatabase.getInstance(application);
        fruitDao = fruitDatabase.fruitDao();
        allFruitsInCart = fruitDao.getAllFruitsInCart();
        allFruitsInStock = fruitDao.getAllFruitsInStock();
    }

    public LiveData<List<Fruit>> getAllFruitsInCart() {
        return allFruitsInCart;
    }

    public LiveData<List<Fruit>> getAllFruitsInStock() {
        return allFruitsInStock;
    }

    public void insert(Fruit fruit) {
        new InsertAsyncTask(fruitDao).execute(fruit);
    }

    public void update(Fruit fruit) {
        new UpdateAsyncTask(fruitDao).execute(fruit);
    }

    public void delete(Fruit fruit) {
        new DeleteAsyncTask(fruitDao).execute(fruit);
    }

    private static class UpdateAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        public UpdateAsyncTask(FruitDao fruitDao) {
            this.fruitDao = fruitDao;
        }

        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.update(fruits[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        public DeleteAsyncTask(FruitDao fruitDao) {
            this.fruitDao = fruitDao;
        }

        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.delete(fruits[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Fruit, Void, Void> {

        private FruitDao fruitDao;

        InsertAsyncTask(FruitDao fruitDao) {
            this.fruitDao =fruitDao;
        }

        @Override
        protected Void doInBackground(Fruit... fruits) {
            fruitDao.insert(fruits[0]);
            return null;
        }
    }

}
