package net.voidsweep;

import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoidSweep extends JavaPlugin {

    @Override
    public void onEnable() {
        AutoCleanCYCLE cleaner = new AutoCleanCYCLE();
        getServer().getScheduler().runTaskTimer(this, cleaner::autoCleanCYCLE, 0L, 12000L);

        getLogger().info("VoidSweep has been started!");

    }

    @Override
    public void onDisable() {

        getLogger().info("VoidSweep has been stoped!");
    }
}
