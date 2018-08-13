package me.fadishawki.bungeemsg.bungee.handlers;

import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import org.json.simple.JSONObject;

public class Message {

    private Receiver receiver;
    private Sender sender;

    private Instance[] instances;

    public Message(Sender sender, Receiver receiver, Instance... instances){
        this.receiver = receiver;
        this.sender =  sender;
        this.instances = instances;
    }

    /* MESSAGE - METHODS */
    public void adjustFilter(Filter filter){
        for(Instance instance : instances){
            instance.adjustFilter(filter);
        }
    }

    public boolean send(){
        boolean sent = true;
        for(Instance instance : instances){
            sent = instance.send(receiver);
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

    public interface Instance {

        Type getType();

        boolean send(Receiver receiver);

        boolean adjustFilter(Filter filter);

        JSONObject serialize();

    }

    public enum Type {

        CHAT,
        ACTION_BAR,
        MESSAGE_LIST,
        TITLE;

    }
}
