package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.R;

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
        final View v = inflater.inflate(R.layout.fragment_exercises, container, false);
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
        return v;
    }


    //Method for checking which button was pressed and executing the correct code
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.exerciseLightButton):
                if(light = false){
                    light = true;
                    intensity = 1;
                    iLight.setText(R.string.selected);
                    if(medium= true){
                        iMedium.setText(R.string.medium);
                        medium = false;
                    }
                    if(intense=true){
                        iIntense.setText(R.string.intense);
                        intense=false;
                    }
                }
                else{
                    light = false;
                    iLight.setText(R.string.light);
                    intensity = 0;
                }
                Log.d("Test","Intensity set to: "+(Integer.toString(intensity)));
                break;

            case(R.id.exerciseMediumButton):
                if(medium = false){
                    medium = true;
                    intensity = 2;
                    iMedium.setText(R.string.selected);
                    if(light= true){
                        iLight.setText(R.string.light);
                        light = false;
                    }
                    if(intense=true){
                        iIntense.setText(R.string.intense);
                        intense=false;
                    }
                }
                else{
                    medium = false;
                    iMedium.setText(R.string.medium);
                    intensity = 0;
                }
                Log.d("Test","Intensity set to: "+(Integer.toString(intensity)));
                break;

            case(R.id.exerciseIntenseButton):
                if(intense = false){
                    intense = true;
                    intensity = 3;
                    iIntense.setText(R.string.selected);
                    if(light= true){
                        iLight.setText(R.string.light);
                        light = false;
                    }
                    if(medium=true){
                        iMedium.setText(R.string.medium);
                        medium=false;
                    }
                }
                else{
                    intense = false;
                    iIntense.setText(R.string.intense);
                    intensity = 0;
                }
                Log.d("Test","Intensity set to: "+(Integer.toString(intensity)));
                break;
            //case(R.id.exerciseConfirm):



        }

    }
}

