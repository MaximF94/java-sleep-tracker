package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserTypeFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private static final LocalTime NIGHT_START = LocalTime.of(0, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    private static final LocalTime OWL_START = LocalTime.of(23, 0);
    private static final LocalTime OWL_END = LocalTime.of(9, 0);

    private static final LocalTime LARK_START = LocalTime.of(22, 0);
    private static final LocalTime LARK_END = LocalTime.of(7, 0);

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions == null || sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Тип пользователя: ", "Недостаточно данных");
        }

        Predicate<SleepingSession> isNightSession = s -> {
            LocalTime start = s.getStartSleep().toLocalTime();
            LocalTime end = s.getEndSleep().toLocalTime();
            return s.getStartSleep().toLocalDate().isBefore(s.getEndSleep().toLocalDate()) ||
                    (start.isBefore(NIGHT_END) && end.isAfter(NIGHT_START));
        };

        Function<SleepingSession, UserType> nightClassifier = s -> {
            LocalTime start = s.getStartSleep().toLocalTime();
            LocalTime end = s.getEndSleep().toLocalTime();
            boolean overnight = s.getStartSleep().toLocalDate().isBefore(s.getEndSleep().toLocalDate());

            if (overnight) {
                if (start.isAfter(OWL_START) && end.isAfter(OWL_END)) return UserType.OWL;
                if (start.isBefore(LARK_START) && end.isBefore(LARK_END)) return UserType.LARK;
                return UserType.PIGEON;
            }

            return end.isAfter(OWL_END) ? UserType.OWL : UserType.PIGEON;
        };

        Map<UserType, Long> typeCounts = sleepingSessions.stream()
                .filter(isNightSession)
                .collect(Collectors.groupingBy(
                        nightClassifier,
                        Collectors.counting()
                ));


        UserType dominantType = typeCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(UserType.PIGEON);

        return new SleepAnalysisResult("Тип пользователя: ", dominantType.displayName);
    }
}
