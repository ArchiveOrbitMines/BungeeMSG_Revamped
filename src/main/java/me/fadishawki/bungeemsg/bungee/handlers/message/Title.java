package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.BungeeMSG;
import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.Collections;

public class Title implements Message.Instance {

    private String title;
    private String subTitle;

    private int fadeIn, stay, fadeOut;

    public Title(String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        this.title = title;
        this.subTitle = subTitle;
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public Title(Title title) {
        this(title.title, title.subTitle, title.fadeIn, title.stay, title.fadeOut);
    }

    @Override
    public Message.Type getType() {
        return Message.Type.TITLE;
    }

    /* OVERRIDABLE METHODS */
    @Override
    public boolean send(Receiver receiver) {
        switch (receiver.getType()) {
            case PLAYER: {
                send(Collections.singletonList((BungeePlayer) receiver));
                break;
            }
            case CHANNEL: {
                send(((Channel) receiver).getPlayers());
                break;
            }
            case SERVER: {
                send(((BungeeServer) receiver).getServerChannel().getPlayers());
                break;
            }
        }
        return true;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(title) && filter.filter(subTitle);
    }

    @Override
    public boolean hasVariable(Variable variable) {
        return title.contains(variable.getVariable()) || subTitle.contains(variable.getVariable());
    }

    @Override
    public JSONObject serialize() {
        return null;
    }

    @Override
    public Message.Instance copy() {
        return new Title(this);
    }

    /* Send Title */
    private void send(Collection<? extends BungeePlayer> players) {
        for (BungeePlayer player : players) {
            player.getPlayer().sendTitle(BungeeMSG.getInstance().getProxy().createTitle().title().title(new TextComponent(this.title)).subTitle(new TextComponent(this.subTitle)).fadeIn(fadeIn * 20).fadeOut(fadeOut * 20).stay(stay * 20));
        }
    }
}
