package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miikka on 9/18/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int dbVersion = 1;
    private static String dbName = "caloriesDB";
    //Basic Constructor
    public DatabaseHelper(Context context){
        super (context,dbName,null,dbVersion);
    }

    //Database Creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating Pedometer Setting table that contains Users weight, Steps per Mile and walking Speed.
        String sql = "CREATE TABLE settings (weight FLOAT, sLength INTEGER);";
        db.execSQL(sql);
        //Creates Exercises table that contains the name, intensity level and duration of the exercise.
        sql = "CREATE TABLE exercises (id INTEGER PRIMARY KEY, exercise TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE myExercise (id INTEGER PRIMARY KEY , intensity INTEGER, duration INTEGER, caloriesBurnt INTEGER, cTime TEXT," +
                " eid INTEGER, FOREIGN KEY(eid) REFERENCES exercise(id) );";
        db.execSQL(sql);
        //Creates Food and Eaten tables that contain data about what the user have eaten and how many calories.
        sql = "CREATE TABLE food (foodName TEXT, calories INT, id INTEGER PRIMARY KEY );";
        db.execSQL(sql);
        sql = "CREATE TABLE eaten (id INTEGER PRIMARY KEY , amount INTEGER, cTime TEXT, fid INTEGER, FOREIGN KEY(fid) REFERENCES food(id) );";
        db.execSQL(sql);
        //Inserts Dummy Data for now.
        sql = "INSERT INTO settings (weight, sLength, speed) VALUES (\"80.5\",\"100\");";
        db.execSQL(sql);
        Log.d("Inserting this:", sql);
        sql = "INSERT INTO exercises (id,exercise) VALUES (1,\"Walking\");";
        db.execSQL(sql);
        sql = "INSERT INTO food (foodName, calories) VALUES (\"Hamburger\",\"500\");";
        db.execSQL(sql);
        Log.d("Inserting this:", sql);
        sql = "INSERT  INTO eaten (amount, cTime, fid) VALUES (\"200\",\"2017-9-25 11:21:00\",\"1\");";
        db.execSQL(sql);
        Log.d("Inserting this:", sql);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    //Method for inserting exercise to the database.
    public void insertExercise(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO exercises (exercise) VALUES(\""+exerciseName+"\");");
        statement.execute();
        statement.close();
        db.close();
    }

    //Method for inserting myExercise information to the database.
    public void insertMyExercise(int intensity, int duration, int caloriesBurnt, int eid, Timestamp time){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO myExercise (intensity, duration, caloriesBurnt, cTime, eid)" +
                " VALUES("+intensity+", "+duration+", " +
                ""+caloriesBurnt+", \"" + time + "\", "+eid+");");
        statement.execute();
        Log.d("Inserting",statement.toString());
        statement.close();
        db.close();
    }
    //inserts food to database
    public void insertFood(Food food){
        String name = food.getName();
        int calories = food.getCalories();
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO food (foodName, calories) VALUES(\""+name+"\",\""+calories+"\");");
        statement.execute();
        statement.close();
        db.close();
    }
    //inserts eaten data to database
    public void insertEaten(Eaten eaten){
        int amount = eaten.getAmount();
        Timestamp time = eaten.getTime();
        int fid = eaten.getFid();
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO eaten (amount,cTime,fid) VALUES (\""+amount+"\",\""+time+"\",\""+fid+"\");");
        statement.execute();
        statement.close();
        db.close();
    }

    //Method for searching the Exercise id to be used in myExercise (for linking myExercise with exercise)
    public int getEidByName(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM exercises WHERE exercise = \""+exerciseName+"\";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        db.close();
        return result;
    }
    //Gets the FoodID by food name. Used for linking food table with eaten.
    public int getFidByName(String foodName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id FROM food WHERE food = \""+foodName+"\";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        db.close();
        return result;
    }

    public List getData(int eid){
        List values = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT intensity,duration FROM myExercise WHERE eid = "+eid+";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            int result = cursor.getInt(0);
            int result2 = cursor.getInt(1);
            values.add(result);
            values.add(result2);
        }
        db.close();
        return values;
    }
    //Gets all the exercises and returns them as a list.
    public List<Exercise> getExercies(){
        List exercises = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT exercise,id from exercises ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Exercise someExercise = new Exercise();
            someExercise.setExerciseName(cursor.getString(0));
            someExercise.setId(cursor.getInt(1));
            exercises.add(someExercise);
        }

        db.close();
        return exercises;
    }

    //Gets all MyExercises and returns them as a list.
        public List<MyExercise> getMyExercies(){
        List myExercises = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id,intensity,duration,caloriesBurnt,eid,cTime from myExercises ORDER BY id DESC";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            MyExercise someExercise = new MyExercise();
            someExercise.setId(cursor.getInt(0));
            someExercise.setIntensity(cursor.getInt(1));
            someExercise.setDuration(cursor.getInt(2));
            someExercise.setCaloriesBurnt(cursor.getInt(3));
            someExercise.setEid(cursor.getInt(4));
            myExercises.add(someExercise);
        }
        db.close();
        return myExercises;
    }
}

