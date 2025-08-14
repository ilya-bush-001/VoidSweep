package net.voidsweep.whitelist;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;

public class ItemWHITELIST {

    private final JavaPlugin plugin;
    private Set<Material> whitelist;

    public ItemWHITELIST(JavaPlugin plugin) {
        this.plugin = plugin;
        loadWhitelist();
    }

    public void loadWhitelist() {
        List<String> list = plugin.getConfig().getStringList("whitelist");
        whitelist = list.stream()
                .map(Material::matchMaterial)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<Material> getWhitelist() {
        return whitelist;
    }
}