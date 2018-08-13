package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageList implements Message.Type {

    private List<ChatMessage> messages;

    public MessageList(List<ChatMessage> messages) {
        this.messages = new ArrayList<>();
    }

    @Override
    public boolean send(Receiver receiver) {
        boolean sent = true;
        for(ChatMessage message : messages){
            if(!message.send(receiver)){
                sent = false;
            }
        }
        return sent;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        boolean modified = false;
        for(ChatMessage message : messages){
            modified = message.adjustFilter(filter);
        }
        return modified;
    }

    @Override
    public JSONObject serialize() {
        return null; //TODO!
    }

    public void addChatMessage(ChatMessage message){
        this.messages.add(message);
    }
}
