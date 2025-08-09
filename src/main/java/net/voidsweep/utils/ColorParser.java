package net.voidsweep.utils;

import org.bukkit.ChatColor;
import java.util.regex.*;

public class ColorParser {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F0-9]{6})");

    public static String parse(String text) {
        if (text == null) return "";

        text = parseHex(text);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static String parseHex(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1).toLowerCase();
            String replacement = "§x" + "§" + hex.charAt(0) + "§" + hex.charAt(1)
                    + "§" + hex.charAt(2) + "§" + hex.charAt(3)
                    + "§" + hex.charAt(4) + "§" + hex.charAt(5);
            matcher.appendReplacement(buffer, replacement);
        }
        return matcher.appendTail(buffer).toString();
    }
}