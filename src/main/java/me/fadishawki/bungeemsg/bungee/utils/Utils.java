package me.fadishawki.bungeemsg.bungee.utils;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import net.md_5.bungee.api.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String getLatestVersion() throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL("http://www.spigotmc.org/api/general.php").openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + 4512).getBytes("UTF-8"));
        String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();

        return version.length() <= 16 ? version : "";
    }
}
