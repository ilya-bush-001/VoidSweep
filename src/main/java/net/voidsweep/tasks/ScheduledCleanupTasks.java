package net.voidsweep.tasks;

import net.md_5.bungee.api.chat.hover.content.Item;
import net.voidsweep.VoidSweep;
import org.bukkit.Bukkit;

public class ScheduledCleanupTasks {
    private final VoidSweep plugin;
    private int taskId = -1;

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
