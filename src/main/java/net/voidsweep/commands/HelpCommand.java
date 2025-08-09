package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class HelpCommand implements CommandExecutor {
    private final VoidSweep plugin;

    public HelpCommand(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> helpMessages = Arrays.asList(
                "&6&lVoidSweep &7- Help",
                "&f/vs clear &7- Clean up the trash immediately",
                "&f/vs reload &7- Reload plugin config",
                "&f/vs on|off &7- Enable/disable auto-cleaning",
                "&f/vs help &7- Show this list"
        );

        helpMessages.forEach(message ->
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message))
        );

        return true;
    }
}