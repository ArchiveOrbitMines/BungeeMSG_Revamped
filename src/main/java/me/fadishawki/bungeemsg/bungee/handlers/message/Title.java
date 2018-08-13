package me.fadishawki.bungeemsg.bungee.handlers.message;

import me.fadishawki.bungeemsg.bungee.handlers.Message;
import me.fadishawki.bungeemsg.bungee.handlers.Receiver;
import me.fadishawki.bungeemsg.bungee.handlers.filter.Filter;
import org.json.simple.JSONObject;

public class Title implements Message.Type {

    private String title;
    private String subTitle;

    public Title(String title, String subTitle){
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public boolean send(Receiver receiver) {
        return false;
    }

    @Override
    public void adjustFilter(Filter filter) {

    }

    @Override
    public JSONObject serialize() {
        return null;
    }
}
