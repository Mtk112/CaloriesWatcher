package Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miikka.calorieswatcher.DatabaseHelper;
import com.example.miikka.calorieswatcher.R;
import com.example.miikka.calorieswatcher.UserSettings;

public class PedometerSettings extends Fragment implements View.OnClickListener {
    EditText getWeight, getStepLength;

    int weight;
    int stepLength;
    DatabaseHelper dbHelper;


    public PedometerSettings() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedometer_settings, container, false);

        dbHelper=new DatabaseHelper(this.getContext());
        UserSettings userSettings = dbHelper.getUserSettings();
        weight=userSettings.getWeight();
        stepLength=userSettings.getStepLength();

        getWeight = (EditText)v.findViewById(R.id.editTextWeight);
        getStepLength = (EditText)v.findViewById(R.id.editStepLength);

        getWeight.setText(""+weight);
        getStepLength.setText(""+stepLength);
        Button button = (Button)v.findViewById(R.id.pedometerSettingsSave);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v){
        try {
            weight = Integer.parseInt(getWeight.getText().toString());
            stepLength = Integer.parseInt((getStepLength.getText().toString()));
            saveSettings();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveSettings(){
        dbHelper.insertSettings(weight,stepLength);
        Toast.makeText(super.getContext(),"Settings saved",Toast.LENGTH_LONG).show();
    }

}
