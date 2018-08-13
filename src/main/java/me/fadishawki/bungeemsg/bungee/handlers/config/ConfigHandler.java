package me.fadishawki.bungeemsg.bungee.handlers.config;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import com.google.common.base.Charsets;
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
import java.nio.file.Path;
import java.util.List;

public class ConfigHandler {

    private Plugin plugin;

    private List<Config> configs;

    public ConfigHandler(Plugin plugin) {
        this.plugin = plugin;
    }

    /* Called on start of Server */
    public void setup(String... configs) {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        File file = new File(plugin.getDataFolder() + "/configs");
        if (!file.exists())
            file.mkdir();

        for (String config : configs) {
            this.configs.add(new Config(config));
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
    public List<Config> getConfigs() {
        return configs;
    }

    /* Get configuration by file name */
    public Config getByName(String name) {
        for (Config config : configs) {
            if (config.name.equals(name))
                return config;
        }
        return null;
    }

    /* Copy file from resources folder */
    private boolean copyFile(String filename, Path path) {
        try {
            Files.copy(plugin.getResourceAsStream(filename), path);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public class Config {

        private final String name;
        private Configuration configuration;
        private File cachedFile;

        Config(String name) {
            this.name = name;

            cachedFile = new File(plugin.getDataFolder() + "/configs", name + ".yml");

            if (!cachedFile.exists()) {
                try {
                    Files.copy(plugin.getResourceAsStream(name + ".yml"), cachedFile.toPath());
                } catch (IOException ex) {
                    ConsoleUtils.printStackTrace(ex);
                }
            }

            load();
        }

        /* GETTERS */
        public String getName() {
            return name;
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
}
