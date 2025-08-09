package net.voidsweep.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.voidsweep.VoidSweep;

public class ToggleCommand implements CommandExecutor {
    private final VoidSweep plugin;

    public ToggleCommand(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("voidsweep.toggle")) {
            sender.sendMessage("§cYou do not have permission to change the auto-clean status.!");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage("§cUsage: §f/vs on|off");
            return true;
        }

        boolean newState = args[0].equalsIgnoreCase("on");
        plugin.getConfigManager().setAutoCleanupEnabled(newState);

        if (newState) {
            plugin.getCleanupTask().start();
        } else {
            plugin.getCleanupTask().stop();
        }

        sender.sendMessage("§aAutomatic cleaning " + (newState ? "enabled" : "disabled") + "!");
        return true;
    }
}
