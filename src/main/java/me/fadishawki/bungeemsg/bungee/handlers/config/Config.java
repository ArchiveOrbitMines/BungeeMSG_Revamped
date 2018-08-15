package me.fadishawki.bungeemsg.bungee.handlers.config;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import com.google.common.base.Charsets;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.types.AnnouncerLoader;
import me.fadishawki.bungeemsg.bungee.utils.ConsoleUtils;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Config {

    public static final AnnouncerLoader ANNOUNCERS = new AnnouncerLoader(Type.announcer_cfg);

    public enum Type {

        announcer_cfg;

        private final String path;
        private final List<ConfigLoader> loaders;

        Type() {
            this(null);
        }

        Type(String path) {
            this.path = path == null ? "" : "/" + path;
            this.loaders = new ArrayList<>();
        }

        /* GETTERS */
        public List<ConfigLoader> getLoaders() {
            return loaders;
        }

        /* Load all config values */
        public boolean load(ConfigHandler handler) {
            boolean success = true;
            for (ConfigLoader loader : loaders) {
                if (!loader.load(handler, this))
                    success = false;
            }
            return success;
        }

        @Override
        public String toString() {
            return path + super.toString();
        }
    }

    private final Config.Type type;
    private Configuration configuration;
    private File cachedFile;

    Config(Plugin plugin, Config.Type type) {
        this.type = type;

        cachedFile = new File(plugin.getDataFolder() + "/configs", type.toString() + ".yml");

        if (!cachedFile.exists()) {
            try {
                Files.copy(plugin.getResourceAsStream(type + ".yml"), cachedFile.toPath());
            } catch (IOException ex) {
                ConsoleUtils.printStackTrace(ex);
            }
        }

        load();
    }

    /* GETTERS */
    public Type getType() {
        return type;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public File getCachedFile() {
        return cachedFile;
    }

    /* Save Configuration */
    public boolean save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, cachedFile);
            return true;
        } catch(IOException ex) {
            ConsoleUtils.printStackTrace(ex);
            return false;
        }
    }

    /* Load Configuration */
    public boolean load() {
        try {
            configuration = YamlConfiguration.getProvider(YamlConfiguration.class).load(new InputStreamReader(new FileInputStream(cachedFile), Charsets.UTF_8));
            return true;
        } catch (IOException ex) {
            ConsoleUtils.printStackTrace(ex);
            return false;
        }
    }
}
