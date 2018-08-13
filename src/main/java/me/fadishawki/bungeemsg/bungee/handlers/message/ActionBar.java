package me.fadishawki.bungeemsg.bungee.handlers.message;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.channel.Channel;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.server.BungeeServer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.SkinConfiguration;
import net.md_5.bungee.api.chat.TextComponent;
import org.json.simple.JSONObject;

public class ActionBar implements Message.Type {

    private String message;

    private int stay;

    public ActionBar(String message){
        this.message = message;
    }


    @Override
    public boolean send(Receiver receiver) {
        switch(receiver.getType()) {
            case PLAYER: {
                BungeePlayer player = (BungeePlayer) receiver;
                player.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                break;
            }
            case CHANNEL: {
                Channel channel = (Channel) receiver;
                for(BungeePlayer player : channel.getPlayers()){
                    player.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                }
                break;
            }
            case SERVER: {
                BungeeServer server = (BungeeServer) receiver;
                for(BungeePlayer player : server.getServerChannel().getPlayers()){
                    player.getPlayer().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                }
                break;
            }
        }
        return true;
    }

    @Override
    public boolean adjustFilter(Filter filter) {
        return filter.filter(message);
    }

    @Override
    public JSONObject serialize() {
        return null;
    }

    /* SETTERS */
    public void setStay(int stay) {
        this.stay = stay;
    }
}
