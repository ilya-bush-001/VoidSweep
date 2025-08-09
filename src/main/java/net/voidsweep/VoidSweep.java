package net.voidsweep;

import net.voidsweep.commands.CommandHandler;
import net.voidsweep.config.ConfigManager;
import net.voidsweep.config.MessagesManager;
import net.voidsweep.gui.StatsGUI;
import net.voidsweep.listeners.GUIListener;
import net.voidsweep.tasks.ScheduledCleanupTasks;
import net.voidsweep.utils.TPSMonitor;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidSweep extends JavaPlugin {
    private ConfigManager configManager;
    private MessagesManager messagesManager;
    private StatsGUI statsGUI;
    private ScheduledCleanupTasks cleanupTask;
    private TPSMonitor tpsMonitor;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        saveResource("config.yml", false);
        saveResource("messages.yml", false);

        configManager = new ConfigManager(this);
        messagesManager = new MessagesManager(this);
        statsGUI = new StatsGUI(this);
        cleanupTask = new ScheduledCleanupTasks(this);
        tpsMonitor = new TPSMonitor(this);

        PluginCommand command = getCommand("vs");
        if (command != null) {
            command.setExecutor(new CommandHandler(this));
        } else {
            getLogger().severe("Failed to register command 'vs'. Check plugin.yml!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // Реєстрація слухача подій
        Bukkit.getPluginManager().registerEvents(new GUIListener(this), this);

        // Запуск тасків
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, tpsMonitor, 100L, 100L);

        getLogger().info("VoidSweep enabled successfully!");
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public StatsGUI getStatsGUI() {
        return statsGUI;
    }

    public ScheduledCleanupTasks getCleanupTask() {
        return cleanupTask;
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidSweep is disabled!");
    }

    public TPSMonitor getTPSMonitor() {
        return tpsMonitor;
    }
}