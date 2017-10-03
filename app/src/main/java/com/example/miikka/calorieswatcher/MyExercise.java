package com.example.miikka.calorieswatcher;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Miikka on 9/28/2017.
 */

public class MyExercise {
    private int id,intensity,duration,caloriesBurnt,eid;
    private Timestamp time;

    //Constructors
    public MyExercise(int id, int intensity, int duration, int caloriesBurnt, int eid, Timestamp time){
        this.id = id;
        this.intensity = intensity;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.eid = eid;
        this.time = time;
    }
    public MyExercise(int intensity, int duration, int caloriesBurnt, int eid, Timestamp time){
        this.intensity = intensity;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.eid = eid;
        this.time = time;
    }
    public MyExercise(){

    }

    //Getters and Setters
    public Timestamp getTime(){ return time;}

    public void setTime(Timestamp time){
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(int caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }
}
