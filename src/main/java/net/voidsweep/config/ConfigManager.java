package net.voidsweep.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {
    private final JavaPlugin plugin;
    private FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        plugin.reloadConfig();
        config = plugin.getConfig();
        config.addDefault("auto-cleanup-enabled", true); // Додаємо дефолтне значення
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public boolean isAutoCleanupEnabled() {
        return config.getBoolean("auto-cleanup-enabled");
    }

    public void setAutoCleanupEnabled(boolean value) {
        config.set("auto-cleanup-enabled", value);
        plugin.saveConfig();
    }

    public double getLowTpsThreshold() {
        return config.getDouble("low-tps-threshold", 15.0);
    }

    public boolean isLowTpsCleanupEnabled() {
        return config.getBoolean("low-tps-cleanup-enabled", true);
    }
}