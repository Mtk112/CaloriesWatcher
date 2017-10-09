package Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miikka.calorieswatcher.R;

/**
 * Fragment with the main menu, basically home screen for the application
 */
public class MenuFragment extends Fragment implements View.OnClickListener{
    Button historyButton;
    Button eatButton;
    Button exerciseButton;
    int selected=0;

    onMenuItemClicked mCallback;


    public MenuFragment() {
        // Required empty public constructor
    }
    /**
     *Interface for changing between fragments.
     */
    public interface onMenuItemClicked{
        public void onNewFragmentSelected(int selected);
    }
    /**
     *
     */
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mCallback = (onMenuItemClicked)activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }
    /**
     * Buttons to launch different fragments.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        historyButton = (Button)v.findViewById(R.id.menuHistoryButton);
        eatButton=(Button)v.findViewById(R.id.menuEatButton);
        exerciseButton=(Button)v.findViewById(R.id.menuExerciseButton);
        historyButton.setOnClickListener(this);
        eatButton.setOnClickListener(this);
        exerciseButton.setOnClickListener(this);



        // Inflate the layout for this fragment
        return v;
    }
    /**
     *listeners for buttons in the menu fragment, callback to the interface
     */
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
