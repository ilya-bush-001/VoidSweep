package net.voidsweep.cycles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoCleanCYCLE {
    private boolean autoCleanEnabled = true;

    public void setAutoCleanEnabled(boolean enabled) {
        this.autoCleanEnabled = enabled;
    }

    public boolean isAutoCleanEnabled() {
        return autoCleanEnabled;
    }


    public int autoCleanCYCLE(boolean broadcast) {
        if (!autoCleanEnabled) return 0;
        int removed = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    removed++;
                }
            }
        }
        if (broadcast) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + String.valueOf(removed) + ChatColor.GREEN + " items have been cleaned.");
        }
        return removed;
    }

    public void warnings(JavaPlugin plugin) {
        int[] seconds = {120, 10, 5, 4, 3, 2, 1};

        for (int sec : seconds) {
            long delay = (120 - sec) * 20L;

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "⚠️ Cleaning in " + sec + " second" + (sec == 1 ? "!" : "s!"));
            }, delay);
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            int removed = autoCleanCYCLE(true);
        }, 2400L);
    }
}
