package me.fadishawki.bungeemsg.bungee.handlers.variables;

/*
 * OrbitMines - @author Fadi Shawki - 2018
 */

import me.fadishawki.bungeemsg.bungee.handlers.Message;

public abstract class Variable {

    private final String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }

    public abstract String getReplacement(Message message);
}
