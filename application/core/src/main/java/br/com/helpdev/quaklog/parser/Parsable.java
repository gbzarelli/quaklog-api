package br.com.helpdev.quaklog.parser;

import lombok.val;

public interface Parsable<T extends ParseObject> {
    T parse(String value) throws GameParserException;

    default String extractTime(String text) {
        val matcher = GameRegexUtils.TIME_PATTERN.matcher(text);
        if (matcher.find()) return matcher.group();
        return null;
    }
}
