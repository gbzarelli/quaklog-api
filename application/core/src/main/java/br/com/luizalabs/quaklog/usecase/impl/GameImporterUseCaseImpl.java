package br.com.luizalabs.quaklog.usecase.impl;

import br.com.luizalabs.quaklog.entity.GamesImported;
import br.com.luizalabs.quaklog.usecase.GameImporterUseCase;
import lombok.val;

import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

import static java.lang.System.out;

@Named
class GameImporterUseCaseImpl implements GameImporterUseCase {

    @Override
    public GamesImported importGame(LocalDate gameDate, InputStream inputStream) throws IOException {
        val reader = new BufferedReader(new InputStreamReader(inputStream));
        test(reader);//TODO
        return GamesImported.empty();
    }

    private void test(BufferedReader reader) throws IOException {
        while (reader.ready()) {
            out.println(reader.readLine());
        }
    }

}
