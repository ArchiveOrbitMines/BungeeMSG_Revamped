package me.fadishawki.bungeemsg.bungee.handlers.config;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.config.Configuration;

public abstract class ConfigLoader {

    private final String path;

    public ConfigLoader(String path) {
        this.path = path;
    }

    /* Make loading for instances easy */
    public abstract boolean load(Configuration configuration, String path);

    /* Load configuration */
    public boolean load(ConfigHandler handler, Config.Type type) {
        Configuration configuration = handler.getConfig(type).getConfiguration();

        if (configuration.contains(this.path))
            return false;

        return load(configuration, this.path);
    }
}
