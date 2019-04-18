package com.example.fruitsstockapp.database;

import com.example.fruitsstockapp.model.Fruit;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FruitDao {

    @Insert
    void insert(Fruit fruit);

    @Update
    void update(Fruit fruit);

    @Delete
    void delete(Fruit fruit);

    @Query("SELECT * FROM fruit_table WHERE inStockCount > 0")
    LiveData<List<Fruit>> getAllFruitsInStock();

    @Query("SELECT * FROM fruit_table WHERE isInCart")
    LiveData<List<Fruit>> getAllFruitsInCart();

    @Insert
    void insertAll(Fruit... fruits);

}
