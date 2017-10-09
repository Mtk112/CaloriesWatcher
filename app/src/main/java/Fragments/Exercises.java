package Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 Fragment for the user to input exercises they have done, outside of walking / jogging.
 For example, if the user plays competive football he can include his competitive football games into the app
 and the application will count the calories burned. THIS IS BY NO MEANS PINPOINT ACCURATE!. Instead it's meant
 just give an idea of what the consumption of calories might be.
 */
public class Exercises extends Fragment implements View.OnClickListener {
    //Variables and UI elements & Database
    DatabaseHelper dbHelper;
    String exerciseName, exerciseDuration = "";
    int intensity, duration, caloriesBurned, eid;
    Timestamp time;
    boolean light, medium, intense = false;
    EditText eDuration, eName;
    Button eConfirm, iLight, iMedium, iIntense;


    public Exercises() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the fragment, "register" the UI components & sets OnClickListeners
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.exercise);
        final View v = inflater.inflate(R.layout.fragment_exercises, container, false);
        dbHelper = new DatabaseHelper(this.getContext());
        eDuration = (EditText) v.findViewById(R.id.exerciseDuration);
        eName = (EditText) v.findViewById(R.id.exerciseName);
        eConfirm = (Button) v.findViewById(R.id.exerciseConfirm);
        eConfirm.setOnClickListener(this);
        iLight = (Button) v.findViewById(R.id.exerciseLightButton);
        iLight.setOnClickListener(this);
        iMedium = (Button) v.findViewById(R.id.exerciseMediumButton);
        iMedium.setOnClickListener(this);
        iIntense = (Button) v.findViewById(R.id.exerciseIntenseButton);
        iIntense.setOnClickListener(this);

        eDuration.setImeOptions(EditorInfo.IME_ACTION_DONE);
        eName.setImeOptions(EditorInfo.IME_ACTION_DONE);

        return v;
    }


    //Method for checking which button was pressed and executing the correct code
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*
            Sets the text on the button that was pressed to red, sets the intensity
            and reverts other buttons text color to white.
            */
            case (R.id.exerciseLightButton):
                if(!light){
                    light = true;
                    intensity = 1;
                    iLight.setTextColor(Color.RED);
                    if(medium){
                        iMedium.setTextColor(Color.WHITE);
                        medium = false;
                    }
                    if(intense){
                        iIntense.setTextColor(Color.WHITE);
                        intense=false;
                    }
                }
                else{
                    light = false;
                    iLight.setTextColor(Color.WHITE);
                    intensity = 0;
                }
                break;

            case(R.id.exerciseMediumButton):
                if(!medium){
                    medium = true;
                    intensity = 2;
                    iMedium.setTextColor(Color.RED);
                    if(light){
                        iLight.setTextColor(Color.WHITE);
                        light = false;
                    }
                    if(intense){
                        iIntense.setTextColor(Color.WHITE);
                        intense=false;
                    }
                }
                else{
                    medium = false;
                    iMedium.setTextColor(Color.WHITE);
                    intensity = 0;
                }
                break;

            case(R.id.exerciseIntenseButton):
                if(!intense){
                    intense = true;
                    intensity = 3;
                    iIntense.setTextColor(Color.RED);
                    if(light){
                        iLight.setTextColor(Color.WHITE);
                        light = false;
                    }
                    if(medium){
                        iMedium.setTextColor(Color.WHITE);
                        medium=false;
                    }
                }
                else{
                    intense = false;
                    iIntense.setTextColor(Color.WHITE);
                    intensity = 0;
                }
                break;
            /*
            Checks if input fields have been filled, then either loads (if only name is filled) or saves the data to database.
            If required fields are empty, toast will pop up and ask for the user to fill in the fields. When inserting data
            is successful toast will pop up that informs the user about the calories burnt by that exercise.
             */
            case(R.id.exerciseConfirm):
                exerciseName = eName.getText().toString();
                exerciseDuration = eDuration.getText().toString();
                if(!exerciseName.isEmpty() && exerciseDuration.isEmpty() && intensity==0){
                    try{
                        eid = dbHelper.getEidByName(exerciseName);
                        Log.d("EID: ",""+eid);
                        List<Integer> results = dbHelper.getData(eid);
                        intensity = results.get(0);
                        duration = results.get(1);
                        Log.d("Intensity/duration: ",""+intensity+"/"+duration);

                        eDuration.setText(""+duration);
                        switch(intensity){
                            case(1):
                                iLight.setTextColor(Color.RED);
                                light=true;
                                break;
                            case(2):
                                iMedium.setTextColor(Color.RED);
                                medium=true;
                                break;
                            case(3):
                                iIntense.setTextColor(Color.RED);
                                intense=true;
                                break;
                        }
                        Toast.makeText(this.getContext(), "Exercise loaded, edit information if you need to before saving!", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        Log.d("Error: ", ""+e);
                    }
                }
                else if(!exerciseName.isEmpty() && !exerciseDuration.isEmpty() && intensity !=0){
                    dbHelper.insertExercise(exerciseName);
                    eid = dbHelper.getEidByName(exerciseName);
                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();
                    time = new java.sql.Timestamp(now.getTime());
                    duration =Integer.parseInt(exerciseDuration);
                    caloriesBurned = intensity*duration*4;
                    dbHelper.insertMyExercise(intensity,duration,caloriesBurned,eid,time);
                    Toast.makeText(this.getContext(), "Exercise saved! \n Calories burnt: "+caloriesBurned, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this.getContext(), "Please fill in the information required.", Toast.LENGTH_SHORT).show();
                }
        }
    }

}

