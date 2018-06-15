package asso.bluetooth.Peer2Peer.Message.MessageHandlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asso.bluetooth.Peer2Peer.Message.MessageHandlerContext;
import asso.bluetooth.Peer2Peer.Message.UnknownMessageFormatException;

/**
 * Created by franc on 06/06/2018.
 */

public class LightsOnMessageHandler extends MessageHandler {

    private Pattern pattern_ = Pattern.compile("LIGHTS_ON (\\S+)$");
    private String message = null;;

    public LightsOnMessageHandler(String completeMessage) throws UnknownMessageFormatException {
        super(completeMessage);
        System.out.println("Cheguei ao construtor do lightson");
        Matcher matcher = pattern_.matcher(completeMessage);

        if (matcher.matches())
        {
            message = matcher.group(1);
            System.out.println("DA MATCH " +message);
        }
        else
            throw new UnknownMessageFormatException(message);
    }

    @Override
    public void handleMessage(MessageHandlerContext context) {
        //do something with the message
        System.out.println("message received : " + message);
    }
}
