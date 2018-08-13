package me.fadishawki.bungeemsg.bungee.handlers;

import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import org.json.simple.JSONObject;

import java.util.List;

public class Message {

    private Receiver receiver;
    private BungeePlayer sender;

    private List<Type> types;

    private boolean modified;

    public Message(BungeePlayer sender, Receiver receiver, String message){
        this.message = message;
        this.receiver = receiver;
        this.sender =  sender;
    }
    
    public void adjustFilter(Filter filter){
        this.message = filter.getMessage(message);
        this.modified = filter.isModified(message);
    }

    public String getMessage(){
        return message;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public BungeePlayer getSender() {
        return sender;
    }

    public boolean isFiltered() {
        return modified;
    }

    public interface Type {

        boolean send(Receiver receiver);

        void adjustFilter(Filter filter);

        JSONObject serialize();

    }
}
