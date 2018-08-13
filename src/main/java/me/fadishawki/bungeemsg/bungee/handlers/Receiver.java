package me.fadishawki.bungeemsg.bungee.handlers;

public interface Receiver {

    boolean receive(Message message);

    Type getReceiverType();

    enum Type {

        PLAYER,
        CHANNEL,
        SERVER;

    }
}
