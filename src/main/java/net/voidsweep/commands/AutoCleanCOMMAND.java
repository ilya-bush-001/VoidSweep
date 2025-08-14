package net.voidsweep.commands;

import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AutoCleanCOMMAND implements CommandExecutor {
    private final AutoCleanCYCLE cleaner;

    public AutoCleanCOMMAND(AutoCleanCYCLE cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(!player.hasPermission("voidsweep.command.clear")) {
                player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                return true;
            }

            int removed = cleaner.autoCleanCYCLE();
            sender.sendMessage(ChatColor.GREEN + String.valueOf(removed) + " items were cleaned manually.");
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        }
        return true;
    }
}
