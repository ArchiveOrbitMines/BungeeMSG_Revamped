package me.fadishawki.bungeemsg.bungee.utils;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.api.ChatColor;

public class Utils {

    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
