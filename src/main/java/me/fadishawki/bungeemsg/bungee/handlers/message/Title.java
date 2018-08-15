package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.BungeeMSG;
import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;

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
    public boolean send(BungeePlayer receiver) {
        receiver.getPlayer().sendTitle(BungeeMSG.getInstance().getProxy().createTitle().title()
                .title(new TextComponent(this.title))
                .subTitle(new TextComponent(this.subTitle))
                .fadeIn(fadeIn * 20)
                .fadeOut(fadeOut * 20)
                .stay(stay * 20)
        );

        return true;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(title) && filter.filter(subTitle);
    }

    @Override
    public boolean applyVariables(BungeePlayer receiver, Variable[] variables) {
        //TODO
        return true;
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
}
