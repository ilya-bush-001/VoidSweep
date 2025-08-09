package net.voidsweep.utils;

import org.bukkit.ChatColor;

public class ColorParser {
    public static String parse(String text) {
        if (text == null) return "";

        text = parseHexColors(text);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static String parseHexColors(String text) {
        return text.replaceAll("(?i)&#([a-f0-9]{6})", "§x§$1");
    }
}