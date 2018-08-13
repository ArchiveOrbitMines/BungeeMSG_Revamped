package me.fadishawki.bungeemsg.bungee.handlers.config.loaders;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import net.md_5.bungee.config.Configuration;

public class IntegerLoader extends ConfigLoader {

    private int value;

    public IntegerLoader(String path) {
        super(path);
    }

    @Override
    public boolean load(Configuration configuration, String path) {
        this.value = configuration.getInt(path);
        return true;
    }

    public int getValue() {
        return value;
    }
}
