package br.com.luizalabs.quaklog.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GameKeysTest {

    @Test
    @Disabled
    void extractKeysFromFile() throws IOException {
        final Map<String, Integer> keyMap = new HashMap<>();
        final Pattern pattern = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");

        Path path = Paths.get("../../documentation/games.log");
        InputStream is = Files.newInputStream(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String value = matcher.group();
                    keyMap.put(value, keyMap.getOrDefault(value, 0) + 1);
                }
            }
        }
        Assertions.assertTrue(true);
    }


    @Test
    void extractModFromFile() throws IOException {
        final Map<String, Integer> keyMap = new HashMap<>();
        final Pattern pattern = Pattern.compile("(?<=:\\d\\d\\s)(.*?)(?=:)");

        Path path = Paths.get("../../documentation/games.log");
        InputStream is = Files.newInputStream(path);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String value = matcher.group();
                    keyMap.put(value, keyMap.getOrDefault(value, 0) + 1);
                }
            }
        }
        Assertions.assertTrue(true);
    }
}