package com.example.miikka.calorieswatcher;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by ville on 9/28/2017.
 */

public class Eaten {
    Food food;
    int amount;//grams
    int fid;
    Timestamp time;
    int id;

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

    public int getFid(){return food.getId();}

    public void setFid(int fid) {this.fid = fid;}

    public void setId(int id){this.id = id;}

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time){this.time = time;}

    public Eaten() {
    }

    public Eaten(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public Eaten(Food food, int amount,int fid, Timestamp time) {
        this.food = food;
        this.amount = amount;
        this.fid = fid;
        this.time = time;
    }

}
