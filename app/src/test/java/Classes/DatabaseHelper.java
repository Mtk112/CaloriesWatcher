package Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        addDummyData(db);


    }
    public void addDummyData(SQLiteDatabase db){
        String sql = "INSERT INTO settings (weight, spm, speed) VALUES (\"80.5\",\"2000\",\"4\")";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
