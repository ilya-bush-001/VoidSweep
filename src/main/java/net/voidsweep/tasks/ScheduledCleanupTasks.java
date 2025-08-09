package net.voidsweep.tasks;

import net.md_5.bungee.api.chat.hover.content.Item;
import net.voidsweep.VoidSweep;
import org.bukkit.Bukkit;

public class ScheduledCleanupTasks {
    private final VoidSweep plugin;
    private int taskId;

    public ScheduledCleanupTask(VoidSweep plugin) {
        this.plugin = plugin;
    }

    public ScheduledCleanupTasks(VoidSweep plugin) {
        this.plugin = plugin;
    }

    public void start() {
        long cleanupInterval = plugin.getConfig().getLong("cleanup-interval-ticks", 6000L);
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(
                plugin,
                this::cleanup,
                cleanupInterval,
                cleanupInterval
        );
    }

    private void cleanup() {
        // Логіка очищення (реалізуємо пізніше)
        Bukkit.getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity instanceof Item) {
                    entity.remove();
                }
            });
        });
    }
}
