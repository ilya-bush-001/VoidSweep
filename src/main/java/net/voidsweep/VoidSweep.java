package net.voidsweep;

import org.bukkit.plugin.java.JavaPlugin;

public final class VoidSweep extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("VoidSweep is enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidSweep is disabled!");
    }
}
