package net.voidsweep.commands.handlers;

import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CommandHANDLER implements CommandExecutor {

    private final AutoCleanCYCLE cleaner;

    public CommandHANDLER(AutoCleanCYCLE cleaner) {
        this.cleaner = cleaner;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Usage: /vs clear|reload");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "clear":
                if (!player.hasPermission("voidsweep.command.clear")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }
                int removed = cleaner.autoCleanCYCLE();
                player.sendMessage(ChatColor.GREEN + String.valueOf(removed) + " items were cleaned manually.");
                break;

            case "reload":
                if (!player.hasPermission("voidsweep.command.reload")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }
                player.sendMessage(ChatColor.GREEN + "Unknown subcommand. Try: clear or reload.");
                break;

            default:
                player.sendMessage(ChatColor.RED + "Unknown subcommand. Try: </vs clear> or </vs reload>.");
                break;
        }
        return true;
    }
}
