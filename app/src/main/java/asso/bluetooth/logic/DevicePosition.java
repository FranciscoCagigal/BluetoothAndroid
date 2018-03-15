package asso.bluetooth.logic;

/**
 * Created by franc on 15/03/2018.
 */

public class DevicePosition {
    private MyBluetoothDevice device;
    private int positionX,positionY;

    public DevicePosition(MyBluetoothDevice device, int positionX, int positionY) {
        this.device = device;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public MyBluetoothDevice getDevice() {
        return device;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
