package me.fadishawki.bungeemsg.bungee.handlers;

public interface Receiver {

    void sendMessage(Message message);

    boolean isChannel();

    boolean isPlayer();

}
