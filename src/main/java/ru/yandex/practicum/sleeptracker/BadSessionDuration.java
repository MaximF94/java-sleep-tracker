package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество сессий с плохим качеством сна: ",
                    "Недостаточно данных");
        }

        int badSessions = sleepingSessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .toList().size();

        return new SleepAnalysisResult("Количество сессий с плохим качеством сна: ", badSessions);
    }
}
