package asso.bluetooth.controllers;

import java.util.List;
import asso.bluetooth.logic.MyBluetoothDevice;

/**
 * Created by franc on 17/03/2018.
 */

public interface BluetoothObserver {
    public void update(List<MyBluetoothDevice> devices);
}
