package me.fadishawki.bungeemsg.bungee.handlers.config.loaders.message;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.config.Config;
import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import me.fadishawki.bungeemsg.bungee.utils.ConsoleUtils;
import net.md_5.bungee.config.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/* E: Entry, L: Loader */
public class EntryMessageLoader<E, L extends MessageLoader> extends ConfigLoader<List<E>> {

    private final Class<L> loaderClass;
    private final Variable[] variables;

    public EntryMessageLoader(Config.Type type, Class<L> loaderClass, String path, Variable... variables) {
        this(type, loaderClass, path, null, variables);
    }

    public EntryMessageLoader(Config.Type type, Class<L> loaderClass, String path, String key, Variable... variables) {
        super(type, path, key);

        this.loaderClass = loaderClass;
        this.variables = variables;
    }

    @Override
    public boolean load(Configuration configuration) {
        boolean success = true;

        this.value = new ArrayList<>();

        for (String entry : configuration.getSection(path).getKeys()) {
            String entryPath = path + "." + entry;

            L loader;
            try {
                loader = loaderClass.getConstructor(Config.Type.class, String.class, String.class, Variable[].class).newInstance(type, entryPath, entry, variables);

            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                ConsoleUtils.printStackTrace(ex);
                success = false;
                continue;
            }

            if (!loader.load(configuration))
                success = false;
            else
                this.value.add((E) loader.getValue());
        }

        return success;
    }
}
