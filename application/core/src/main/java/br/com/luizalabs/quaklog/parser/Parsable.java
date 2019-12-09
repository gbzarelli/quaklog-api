package br.com.luizalabs.quaklog.parser;

public interface Parsable<T extends ParseObject> {
    T parse(String value);
}
