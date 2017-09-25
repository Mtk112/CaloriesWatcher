package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.miikka.calorieswatcher.R;

public class PedometerSettings extends Fragment implements View.OnClickListener {
    EditText getWeight, getStepLength;

    int weight;
    int stepLength;

    public PedometerSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedometer_settings, container, false);

        getWeight = (EditText)v.findViewById(R.id.editTextWeight);
        getStepLength = (EditText)v.findViewById(R.id.editStepLength);
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
        //TODO save the settings somewhere for other fragments to use
    }

}
