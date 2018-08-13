package me.fadishawki.bungeemsg.bungee.handlers.player;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BungeePlayer implements Receiver {

    private static List<BungeePlayer> players = new ArrayList<>();

    private BungeeServer server;
    private Channel currentChannel;

    private ProxiedPlayer player;

    public BungeePlayer(ProxiedPlayer player){
        this.player = player;
    }

    /*JOIN & LEAVE METHODS*/
    public void connect(BungeeServer server) {
        if (server != null) {
            disconnect();
        }
        this.server = server;
        if (server != null) {
            player.setReconnectServer(server.getServer());
        }
    }

    public void disconnect(){
        this.server.leave(this);
        this.server = null;
    }

    /* GETTERS */
    public ProxiedPlayer getPlayer() {
        return player;
    }

    public BungeeServer getConnectedServer(){
        return server;
    }

    /* OVERRIDABLE METHODS */
    @Override
    public void receiveMessage(Message message) {

    }

    @Override
    public Type getType() {
        return Type.PLAYER;
    }

    /* MESSAGE METHODS */
    public void sendMessage(Message message) {

    }

    /* STATIC METHODS */
    public static BungeePlayer getPlayer(String name){
        for(BungeePlayer player : players){
            if(name.equals(player.getPlayer().getName())){
                return player;
            }
        }
        return null;
    }

    public static BungeePlayer getPlayer(UUID id){
        for(BungeePlayer player : players){
            if(player.getPlayer().getUniqueId().equals(id)){
                return player;
            }
        }
        return null;
    }

    public static BungeePlayer getPlayer(ProxiedPlayer player){
        return getPlayer(player.getUniqueId());
    }
}
