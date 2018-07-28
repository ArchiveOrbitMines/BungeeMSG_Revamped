package me.fadishawki.bungeemsg.bungee.handlers.server;

import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class BungeeServer {

    private List<BungeePlayer> players;

    private ServerInfo server;

    private Channel serverChannel;

    private String name;

    public BungeeServer(ServerInfo server, String name){
        this.players = new ArrayList<>();
        this.serverChannel = new Channel("channel_" + name);
        this.server = server;
        this.name = name;
    }

    public void join(BungeePlayer player){
        players.add(player);
    }

    public void leave(BungeePlayer player){
        players.remove(player);
    }


    public Channel getServerChannel() {
        return serverChannel;
    }

    public String getName() {
        return name;
    }

    public ServerInfo getServer() {
        return server;
    }
}
