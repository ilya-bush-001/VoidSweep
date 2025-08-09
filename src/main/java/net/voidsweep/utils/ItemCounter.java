package net.voidsweep.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Item;

public class ItemCounter {
    public static int countItemsInWorld(World world) {
        return (int) world.getEntities().stream()
                .filter(entity -> entity instanceof Item)
                .count();
    }

    public static int countAllItems() {
        return Bukkit.getWorlds().stream()
                .mapToInt(ItemCounter::countItemsInWorld)
                .sum();
    }
}