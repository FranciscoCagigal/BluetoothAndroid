package asso.bluetooth.logic;

/**
 * Created by franc on 15/03/2018.
 */

public class MyBluetoothDevice {
    private String macAddress, name;
    private int rssi,type;

    public MyBluetoothDevice(String macAddress, String name, int rssi, int type) {
        this.macAddress = macAddress;
        this.name = name;
        this.rssi = rssi;
        this.type = type;
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

    public int getType() {
        return type;
    }

}
