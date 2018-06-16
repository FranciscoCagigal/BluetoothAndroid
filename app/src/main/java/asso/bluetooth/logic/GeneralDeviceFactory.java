package asso.bluetooth.logic;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

/**
 * Created by franc on 02/05/2018.
 */

public class GeneralDeviceFactory extends DeviceFactoryMethod {
    @Override
    public MyBluetoothDevice makeDevice(String macAddress, String name, int rssi, BluetoothDevice device, int type) {
        switch (type){
            case BluetoothClass.Device.COMPUTER_LAPTOP:
                return new LaptopDevice(macAddress, name, rssi, device);
            case BluetoothClass.Device.PHONE_SMART:
                return new SmartphoneDevice(macAddress, name, rssi, device);
            default:
                return null;
        }

    }
}
