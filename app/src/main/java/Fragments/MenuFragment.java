package Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miikka.calorieswatcher.R;

public class MenuFragment extends Fragment implements View.OnClickListener{
    Button historyButton;
    Button eatButton;
    Button exerciseButton;
    int selected=0;

    onMenuItemClicked mCallback;


    public MenuFragment() {
        // Required empty public constructor
    }

    public interface onMenuItemClicked{
        public void onNewFragmentSelected(int selected);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mCallback = (onMenuItemClicked)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);


        historyButton = (Button)v.findViewById(R.id.menuHistoryButton);
        eatButton=(Button)v.findViewById(R.id.menuEatButton);
        exerciseButton=(Button)v.findViewById(R.id.menuExerciseButton);
        historyButton.setOnClickListener(this);
        eatButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);



        // Inflate the layout for this fragment
        return v;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case(R.id.menuHistoryButton):
                selected =1;
                break;
            case(R.id.menuEatButton):
                selected =2;
                break;
            case(R.id.menuExerciseButton):
                selected=3;
                break;
        }
        mCallback.onNewFragmentSelected(selected);
    }

}
