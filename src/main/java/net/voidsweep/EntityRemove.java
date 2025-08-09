package net.voidsweep;

import org.bukkit.entity.Item;
import org.bukkit.entity.Entity;
import java.util.List;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class EntityRemove {
    public void clearItems() {
        List<Entity> entities = Objects.requireNonNull(getServer().getWorld("world")).getEntities();
        int removed = 0;

        for (Entity entity : entities) {
            if (entity instanceof Item) {
                entity.remove();
                removed++;
            }
        }
        getServer().broadcastMessage(removed + " items deleted!");
    }
}
