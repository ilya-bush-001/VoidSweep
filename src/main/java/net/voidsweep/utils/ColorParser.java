package net.voidsweep.utils;

import net.md_5.bungee.api.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorParser {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");
    private static final Pattern GRADIENT_PATTERN = Pattern.compile("<gradient:#([A-Fa-f0-9]{6}:#([A-Fa-f0-9]{6})>(.+?)</gradient>)");

    public static String parse(String text) {
        // Обробка HEX-кольорів (&#RRGGBB)
        text = parseHexColors(text);
        // Обробка градієнтів
        text = parseGradients(text);
        // Стандартні кольори Bukkit (&a, &l тощо)
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static String parseHexColors(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
        }
        return matcher.appendTail(buffer).toString();
    }

    private static String parseGradients(String text) {
        Matcher matcher = GRADIENT_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String startColor = matcher.group(1);
            String endColor = matcher.group(2);
            String content = matcher.group(3);

            String gradient = applyGradient(content, startColor, endColor);
            matcher.appendReplacement(buffer, gradient);
        }
        return matcher.appendTail(buffer).toString();
    }

    private static String applyGradient(String text, String startHex, String endHex) {
        StringBuilder result = new StringBuilder();
        int length = text.length();

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (float) length;
            String interpolated = interpolateColor(startHex, endHex, ratio);
            result.append(ChatColor.of(interpolated)).append(text.charAt(i));
        }

        return result.toString();
    }

    private static String interpolateColor(String startHex, String endHex, float ratio) {
        int startR = Integer.parseInt(startHex.substring(0, 2), 16);
        int startG = Integer.parseInt(startHex.substring(2, 4), 16);
        int startB = Integer.parseInt(startHex.substring(4, 6), 16);

        int endR = Integer.parseInt(endHex.substring(0, 2), 16);
        int endG = Integer.parseInt(endHex.substring(2, 4), 16);
        int endB = Integer.parseInt(endHex.substring(4, 6), 16);

        int currentR = (int) (startR + ratio * (endR - startR));
        int currentG = (int) (startG + ratio * (endG - startG));
        int currentB = (int) (startB + ratio * (endB - startB));

        return String.format("#%02x%02x%02x", currentR, currentG, currentB);
    }
}