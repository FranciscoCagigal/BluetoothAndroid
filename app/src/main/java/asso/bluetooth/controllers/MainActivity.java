package asso.bluetooth.controllers;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

import asso.bluetooth.R;
import asso.bluetooth.logic.DeviceFinder;
import asso.bluetooth.logic.MyBluetoothDevice;
import asso.bluetooth.logic.MyBroadcastReceiver;
import asso.bluetooth.views.DrawGraph;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DeviceFinder finder = new DeviceFinder(getApplicationContext());
        (new Thread(finder)).start();

        final DrawGraph drawgraph = new DrawGraph(this);

        MyBluetoothDevice b1 = new MyBluetoothDevice("01:02:03","francisco",30);
        MyBluetoothDevice b2 = new MyBluetoothDevice("04:05:06","pedro",60);
        ArrayList<MyBluetoothDevice> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        drawgraph.setDevices(list);

        drawgraph.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    System.out.println(event.getX());
                    System.out.println(drawgraph.getDevice(event.getX(),event.getY()));
                    MyBluetoothDevice b1 = new MyBluetoothDevice("01:02:03","francisco",30);
                    MyBluetoothDevice b2 = new MyBluetoothDevice("04:05:06","pedro",60);
                    MyBluetoothDevice b3 = new MyBluetoothDevice("07:08:09","ana",90);
                    MyBluetoothDevice b4 = new MyBluetoothDevice("10:11:12","sofia",90);
                    ArrayList<MyBluetoothDevice> list = new ArrayList<>();
                    list.add(b1);
                    list.add(b2);
                    list.add(b3);
                    list.add(b4);
                    drawgraph.setDevices(list);
                    drawgraph.invalidate();
                    return true;
                }
                return false;
            }
        });

        setContentView(drawgraph);


        /*setContentView(R.layout.activity_main);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                System.out.println("lolololol " + action);
                //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    System.out.println(device.getName());
                    //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                    int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    System.out.println("lol " + rssi);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);

        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();*/

    }

}
