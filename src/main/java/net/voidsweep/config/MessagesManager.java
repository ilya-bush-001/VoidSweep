package net.voidsweep.config;

import net.voidsweep.utils.ColorParser;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.List;

public class MessagesManager {
    private final JavaPlugin plugin;
    private YamlConfiguration messages;

    public MessagesManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public String get(String path) {
        String message = messages.getString(path, "&cMissing message: " + path);
        return ColorParser.parse(message.replace("&", "§"));
    }

    public String getWithPrefix(String path) {
        return get("prefix") + get(path);
    }

    public List<String> getStringList(String path) {
        return messages.getStringList(path);
    }
}