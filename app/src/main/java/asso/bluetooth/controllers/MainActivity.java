package asso.bluetooth.controllers;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asso.bluetooth.R;
import asso.bluetooth.Peer2Peer.Server.ServerThread;
import asso.bluetooth.Peer2Peer.Client.ConnectThread;
import asso.bluetooth.logic.DeviceFinder;
import asso.bluetooth.logic.MyBluetoothDevice;
import asso.bluetooth.views.DrawGraph;

public class MainActivity extends AppCompatActivity implements BluetoothObserver {

    private DrawGraph drawgraph;
    private ConnectThread connectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

        drawgraph= new DrawGraph(this);
        drawgraph.setDevices(new ArrayList<MyBluetoothDevice>());

        DeviceFinder finder = new DeviceFinder(getApplicationContext());
        finder.attach(this);

        (new Thread(finder)).start();
        (new Thread(new ServerThread())).start();

        drawgraph.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    MyBluetoothDevice device_clicked = drawgraph.getAllDevice(event.getX(),event.getY());
                    if(device_clicked != null){
                        System.out.println("device " + device_clicked);
                        System.out.println("device name " + device_clicked.getName());
                        openInfoDisplay(device_clicked);
                    }
                    return true;
                }
                return false;
            }
        });
        setContentView(drawgraph);
    }

    protected void openInfoDisplay(final MyBluetoothDevice device){
        setContentView(R.layout.infodisplay);
        TextView textView = findViewById(R.id.name_text);
        System.out.println("tetx " + textView);
        textView.append(device.getName());
        textView = findViewById(R.id.mac_text);
        textView.append(" " + device.getMacAddress());
        textView = findViewById(R.id.rssi_text);
        textView.append(" " + device.getRssi());
        Button button= (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectThread.cancel();
                setContentView(drawgraph);
            }
        });
        button= (Button) findViewById(R.id.conntect_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectThread = new ConnectThread(device.getBluetoothDevice());
                (new Thread(connectThread)).start();
            }
        });
        button= (Button) findViewById(R.id.lights_on);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connectThread.sendMessage("LIGHTS_ON something");
            }
        });

    }


    @Override
    public void update(List<MyBluetoothDevice> devices) {
        drawgraph.setDevices(devices);
        drawgraph.invalidate();
    }
}