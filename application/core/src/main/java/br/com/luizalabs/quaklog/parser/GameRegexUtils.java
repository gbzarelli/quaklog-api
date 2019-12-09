package br.com.luizalabs.quaklog.parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameRegexUtils {
    public static final Pattern TIME_PATTERN = Pattern.compile("\\d.*(?<=\\d:\\d\\d)");
    public static final Pattern KEY_PATTERN = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");
    public static final Pattern SINGLE_ID_AFTER_KEY_PATTERN = Pattern.compile("(?<=[a-zA-Z]:\\s)\\d+");
}
