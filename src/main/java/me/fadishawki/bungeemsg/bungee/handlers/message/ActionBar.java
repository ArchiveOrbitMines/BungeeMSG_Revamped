package me.fadishawki.bungeemsg.bungee.handlers.message;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import org.json.simple.JSONObject;

public class ActionBar implements Message.Type {

    @Override
    public void send(Receiver receiver) {

    }

    @Override
    public JSONObject serialize() {
        return null;
    }
}
