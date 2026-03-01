package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Максимальная продолжительность сессии (в минутах): ",
                    "Недостаточно данных");
        }

        Long maxSession = sleepingSessions.stream()
                .map(session -> Duration.between(session.getStartSleep(), session.getEndSleep())
                        .toMinutes())
                .max(Long::compare)
                .orElse(0L);

        return new SleepAnalysisResult("Максимальная продолжительность сессии (в минутах): ", maxSession);
    }
}
