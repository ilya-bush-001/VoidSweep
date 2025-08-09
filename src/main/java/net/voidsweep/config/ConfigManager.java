package net.voidsweep.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

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
        config.addDefault("auto-cleanup-enabled", true);
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

    public int getMaxItemsThreshold() {
        return config.getInt("max-items-threshold", 500);
    }

    public boolean isItemsCleanupEnabled() {
        return config.getBoolean("items-cleanup-enabled", true);
    }

    public <T> T get(String path, Class<T> type) {
        return type.cast(config.get(path));
    }

    public List<String> getProtectedItems() {
        return config.getStringList("advanced.protected-items");
    }

    public int getCooldown() {
        return config.getInt("advanced.cooldown", 60);
    }
}