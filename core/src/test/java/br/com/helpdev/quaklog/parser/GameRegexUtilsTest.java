package br.com.helpdev.quaklog.parser;

import br.com.helpdev.quaklog.processor.parser.GameRegexUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GameRegexUtilsTest {

    private static final String CLIENT_USER_INFO_CHANGED = "n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";
    private static final String INIT_GAME = "\\sv_floodProtect\\1\\sv_maxPing\\0\\sv_minPing\\0\\sv_maxRate\\10000\\sv_minRate\\0\\sv_hostname\\Code Miner Server\\g_gametype\\0\\sv_privateClients\\2\\sv_maxclients\\16\\sv_allowDownload\\0\\bot_minplayers\\0\\dmflags\\0\\fraglimit\\20\\timelimit\\15\\g_maxGameClients\\0\\capturelimit\\8\\version\\ioq3 1.36 linux-x86_64 Apr 12 2009\\protocol\\68\\mapname\\q3dm17\\gamename\\baseq3\\g_needpass\\0";

    @Test
    void shouldExtractParametersFromClientChangedWithSuccess() {
        final var map = GameRegexUtils.extractPairsMap("\\", CLIENT_USER_INFO_CHANGED);
        assertValuesClient(map);
    }

    @Test
    void shouldExtractParametersFromInitGameWithSuccess() {
        final var map = GameRegexUtils.extractPairsMap("\\", INIT_GAME);
        assertValuesInitGame(map);
    }

    @Test
    void shouldExtractInvalidIntegerInExtractIntegerWhenHaveInvalidInput() {
        final var integer = GameRegexUtils.extractInteger(GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN, "", -1);
        assertEquals(-1, integer.intValue());
    }

    @Test
    void shouldExtractValidIntegerInExtractIntegerWithValidInput() {
        final var integer = GameRegexUtils.extractInteger(GameRegexUtils.SINGLE_ID_AFTER_KEY_PATTERN, "00:00 ABC: 10 XXX", -1);
        assertEquals(10, integer.intValue());
    }

    @Test
    void shouldExtractNullObjectInExtractStringWhenExtractInvalidInput() {
        final var value = GameRegexUtils.extractString(GameRegexUtils.KEY_PATTERN, "");
        assertNull(value);
    }

    @Test
    void shouldExtractValueStringInExtractStringWhenExtractInvalidInput() {
        final var value = GameRegexUtils.extractString(GameRegexUtils.KEY_PATTERN, "00:00 ABC: 10 XXX");
        assertEquals("ABC", value);
    }

    private void assertValuesInitGame(Map<String, String> map) {
        assertEquals("1", map.get("sv_floodProtect"));
        assertEquals("0", map.get("sv_maxPing"));
        assertEquals("0", map.get("sv_minPing"));
        assertEquals("10000", map.get("sv_maxRate"));
        assertEquals("0", map.get("sv_minRate"));
        assertEquals("Code Miner Server", map.get("sv_hostname"));
        assertEquals("0", map.get("g_gametype"));
        assertEquals("2", map.get("sv_privateClients"));
        assertEquals("16", map.get("sv_maxclients"));
        assertEquals("0", map.get("sv_allowDownload"));
        assertEquals("0", map.get("bot_minplayers"));
        assertEquals("0", map.get("dmflags"));
        assertEquals("20", map.get("fraglimit"));
        assertEquals("15", map.get("timelimit"));
        assertEquals("0", map.get("g_maxGameClients"));
        assertEquals("8", map.get("capturelimit"));
        assertEquals("ioq3 1.36 linux-x86_64 Apr 12 2009", map.get("version"));
        assertEquals("68", map.get("protocol"));
        assertEquals("q3dm17", map.get("mapname"));
        assertEquals("baseq3", map.get("gamename"));
        assertEquals("0", map.get("g_needpass"));
    }

    private void assertValuesClient(Map<String, String> map) {
        assertEquals("Isgalamido", map.get("n"));
        assertEquals("0", map.get("t"));
        assertEquals("uriel/zael", map.get("model"));
        assertEquals("uriel/zael", map.get("hmodel"));
        assertEquals("", map.get("g_redteam"));
        assertEquals("", map.get("g_blueteam"));
        assertEquals("5", map.get("c1"));
        assertEquals("5", map.get("c2"));
        assertEquals("100", map.get("hc"));
        assertEquals("0", map.get("w"));
        assertEquals("0", map.get("l"));
        assertEquals("0", map.get("tt"));
        assertEquals("0", map.get("tl"));
    }

}