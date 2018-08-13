package me.fadishawki.bungeemsg.bungee.handlers.config.loaders;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import net.md_5.bungee.config.Configuration;

public class BooleanLoader extends ConfigLoader {

    private boolean value;

    public BooleanLoader(String path) {
        super(path);
    }

    @Override
    public boolean load(Configuration configuration, String path) {
        this.value = configuration.getBoolean(path);
        return true;
    }

    public boolean getValue() {
        return value;
    }
}
