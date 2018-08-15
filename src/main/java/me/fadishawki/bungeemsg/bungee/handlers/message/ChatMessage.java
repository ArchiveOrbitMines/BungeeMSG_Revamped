package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;

public class ChatMessage implements Message.Instance {

    private String message;

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage(ChatMessage chatMessage) {
        this(chatMessage.message);
    }

    @Override
    public Message.Type getType() {
        return Message.Type.CHAT;
    }

    @Override
    public boolean send(Receiver receiver) {
        //TODO: PERHAPS CHANGE SOME & ADD A FORMAT! (MSG: [name] >> [you] : message) (CHANNEL: [name] : message)
        switch (receiver.getType()) {
            case PLAYER: {
                BungeePlayer player = (BungeePlayer) receiver;
                player.getPlayer().sendMessage(new TextComponent(message));
                break;
            }
            case CHANNEL: {
                Channel channel = (Channel) receiver;
                for (BungeePlayer player : channel.getPlayers()) {
                    player.getPlayer().sendMessage(new TextComponent(message));
                }
                break;
            }
            case SERVER: {
                BungeeServer server = (BungeeServer) receiver;
                for (BungeePlayer player : server.getServerChannel().getPlayers()) {
                    player.getPlayer().sendMessage(new TextComponent(message));
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(message);
    }

    @Override
    public boolean hasVariable(Variable variable) {
        return message.contains(variable.getVariable());
    }

    @Override
    public JSONObject serialize() {
        return null;
    }

    @Override
    public Message.Instance copy() {
        return new ChatMessage(this);
    }
}
