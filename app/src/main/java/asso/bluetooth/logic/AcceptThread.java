package asso.bluetooth.logic;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;



public class AcceptThread extends Thread {
    private final BluetoothServerSocket mmServerSocket;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final String name_of_service = "my_bluetooth_app";
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public AcceptThread() {
        // Use a temporary object that is later assigned to mmServerSocket
        // because mmServerSocket is final.
        BluetoothServerSocket tmp = null;
        try {
            // MY_UUID is the app's UUID string, also used by the client code.
            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(name_of_service, MY_UUID);
        } catch (IOException e) {
            System.out.println("Socket's listen() method failed" + e);
        }
        mmServerSocket = tmp;
    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
                System.out.println("RECEBI UM PEDIDO");
            } catch (IOException e) {
                System.out.println("Socket's accept() method failed" + e);
                break;
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                manageMyConnectedSocket(socket);
            }
        }
    }

    private void manageMyConnectedSocket(BluetoothSocket socket){
        System.out.println("TOU À ESPERA DE LER");
        byte[] buffer = new byte[1024];
        try{
            while(socket.getInputStream().read(buffer)>=0){
                String str = new String(buffer, StandardCharsets.UTF_8).replace("\0", "");;
                System.out.println("Recebi " + str);
            }
        }catch (Exception e){
            System.out.println("socket fechou");
            System.out.println(e);
        }
    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close the connect socket" + e);
        }
    }
}