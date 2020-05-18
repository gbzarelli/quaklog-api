package br.com.helpdev.quaklog.parser.impl;

import br.com.helpdev.quaklog.parser.GameRegexUtils;
import br.com.helpdev.quaklog.parser.Parsable;
import br.com.helpdev.quaklog.parser.ParseObject;

interface SingleIDParser<T extends ParseObject> extends Parsable<T> {

    default Integer extractSingleIDAfterKeyPattern(String value) {
        return GameRegexUtils.extractInteger(GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN, value, -1);
    }

}
