package com.example.fruitsstockapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fruit_table")
public class Fruit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String fruitName;

    private int inStockCount;

    private int inCartCount;

    private boolean isInCart;

    public Fruit() {
    }

    public Fruit(String fruitName, int inStockCount, int inCartCount, boolean isInCart) {
        this.fruitName = fruitName;
        this.inStockCount = inStockCount;
        this.inCartCount = inCartCount;
        this.isInCart = isInCart;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getInStockCount() {
        return inStockCount;
    }

    public void setInStockCount(int inStockCount) {
        this.inStockCount = inStockCount;
    }

    public int getInCartCount() {
        return inCartCount;
    }

    public void setInCartCount(int inCartCount) {
        this.inCartCount = inCartCount;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    public static Fruit[] populateDatabase() {
        return new Fruit[] {
            new Fruit("Mango", 10, 5, true),
            new Fruit("Banana", 0, 0, false),
            new Fruit("Apple", 0, 10, true),
            new Fruit("Chickoo", 50, 0, false),
        };
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
