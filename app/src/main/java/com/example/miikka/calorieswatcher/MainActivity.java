package com.example.miikka.calorieswatcher;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Fragments.Exercises;
import Fragments.FoodIntake;
import Fragments.Histograph;
import Fragments.MenuFragment;
import Fragments.PedometerSettings;

public class MainActivity extends AppCompatActivity implements MenuFragment.onMenuItemClicked{
    MenuFragment menuFragment = new MenuFragment();
    Exercises exercises = new Exercises();
    Histograph histograph= new Histograph();
    FoodIntake foodIntake = new FoodIntake();
    PedometerSettings pedometerSettings = new PedometerSettings();
    FragmentTransaction transaction;

    @Override
    public void onNewFragmentSelected(int fragment){
        switch(fragment){
            case(1):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,histograph);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case(2):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,foodIntake);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case(3):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,exercises);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case(4):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,pedometerSettings);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Histograph hFrag = new Histograph();
        //Exercises hFrag = new Exercises();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate, hFrag).commit();
        MenuFragment menu = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,menu).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}

