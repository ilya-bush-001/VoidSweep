package net.voidsweep.cycles;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class AutoCleanCYCLE {
    public void autoCleanCYCLE() {
        int removed = 0;
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                    removed++;
                }
            }
        }
        Bukkit.broadcastMessage("§e" + removed + " §aitems have been cleaned.");
    }
}
