package br.com.helpdev.quaklog.processor.parser;



public interface Parsable<T extends ParseObject> {
    T parse(final String value) throws GameParserException;

    default String extractTime(final String text) {
        final var matcher = GameRegexUtils.TIME_PATTERN.matcher(text);
        if (matcher.find()) return matcher.group();
        return null;
    }
}
