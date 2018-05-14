package asso.bluetooth.controllers;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import asso.bluetooth.R;
import asso.bluetooth.logic.MyBluetoothDevice;

import android.view.View;
import java.lang.reflect.Method;

public class InfoDisplay extends AppCompatActivity {
    MyBluetoothDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_display);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        device = (MyBluetoothDevice)intent.getSerializableExtra("device");

        String nome = device.getName();
        String mac = device.getMacAddress();
        String power = String.valueOf(device.getRssi());

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.nome);
        textView.setText("Nome: ");
        textView.append(nome);

        textView = findViewById(R.id.mac);
        textView.setText("Mac: ");
        textView.append(mac);

        textView = findViewById(R.id.power);
        textView.setText("Power: ");
        textView.append(power);
    }

    public boolean createBond(BluetoothDevice btDevice)
            throws Exception
    {
        Class class1 = Class.forName("android.bluetooth.BluetoothDevice");
        Method createBondMethod = class1.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);

        return returnValue.booleanValue();
    }

    public void returnReply(View view) {

        Intent replyIntent = new Intent();
        replyIntent.putExtra("Reply_OK", "Reply_OK");
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    public void replyConnect(View view) {

        System.out.println("Vou ligar-me ao " + device.getBluetoothDevice().getName());
        try {
            if(createBond(device.getBluetoothDevice())){

                System.out.println("conectei-me ao: " + device.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replyQqlrCoisa(View view) {

        Intent replyIntent = new Intent();
        replyIntent.putExtra("Reply_OK", "Reply_OK");
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}
