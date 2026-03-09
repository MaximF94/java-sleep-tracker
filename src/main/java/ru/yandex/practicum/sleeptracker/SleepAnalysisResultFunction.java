package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public interface SleepAnalysisResultFunction extends Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    SleepAnalysisResult apply(List<SleepingSession> sleepingSessions);
}
