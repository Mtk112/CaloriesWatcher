package com.example.miikka.calorieswatcher;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Calendar;

import Fragments.Exercises;
import Fragments.FoodIntake;
import Fragments.Histograph;
import Fragments.MenuFragment;
import Fragments.MoveFragment;
import Fragments.PedometerSettings;

/**
 * CaloriesWatched MainActivity, runs when the application is started
 */
public class MainActivity extends AppCompatActivity implements MenuFragment.onMenuItemClicked, SensorEventListener, View.OnClickListener{
    MenuFragment menuFragment = new MenuFragment();
    private SensorManager sensorManager;
    private float steps=0;
    private float lastSteps=0;
    private Handler handler = new Handler();
    DatabaseHelper dbHelper;
    Timestamp time;
    boolean activityRunning;
    UserSettings userSettings;

    /**
     * sensor change listener
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.steps=sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    Exercises exercises = new Exercises();
    Histograph histograph= new Histograph();
    FoodIntake foodIntake = new FoodIntake();
    MoveFragment moveFragment = new MoveFragment();
    PedometerSettings pedometerSettings = new PedometerSettings();
    FragmentTransaction transaction;

    /**
     * Loads the selected fragment when called
     * 0=menuFragment,1=histograph,2=foodIntake,3=exercises,4=pedometerSettings,5=moveFragment
     */
    @Override
    public void onNewFragmentSelected(int fragment){
        switch(fragment){
            case(0):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,menuFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
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
            case(5):
                transaction=getSupportFragmentManager().beginTransaction().replace(R.id.fragTemplate,moveFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }
    /**
     * Method that is run when application is started, starts the step sensor listener and calls the method that starts default fragment(menu)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        final FloatingActionButton faButton = (FloatingActionButton)findViewById(R.id.fab);
        faButton.setOnClickListener(this);
        dbHelper= new DatabaseHelper(this);
        userSettings=dbHelper.getUserSettings();
        onNewFragmentSelected(0);
        handler.postDelayed(runnable,3600000);
    }
    /**
     *
     */
    @Override
    protected void onPause(){
        super.onPause();
        activityRunning=false;
    }
    /**
     * Timer method so the application creates exercises from steps taken once an hour
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int stepLength=userSettings.getStepLength();
            int weight=userSettings.getWeight();
            if(lastSteps != steps){
                float distance = (steps-lastSteps)*stepLength/100;
                float speed = distance/1000;
                int calories = (int)((0.0171*speed*speed*speed)-(0.1062*speed*speed)+(0.8710*speed)+1.4577)*weight;//TODO fix the calculation with proper values maybe
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                time = new java.sql.Timestamp(now.getTime());
                dbHelper.insertMyExercise(1,60,calories,1,time);
            }
            lastSteps=steps;
            handler.postDelayed(runnable,3600000);
        }
    };

    /**
     * Inflates the menu for the actionbar
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    /**
     * Actionbar click method, listens to clicked items in the actionbar
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                onNewFragmentSelected(4);
                return true;
            case R.id.action_home:
                onNewFragmentSelected(0);
                return true;
            case R.id.action_move:
                onNewFragmentSelected(5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     *
     */
    @Override
    protected void onResume(){
        super.onResume();
        activityRunning=true;
        Sensor stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (stepCounter != null){
            sensorManager.registerListener(this,stepCounter,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this,"Count sensor not available",Toast.LENGTH_LONG).show();
        }

    }
    /**
     * onClick method for the floating action button, shows a toast with the current steps showing
     */
    @Override
    public void onClick(View view){

        Toast.makeText(this,steps+"steps",Toast.LENGTH_SHORT).show();
    }
    /**
     * Method creates a backstack for the fragments
     */
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}

