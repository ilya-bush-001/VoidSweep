package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadPluginCOMMAND implements CommandExecutor {
    private final VoidSweep sweep;

    public ReloadPluginCOMMAND(VoidSweep sweep) {
        this.sweep = sweep;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if(!sender.hasPermission("voidsweep.command.reload")) {
            sender.sendMessage(ChatColor.RED + "You don't have enough permissions!");
            return true;
        }

        sweep.reloadConfig();

        sender.sendMessage(ChatColor.GREEN + "The plugin has been successfully reloaded!");

        return true;
    }
}
