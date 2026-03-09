package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class AverageSessionDuration implements SleepAnalysisResultFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах): ",
                    "Недостаточно данных");
        }

        Long averageSession = (long) sleepingSessions.stream()
                .map(session -> Duration.between(session.getStartSleep(), session.getEndSleep())
                        .toMinutes())
                .mapToLong(Long::longValue)
                .average()
                .orElse(0L);


        return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах): ", averageSession);
    }
}
