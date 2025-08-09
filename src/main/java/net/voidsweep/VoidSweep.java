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
        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);

        this.getCommand("vs").setExecutor(new CommandHandler(this));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidSweep is disabled!");
    }
}
