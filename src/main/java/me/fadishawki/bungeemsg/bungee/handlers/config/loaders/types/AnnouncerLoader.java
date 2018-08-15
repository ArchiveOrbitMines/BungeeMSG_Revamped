package me.fadishawki.bungeemsg.bungee.handlers.config.loaders.types;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Announcer;
import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.config.Config;
import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigHandler;
import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.BooleanLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.EntryLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.IntegerLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.StringListLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.message.EntryMessageLoader;
import me.fadishawki.bungeemsg.bungee.handlers.config.loaders.message.MessageLoader;
import net.md_5.bungee.config.Configuration;

public class AnnouncerLoader extends EntryLoader<Announcer, AnnouncerLoader.Entry> {

    private BooleanLoader useLoader;

    public AnnouncerLoader(Config.Type type) {
        super(type, AnnouncerLoader.Entry.class, "Announcements");

        useLoader = new BooleanLoader(type, "Use");
    }

    @Override
    public boolean load(ConfigHandler handler, Config.Type type) {
        /* On pre-reload, stop all announcements */
        for (Announcer announcer : getValue()) {
            announcer.cancel();
        }

        return super.load(handler, type);
    }

    @Override
    public boolean load(Configuration configuration) {
        if (!useLoader.load(configuration))
            return false;

        if (!useLoader.getValue())
            return true;

        return super.load(configuration);
    }

    public class Entry extends ConfigLoader<Announcer> {

        private StringListLoader serversLoader;
        private IntegerLoader intervalLoader;
        private EntryMessageLoader<Message, MessageLoader> announcementLoader;

        public Entry(Config.Type type, String path) {
            this(type, path, null);
        }

        public Entry(Config.Type type, String path, String key) {
            super(type, path, key);

            serversLoader = new StringListLoader(type, path + ".Servers");
            intervalLoader = new IntegerLoader(type, path + ".Interval");
            announcementLoader = new EntryMessageLoader<>(type, MessageLoader.class, path + ".Announcements"

            );
        }

        @Override
        public boolean load(Configuration configuration) {
            if (!serversLoader.load(configuration))
                return false;
            if (!intervalLoader.load(configuration))
                return false;
            if (!announcementLoader.load(configuration))
                return false;

            this.value = new Announcer(key, serversLoader.getValue(), intervalLoader.getValue(), announcementLoader.getValue());

            return true;
        }

        public StringListLoader getServersLoader() {
            return serversLoader;
        }

        public IntegerLoader getIntervalLoader() {
            return intervalLoader;
        }

        public EntryMessageLoader<Message, MessageLoader> getAnnouncementLoader() {
            return announcementLoader;
        }
    }
}
