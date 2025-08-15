package net.voidsweep.commands.handlers;

import net.voidsweep.VoidSweep;
import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class CommandHANDLER implements CommandExecutor {

    private final AutoCleanCYCLE cleaner;
    private final VoidSweep plugin;

    public CommandHANDLER(AutoCleanCYCLE cleaner, VoidSweep plugin) {
        this.cleaner = cleaner;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        switch (args[0].toLowerCase()) {
            case "clear":
                if (!player.hasPermission("voidsweep.command.clear")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }
                int removed = cleaner.autoCleanCYCLE(false);
                player.sendMessage(ChatColor.GREEN + String.valueOf(removed) + " items were cleaned manually.");
                break;

            case "reload":
                if (!player.hasPermission("voidsweep.command.reload")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }
                plugin.reloadConfig();
                cleaner.reloadWhitelist();
                player.sendMessage(ChatColor.GREEN + "The plugin has been successfully reloaded!");
                break;

            case "on":
                if (!player.hasPermission("voidsweep.command.autoclean.on")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("on")) {
                    cleaner.setAutoCleanEnabled(true);
                    sender.sendMessage(ChatColor.GREEN + "Auto-cleaning enabled.");
                    return true;
                }

            case "off":
                if (!player.hasPermission("voidsweep.command.autoclean.off")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("off")) {
                    cleaner.setAutoCleanEnabled(false);
                    sender.sendMessage(ChatColor.RED + "❌ Auto-cleaning disabled.");
                    return true;
                }

            case "help":
                if (!player.hasPermission("voidsweep.command.help")) {
                    player.sendMessage(ChatColor.RED + "You don't have enough permissions!");
                    return true;
                }

                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.GOLD + "=== VoidSweep Help ===");
                    sender.sendMessage(ChatColor.YELLOW + "/vs clear" + ChatColor.WHITE + " - Clean items manually.");
                    sender.sendMessage(ChatColor.YELLOW + "/vs reload" + ChatColor.WHITE + " - Reload configuration.");
                    sender.sendMessage(ChatColor.YELLOW + "/vs toggle" + ChatColor.WHITE + " - Enable/disable auto-cleaning.");
                    sender.sendMessage(ChatColor.YELLOW + "/vs help" + ChatColor.WHITE + " - Show this message.");
                    sender.sendMessage(ChatColor.GOLD + "===============");
                    return true;
                }

            default:
                player.sendMessage(ChatColor.RED + "Unknown subcommand. Usage: /vs <clear|reload|on|off>.");
                break;
        }
        return true;
    }
}
