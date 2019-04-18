package com.example.fruitsstockapp.interfaces;

import com.example.fruitsstockapp.model.Fruit;

public interface IFruitsInStockListener {

    void addToCart(Fruit fruit);

    void deleteFruit(Fruit fruit);

    void updateFruit(Fruit fruit);

}
