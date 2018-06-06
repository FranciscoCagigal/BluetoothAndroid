package asso.bluetooth.logic.Peer2Peer.Message.MessageHandlers;

/**
 * Created by franc on 06/06/2018.
 */

import asso.bluetooth.logic.Peer2Peer.Message.MessageHandlerContext;
import asso.bluetooth.logic.Peer2Peer.Message.UnknownMessageFormatException;

public abstract class MessageHandler
{
    protected String message_ = null;

    public MessageHandler(String message) throws UnknownMessageFormatException
    {
        message_ = message;
    }

    public abstract void handleMessage(MessageHandlerContext context);
}