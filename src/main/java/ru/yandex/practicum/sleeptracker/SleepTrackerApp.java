package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SleepTrackerApp {

    public static void main(String[] args) {
        ClassLoader classLoader = SleepLogLoader.class.getClassLoader();
        String filename = classLoader.getResource("sleep_log.txt").getFile();
        List<SleepingSession> sleepingSessions = SleepLogLoader.loadDataFromFile(filename);

        List<Function> functions = new ArrayList<>();
        functions.add(new SleepCountFunction());
        functions.add(new MinSessionDuration());
        functions.add(new MaxSessionDuration());
        functions.add(new AverageSessionDuration());
        functions.add(new BadSessionDuration());
        functions.add(new SleeplessNightCountFunction());
        functions.add(new UserTypeFunction());

        functions.stream()
                .map(function -> function.apply(sleepingSessions))
                .peek(System.out::println)
                .collect(Collectors.toList());


    }
}