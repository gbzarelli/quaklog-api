package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameRegexUtils;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.parser.ParseObject;

interface SingleIDParser<T extends ParseObject> extends Parsable<T> {

    default Integer extractSingleIDAfterKeyPattern(final String value) {
        return GameRegexUtils.extractInteger(GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN, value, -1);
    }

}
