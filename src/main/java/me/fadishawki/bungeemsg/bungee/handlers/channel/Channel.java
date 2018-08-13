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

    public String getName() {
        return name;
    }

    public List<BungeePlayer> getPlayers() {
        return players;
    }

    @Override
    public void receiveMessage(Message message) {
        for(BungeePlayer player : players){
            player.sendMessage(message);
        }
    }

    @Override
    public Type getType() {
        return Type.CHANNEL;
    }
}
