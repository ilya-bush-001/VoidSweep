package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ClearCommand implements CommandExecutor {
    private final VoidSweep plugin;

    public ClearCommand(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!sender.hasPermission("voidsweep.clear")) {
            sender.sendMessage("§cYou don`t have permissions!");
            return true;
        }

        plugin.getCleanupTask().cleanup();
        sender.sendMessage("§aItems deleted!");
        return true;
    }
}
