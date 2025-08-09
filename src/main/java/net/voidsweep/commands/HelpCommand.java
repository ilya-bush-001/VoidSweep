package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCommand implements CommandExecutor {
    private final VoidSweep plugin;

    public HelpCommand(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(plugin.getMessagesManager().getWithPrefix("commands.help-header"));
        List<String> helpMessages = plugin.getMessagesManager().getStringList("commands.help-entries");
        helpMessages.forEach(message -> sender.sendMessage(message));
        return true;
    }
}