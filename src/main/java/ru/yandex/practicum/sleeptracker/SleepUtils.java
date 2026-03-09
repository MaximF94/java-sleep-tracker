package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;

public class SleepUtils {
    private static final LocalTime NIGHT_START = LocalTime.of(0, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    private SleepUtils() {
    }

    public static boolean isNightSession(SleepingSession session) {
        LocalTime start = session.getStartSleep().toLocalTime();
        LocalTime end = session.getEndSleep().toLocalTime();
        return session.getStartSleep().toLocalDate().isBefore(session.getEndSleep().toLocalDate()) ||
                (start.isBefore(NIGHT_END) && end.isAfter(NIGHT_START));
    }
}
