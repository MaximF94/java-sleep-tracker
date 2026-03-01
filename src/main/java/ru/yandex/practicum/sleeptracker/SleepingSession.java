package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
    private LocalDateTime startSleep;
    private LocalDateTime endSleep;
    private SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality sleepQuality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.sleepQuality = sleepQuality;
    }


    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
