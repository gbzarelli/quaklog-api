package br.com.helpdev.quaklog.processor.parser.impl;

import br.com.helpdev.quaklog.processor.parser.GameParserException;
import br.com.helpdev.quaklog.processor.parser.GameRegexUtils;
import br.com.helpdev.quaklog.processor.parser.Parsable;
import br.com.helpdev.quaklog.processor.parser.objects.KillObParser;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KillParser implements Parsable<KillObParser> {

    @Override
    public KillObParser parse(final String value) throws GameParserException {
        try {
            final var ids = getIDs(value);
            if (ids.size() != 3) throw new GameParserException("Falha ao extrair IDs da Kill");
            return KillObParser.builder()
                    .gameTime(extractTime(value))
                    .killerID(ids.get(0))
                    .killedID(ids.get(1))
                    .killedModeID(ids.get(2))
                    .killer(getKillerName(value))
                    .killed(getKilledName(value))
                    .killedMode(getKilledMode(value))
                    .build();
        } catch (Exception e) {
            throw new GameParserException(e.getMessage(), e);
        }
    }

    private String getKilledMode(final String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_MODE, value);
    }

    private String getKilledName(final String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_KILLED, value);
    }

    private String getKillerName(final String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_KILLER, value);
    }

    private List<Integer> getIDs(final String value) {
        final var matcher = GameRegexUtils.KILL_IDS.matcher(value);
        if (matcher.find()) {
            final var group = matcher.group();
            return Arrays.stream(group.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
