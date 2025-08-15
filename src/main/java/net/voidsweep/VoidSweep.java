package net.voidsweep;

import net.voidsweep.commands.handlers.CommandHANDLER;
import net.voidsweep.cycles.AutoCleanCYCLE;
import net.voidsweep.whitelist.ItemWHITELIST;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class VoidSweep extends JavaPlugin {
    private File messagesFile;
    private FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        saveConfig();
        loadMessages();

        ItemWHITELIST whitelistManager = new ItemWHITELIST(this);
        whitelistManager.loadWhitelist();

        AutoCleanCYCLE cleaner = new AutoCleanCYCLE(whitelistManager);

        Objects.requireNonNull(getCommand("vs")).setExecutor(new CommandHANDLER(cleaner, this));

        getServer().getScheduler().runTaskTimer(this, () -> cleaner.warnings(this), 600L, 12000L);
        getServer().getScheduler().runTaskTimer(this, () -> cleaner.autoCleanCYCLE(true), 12000L, 12000L);

        getLogger().info("VoidSweep has been started!");
    }

    @Override
    public void onDisable() {

        getLogger().info("VoidSweep has been stoped!");

    }

    public void loadMessages() {
        messagesFile = new File(getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }
}
