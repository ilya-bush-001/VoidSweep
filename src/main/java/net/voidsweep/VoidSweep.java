package net.voidsweep;

import net.voidsweep.commands.CommandHandler;
import net.voidsweep.config.ConfigManager;
import net.voidsweep.config.MessagesManager;
import net.voidsweep.gui.StatsGUI;
import net.voidsweep.listeners.GUIListener;
import net.voidsweep.utils.TPSMonitor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidSweep extends JavaPlugin {
    private ScheduledCleanupTask cleanupTask;
    private boolean isScheduledCleanupEnabled = true;

    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        saveResource("messages.yml", false);

        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);

        tpsMonitor = new TPSMonitor(this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, tpsMonitor, 100L, 100L);

        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);

        this.getCommand("vs").setExecutor(new CommandHandler(this));

        statsGUI = new StatsGUI(this);
        getCommand("vs").setExecutor(new CommandHandler(this));
        Bukkit.getPluginManager().registerEvents(new GUIListener(this), this);

        public StatsGUI getStatsGUI() {
            return statsGUI;
        }
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
