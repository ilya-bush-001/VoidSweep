package net.voidsweep.gui;

import net.voidsweep.VoidSweep;
import net.voidsweep.utils.ItemCounter;
import net.voidsweep.utils.TPSMonitor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StatsGUI {
    private final VoidSweep plugin;

    public StatsGUI(VoidSweep plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        String title = plugin.getMessagesManager().get("gui.control-panel-title");
        Inventory gui = Bukkit.createInventory(null, 27, title);

        boolean enabled = plugin.getConfigManager().isAutoCleanupEnabled();
        long interval = plugin.getConfigManager().getCleanupIntervalTicks() / 20;

        ItemStack toggleBtn = new ItemStack(
                enabled ? Material.LIME_DYE : Material.GRAY_DYE
        );
        ItemMeta toggleMeta = toggleBtn.getItemMeta();
        toggleMeta.setDisplayName(plugin.getMessagesManager().get("gui.buttons.toggle." + (enabled ? "enabled" : "disabled")));

        toggleMeta.setLore(plugin.getMessagesManager()
                .getStringList("gui.buttons.toggle.lore")
                .stream()
                .map(line -> line.replace("{interval}", String.valueOf(interval)))
                .collect(Collectors.toList()));
        toggleBtn.setItemMeta(toggleMeta);
        gui.setItem(11, toggleBtn);

        ItemStack cleanBtn = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta cleanMeta = cleanBtn.getItemMeta();
        cleanMeta.setDisplayName("§6Instant cleansing");
        cleanMeta.setLore(Arrays.asList(
                "§7Click to launch",
                "§7Subjects: §f" + ItemCounter.countAllItems()
        ));
        cleanBtn.setItemMeta(cleanMeta);
        gui.setItem(13, cleanBtn);

        ItemStack infoBtn = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = infoBtn.getItemMeta();
        infoMeta.setDisplayName("§bServer statistics");
        infoMeta.setLore(Arrays.asList(
                "§7TPS: §f" + String.format("%.2f", plugin.getTPSMonitor().getLastTPS()),
                "§7Chunks: §f" + Bukkit.getWorlds().stream().mapToInt(w -> w.getLoadedChunks().length).sum(),
                "§7Entities: §f" + Bukkit.getWorlds().stream().mapToInt(w -> w.getEntities().size()).sum()
        ));
        infoBtn.setItemMeta(infoMeta);
        gui.setItem(15, infoBtn);

        player.openInventory(gui);
    }
}