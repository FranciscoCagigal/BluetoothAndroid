package asso.bluetooth.logic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;
import java.util.List;

import asso.bluetooth.controllers.BluetoothObserver;

/**
 * Created by franc on 16/03/2018.
 */

public class DeviceFinder implements Runnable{
    Context context;
    private List<BluetoothObserver> observers = new ArrayList<>();
    private List<MyBluetoothDevice> devices = new ArrayList<>();
    private BluetoothAdapter mBluetoothAdapter;

    public DeviceFinder(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                System.out.println("lolololol " + action);
                //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    int type = device.getBluetoothClass().getDeviceClass();

                    System.out.println(BluetoothClass.Device.COMPUTER_LAPTOP);
                    devices.add(new MyBluetoothDevice(device.getAddress(),device.getName(),50, type));
                    //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                    //int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    //System.out.println("lol " + rssi);
                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                    notifyAllObservers();
                    mBluetoothAdapter.startDiscovery();
                }
            }
        };

        context.registerReceiver(mReceiver, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();
    }

    public void attach(BluetoothObserver observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (BluetoothObserver observer : observers) {
            observer.update(devices);
        }
        devices= new ArrayList<>();
    }
}
