package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class SleepCountFunction implements SleepAnalysisResultFunction {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество сессий сна: ",
                    "Недостаточно данных");
        }

        return new SleepAnalysisResult("Количество сессий сна: ", sleepingSessions.size());
    }
}
