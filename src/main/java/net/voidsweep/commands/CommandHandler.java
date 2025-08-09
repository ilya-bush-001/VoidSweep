package net.voidsweep.commands;

import net.voidsweep.VoidSweep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHandler implements CommandExecutor {
    private final VoidSweep plugin;

    public CommandHandler(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                plugin.getStatsGUI().open((Player) sender);
                return true;
            }
            return new HelpCommand(plugin).onCommand(sender, cmd, label, args);
        }

        switch (args[0].toLowerCase()) {
            case "clear":
                return new ClearCommand(plugin).onCommand(sender, cmd, label, args);
            case "reload":
                return new ReloadCommand(plugin).onCommand(sender, cmd, label, args);
            case "help":
                return new HelpCommand(plugin).onCommand(sender, cmd, label, args);
            case "on":
            case "off":
                return new ToggleCommand(plugin).onCommand(sender, cmd, label, args);
            default:
                sender.sendMessage("§cUnknown command! Usage §f/vs help");
                return true;
        }
    }
}