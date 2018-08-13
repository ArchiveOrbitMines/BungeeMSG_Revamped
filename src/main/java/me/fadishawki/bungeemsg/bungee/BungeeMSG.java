package me.fadishawki.bungeemsg.bungee;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.util.ArrayList;
import java.util.List;

public class BungeeMSG extends Plugin {

    private static BungeeMSG instance;
    private static ProxyServer proxy;

    private Metrics metrics;

    private List<BungeeServer> servers;
    private List<Channel> channels;

    @Override
    public void onEnable() {
        instance = this;
        metrics = new Metrics(this);

        proxy = getProxy();

        this.servers = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    public static BungeeMSG getInstance() {
        return instance;
    }

    public static ProxyServer getProxyServer(){
        return proxy;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public List<BungeeServer> getServers() {
        return servers;
    }
}
