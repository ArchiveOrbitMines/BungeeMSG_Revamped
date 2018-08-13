package me.fadishawki.bungeemsg.bungee;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BungeeMSG extends Plugin {

    private static ProxyServer proxy;

    private List<BungeeServer> servers;

    @Override
    public void onEnable() {
        this.servers = new ArrayList<>();
        proxy = getProxy();

    }

    public static ProxyServer getProxyServer(){
        return proxy;
    }
}
