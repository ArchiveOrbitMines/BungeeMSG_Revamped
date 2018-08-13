package me.fadishawki.bungeemsg.bungee.handlers.channel;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Receiver {

    private List<BungeePlayer> listeners;

    private Type type;

    private String name;

    private boolean muted;

    private char chatSymbol;

    public Channel(Type type, String name, char symbol) {
        this.name = name;
        this.type = type;
        this.muted = false;
        this.listeners = new ArrayList<>();
        this.chatSymbol = symbol;
    }

    public void join(BungeePlayer player) {
        this.listeners.add(player);
    }

    public void leave(BungeePlayer player) {
        this.listeners.remove(player);
    }

    /* GETTERS */
    public String getName() {
        return name;
    }

    public List<BungeePlayer> getPlayers() {
        return listeners;
    }

    public Type getChannelType() {
        return type;
    }

    /* BOOLEANS */
    public boolean hasSymbol(){
        return chatSymbol != ' ';
    }

    public boolean isSymbol(char symbol){
        return chatSymbol == symbol;
    }

    public boolean isMuted(){
        return muted;
    }

    /* SETTERS */
    public void setMuted(boolean muted){
        this.muted = muted;
    }

    /* OVERRIDABLE METHODS */
    @Override
    public Receiver.Type getReceiverType() {
        return Receiver.Type.CHANNEL;
    }

    @Override
    public boolean receive(Message message) {
        for (BungeePlayer player : listeners) {
            player.receive(message);
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Channel) {
            Channel channel = (Channel) obj;
            return channel.getName().equals(name);
        }
        return false;
    }

    public enum Type {

        SERVER,
        NORMAL;

    }
}
