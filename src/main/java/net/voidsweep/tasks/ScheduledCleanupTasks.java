package net.voidsweep.tasks;

import net.md_5.bungee.api.chat.hover.content.Item;
import net.voidsweep.VoidSweep;
import net.voidsweep.utils.ItemCounter;
import org.bukkit.Bukkit;

public class ScheduledCleanupTasks {
    private final VoidSweep plugin;
    private int taskId = -1;

    private boolean shouldCleanupByItems() {
        if (!plugin.getConfigManager().isItemsCleanupEnabled()) {
            return false;
        }
        int currentItems = ItemCounter.countAllItems();
        return currentItems >= plugin.getConfigManager().getMaxItemsThreshold();
    }

    public void cleanup() {
        if (shouldCleanupByItems()) {
            Bukkit.getLogger().info("[VoidSweep] Emergency cleanup launched due to exceeding the item limit!");
        }
        Bukkit.getWorlds().forEach(world -> {
            world.getEntitiesByClass(Item.class).forEach(Item::remove);
        });
    }

    public ScheduledCleanupTask(VoidSweep plugin) {
        this.plugin = plugin;
    }

    public ScheduledCleanupTasks(VoidSweep plugin) {
        this.plugin = plugin;
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

    private void cleanup() {
        Bukkit.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity instanceof Item) {
                    entity.remove();
                }
            });
        });
    }
}
