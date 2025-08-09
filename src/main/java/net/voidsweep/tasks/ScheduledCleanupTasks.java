package net.voidsweep.tasks;

import net.voidsweep.VoidSweep;
import net.voidsweep.utils.ItemCounter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;

public class ScheduledCleanupTasks {
    private final VoidSweep plugin;
    private int taskId = -1;

    public ScheduledCleanupTasks(VoidSweep plugin) {
        this.plugin = plugin;
    }

    private boolean shouldCleanupByItems() {
        if (!plugin.getConfigManager().isItemsCleanupEnabled()) {
            return false;
        }
        int currentItems = ItemCounter.countAllItems();
        return currentItems >= plugin.getConfigManager().getMaxItemsThreshold();
    }

    public int cleanup() {
        int count = 0;
        if (shouldCleanupByItems()) {
            Bukkit.getLogger().info("[VoidSweep] Emergency cleanup launched due to exceeding the item limit!");
        }

        for (org.bukkit.World world : Bukkit.getWorlds()) {
            for (org.bukkit.entity.Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    count++;
                }
            }
        }
        return count;
    }

    public void start() {
        if (taskId != -1) return;
        long interval = plugin.getConfigManager().getCleanupIntervalTicks();
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(
                plugin,
                this::cleanup,
                interval,
                interval
        );
    }

    public void stop() {
        if (taskId == -1) return;
        Bukkit.getScheduler().cancelTask(taskId);
        taskId = -1;
    }
}