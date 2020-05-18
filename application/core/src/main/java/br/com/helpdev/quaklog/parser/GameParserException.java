package br.com.helpdev.quaklog.parser;

public class GameParserException extends Exception {
    public GameParserException(String message) {
        super(message);
    }

    public GameParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
