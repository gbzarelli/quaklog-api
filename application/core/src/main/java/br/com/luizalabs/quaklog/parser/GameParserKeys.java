package br.com.luizalabs.quaklog.parser;

import br.com.luizalabs.quaklog.parser.impl.*;
import lombok.val;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Keys extracted from GameKeysTest.java
 */
public enum GameParserKeys {

    CLIENT_BEGIN("ClientBegin", new ClientBeginParser()),
    ITEM("Item", new ItemParser()),
    CLIENT_USER_INFO_CHANGED("ClientUserinfoChanged", new ClientUserInfoChangedParser()),
    SHUTDOWN_GAME("ShutdownGame", new ShutdownGameParser()),
    INIT_GAME("InitGame", new InitGameParser()),
    CLIENT_DISCONNECT("ClientDisconnect", new ClientDisconnectParser()),
    KILL("Kill", new KillParser()),
    CLIENT_CONNECT("ClientConnect", new ClientConnectParser());

    private static final Pattern EXTRACT_KEY_PATTERN = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");
    public final String key;
    public final Parsable parsable;

    GameParserKeys(String key, Parsable parsable) {
        this.key = key;
        this.parsable = parsable;
    }

    public static Optional<GameParserKeys> getParserByText(String text) {
        final val matcher = EXTRACT_KEY_PATTERN.matcher(text);
        if (matcher.find()) {
            return getByKey(matcher.group());
        }
        return Optional.empty();
    }

    public static Optional<GameParserKeys> getByKey(String key) {
        return Arrays.stream(values()).filter(it -> it.key.equals(key)).findFirst();
    }
}
