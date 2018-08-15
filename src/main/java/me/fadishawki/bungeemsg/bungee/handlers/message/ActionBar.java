package me.fadishawki.bungeemsg.bungee.handlers.message;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.BungeeMSG;
import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.handlers.variables.Variable;
import me.fadishawki.bungeemsg.bungee.runnables.BungeeRunnable;
import me.fadishawki.bungeemsg.bungee.runnables.Timer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActionBar implements Message.Instance {

    private String message;

    private int stay;

    public ActionBar(String message, int stay){
        this.message = message;
        this.stay = stay;
    }

    public ActionBar(ActionBar actionBar){
        this(actionBar.message, actionBar.stay);
    }

    @Override
    public Message.Type getType() {
        return Message.Type.ACTION_BAR;
    }

    @Override
    public boolean send(BungeePlayer receiver) {
        new Instance(receiver.getPlayer(), message, stay).send();

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
        return new ActionBar(this);
    }

    /* SETTERS */
    public void setStay(int stay) {
        this.stay = stay;
    }

    public class Instance {

        private Map<ProxiedPlayer, Instance> actionBars = new HashMap<>();

        private final ProxiedPlayer player;
        private final TextComponent message;
        private final long stay;

        private Timer timer;

        public Instance(ProxiedPlayer player, String message, long stay) {
            this.message = new TextComponent(message);
            this.player = player;
            this.stay = stay;
        }

        public void send() {
            actionBars.put(player, this);

            timer = new Timer(BungeeMSG.getInstance(), new BungeeRunnable.Time(BungeeRunnable.TimeUnit.SECOND, (int) stay), new BungeeRunnable.Time(BungeeRunnable.TimeUnit.SECOND, 1)) {

                @Override
                public void onInterval() {
                    if (!player.isConnected()) {
                        stop();
                        return;
                    }

                    /* If another actionbar stopped */
                    if (!actionBars.containsKey(player))
                        actionBars.put(player, Instance.this);

                    /* Check if most recent actionbar is this actionbar */
                    if (actionBars.get(player) == Instance.this)
                        player.sendMessage(ChatMessageType.ACTION_BAR, message);
                }

                @Override
                public void onFinish() {
                    stop();
                }
            };
        }

        public void stop() {
            if (actionBars.get(player) == this)
                actionBars.remove(player);

            if (timer != null)
                timer.cancel();
        }

        public void forceStop() {
            stop();

            /* Clear ActionBar */ //TODO this might be causing the flickering when multiple actionbars are on?
            player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(""));
        }
    }
}
