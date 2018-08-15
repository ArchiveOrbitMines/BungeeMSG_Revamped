package me.fadishawki.bungeemsg.bungee.command;

import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private List<Argument> arguments;

    private String command;

    public Command(String command) {
        this.command = command;
        this.arguments = new ArrayList<>();
    }

    /* first execution */
    public void execute(BungeePlayer player, String[] args){
        //TODO: CHECK PLAYER HAS PERMISSION!
        List<String> arguments = Arrays.asList(args).subList(1, args.length);
        Argument argument = getArgument(arguments.get(0));
        if(argument != null){
            arguments.remove(argument.getArgument());
            argument.execute(player, (String[]) arguments.toArray());
        } else if(arguments.isEmpty()){
            this.toExecute(player, args);
        } else {
            //TODO: HELP ARGUMENT
        }
    }

    public abstract void toExecute(BungeePlayer player, String[] args);

    /* GETTERS */
    public abstract String[] getAlias();

    public Argument getArgument(String argument){
        for(Argument arg : arguments){
            if(arg.getArgument().equalsIgnoreCase(argument)){
                return arg;
            }
        }
        return null; //TODO: RETURN HELP ARGUMENT
    }

    public String getCommand() {
        return command;
    }

    /* SETTERS */
    public void addArgument(Argument argument){
        this.arguments.add(argument);
    }

    /* SUB-CLASS */
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
