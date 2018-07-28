package me.fadishawki.bungeemsg.bungee.handlers.channel;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Receiver {

    private List<BungeePlayer> players;

    private String name;

    public Channel(String name){
        this.name = name;
        this.players = new ArrayList<>();
    }

    @Override
    public void sendMessage(Message message) {
        for(BungeePlayer player : players){
            player.sendMessage(message);
        }
    }

    @Override
    public boolean isChannel() {
        return true;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }
}
