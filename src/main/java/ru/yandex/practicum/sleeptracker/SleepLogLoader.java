package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SleepLogLoader {

    public static List<SleepingSession> loadDataFromFile(String filename) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            return fileReader.lines()
                    .map(line -> line.split(";"))
                    .filter(parts -> parts.length == 3)
                    .map(parts -> new SleepingSession(
                            LocalDateTime.parse(parts[0], formatter),
                            LocalDateTime.parse(parts[1], formatter),
                            SleepQuality.valueOf(parts[2]))
                    )
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
            return List.of();
        }

    }
}
