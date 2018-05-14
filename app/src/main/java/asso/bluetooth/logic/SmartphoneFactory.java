package asso.bluetooth.logic;

import android.bluetooth.BluetoothDevice;

/**
 * Created by franc on 02/05/2018.
 */

public class SmartphoneFactory extends DeviceMaker {
    @Override
    public MyBluetoothDevice makeDevice(String macAddress, String name, int rssi, BluetoothDevice device) {
        return new SmartphoneDevice(macAddress, name, rssi, device);
    }
}
