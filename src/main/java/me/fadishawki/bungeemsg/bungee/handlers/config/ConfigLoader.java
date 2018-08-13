package me.fadishawki.bungeemsg.bungee.handlers.config;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.config.Configuration;

public abstract class ConfigLoader<T> {

    protected final Config.Type type;
    protected final String path;
    protected final String key;
    protected T value;

    public ConfigLoader(Config.Type type, String path) {
        this(type, path, null);
    }

    public ConfigLoader(Config.Type type, String path, String key) {
        this.type = type;
        this.path = path;
        this.key = key;

        this.type.getLoaders().add(this);
    }

    /* Make loading for instances easy */
    public abstract boolean load(Configuration configuration);

    /* Load configuration */
    public boolean load(ConfigHandler handler, Config.Type type) {
        Configuration configuration = handler.getConfig(type).getConfiguration();

        if (configuration.contains(this.path))
            return false;

        /* Reset Value */
        this.value = null;

        return load(configuration);
    }

    /* Get the loaded value */
    public T getValue() {
        return value;
    }
}
