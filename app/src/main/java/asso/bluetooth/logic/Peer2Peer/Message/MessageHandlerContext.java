package asso.bluetooth.logic.Peer2Peer.Message;

/**
 * Created by franc on 06/06/2018.
 */

import java.io.InputStream;
import java.io.OutputStream;

import asso.bluetooth.logic.Peer2Peer.Server.ServerThread;

public class MessageHandlerContext
{
    private ServerThread messageBroker_ = null;
    private InputStream inputStream_ = null;
    private OutputStream outputStream_ = null;

    public MessageHandlerContext(ServerThread messageBroker,
                                 InputStream inputStream,
                                 OutputStream outputStream)
    {
        messageBroker_ = messageBroker;
        inputStream_ = inputStream;
        outputStream_ = outputStream;
    }

    public ServerThread getMessageBroker() { return messageBroker_; }
    public InputStream getInputStream() { return inputStream_; }
    public OutputStream getOutputStream() { return outputStream_; }
}