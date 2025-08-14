package net.voidsweep.cycles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class AutoCleanCYCLE {
    public int autoCleanCYCLE() {
        int removed = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    removed++;
                }
            }
        }
        Bukkit.broadcastMessage(ChatColor.YELLOW + String.valueOf(removed) + ChatColor.GREEN + " items have been cleaned.");
        return removed;
    }
}
