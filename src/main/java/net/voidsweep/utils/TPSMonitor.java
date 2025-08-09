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
        lastTPS = Bukkit.getServer().getTPS()[0];

        if (plugin.getConfigManager().isLowTpsCleanupEnabled()
                && lastTPS < plugin.getConfigManager().getLowTpsThreshold()) {
            plugin.getCleanupTask().cleanup();
            Bukkit.getLogger().warning("[VoidSweep] Automatic cleaning due to low TPS: " + lastTPS);
        }
    }

    public double getLastTPS() {
        return lastTPS;
    }
}