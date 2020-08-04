package br.com.helpdev.quaklog.parser;

import br.com.helpdev.quaklog.processor.parser.GameParserKey;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GameParserKeyTest {
    @Test
    void shouldFindItemWithSuccess() {
        assertEquals(GameParserKey.CLIENT_BEGIN, GameParserKey.getByKey("ClientBegin").orElseGet(null));
        assertEquals(GameParserKey.ITEM, GameParserKey.getByKey("Item").orElseGet(null));
        assertEquals(GameParserKey.CLIENT_USER_INFO_CHANGED, GameParserKey.getByKey("ClientUserinfoChanged").orElseGet(null));
        assertEquals(Optional.empty(), GameParserKey.getByKey("abc"));
    }

    @Test
    void shouldParseItemWithSuccess() {
        assertEquals(GameParserKey.CLIENT_CONNECT, GameParserKey.getParserByText("21:51 ClientConnect: 3").orElseGet(null));
        assertEquals(GameParserKey.CLIENT_USER_INFO_CHANGED, GameParserKey.getParserByText("20:38 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0").orElseGet(null));
        assertEquals(GameParserKey.ITEM, GameParserKey.getParserByText("21:49 Item: 2 weapon_rocketlauncher").orElseGet(null));
        assertEquals(GameParserKey.KILL, GameParserKey.getParserByText("22:40 Kill: 2 2 7: Isgalamido killed Isgalamido by MOD_ROCKET_SPLASH").orElseGet(null));
    }
}