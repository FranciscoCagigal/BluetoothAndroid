package asso.bluetooth.logic;

/**
 * Created by franc on 15/03/2018.
 */

public class MyBluetoothDevice {
    private String macAddress;
    private String name;
    private int rssi;

    public MyBluetoothDevice(String macAddress, String name, int rssi) {
        this.macAddress = macAddress;
        this.name = name;
        this.rssi = rssi;
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
}
