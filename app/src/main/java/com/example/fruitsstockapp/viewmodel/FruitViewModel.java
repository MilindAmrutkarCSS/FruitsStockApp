package com.example.fruitsstockapp.viewmodel;

import android.app.Application;

import com.example.fruitsstockapp.database.FruitRepository;
import com.example.fruitsstockapp.model.Fruit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FruitViewModel extends AndroidViewModel {

    private FruitRepository fruitRepository;
    private LiveData<List<Fruit>> allFruitsInCart;
    private LiveData<List<Fruit>> allFruitsInStock;

    public FruitViewModel(@NonNull Application application) {
        super(application);
        fruitRepository = new FruitRepository(application);
        allFruitsInCart = fruitRepository.getAllFruitsInCart();
        allFruitsInStock = fruitRepository.getAllFruitsInStock();
    }

    public void insert(Fruit fruit) {
        fruitRepository.insert(fruit);
    }

    public void update(Fruit fruit) {
        fruitRepository.update(fruit);
    }

    public void delete(Fruit fruit) {
        fruitRepository.delete(fruit);
    }

    public LiveData<List<Fruit>> getAllFruitsInCart() {
        return allFruitsInCart;
    }

    public LiveData<List<Fruit>> getAllFruitsInStock() {
        return allFruitsInStock;
    }


}
