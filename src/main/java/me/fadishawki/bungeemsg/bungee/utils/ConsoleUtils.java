package me.fadishawki.bungeemsg.bungee.utils;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class ConsoleUtils {

    public static void empty() {
        message("");
    }

    public static void message(String... messages) {
        CommandSender console = ProxyServer.getInstance().getConsole();

        for (String message : messages) {
            console.sendMessage(new TextComponent("[BungeeMSG] " + message));
        }
    }

    public static void printStackTrace(Exception ex) {
        message(
                "",
                "",
                "§cAn issue occurred in BungeeMSG, please contact our developers with a copy of the following error(s):",
                "",
                ""
        );

        ex.printStackTrace();
    }
}
