package net.voidsweep;

import net.voidsweep.commands.CommandHandler;
import net.voidsweep.config.ConfigManager;
import net.voidsweep.config.MessagesManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidSweep extends JavaPlugin {
    private ScheduledCleanupTask cleanupTask;
    private boolean isScheduledCleanupEnabled = true;

    @Override
    public void onEnable() {
        this.getCommand("vs").setExecutor(new CommandHandler(this));

        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);

        getLogger().info("VoidSweep is enabled!");
        this.saveDefaultConfig();

        cleanupTask = new ScheduledCleanupTask(this);
        if (isScheduledCleanupEnabled) {
            cleanupTask.start();
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidSweep is disabled!");
    }
}
