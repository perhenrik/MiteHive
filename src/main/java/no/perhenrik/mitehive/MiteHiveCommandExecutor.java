package no.perhenrik.mitehive;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by perhenrik on 16.07.2016.
 */

public class MiteHiveCommandExecutor implements CommandExecutor {
    private final MiteHivePlugin plugin;

    public MiteHiveCommandExecutor(MiteHivePlugin miteHivePlugin) {
        this.plugin = miteHivePlugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean isOp = sender.isOp();

        if (cmd.getName().equalsIgnoreCase("mitehive")) {
            if(args.length < 1) {
                usage(sender);
            } else {
                ReturnValue ret = new ReturnValue();
                ret.setOk(false);
                ret.setMessage("You do not have permission to run this command");
                switch(args[0]) {
                    case "info":
                        if(isOp) {
                            info(sender);
                            ret.setOk(true);
                            ret.setMessage("");
                        }
                        break;
                    case "reload":
                        if(isOp) {
                            plugin.reload();
                            ret.setOk(true);
                            ret.setMessage("MiteHive reloaded");
                        }
                        break;
                    case "debug":
                        if(isOp) {
                            plugin.setDebug(!plugin.getDebug());
                            plugin.saveConfig();
                            ret.setOk(true);
                            ret.setMessage("debug toggled to " + plugin.getDebug());
                        }
                        break;
                    default:
                        usage(sender);
                        ret.setOk(true);
                        ret.setMessage("");
                }

                if(!ret.isOk()) {
                    sendError(sender, ret.getMessage());
                } else {
                    sendText(sender, ret.getMessage());
                }

            }
            return true;
        }
        return false;
    }

    private void sendError(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    private void sendText(CommandSender sender, String text) {
        sender.sendMessage(text);
    }

    private void info(CommandSender sender) {
        sender.sendMessage("MiteHive Info:");
        sender.sendMessage("------------");
        sender.sendMessage(MiteHivePlugin.ENABLED_WORLD + plugin.getWorld());
        sender.sendMessage(MiteHivePlugin.DEBUG + plugin.getDebug());
    }

    private void usage(CommandSender sender) {
        //sender.sendMessage("Sunburn will kill all living entities and plants exposed to sunlight");
    }

}
