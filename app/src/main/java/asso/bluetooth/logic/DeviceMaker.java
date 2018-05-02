package asso.bluetooth.logic;

/**
 * Created by franc on 02/05/2018.
 */

public abstract class DeviceMaker {
    public abstract MyBluetoothDevice makeDevice(String macAddress, String name, int rssi);
}
