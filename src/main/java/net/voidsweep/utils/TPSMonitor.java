package net.voidsweep.utils;

import net.voidsweep.VoidSweep;
import org.bukkit.Bukkit;

public class TPSMonitor implements Runnable {
    private final VoidSweep plugin;
    private double lastTPS = 20.0;

    public TPSMonitor(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        // Отримуємо поточний TPS (1.16.5 Paper API)
        lastTPS = Bukkit.getServer().getTPS()[0];

        // Якщо TPS нижче порогу - запускаємо очищення
        if (plugin.getConfigManager().isLowTpsCleanupEnabled()
                && lastTPS < plugin.getConfigManager().getLowTpsThreshold()) {
            plugin.getCleanupTask().cleanup();
            Bukkit.getLogger().warning("[VoidSweep] Автоматичне очищення через низький TPS: " + lastTPS);
        }
    }

    public double getLastTPS() {
        return lastTPS;
    }
}