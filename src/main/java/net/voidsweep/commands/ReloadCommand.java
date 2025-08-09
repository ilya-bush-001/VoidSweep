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
            sender.sendMessage("§cУ вас немає дозволу на перезавантаження конфігурації!");
            return true;
        }

        plugin.reloadConfig();
        plugin.getMessagesManager().reload();

        sender.sendMessage("§aКонфігурація плагіна перезавантажена!");
        return true;
    }
}