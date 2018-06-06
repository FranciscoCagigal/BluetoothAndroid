package asso.bluetooth.logic.Peer2Peer.Server;

import android.bluetooth.BluetoothSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import asso.bluetooth.logic.Peer2Peer.Message.MessageHandlers.MessageHandler;
import asso.bluetooth.logic.Peer2Peer.Message.MessageHandlerContext;

/**
 * Created by franc on 06/06/2018.
 */

class HandleClientThread implements Runnable {

    private ServerThread messageBroker= null;
    private BluetoothSocket socket = null;

    public HandleClientThread(ServerThread messageBroker, BluetoothSocket socket)
    {
        this.messageBroker = messageBroker;
        this.socket = socket;
    }


    /**
     * Read and process the message.
     */
    public void run()
    {
        try
        {
            System.out.println("TOU À ESPERA DE MENSAGEM: ");
            byte[] buffer = new byte[1024];
            socket.getInputStream().read(buffer);
            String message = new String(buffer, StandardCharsets.UTF_8).replace("\0", "");
            System.out.println("RECEBI ESTA MENSAGEM: " + message);
            MessageHandler handler = messageBroker.getMessageHandlerFactory().getMessageHandler(message);
            if (handler != null){
                handler.handleMessage(new MessageHandlerContext(messageBroker, socket.getInputStream(), socket.getOutputStream()));
            }
            else
                System.out.println("No message handler found for:  " + message);

            socket.close();
        }
        catch(IOException e)
        {
            System.out.println("IOException on read:  " + e);
        }
    }
}
