package me.fadishawki.bungeemsg.bungee.runnables;

import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.*;

/*
* OrbitMines - @author Fadi Shawki - 2017
*/
public abstract class ProxiedPlayerRunnable {
    
    private Map<Long, List<ProxiedPlayerRunnable>> playerRunnables = new HashMap<>();

    protected final Plugin plugin;

    private BungeeRunnable.Time time;
    private ScheduledTask task;

    public ProxiedPlayerRunnable(Plugin plugin, BungeeRunnable.TimeUnit timeUnit, int amount) {
        this.plugin = plugin;
        this.time = new BungeeRunnable.Time(timeUnit, amount);

        start();
    }

    public abstract void run(BungeePlayer omp);

    public BungeeRunnable.TimeUnit getTimeUnit() {
        return time.getTimeUnit();
    }

    public int getAmount() {
        return time.getAmount();
    }

    public void cancel() {
        if (!playerRunnables.containsKey(time.getSeconds()))
            return;

        if (task != null && playerRunnables.get(time.getSeconds()).size() == 1) {
            task.cancel();
            playerRunnables.remove(time.getSeconds());
            return;
        }

        playerRunnables.get(time.getSeconds()).remove(this);
    }

    private void start() {
        long seconds = time.getSeconds();

        if (playerRunnables.containsKey(seconds)) {
            playerRunnables.get(seconds).add(this);
            return;
        }

        playerRunnables.put(seconds, new ArrayList<>(Collections.singletonList(this)));

        task = plugin.getProxy().getScheduler().schedule(plugin, () -> {
            List<ProxiedPlayerRunnable> runnables = playerRunnables.get(time.getSeconds());

            //TODO -> RedisBungee

            for (BungeePlayer omp : BungeePlayer.getPlayers()) {
                for (ProxiedPlayerRunnable runnable : runnables) {
                    runnable.run(omp);
                }
            }
        }, 0, time.getSeconds(), java.util.concurrent.TimeUnit.SECONDS);
    }

    public boolean isRunning() {
        return playerRunnables.containsKey(time.getSeconds()) && playerRunnables.get(time.getSeconds()).contains(this);
    }
}
