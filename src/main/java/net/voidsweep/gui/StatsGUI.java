package net.voidsweep.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.voidsweep.VoidSweep;
import net.voidsweep.utils.TPSMonitor;
import net.voidsweep.utils.ItemCounter;

import java.util.Arrays;

public class StatsGUI {
    private final VoidSweep plugin;

    public StatsGUI(VoidSweep plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        Inventory gui = Bukkit.createInventory(
                null,
                27,
                "§8VoidSweep Control Panel"
        );

        // Кнопка статусу автоочищення
        ItemStack toggleBtn = new ItemStack(
                plugin.getConfigManager().isAutoCleanupEnabled()
                        ? Material.LIME_DYE
                        : Material.GRAY_DYE
        );
        ItemMeta toggleMeta = toggleBtn.getItemMeta();
        toggleMeta.setDisplayName("§aSelf-cleaning: " +
                (plugin.getConfigManager().isAutoCleanupEnabled() ? "§aEnabled" : "§cDisabled"));
        toggleMeta.setLore(Arrays.asList(
                "§7Click to change status",
                "§7Interval: §f" + (plugin.getConfigManager().getCleanupIntervalTicks() / 20) + " сек"
        ));
        toggleBtn.setItemMeta(toggleMeta);
        gui.setItem(11, toggleBtn);

        // Кнопка миттєвого очищення
        ItemStack cleanBtn = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta cleanMeta = cleanBtn.getItemMeta();
        cleanMeta.setDisplayName("§6Instant cleansing");
        cleanMeta.setLore(Arrays.asList(
                "§7Click to launch",
                "§7Subjects: §f" + ItemCounter.countAllItems()
        ));
        cleanBtn.setItemMeta(cleanMeta);
        gui.setItem(13, cleanBtn);

        // Інформаційна панель
        ItemStack infoBtn = new ItemStack(Material.BOOK);
        ItemMeta infoMeta = infoBtn.getItemMeta();
        infoMeta.setDisplayName("§bServer statistics");
        infoMeta.setLore(Arrays.asList(
                "§7TPS: §f" + String.format("%.2f", TPSMonitor.getLastTPS()),
                "§7Chunks: §f" + Bukkit.getWorlds().stream().mapToInt(w -> w.getLoadedChunks().length).sum(),
                "§7Entities: §f" + Bukkit.getWorlds().stream().mapToInt(w -> w.getEntities().size()).sum()
        ));
        infoBtn.setItemMeta(infoMeta);
        gui.setItem(15, infoBtn);

        player.openInventory(gui);
    }
}