package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import Fragments.Exercises;
import Fragments.FoodIntake;
import Fragments.Histograph;
import Fragments.MenuFragment;
import Fragments.PedometerSettings;

public class MainActivity extends AppCompatActivity implements MenuFragment.onMenuItemClicked, SensorEventListener{
    MenuFragment menuFragment = new MenuFragment();
    private SensorManager sensorManager;
    private float steps=0;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.steps=sensorEvent.values[0];
        Toast.makeText(this,steps+"steps",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

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
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        MenuFragment menu = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,menu).commit();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Sensor stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounter != null){
            sensorManager.registerListener(this,stepCounter,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Count sensor not available",Toast.LENGTH_LONG).show();
        }

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

