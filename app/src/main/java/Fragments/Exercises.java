package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class Exercises extends Fragment {
    //Variables and UI elements & Database
    DatabaseHelper dbHelper;
    private String exerciseName;
    private int intensity, duration, caloriesBurned, eid;
    EditText eDuration,eName;
    Button eConfirm,iLight,iMedium,iIntense;


    public Exercises() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflates the fragment & "register" the UI components
        final View v = inflater.inflate(R.layout.fragment_exercises,container,false);
        eDuration = (EditText)v.findViewById(R.id.exerciseDuration);
        eName = (EditText)v.findViewById(R.id.exerciseName);

        return v;
    }

}
