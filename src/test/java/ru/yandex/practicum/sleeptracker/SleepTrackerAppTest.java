package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {


    @Test
    public void testCountSessionFunctionIsNotNull() {
        SleepCountFunction function = new SleepCountFunction();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 6, 0), SleepQuality.GOOD) // Заканчивается ровно в 6:00
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(1, result.getResult());
    }

    @Test
    public void testCountSessionFunctionIsNull() {
        SleepCountFunction function = new SleepCountFunction();

        List<SleepingSession> sleepingSessions = new ArrayList<>();

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals("Недостаточно данных", result.getResult());
    }

    @Test
    public void testMaxSessionDurationIsNotNull() {
        MaxSessionDuration function = new MaxSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 10, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(390L, result.getResult());
    }

    @Test
    public void testMaxSessionDurationSameDuration() {
        MaxSessionDuration function = new MaxSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 6, 30),
                        LocalDateTime.of(2025, 10, 1, 13, 0), SleepQuality.GOOD)

        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(390L, result.getResult());
    }

    @Test
    public void testMinSessionDurationIsNotNull() {
        MinSessionDuration function = new MinSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 10, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(60L, result.getResult());
    }

    @Test
    public void testMinSessionDurationSameDuration() {
        MinSessionDuration function = new MinSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 6, 30),
                        LocalDateTime.of(2025, 10, 1, 13, 0), SleepQuality.GOOD)

        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(390L, result.getResult());
    }

    @Test
    public void testAverageSessionDurationIsNotNull() {
        AverageSessionDuration function = new AverageSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 10, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(230L, result.getResult());
    }

    @Test
    public void testAverageSessionDurationOneSession() {
        AverageSessionDuration function = new AverageSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 6, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(30L, result.getResult());
    }

    @Test
    public void testBadSessionDurationIsNotNull() {
        BadSessionDuration function = new BadSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.BAD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.BAD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 10, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(2, result.getResult());
    }

    @Test
    public void testBadSessionDurationIsNull() {
        BadSessionDuration function = new BadSessionDuration();

        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 12, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 10, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);
        assertEquals(0, result.getResult());
    }


    @Test
    public void testSleeplessNightBoundaryTimes() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Подсчет ночей с граничными значениями
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 5, 30),
                        LocalDateTime.of(2025, 10, 1, 6, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 6, 0),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(1L, result.getResult());
    }

    @Test
    public void testSleeplessNightStartOneDayEndTwoDay() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Тест на случай, когда сессия захватывает две даты
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 23, 30),
                        LocalDateTime.of(2025, 10, 2, 7, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(0L, result.getResult());
    }

    @Test
    public void testSleeplessNightIsSleeplessNight() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Тест на бессонную ночь
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 20, 30),
                        LocalDateTime.of(2025, 10, 21, 9, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 22, 13, 30),
                        LocalDateTime.of(2025, 10, 22, 16, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(1L, result.getResult());
    }

    @Test
    public void testSleeplessNightSessionsInOneDay() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Лег и проснулся до 12 и после 12
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 1, 0),
                        LocalDateTime.of(2025, 10, 20, 9, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 13, 30),
                        LocalDateTime.of(2025, 10, 20, 16, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(0L, result.getResult());
    }

    @Test
    public void testSleeplessNightInDifferentMonth() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Две сессии сна с переходом в следующий месяц
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 31, 4, 30),
                        LocalDateTime.of(2025, 10, 31, 11, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 11, 1, 1, 30),
                        LocalDateTime.of(2025, 11, 1, 11, 0), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(0L, result.getResult());
    }

    @Test
    public void testSleeplessNightFirstSessionStartsAtMidnight() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Две сессии сна, где первая сессия начинается после 00:00
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 0, 30),
                        LocalDateTime.of(2025, 10, 2, 8, 0), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.of(2025, 10, 2, 23, 30),
                        LocalDateTime.of(2025, 10, 3, 7, 30), SleepQuality.GOOD)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals(0L, result.getResult());
    }

    @Test
    public void testSleeplessNightIsNull() {
        SleeplessNightCountFunction function = new SleeplessNightCountFunction();

        //Сессии сна отсутствуют
        List<SleepingSession> sleepingSessions = new ArrayList<>();

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals("Недостаточно данных", result.getResult());
    }

    @Test
    public void testUserTypeFunctionIsOwl() {
        UserTypeFunction function = new UserTypeFunction();

        //лёг после 23:00 и проснулся после 9:00. Без учета дневных сессий
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 1, 0),
                        LocalDateTime.of(2025, 10, 20, 12, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 13, 30),
                        LocalDateTime.of(2025, 10, 20, 16, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 23, 30),
                        LocalDateTime.of(2025, 10, 21, 10, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals("Сова", result.getResult());

    }

    @Test
    public void testUserTypeFunctionIsLark() {
        UserTypeFunction function = new UserTypeFunction();

        //лёг до 22:00 и проснулся до 7:00. Без учета дневных сессий
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 19, 21, 0),
                        LocalDateTime.of(2025, 10, 20, 6, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 13, 30),
                        LocalDateTime.of(2025, 10, 20, 16, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 19, 30),
                        LocalDateTime.of(2025, 10, 21, 5, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals("Жаворонок", result.getResult());

    }

    @Test
    public void testUserTypeFunctionIsPigeon() {
        UserTypeFunction function = new UserTypeFunction();

        //лёг до 22:00 и проснулся до 7:00 первые 2 дня и последующие после 22:00 и проснулся после 7:00.
        List<SleepingSession> sleepingSessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 19, 21, 0),
                        LocalDateTime.of(2025, 10, 20, 6, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 13, 30),
                        LocalDateTime.of(2025, 10, 20, 16, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 20, 23, 30),
                        LocalDateTime.of(2025, 10, 21, 8, 0), SleepQuality.NORMAL),
                new SleepingSession(LocalDateTime.of(2025, 10, 21, 23, 30),
                        LocalDateTime.of(2025, 10, 22, 8, 0), SleepQuality.NORMAL)
        );

        SleepAnalysisResult result = function.apply(sleepingSessions);

        assertEquals("Голубь", result.getResult());
    }

}