package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.BungeeMSG;
import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;

public class Title implements Message.Type {

    private String title;
    private String subTitle;

    private int fadeIn, fadeOut, stay;

    public Title(String title, String subTitle){
        this.title = title;
        this.subTitle = subTitle;
    }

    /* OVERRIDABLE METHODS */
    @Override
    public boolean send(Receiver receiver) {
        switch(receiver.getType()){
            case PLAYER: {
                BungeePlayer player = (BungeePlayer) receiver;
                player.getPlayer().sendTitle(createNewTitle());
                break;
            }
            case CHANNEL: {
                Channel channel = (Channel) receiver;
                for (BungeePlayer player : channel.getPlayers()){
                    player.getPlayer().sendTitle(createNewTitle());
                    break;
                }
            }
            case SERVER: {
                BungeeServer server = (BungeeServer) receiver;
                for (BungeePlayer player : server.getServerChannel().getPlayers()){
                    player.getPlayer().sendTitle(createNewTitle());
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(title) && filter.filter(subTitle);
    }

    @Override
    public JSONObject serialize() {
        return null;
    }

    /* SETTERS */
    public void setFadeIn(int fadeIn){
        this.fadeIn =  fadeIn;
    }

    public void setFadeOut(int fadeOut){
        this.fadeOut = fadeOut;
    }

    public void stay(int stay){
        this.stay = stay;
    }

    /* creates new title */
    private net.md_5.bungee.api.Title createNewTitle(){
        return BungeeMSG.getProxyServer().createTitle().title().title(new TextComponent(this.title)).subTitle(new TextComponent(this.subTitle)).fadeIn(fadeIn).fadeOut(fadeOut).stay(stay);
    }
}
