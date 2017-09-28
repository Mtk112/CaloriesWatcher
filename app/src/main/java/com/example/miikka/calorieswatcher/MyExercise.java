package com.example.miikka.calorieswatcher;

/**
 * Created by Miikka on 9/28/2017.
 */

public class MyExercise {
    private int id,intensity,duration,caloriesBurnt,eid;

    //Constructors
    public MyExercise(int id, int intensity, int duration, int caloriesBurnt, int eid){
        this.id = id;
        this.intensity = intensity;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.eid = eid;
    }
    public MyExercise(int intensity, int duration, int caloriesBurnt, int eid){
        this.intensity = intensity;
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
        this.eid = eid;
    }
    public MyExercise(){

    }

    //Getters and Setters

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
