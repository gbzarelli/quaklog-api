package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.ParseObject;
import lombok.val;

import static br.com.luizalabs.quaklog.parser.GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN;

interface SingleIDParser<T extends ParseObject> extends Parsable<T> {

    default Integer extractSingleIDAfterKeyPattern(String value) {
        final val matcher = SINGLE_ID_AFTER_KEY_PATTERN.matcher(value);
        if (matcher.find()) return Integer.parseInt(matcher.group().trim());
        return -1;
    }

}
