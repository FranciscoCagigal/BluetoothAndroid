package asso.bluetooth.logic;

import android.bluetooth.BluetoothDevice;

import asso.bluetooth.R;

/**
 * Created by franc on 02/05/2018.
 */

public class SmartphoneDevice extends MyBluetoothDevice {
    public SmartphoneDevice(String macAddress, String name, int rssi, BluetoothDevice device) {
        super(macAddress, name, rssi, device);
    }

    @Override
    public int getImage() {
        return R.mipmap.smartphone;
    }
}
