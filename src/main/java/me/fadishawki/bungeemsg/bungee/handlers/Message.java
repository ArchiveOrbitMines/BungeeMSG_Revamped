package me.fadishawki.bungeemsg.bungee.handlers;

import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import org.json.simple.JSONObject;

import java.util.List;

public class Message {

    private Receiver receiver;
    private Sender sender;

    private Message.Type[] types;

    public Message(Sender sender, Receiver receiver, Message.Type... types){
        this.receiver = receiver;
        this.sender =  sender;
        this.types = types;
    }

    /* MESSAGE - METHODS */
    public void adjustFilter(Filter filter){
        for(Type type : types){
            type.adjustFilter(filter);
        }
    }

    public boolean send(){
        boolean sent = true;
        for(Type type : types){
            sent = type.send(receiver);
        }
        return sent;
    }

    /* GETTERS */
    public Receiver getReceiver() {
        return receiver;
    }

    public Sender getSender() {
        return sender;
    }

    public interface Type {

        boolean send(Receiver receiver);

        boolean adjustFilter(Filter filter);

        JSONObject serialize();

    }
}
