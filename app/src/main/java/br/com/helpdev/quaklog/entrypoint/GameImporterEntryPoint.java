package br.com.helpdev.quaklog.entrypoint;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @param <I> input type
 * @param <O> output type
 */
public interface GameImporterEntryPoint<I, O> {
    O importGame(LocalDate fileDate, I file) throws IOException;
}
