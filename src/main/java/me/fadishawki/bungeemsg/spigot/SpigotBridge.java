package me.fadishawki.bungeemsg.spigot;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.util.Map;

public class SpigotBridge extends JavaPlugin {

    private Metrics metrics;

    @java.lang.Override
    public void onEnable() {
        this.metrics = new Metrics(this);
    }
}
