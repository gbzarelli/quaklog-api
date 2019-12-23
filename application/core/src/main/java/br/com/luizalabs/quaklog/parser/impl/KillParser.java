package br.com.luizalabs.quaklog.parser.impl;

import br.com.luizalabs.quaklog.parser.GameParserException;
import br.com.luizalabs.quaklog.parser.GameRegexUtils;
import br.com.luizalabs.quaklog.parser.Parsable;
import br.com.luizalabs.quaklog.parser.objects.KillObParser;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KillParser implements Parsable<KillObParser> {

    @Override
    public KillObParser parse(String value) throws GameParserException {
        try {
            val ids = getIDs(value);
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

    private String getKilledMode(String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_MODE, value);
    }

    private String getKilledName(String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_KILLED, value);
    }

    private String getKillerName(String value) {
        return GameRegexUtils.extractString(GameRegexUtils.KILL_KILLER, value);
    }

    private List<Integer> getIDs(String value) {
        val matcher = GameRegexUtils.KILL_IDS.matcher(value);
        if (matcher.find()) {
            val group = matcher.group();
            return Arrays.stream(group.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
