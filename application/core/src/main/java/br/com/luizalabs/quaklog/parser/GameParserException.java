package br.com.luizalabs.quaklog.parser;

import lombok.AllArgsConstructor;

public class GameParserException extends Exception {
    public GameParserException(String message) {
        super(message);
    }

    public GameParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
