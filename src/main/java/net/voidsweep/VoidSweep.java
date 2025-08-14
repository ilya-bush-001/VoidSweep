package net.voidsweep;

import net.voidsweep.commands.AutoCleanCOMMAND;
import net.voidsweep.commands.handlers.CommandHANDLER;
import net.voidsweep.cycles.AutoCleanCYCLE;
import net.voidsweep.whitelist.ItemWHITELIST;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VoidSweep extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        ItemWHITELIST whitelistManager = new ItemWHITELIST(this);
        whitelistManager.loadWhitelist();

        AutoCleanCYCLE cleaner = new AutoCleanCYCLE(whitelistManager);

        Objects.requireNonNull(getCommand("vs")).setExecutor(new CommandHANDLER(cleaner, this));

        getServer().getScheduler().runTaskTimer(this, () -> cleaner.warnings(this), 120L, 12000L);
        getServer().getScheduler().runTaskTimer(this, () -> cleaner.autoCleanCYCLE(true), 200L, 12000L);

        getLogger().info("VoidSweep has been started!");
    }

    @Override
    public void onDisable() {

        getLogger().info("VoidSweep has been stoped!");

    }
}
