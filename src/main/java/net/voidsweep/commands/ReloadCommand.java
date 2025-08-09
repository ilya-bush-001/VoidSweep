package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private final VoidSweep plugin;

    public ReloadCommand(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("voidsweep.reload")) {
            sender.sendMessage("§cYou do not have permission to reboot the configuration.!");
            return true;
        }

        plugin.reloadConfig();
        plugin.getMessagesManager().reload();

        sender.sendMessage("§aPlugin configuration reloaded!");
        return true;
    }
}