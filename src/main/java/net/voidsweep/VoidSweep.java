package net.voidsweep;

import net.voidsweep.commands.AutoCleanCOMMAND;
import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VoidSweep extends JavaPlugin {

    @Override
    public void onEnable() {
        AutoCleanCYCLE cleaner = new AutoCleanCYCLE();
        getServer().getScheduler().runTaskTimer(this, cleaner::autoCleanCYCLE, 0L, 12000L);

        getLogger().info("VoidSweep has been started!");

        Objects.requireNonNull(getCommand("vs")).setExecutor(new AutoCleanCOMMAND(cleaner));

    }

    @Override
    public void onDisable() {

        getLogger().info("VoidSweep has been stoped!");
    }
}
