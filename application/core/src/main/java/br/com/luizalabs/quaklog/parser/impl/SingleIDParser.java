package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.ParseObject;

import static br.com.luizalabs.quaklog.parser.GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN;
import static br.com.luizalabs.quaklog.parser.GameRegexUtils.extractInteger;

interface SingleIDParser<T extends ParseObject> extends Parsable<T> {

    default Integer extractSingleIDAfterKeyPattern(String value) {
        return extractInteger(SINGLE_ID_AFTER_KEY_PATTERN, value, -1);
    }

}
