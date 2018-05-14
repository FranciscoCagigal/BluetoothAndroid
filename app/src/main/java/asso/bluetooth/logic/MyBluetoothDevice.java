package asso.bluetooth.logic;
import android.bluetooth.BluetoothDevice;

import java.io.Serializable;
/**
 * Created by franc on 15/03/2018.
 */
@SuppressWarnings("serial")
public abstract class MyBluetoothDevice implements Serializable {
    private String macAddress, name;
    private int rssi;
    private BluetoothDevice device;

    public MyBluetoothDevice(String macAddress, String name, int rssi, BluetoothDevice device) {
        this.macAddress = macAddress;
        this.name = name;
        this.rssi = rssi;
        this.device = device;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getName() {
        return name;
    }

    public int getRssi() {
        return rssi;
    }

    public BluetoothDevice getBluetoothDevice(){ return device;}

    public abstract int getImage();

}
