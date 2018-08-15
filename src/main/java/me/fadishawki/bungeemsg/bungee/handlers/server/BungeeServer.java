package me.fadishawki.bungeemsg.bungee.handlers.server;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BungeeServer implements Receiver {

    private static List<BungeeServer> servers = new ArrayList<>();

    private Set<BungeePlayer> players;

    private ServerInfo server;

    public BungeeServer(ServerInfo server){
        servers.add(this);

        this.players = new HashSet<>();
        this.server = server;

        /* Add players that are already online to the hashset */
        for (ProxiedPlayer player : server.getPlayers()) {
            join(BungeePlayer.getPlayer(player));
        }
    }

    public void destroy() {
        servers.remove(this);
    }

    /* PLAYER METHODS */
    public void join(BungeePlayer player){
        players.add(player);
    }

    public void leave(BungeePlayer player){
        players.remove(player);
    }

    /* GETTERS */
    public String getName() {
        return server.getName();
    }

    public ServerInfo getServer() {
        return server;
    }

    public Set<BungeePlayer> getPlayers() {
        return players;
    }

    /* OVERRIDABLE MESSAGES */
    @Override
    public boolean receive(Message message) {
        boolean sent = true;
        for (BungeePlayer player : players) {
            if (!player.receive(message.copy()))
                sent = false;
        }
        return sent;
    }

    @Override
    public Type getType() {
        return Type.SERVER;
    }

    public static BungeeServer getServer(ServerInfo info) {
        for (BungeeServer server : servers) {
            if (server.getName().equals(info.getName()))
                return server;
        }

        return new BungeeServer(info);
    }
}
