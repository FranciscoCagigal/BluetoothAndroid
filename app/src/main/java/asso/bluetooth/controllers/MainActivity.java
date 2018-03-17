package asso.bluetooth.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import asso.bluetooth.R;
import asso.bluetooth.logic.MyBluetoothDevice;
import asso.bluetooth.views.DrawGraph;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    ArrayList<String> listItems=new ArrayList<String>();
    private ListView mListView;
    public static final String EXTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final DrawGraph drawgraph = new DrawGraph(this);

        MyBluetoothDevice b1 = new MyBluetoothDevice("01:02:03","francisco",30);
        MyBluetoothDevice b2 = new MyBluetoothDevice("04:05:06","pedro",60);
        ArrayList<MyBluetoothDevice> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        drawgraph.setDevices(list);

        drawgraph.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    System.out.println(event.getX());
                    System.out.println(drawgraph.getDevice(event.getX(),event.getY()));
                    MyBluetoothDevice b1 = new MyBluetoothDevice("01:02:03","francisco",30);
                    MyBluetoothDevice b2 = new MyBluetoothDevice("04:05:06","pedro",60);
                    MyBluetoothDevice b3 = new MyBluetoothDevice("07:08:09","ana",90);
                    ArrayList<MyBluetoothDevice> list = new ArrayList<>();
                    list.add(b1);
                    list.add(b2);
                    list.add(b3);
                    drawgraph.setDevices(list);
                    drawgraph.invalidate();

                    openInfoDisplay(drawgraph.getDevice(event.getX(),event.getY()));

                    return true;
                }
                return false;
            }
        });

        setContentView(drawgraph);


        /*setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                System.out.println("lolololol " + action);
                //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    System.out.println(device.getName());
                    listItems.add(device.getName());
                    adapter.notifyDataSetChanged();
                    //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                    int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    System.out.println("lol " + rssi);
                }
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, filter);

        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.startDiscovery();*/

    }

    protected void openInfoDisplay(String message){
        Intent intent = new Intent(MainActivity.this, InfoDisplay.class);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    protected void setListAdapter(ListAdapter adapter) {
        getListView().setAdapter(adapter);
    }

    protected ListView getListView() {
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listview);
        }
        return mListView;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
