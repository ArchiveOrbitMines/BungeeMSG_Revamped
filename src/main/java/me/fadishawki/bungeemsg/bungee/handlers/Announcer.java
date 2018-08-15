package me.fadishawki.bungeemsg.bungee.handlers;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.BungeeMSG;
import me.fadishawki.bungeemsg.bungee.ScrollerList;
import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import me.fadishawki.bungeemsg.bungee.runnables.BungeeRunnable;
import me.fadishawki.bungeemsg.bungee.runnables.Timer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Announcer implements Sender {

    private final Plugin plugin;

    private final String name;
    private final List<String> servers;
    private final int interval;

    private final ScrollerList<Message> messages;

    private Timer timer;

    public Announcer(String name, List<String> servers, int interval, List<Message> messages) {
        this.plugin = BungeeMSG.getInstance();

        this.name = name;
        this.servers = servers.contains("[ALL]") ? null : servers;
        this.interval = interval;
        this.messages = new ScrollerList<>(messages);

        startTimer();
    }

    public String getName() {
        return name;
    }

    public int getInterval() {
        return interval;
    }

    public void cancel() {
        if (this.timer != null)
            this.timer.cancel();
    }

    /* Start timer on interval */
    private void startTimer() {
        this.timer = new Timer(plugin, new BungeeRunnable.Time(BungeeRunnable.TimeUnit.SECOND, interval)) {
            @Override
            public void onFinish() {
                Message message = messages.next();

                for (BungeePlayer player : getPlayers()) {
                    message.copy(Announcer.this, player).send();
                }

                restart();
            }
        };
    }

    private List<BungeePlayer> getPlayers() {
        if (servers == null)
            return BungeePlayer.getPlayers();

        List<BungeePlayer> players = new ArrayList<>();
        for (BungeePlayer player : BungeePlayer.getPlayers()) {
            if (this.servers.contains(player.getConnectedServer().getServer().getName()))
                players.add(player);
        }

        return players;
    }
}
