package me.fadishawki.bungeemsg.bungee.handlers.config.loaders;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.config.ConfigLoader;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.message.ChatMessage;
import me.fadishawki.bungeemsg.bungee.handlers.message.MessageList;
import me.fadishawki.bungeemsg.bungee.utils.Utils;
import net.md_5.bungee.config.Configuration;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageLoader extends ConfigLoader {

    private Message value;

    public MessageLoader(String path) {
        super(path);
    }

    @Override
    public boolean load(Configuration configuration, String path) {
        List<Message.Type> types = new ArrayList<>();


        //TODO MOVE THESE TO ENUM -> Change Message.Type to Message.Insance, Message.Type -> Enum
        //TODO switch through all Enum values

        /* ChatMessage */
        if (configuration.contains(path + ".Message")) {
            String message = Utils.color(configuration.getString(path + ".Message"));

            types.add(new ChatMessage(message) {
                @Override
                public void adjustFilter(Filter filter) {
                    throw new IllegalStateException();
                }

                @Override
                public boolean send(Receiver receiver) {
                    throw new IllegalStateException();
                }

                @Override
                public JSONObject serialize() {
                    throw new IllegalStateException();
                }
            });
        }

        /* MessageList */
        if (configuration.contains(path + ".MessageList")) {
            List<String> messageList = new ArrayList<>();
            for (String message : configuration.getStringList(path + ".MessageList")) {
                messageList.add(Utils.color(message));
            }

            types.add(new MessageList(messageList) {
                @Override
                public void adjustFilter(Filter filter) {
                    throw new IllegalStateException();
                }

                @Override
                public boolean send(Receiver receiver) {
                    throw new IllegalStateException();
                }

                @Override
                public JSONObject serialize() {
                    throw new IllegalStateException();
                }
            });
        }

        /* Title */
        if (configuration.contains(path + ".Title")) {
            List<String> messageList = new ArrayList<>();
            for (String message : configuration.getStringList(path + ".Title")) {
                messageList.add(Utils.color(message));
            }

            types.add(new MessageList(messageList) {
                @Override
                public void adjustFilter(Filter filter) {
                    throw new IllegalStateException();
                }

                @Override
                public boolean send(Receiver receiver) {
                    throw new IllegalStateException();
                }

                @Override
                public JSONObject serialize() {
                    throw new IllegalStateException();
                }
            });
        }

        /* ActionBar */
        if (configuration.contains(path + ".ActionBar")) {


        }

        return true;
    }

    public Message getValue() {
        return value;
    }
}
