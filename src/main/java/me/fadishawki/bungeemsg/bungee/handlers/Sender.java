package me.fadishawki.bungeemsg.bungee.handlers;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

public interface Sender {

    boolean send(Receiver receiver, Message.Instance... instance);

    Type getSenderType();

    enum Type {

        PLAYER;

    }
}
