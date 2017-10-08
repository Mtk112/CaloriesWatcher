package Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.miikka.calorieswatcher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoveFragment extends Fragment implements View.OnClickListener{
    FragmentTransaction transaction;
    Button button1,button2;
    MetawearFragment metawearFragment;
    public MoveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_move, container, false);
        button1 = (Button)view.findViewById(R.id.buttonMetawearYes);
        button2 = (Button)view.findViewById(R.id.buttonMetawearNo);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonMetawearYes:
                metawearFragment = new MetawearFragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moveContainer,metawearFragment);
                break;
            case R.id.buttonMetawearNo:
                //do something...maybe
                break;
        }

    }
}
