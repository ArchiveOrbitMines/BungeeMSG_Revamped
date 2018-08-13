package me.fadishawki.bungeemsg.bungee.handlers.config.loaders.message;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.Sender;
import me.fadishawki.bungeemsg.bungee.handlers.config.Config;
import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MessageLoader extends ConfigLoader<Message> {

    private Variable[] variables;

    public MessageLoader(Config.Type type, String path, Variable... variables) {
        this(type, path, null, variables);
    }

    public MessageLoader(Config.Type type, String path, String key, Variable... variables) {
        super(type, path, key);

        this.variables = variables;
    }

    @Override
    public boolean load(Configuration configuration) {
        List<Message.Instance> instances = new ArrayList<>();

        for (Message.Type type : Message.Type.values) {
            String typePath = path + "." + type.getPath();

            if (configuration.contains(typePath))
                instances.add(type.load(configuration, typePath));
        }

        if (instances.size() == 0)
            return false;

        value = new Message(null, null, variables, instances.toArray(new Message.Instance[0]));

        return true;
    }

    @Override
    public Message getValue() {
        throw new IllegalStateException();
    }

    public Message getValue(Sender sender, Receiver receiver) {
        return value.copy(sender, receiver);
    }
}
