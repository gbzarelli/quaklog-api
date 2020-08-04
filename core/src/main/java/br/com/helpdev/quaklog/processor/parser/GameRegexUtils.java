package br.com.helpdev.quaklog.processor.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class GameRegexUtils {
    private GameRegexUtils() {
    }

    public static final Pattern TIME_PATTERN = Pattern.compile("\\d.*(?<=\\d:\\d\\d)");
    public static final Pattern KEY_PATTERN = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");

    public static final Pattern SINGLE_ID_AFTER_KEY_PATTERN = Pattern.compile("(?<=[a-zA-Z]:\\s)\\d+");
    public static final Pattern AFTER_KEY = Pattern.compile("(?<=[a-zA-Z]:\\s).+$");
    public static final Pattern AFTER_KEY_AND_NUMBER_GROUP2 = Pattern.compile("\\w+:\\s\\d+\\s(.*)");
    public static final Pattern KILL_IDS = Pattern.compile("(?<=:\\s).*\\d");
    public static final Pattern KILL_KILLER = Pattern.compile("(?<=\\d:\\s).*(?=\\skilled)");
    public static final Pattern KILL_KILLED = Pattern.compile("(?<=\\skilled\\s).*(?=\\sby)");
    public static final Pattern KILL_MODE = Pattern.compile("(?<=\\sby\\s).*");

    public static Integer extractInteger(final Pattern pattern,
                                         final String value,
                                         final Integer defaultValue) {
        final var matcher = pattern.matcher(value);
        if (matcher.find()) return Integer.parseInt(matcher.group().trim());
        return defaultValue;
    }

    public static Map<String, String> extractPairsMap(final String splitBy,
                                                      final String text) {
        final var map = new HashMap<String, String>();
        var subStringText = text;
        if (text.startsWith(splitBy)) {
            subStringText = text.substring(1);
        }
        final var split = subStringText.split(Pattern.quote(splitBy));
        var key = "null";
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                key = split[i];
            } else {
                map.put(key, split[i]);
            }
        }
        return map;
    }

    public static String extractString(final Pattern pattern,
                                       final String value) {
        final var matcher = pattern.matcher(value);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
