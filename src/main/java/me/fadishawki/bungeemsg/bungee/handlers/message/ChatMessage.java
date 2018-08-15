package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
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
    public boolean send(BungeePlayer receiver) {
        receiver.getPlayer().sendMessage(new TextComponent(message));

        return true;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(message);
    }

    @Override
    public boolean applyVariables(BungeePlayer receiver, Variable[] variables) {
        //TODO
        return true;
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
