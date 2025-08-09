package net.voidsweep.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ColorParser {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final LegacyComponentSerializer LEGACY_SERIALIZER =
            LegacyComponentSerializer.builder()
                    .hexColors()
                    .useUnusualXRepeatedCharacterHexFormat()
                    .build();

    public static String parse(String text) {
        Component component = MINI_MESSAGE.deserialize(text);
        return LEGACY_SERIALIZER.serialize(component);
    }
}