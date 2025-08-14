package net.voidsweep;

import net.voidsweep.commands.AutoCleanCOMMAND;
import net.voidsweep.commands.handlers.CommandHANDLER;
import net.voidsweep.cycles.AutoCleanCYCLE;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VoidSweep extends JavaPlugin {

    @Override
    public void onEnable() {

        AutoCleanCYCLE cleaner = new AutoCleanCYCLE();
        Objects.requireNonNull(getCommand("vs")).setExecutor(new CommandHANDLER(cleaner, this));

        getServer().getScheduler().runTaskTimer(this, () -> cleaner.autoCleanCYCLE(true), 200L, 12000L);

        reloadConfig();

        getLogger().info("VoidSweep has been started!");

    }

    @Override
    public void onDisable() {

        getLogger().info("VoidSweep has been stoped!");

    }
}
