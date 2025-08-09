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
            sender.sendMessage(plugin.getMessagesManager().getWithPrefix("commands.no-permission"));
            return true;
        }

        int count = plugin.getCleanupTask().cleanup();
        sender.sendMessage(plugin.getMessagesManager()
                .get("commands.clear-success")
                .replace("{count}", String.valueOf(count)));
        return true;
    }
}
