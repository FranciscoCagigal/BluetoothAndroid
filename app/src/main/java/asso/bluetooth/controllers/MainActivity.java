package asso.bluetooth.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import asso.bluetooth.logic.DeviceFinder;
import asso.bluetooth.logic.MyBluetoothDevice;
import asso.bluetooth.views.DrawGraph;

public class MainActivity extends AppCompatActivity implements BluetoothObserver {

    private DrawGraph drawgraph;
    public static final String EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawgraph= new DrawGraph(this);
        drawgraph.setDevices(new ArrayList<MyBluetoothDevice>());

        DeviceFinder finder = new DeviceFinder(getApplicationContext());
        finder.attach(this);
        System.out.println("vou lançar finder");
        (new Thread(finder)).start();

        drawgraph.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    System.out.println(event.getX());
                    System.out.println(drawgraph.getDevice(event.getX(),event.getY()));
                    openInfoDisplay(drawgraph.getDevice(event.getX(),event.getY()));

                    return true;
                }
                return false;
            }
        });

        setContentView(drawgraph);


        /*int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);*/

    }

    protected void openInfoDisplay(String message){
        Intent intent = new Intent(MainActivity.this, InfoDisplay.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }



    @Override
    public void update(List<MyBluetoothDevice> devices) {
        System.out.println("recebi update");
        drawgraph.setDevices(devices);
        drawgraph.invalidate();
    }
}