package me.fadishawki.bungeemsg.bungee;

import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {

    private List<Argument> arguments;

    private String command;

    public Command(String command) {
        this.command = command;
        this.arguments = new ArrayList<>();
    }

    public String getCommand() {
        return command;
    }

    public abstract void execute(BungeePlayer player, String[] args);

    public abstract String[] getAlias();

    public Argument getArgument(String argument){
        for(Argument arg : arguments){

        }
        return null; //TODO: RETURN HELP ARGUMENT
    }

    public void addArgument(Argument argument){
        this.arguments.add(argument);
    }

    public abstract class Argument {

        private List<Argument> arguments;

        private String argument;

        public Argument(String argument){
            this.argument = argument;
            this.arguments = new ArrayList<>();
        }

        public void execute(BungeePlayer player, String[] args){

        }

        public abstract void toExecute(BungeePlayer player, String[] args);

        public abstract String[] getAlias();

        public String getArgument() {
            return argument;
        }

        public void addArgument(Argument argument){
            this.arguments.add(argument);
        }
    }
}
