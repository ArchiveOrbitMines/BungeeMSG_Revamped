package me.fadishawki.bungeemsg.bungee.runnables;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

/*
* OrbitMines - @author Fadi Shawki - 2017
*/
public abstract class Timer extends Cooldown {

    private final Plugin plugin;

    private final BungeeRunnable.Time interval;

    private BungeeRunnable runnable;
    private long totalSeconds;
    private long remainingSeconds;
    private int intervalSeconds;

    public Timer(Plugin plugin, BungeeRunnable.Time cooldown) {
        this(plugin, cooldown, -1);
}

    public Timer(Plugin plugin, BungeeRunnable.Time cooldown, long delay) {
        this(plugin, cooldown, cooldown, delay);
    }

    public Timer(Plugin plugin, BungeeRunnable.Time cooldown, BungeeRunnable.Time interval) {
        this(plugin, cooldown, interval, -1);
    }

    public Timer(Plugin plugin, BungeeRunnable.Time cooldown, BungeeRunnable.Time interval, long delay) {
        /* 20 ticks = 1 second, 1000 millis = 1 second */
        super(cooldown.getSeconds() * 1000);

        this.plugin = plugin;

        this.interval = interval;

        restart(delay);
    }

    /* Called on Finish */
    public abstract void onFinish();

    /* Called every #getInterval, override to use */
    public  void onInterval() {
    }

    public BungeeRunnable.Time getInterval() {
        return interval;
    }

    public BungeeRunnable getRunnable() {
        return runnable;
    }

    /* Between 0 and 1 */
    public float getProgress() {
        return (float) remainingSeconds / (float) totalSeconds;
    }

    public float getReverseProgress() {
        return ((float) totalSeconds - (float) remainingSeconds) / (float) totalSeconds;
    }

    public long getTotalSeconds() {
        return totalSeconds;
    }

    public void restart() {
        restart(-1);
    }

    public void restart(long delay) {
        totalSeconds = cooldown / 1000;
        remainingSeconds = totalSeconds;
        intervalSeconds = 0;

        if (delay == -1) {
            startRunnable();
            return;
        }

        ProxyServer.getInstance().getScheduler().schedule(plugin, this::startRunnable, delay, java.util.concurrent.TimeUnit.SECONDS);
    }

    private void startRunnable() {
        if (this.runnable != null) {
            runnable.restart();
            return;
        }

        long secondsPerInterval = interval.getSeconds();

        runnable = new BungeeRunnable(plugin, BungeeRunnable.TimeUnit.SECOND, 1) {
            @Override
            public void run() {
                remainingSeconds--;

                if (remainingSeconds == 0) {
                    cancel();
                    onFinish();
                    return;
                }

                intervalSeconds++;

                if (secondsPerInterval == intervalSeconds) {
                    onInterval();

                    intervalSeconds = 0;
                }
            }
        };
    }

    public void cancel() {
        runnable.cancel();
    }

    public void finish() {
        remainingSeconds = 0;
        onFinish();
        cancel();
    }

    public long getRemainingSeconds() {
        return remainingSeconds;
    }
}
