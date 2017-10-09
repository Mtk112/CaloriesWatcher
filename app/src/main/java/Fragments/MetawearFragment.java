package Fragments;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.miikka.calorieswatcher.R;
import com.mbientlab.metawear.MetaWearBoard;
import com.mbientlab.metawear.android.BtleService;
import com.mbientlab.metawear.module.AccelerometerBmi160;


import bolts.Continuation;
import bolts.Task;




/**
 * Fragment builds connections to external sensor and starts monitoring time stood still
 */
public class MetawearFragment extends Fragment implements ServiceConnection, View.OnClickListener{
    private final String MW_MAC_ADDRESS= "C1:A8:34:4D:BF:8E";
    private MetaWearBoard board;
    private AccelerometerBmi160 accelerometerBmi160;
    final AccelerometerBmi160.SignificantMotionDataProducer significantMotion =
            accelerometerBmi160.motion(AccelerometerBmi160.SignificantMotionDataProducer.class);



    private Handler handler = new Handler();
    Button button;
    EditText editText;
    int timer;

    private BtleService.LocalBinder serviceBinder;


    public MetawearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_metawear, container, false);

        getContext().getApplicationContext().bindService(new Intent(getContext(), BtleService.class),
                this, Context.BIND_AUTO_CREATE);

        editText = (EditText)v.findViewById(R.id.metawearEditText);

        button = (Button)v.findViewById(R.id.startMetawear);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        serviceBinder = (BtleService.LocalBinder) service;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        getContext().getApplicationContext().unbindService(this);
    }
    /**
     *connects to the metawear board
     */
    public void retrieveBoard() {
        final BluetoothManager btManager=
                (BluetoothManager) getContext().getSystemService(Context.BLUETOOTH_SERVICE);
        final BluetoothDevice remoteDevice=
                btManager.getAdapter().getRemoteDevice(MW_MAC_ADDRESS);

        // Create a MetaWear board object for the Bluetooth Device
        board= serviceBinder.getMetaWearBoard(remoteDevice);

        board.connectAsync().continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                if (task.isFaulted()) {
                    Log.i("MainActivity", "Failed to connect");
                } else {
                    Log.i("MainActivity", "Connected");
                }
                return null;
            }
        });

        board.disconnectAsync().continueWith(new Continuation<Void, Void>() {
            @Override
            public Void then(Task<Void> task) throws Exception {
                Log.i("MainActivity", "Disconnected");
                return null;
            }
        });
        board.onUnexpectedDisconnect(new MetaWearBoard.UnexpectedDisconnectHandler() {
            @Override
            public void disconnected(int status) {
                Log.i("MainActivity", "Unexpectedly lost connection: " + status);
            }
        });

        Log.i("MainActivity", "board model = " + board.getModel());
    }

    /**
     *reads and parses the user set timer for standing still
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case(R.id.startMetawear):
                timer = Integer.parseInt(editText.getText().toString());
                timer =timer*1000;
                handler.postDelayed(runnable,timer);
                break;
        }
    }
    /**
     *Should start the async task for monitoring the movement of the metawear sensor
     * Not working yet.
     *
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            /*significantMotion.configure()
                    .proofTime(AccelerometerBmi160.ProofTime.PT_1_S)
                    .skipTime(AccelerometerBmi160.SkipTime.ST_1_5_S)
                    .commit();
            significantMotion.addRouteAsync(new RouteBuilder() {
                @Override
                public void configure(RouteComponent source) {
                    source.stream(new Subscriber() {
                        @Override
                        public void apply(Data data, Object... env) {
                            actual.set(data.bytes()[0]);                        }
                    });
                }
            }).continueWith(new Continuation<Route, Object>() {
                @Override
                public Void then(Task<Route> task)throws Exception{
                    significantMotion.start();
                    accelerometerBmi160.start();
                    return null;
                }
            });*/

            handler.postDelayed(runnable,timer);
        }
    };

}
