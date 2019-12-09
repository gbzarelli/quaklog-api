package br.com.luizalabs.quaklog.parser;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameRegexUtilsTest {

    private static final String CLIENT_USER_INFO_CHANGED = "n\\Isgalamido\\t\\0\\model\\uriel/zael\\hmodel\\uriel/zael\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\100\\w\\0\\l\\0\\tt\\0\\tl\\0";

    @Test
    void x() {
        Map<String, String> map = GameRegexUtils.extractPairsMap("\\", CLIENT_USER_INFO_CHANGED);
        assertContainsKeys(map);
        assertValues(map);
    }

    private void assertValues(Map<String, String> map) {
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

    private void assertContainsKeys(Map<String, String> map) {
        assertTrue(map.containsKey("n"));
        assertTrue(map.containsKey("t"));
        assertTrue(map.containsKey("model"));
        assertTrue(map.containsKey("hmodel"));
        assertTrue(map.containsKey("g_redteam"));
        assertTrue(map.containsKey("g_blueteam"));
        assertTrue(map.containsKey("c1"));
        assertTrue(map.containsKey("c2"));
        assertTrue(map.containsKey("hc"));
        assertTrue(map.containsKey("w"));
        assertTrue(map.containsKey("l"));
        assertTrue(map.containsKey("tt"));
        assertTrue(map.containsKey("tl"));
    }

}