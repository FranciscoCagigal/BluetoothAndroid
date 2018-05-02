package asso.bluetooth.logic;

import asso.bluetooth.R;

/**
 * Created by franc on 02/05/2018.
 */

public class SmartphoneDevice extends MyBluetoothDevice {
    public SmartphoneDevice(String macAddress, String name, int rssi) {
        super(macAddress, name, rssi);
    }

    @Override
    public int getImage() {
        return R.mipmap.ic_launcher;
    }
}
