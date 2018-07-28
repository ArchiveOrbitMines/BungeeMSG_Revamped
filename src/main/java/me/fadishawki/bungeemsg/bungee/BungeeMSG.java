package me.fadishawki.bungeemsg.bungee;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class BungeeMSG extends Plugin {

    private List<BungeeServer> servers;


    @Override
    public void onEnable() {
        this.servers = new ArrayList<>();
    }
}
