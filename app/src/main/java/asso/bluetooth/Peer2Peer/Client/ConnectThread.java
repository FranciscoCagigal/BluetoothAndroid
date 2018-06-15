package asso.bluetooth.Peer2Peer.Client;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by franc on 05/06/2018.
 */

public class ConnectThread extends Thread {
    private BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    public ConnectThread(BluetoothDevice device) {
        // Use a temporary object that is later assigned to mmSocket
        // because mmSocket is final.
        BluetoothSocket tmp = null;
        mmDevice = device;

        try {
            // Get a BluetoothSocket to connect with the given BluetoothDevice.
            // MY_UUID is the app's UUID string, also used in the server code.
            tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            System.out.println("Socket's create() method failed" + e);
        }
        mmSocket = tmp;
    }

    public void run() {
        // Cancel discovery because it otherwise slows down the connection.
        mBluetoothAdapter.cancelDiscovery();
        System.out.println("CANCELEI DISCOVERY");
        try {
            // Connect to the remote device through the socket. This call blocks
            // until it succeeds or throws an exception.
            mmSocket.connect();
            System.out.println("CONECTEI-ME");
        } catch (IOException connectException) {
            // Unable to connect; close the socket and return.
            System.out.println("NAO CONSEGUI CONECTAR-ME");
            System.out.println(connectException);
            try {
                mmSocket =(BluetoothSocket) mmDevice.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(mmDevice,2);
                mmSocket.connect();
            } catch (Exception e) {
                System.out.println("NAO CONSEGUI CONECTAR-ME OUTRA VEZ");
                System.out.println(e);
            }
        }

        // The connection attempt succeeded. Perform work associated with
        // the connection in a separate thread.
        manageMyConnectedSocket(mmSocket);
    }

    private void manageMyConnectedSocket(BluetoothSocket socket){
        byte[] buffer = new byte[1024];
        try{
            while(socket.getInputStream().read(buffer)>=0){
                System.out.println("Recebi " + buffer.toString());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendMessage(String str){
        try{
            mmSocket.getOutputStream().write(str.getBytes());
            System.out.println("ENVIEI MENSAGEM");
        }catch (Exception e){
            System.out.println("CORREU MAL ENVIAR MENSAGEM");
            System.out.println(e);
        }

    }

    // Closes the client socket and causes the thread to finish.
    public void cancel() {
        if(mmSocket!=null){
            try {
                mmSocket.close();
            } catch (IOException e) {
                System.out.println("Could not close the client socket" + e);
            }
        }
    }
}
