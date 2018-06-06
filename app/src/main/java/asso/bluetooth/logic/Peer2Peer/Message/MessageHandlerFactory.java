package asso.bluetooth.logic.Peer2Peer.Message;

/**
 * Created by franc on 06/06/2018.
 */

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.lang.reflect.Constructor;

import asso.bluetooth.logic.Peer2Peer.Message.MessageHandlers.MessageHandler;

public class MessageHandlerFactory
{
    private Set<Class> handlers_ = new HashSet<Class>();
    public MessageHandlerFactory()
    {
        try{
            //list of handlers
            addHandler("asso.bluetooth.logic.Peer2Peer.Message.MessageHandlers.LightsOnMessageHandler");
            System.out.println("ADICIONEI HANDLER ");

        }catch(Exception e){
           System.out.print("Unable to add handlers: " + e);
        }

    }


    private void addHandler(String handlerName) throws ClassNotFoundException
    {
        Class handlerClass = null;
        try
        {
            handlerClass = Class.forName(handlerName);
            handlers_.add(handlerClass);
        }
        catch (Exception e)
        {
            System.out.println(e);
            throw new ClassNotFoundException(handlerName);
        }
    }


    public MessageHandler getMessageHandler(String message)
    {
        MessageHandler handler = null;
        Constructor ctor;
        Iterator it = handlers_.iterator();

        while(it.hasNext() && (handler == null))
        {
            System.out.println("TEM NEXT ");
            try
            {
                ctor = ((Class)it.next()).getConstructor(String.class);
                System.out.println("ISTO " + ctor.getName());
                try
                {
                    handler = (MessageHandler)ctor.newInstance(message);
                }
                catch (Exception e)
                {
                    handler = null;
                }
            }
            catch (NoSuchMethodException nsm)
            {
                System.out.println("Bad handler found:  " + nsm.toString());
            }
        }

        return handler;
    }

}
