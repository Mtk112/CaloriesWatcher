package com.example.miikka.calorieswatcher;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by ville on 9/28/2017.
 */

public class Eaten {
    int amount;//grams
    int fid;
    Timestamp time;
    int id;



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFid(){return fid;}

    public void setFid(int fid) {this.fid = fid;}

    public void setId(int id){this.id = id;}

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time){this.time = time;}

    public Eaten() {
    }

    public Eaten(int amount,Timestamp time, int fid) {
        this.amount = amount;
        this.time = time;
        this.fid = fid;
    }

    public Eaten(int id, int amount,int fid, Timestamp time) {
        this.id = id;
        this.amount = amount;
        this.fid = fid;
        this.time = time;
    }

}
