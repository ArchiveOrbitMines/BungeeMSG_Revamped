package me.fadishawki.bungeemsg.bungee.handlers.config.loaders;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import net.md_5.bungee.config.Configuration;

public class StringLoader extends ConfigLoader {

    private String value;

    public StringLoader(String path) {
        super(path);
    }

    @Override
    public boolean load(Configuration configuration, String path) {
        this.value = configuration.getString(path);
        return true;
    }

    public String getValue() {
        return value;
    }
}
