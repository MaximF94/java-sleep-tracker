package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepCountFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество сессий сна: ",
                    "Недостаточно данных");
        }

        return new SleepAnalysisResult("Количество сессий сна: ", sleepingSessions.size());
    }
}
