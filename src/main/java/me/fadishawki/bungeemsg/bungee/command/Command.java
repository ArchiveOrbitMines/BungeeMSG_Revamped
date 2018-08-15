package me.fadishawki.bungeemsg.bungee.command;

import me.fadishawki.bungeemsg.bungee.handlers.player.BungeePlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Command {

    private List<Argument> arguments;

    private String permission;

    private String command;

    public Command(String command) {
        this(command, "");
    }

    public Command(String command, String permission){
        this.command = command;
        this.permission = permission;
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

    abstract void toExecute(BungeePlayer player, String[] args);

    /* GETTERS */
    public abstract String[] getAlias();

    private Argument getArgument(String argument){
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

        private String permission;

        private String argument;

        public Argument(String argument){
            this(argument, "");
        }

        public Argument(String argument, String permission){
            this.argument = argument;
            this.permission = permission;
            this.arguments = new ArrayList<>();
        }

        void execute(BungeePlayer player, String[] args){
            //TODO: CHECK PLAYERS PERMISSION!
            List<String> arguments = Arrays.asList(args).subList(1, args.length);
            Argument argument = getArgument(arguments.get(0));
            if(argument != null){
                arguments.remove(argument.getArgument());
                argument.execute(player, args);
            } else if(arguments.isEmpty()){
                this.toExecute(player, args);
            } else {
                //TODO: HELP ARGUMENT!
            }
        }

        abstract void toExecute(BungeePlayer player, String[] args);

        public abstract String[] getAlias();

        String getArgument() {
            return argument;
        }

        private Argument getArgument(String argument){
            for(Argument arg : arguments){
                if(arg.getArgument().equalsIgnoreCase(argument)){
                    return arg;
                }
            }
            return null; //TODO: RETURN HELP ARGUMENT!
        }

        public void addArgument(Argument argument){
            this.arguments.add(argument);
        }
    }
}
