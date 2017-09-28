package com.example.miikka.calorieswatcher;

/**
 * Created by ville on 9/28/2017.
 */

public class Eaten {
    Food food;
    int amount;//grams

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Eaten() {
    }

    public Eaten(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }
}
