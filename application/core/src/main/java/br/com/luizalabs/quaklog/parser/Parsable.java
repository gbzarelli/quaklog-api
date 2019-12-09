package br.com.luizalabs.quaklog.parser;

import jdk.internal.jline.internal.Nullable;
import lombok.val;

public interface Parsable<T extends ParseObject> {
    T parse(String value) throws GameParserException;

    @Nullable
    default String extractTime(String text) {
        val matcher = GameRegexUtils.TIME_PATTERN.matcher(text);
        if (matcher.find()) return matcher.group();
        return null;
    }
}
