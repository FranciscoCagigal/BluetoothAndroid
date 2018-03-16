package asso.bluetooth.logic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;

/**
 * Created by franc on 16/03/2018.
 */

public class DeviceFinder implements Runnable{
    Context context;

    public DeviceFinder(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        MyBroadcastReceiver bR = new MyBroadcastReceiver();

        context.registerReceiver(bR, filter);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();
    }
}
