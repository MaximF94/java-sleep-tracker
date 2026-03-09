package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;

public class MinSessionDuration implements SleepAnalysisResultFunction {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Минимальная продолжительность сессии (в минутах): ",
                    "Недостаточно данных");
        }

        Long minSession = sleepingSessions.stream()
                .map(session -> Duration.between(session.getStartSleep(), session.getEndSleep())
                        .toMinutes())
                .min(Long::compare)
                .orElse(0L);

        return new SleepAnalysisResult("Минимальная продолжительность сессии (в минутах): ", minSession);
    }
}
