package net.voidsweep.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import net.voidsweep.VoidSweep;

public class GUIListener implements Listener {
    private final VoidSweep plugin;

    public GUIListener(VoidSweep plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§8VoidSweep Control Panel")) return;

        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null) return;

        switch (event.getRawSlot()) {
            case 11:
                boolean newState = !plugin.getConfigManager().isAutoCleanupEnabled();
                plugin.getConfigManager().setAutoCleanupEnabled(newState);
                break;

            case 13:
                plugin.getCleanupTask().cleanup();
                event.getWhoClicked().sendMessage("§aCleaning started!");
                break;
        }

        if (event.getWhoClicked() instanceof Player) {
            plugin.getStatsGUI().open((Player) event.getWhoClicked());
        }
    }
}