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

import com.example.miikka.calorieswatcher.R;

public class PedometerSettings extends Fragment implements View.OnClickListener {
    EditText getWeight, getStepLength;

    int weight;
    int stepLength;

    onPedometerSettingsChanged mCallback;

    public PedometerSettings() {
        // Required empty public constructor
    }
    public interface onPedometerSettingsChanged{
        public void onSettingsChanged(int weight,int stepLength);
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mCallback = (onPedometerSettingsChanged) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
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
        mCallback.onSettingsChanged(weight,stepLength);
        Toast.makeText(super.getContext(),"Settings saved",Toast.LENGTH_LONG).show();
    }

}
