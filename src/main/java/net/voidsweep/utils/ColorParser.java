package net.voidsweep.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorParser {
    private static final Pattern HEX_PATTERN = Pattern.compile("(?i)&#([a-f0-9]{6})");
    private static final Pattern HEX_LEGACY_PATTERN = Pattern.compile("(?i)&x([&§][a-f0-9]){6}");

    public static String parse(String text) {
        if (text == null) return "";

        text = parseHexColors(text);
        text = ChatColor.translateAlternateColorCodes('&', text);
        return text;
    }

    private static String parseHexColors(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + hex.charAt(0) + "§" + hex.charAt(1) +
                    "§" + hex.charAt(2) + "§" + hex.charAt(3) +
                    "§" + hex.charAt(4) + "§" + hex.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }
}