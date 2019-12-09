package br.com.luizalabs.quaklog.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameRegexUtils {

    public static final Pattern TIME_PATTERN = Pattern.compile("\\d.*(?<=\\d:\\d\\d)");
    public static final Pattern KEY_PATTERN = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");
    public static final Pattern SINGLE_ID_AFTER_KEY_PATTERN = Pattern.compile("(?<=[a-zA-Z]:\\s)\\d+");
    public static final Pattern AFTER_KEY = Pattern.compile("(?<=[a-zA-Z]:\\s).+$");
    public static final Pattern AFTER_NUMERIC = Pattern.compile("(?<=\\d\\s\\b).*");

    public static Integer extractInteger(Pattern pattern, String value, Integer defaultValue) {
        final val matcher = pattern.matcher(value);
        if (matcher.find()) return Integer.parseInt(matcher.group().trim());
        return defaultValue;
    }

    public static Map<String, String> extractPairsMap(String splitBy, String text) {
        Map<String, String> map = new HashMap<>();
        if(text.startsWith(splitBy)){
            text = text.substring(1);
        }
        String[] split = text.split(Pattern.quote(splitBy));
        String key = "null";
        for (int i = 0; i < split.length; i++) {
            if (i % 2 == 0) {
                key = split[i];
            } else {
                map.put(key, split[i]);
            }
        }
        return map;
    }
}
