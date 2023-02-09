package octo.content;

import arc.struct.Seq;
import mindustry.Vars;
import octo.core.cmd.Command;
import octo.core.cmd.CommandArg;
import octo.core.cmd.CommandEntry;
import octo.core.util.Gamemode;
import org.jetbrains.annotations.NotNull;

public class OctoCommands {
    public static final CommandEntry entry = new CommandEntry();

    public static void registerCommands() {
        entry.register(new Command("js") {}.register(new CommandArg() {
            @Override
            public @NotNull String name() {
                return "-eval";
            }

            @Override
            public void parse(Seq<String> args, Command command, CommandEntry entry) {
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i < args.size; i++) {
                    builder.append(args.get(i));

                    if(i < args.size - 1) {
                        builder.append(" ");
                    }
                }

                entry.log(Vars.mods.getScripts().runConsole(builder.toString()), true);
            }
        }));

        entry.register(new Command("log") {}.register(new CommandArg() {
            @Override
            public @NotNull String name() {
                return "-lg";
            }

            @Override
            public void parse(Seq<String> args, Command command, CommandEntry entry) {
                entry.log(args, true);
            }
        }));

        entry.register(new Command("gamemode") {}.register(new CommandArg() {
            @Override
            public @NotNull String name() {
                return "m";
            }

            @Override
            public void parse(Seq<String> args, Command command, CommandEntry entry) {
                if(args.size > 0) {
                    Gamemode gamemode = null;
                    switch(args.get(0).toLowerCase()) {
                        case "creative", "1": {
                            gamemode = Gamemode.CREATIVE;
                            break;
                        }

                        case "survival", "2": {
                            gamemode = Gamemode.SURVIVAL;
                            break;
                        }

                        case "hardcore", "3": {
                            gamemode = Gamemode.HARDCORE;
                            break;
                        }

                        default: {
                            entry.log("[red]ERROR: Gamemode don`t exists: " + args.get(0), true);
                        }
                    }

                    if(gamemode != null) {
                        if(gamemode == Gamemode.mode) {
                            entry.log("[yellow]WARNING: gamemode alrealy has this value", true);
                        } else {
                            Gamemode.mode = gamemode;
                            entry.log("[gray]mode changed to " + gamemode.name().toLowerCase(), true);
                        }
                    }
                } else {
                    entry.log("[red]ERROR: you don`t select mode", true);
                }
            }
        }));
    }
}