package me.fadishawki.bungeemsg.bungee.handlers.player;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.Sender;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BungeePlayer implements Receiver, Sender {

    private static List<BungeePlayer> players = new ArrayList<>();

    private BungeeServer server;
    private Channel currentChannel;

    private ProxiedPlayer player;

    public BungeePlayer(ProxiedPlayer player){
        this.player = player;
    }

    /* JOIN & LEAVE METHODS */
    public void connect(BungeeServer server) {
        if (this.server != null) {
            this.disconnect();
        }
        this.server = server;
        if (this.server != null) {
            this.player.setReconnectServer(server.getServer());
            this.currentChannel = server.getServerChannel();
        }
    }

    public void disconnect(){
        this.server.leave(this);
        this.server = null;
    }

    /* GETTERS */
    public ProxiedPlayer getPlayer(){
        return player;
    }

    public BungeeServer getConnectedServer(){
        return server;
    }

    public Channel getCurrentChannel() {
        return currentChannel;
    }

    /* OVERRIDABLE METHODS */
    @Override
    public boolean receive(Message message) {

        return false;
    }

    @Override
    public Type getType() {
        return Type.PLAYER;
    }

    @Override
    public boolean send(Message message) {
        return message.send();
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

    public static List<BungeePlayer> getPlayers() {
        return players;
    }
}
