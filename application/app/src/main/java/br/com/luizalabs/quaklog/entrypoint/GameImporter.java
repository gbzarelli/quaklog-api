package br.com.luizalabs.quaklog.entrypoint;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @param <I> input type
 * @param <O> output type
 */
public interface GameImporter<I, O> {
    public O importGame(LocalDate fileDate, I file) throws IOException;
}
