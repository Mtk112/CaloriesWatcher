package com.example.miikka.calorieswatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Fragments.Exercises;
import Fragments.Histograph;

public class MainActivity extends AppCompatActivity {
//jottain
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //Histograph hFrag = new Histograph();
    Exercises hFrag = new Exercises();
    getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate, hFrag).commit();

}
}
