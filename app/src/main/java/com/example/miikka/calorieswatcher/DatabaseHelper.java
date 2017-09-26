package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

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
        String sql = "CREATE TABLE settings (weight FLOAT, spm INTEGER, speed INTEGER);";
        db.execSQL(sql);
        //Creates Exercises table that contains the name, intensity level and duration of the exercise.
        sql = "CREATE TABLE exercises (exercise TEXT, _id INTEGER PRIMARY KEY AUTOINCREMENT);";
        db.execSQL(sql);
        sql = "CREATE TABLE myExercise (_id INTEGER PRIMARY KEY AUTOINCREMENT, intensity INTEGER, duration INTEGER,caloriesBurnt INTEGER, eid INTEGER FOREIGN KEY(eid) REFERENCES exercise(_id) );";
        db.execSQL(sql);
        //Creates Food and Eaten tables that contain data about what the user have eaten and how many calories.
        sql = "CREATE TABLE food (food TEXT, calories INT, _id INTEGER PRIMARY KEY AUTOINCREMENT));";
        db.execSQL(sql);
        sql = "CREATE TABLE eaten (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount INTEGER, time TIMESTAMP, fid INTEGER, FOREIGN KEY(fid) REFERENCES food(_id) );";
        //Inserts Dummy Data for now.
        addDummyData(db);



    }
    //Method to add Dummy data... for science!
    private void addDummyData(SQLiteDatabase db){
        String sql = "INSERT INTO settings (weight, spm, speed) VALUES (\"80.5\",\"2000\",\"4\");";
        db.execSQL(sql);
        sql = "INSERT INTO exercises (exercise) VALUES (\"Competive football match\");";
        db.execSQL(sql);
        sql = "INSERT INTO myExercise (intensity,duration,caloriesBurnt, eid) VALUES (\"3\",\"90\",\"1050\",\"1\");";
        db.execSQL(sql);
        sql = "INSERT INTO food (food, calories) VALUES (\"Hamburger\",\"500\");";
        db.execSQL(sql);
        sql = "INSERT  INTO eaten (amount, time, fid) VALUES (\"200\",\"2017-9-25 11:21:00\",\"1\");";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    //Method for inserting exercise to the database.
    public void insertExercise(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO exercise (exercise) VALUES(\""+exerciseName+"\");");
        statement.execute();
        statement.close();
        db.close();
    }
    //Method for inserting myExercise information to the database.
    public void insertMyExercise(int intensity, int duration, int caloriesBurnt, int eid){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("INSERT INTO myExercise (intensity,duration,caloriesBurnt,eid) VALUES(\""+intensity+"\",\""+duration+"\"," +
                "\""+caloriesBurnt+"\",\""+eid+"\");");
        statement.execute();
        statement.close();
        db.close();
    }

    //Method for searching the Exercise id to be used in myExercise (for linking myExercise with exercise)
    public int getEidByName(String exerciseName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT _id from exercise WHERE exerciseName = "+exerciseName+";";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        int result = cursor.getInt(0);
        db.close();
        return result;
    }
}

