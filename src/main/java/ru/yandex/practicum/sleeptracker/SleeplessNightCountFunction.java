package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class SleeplessNightCountFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    private static final LocalTime NIGHT_START = LocalTime.of(0, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);
    private static final LocalTime FIRST_SESSION_START_TIME = LocalTime.of(12, 0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей: ", "Недостаточно данных");
        }

        Predicate<SleepingSession> isSleepful = s -> {
            LocalTime start = s.getStartSleep().toLocalTime();
            LocalTime end = s.getEndSleep().toLocalTime();
            return s.getStartSleep().toLocalDate().isBefore(s.getEndSleep().toLocalDate()) ||
                    (start.isBefore(NIGHT_END) && end.isAfter(NIGHT_START));
        };

        Function<SleepingSession, LocalDate> toNightDate = s ->
                FIRST_SESSION_START_TIME.isAfter(s.getStartSleep().toLocalTime())
                        ? s.getStartSleep().toLocalDate().minusDays(1)
                        : s.getStartSleep().toLocalDate();

        long totalNights = ChronoUnit.DAYS.between(
                sleepingSessions.getFirst().getStartSleep().toLocalDate(),
                sleepingSessions.getLast().getEndSleep().toLocalDate());

        if (sleepingSessions.getFirst().getStartSleep().toLocalTime().isBefore(FIRST_SESSION_START_TIME)) {
            totalNights++;
        }

        long sleepNightsCount = sleepingSessions.stream()
                .filter(isSleepful)
                .map(toNightDate)
                .distinct()
                .count();

        return new SleepAnalysisResult(
                "Количество бессонных ночей: ",
                totalNights - sleepNightsCount
        );
    }
}
