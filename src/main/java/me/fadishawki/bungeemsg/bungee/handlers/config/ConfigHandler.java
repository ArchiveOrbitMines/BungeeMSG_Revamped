package me.fadishawki.bungeemsg.bungee.handlers.config;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    private Plugin plugin;

    private List<Config> configs;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;
        this.configs = new ArrayList<>();
    }

    /* Called on start of Server */
    public void setup() {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        File file = new File(plugin.getDataFolder() + "/configs");
        if (!file.exists())
            file.mkdir();

        for (Config.Type type : Config.Type.values()) {
            this.configs.add(new Config(plugin, type));
        }
    }

    /* Reload all configurations on reload */
    public boolean reload() {
        boolean success = true;
        for (Config config : configs) {
            if (!config.load())
                success = false;
        }
        return success;
    }

    /* GETTERS */
    public Plugin getPlugin() {
        return plugin;
    }

    public List<Config> getConfigs() {
        return configs;
    }

    /* Get configuration by file type */
    public Config getConfig(Config.Type type) {
        for (Config config : configs) {
            if (config.getType() == type)
                return config;
        }
        return null;
    }
}
