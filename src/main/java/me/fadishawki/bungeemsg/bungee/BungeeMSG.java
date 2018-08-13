package me.fadishawki.bungeemsg.bungee;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.plugin.Plugin;
import org.bstats.bungeecord.Metrics;

import java.util.ArrayList;
import java.util.List;

public class BungeeMSG extends Plugin {

    private static BungeeMSG instance;
    private Metrics metrics;

    private List<BungeeServer> servers;

    @Override
    public void onEnable() {
        instance = this;
        metrics = new Metrics(this);

        this.servers = new ArrayList<>();
    }

    public static BungeeMSG getInstance() {
        return instance;
    }

    public Metrics getMetrics() {
        return metrics;
    }
}
