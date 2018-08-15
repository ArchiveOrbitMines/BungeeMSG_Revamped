package me.fadishawki.bungeemsg.bungee.handlers.server;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class BungeeServer implements Receiver {

    private List<BungeePlayer> players;

    private ServerInfo server;

    private Channel serverChannel;

    private String name;

    public BungeeServer(ServerInfo server, String name){
        this.players = new ArrayList<>();
        this.serverChannel = new Channel(Channel.Type.SERVER, "channel_" + name, ' ');
        this.server = server;
        this.name = name;
    }

    /* PLAYER METHODS */
    public void join(BungeePlayer player){
        this.players.add(player);
        this.serverChannel.join(player);
    }

    public void leave(BungeePlayer player){
        this.players.remove(player);
        this.serverChannel.leave(player);
    }

    /* GETTERS */
    public Channel getServerChannel() {
        return serverChannel;
    }

    public String getName() {
        return name;
    }

    public ServerInfo getServer() {
        return server;
    }

    public List<BungeePlayer> getPlayers() {
        return players;
    }

    /* OVERRIDABLE MESSAGES */
    @Override
    public boolean receive(Message message) {
       return serverChannel.receive(message);
    }

    @Override
    public Type getReceiverType() {
        return Type.SERVER;
    }
}
