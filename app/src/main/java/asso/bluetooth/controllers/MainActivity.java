package asso.bluetooth.controllers;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
        System.out.println("vou lançar finder");
        (new Thread(finder)).start();

        drawgraph.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    System.out.println(event.getX());
                    String nome = drawgraph.getDevice(event.getX(),event.getY());
                    System.out.println(nome);

                    if(nome != null)
                        openInfoDisplay(drawgraph.getAllDevice(event.getX(),event.getY()));

                    return true;
                }
                return false;
            }
        });

        setContentView(drawgraph);
        
    }

    protected void openInfoDisplay(MyBluetoothDevice device){
        Intent intent = new Intent(MainActivity.this, InfoDisplay.class);

        intent.putExtra("nome", device.getName());
        intent.putExtra("mac", device.getMacAddress());
        intent.putExtra("power", Integer.toString(device.getRssi()));
        startActivity(intent);
    }



    @Override
    public void update(List<MyBluetoothDevice> devices) {
        System.out.println("recebi update");
        drawgraph.setDevices(devices);
        drawgraph.invalidate();
    }
}