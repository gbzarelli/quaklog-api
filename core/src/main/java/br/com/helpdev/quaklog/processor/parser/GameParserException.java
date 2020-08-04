package br.com.helpdev.quaklog.processor.parser;

public class GameParserException extends Exception {
    public GameParserException(String message) {
        super(message);
    }

    public GameParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
