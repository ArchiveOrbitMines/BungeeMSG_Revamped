package me.fadishawki.bungeemsg.bungee.handlers;

public interface Receiver {

    void receiveMessage(Message message);

    Type getType();

    enum Type {

        PLAYER,
        CHANNEL,
        SERVER;

    }
}
