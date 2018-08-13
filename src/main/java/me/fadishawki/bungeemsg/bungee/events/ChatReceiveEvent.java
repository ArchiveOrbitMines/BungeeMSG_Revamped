package me.fadishawki.bungeemsg.bungee.events;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.message.ChatMessage;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatReceiveEvent implements Listener {

    @EventHandler
    public void onSend(ChatEvent event){
        if(!event.isCommand()){
            if(event.getSender() instanceof ProxiedPlayer){
                BungeePlayer player = BungeePlayer.getPlayer((ProxiedPlayer) event.getSender());
                player.send(new Message(player, player.getCurrentChannel(), new ChatMessage(event.getMessage()))); //GODVERDOMME
            }
        } else {
            //TODO: DISABLE COMMANDS (SERVERS STUFF)
        }
    }
}
