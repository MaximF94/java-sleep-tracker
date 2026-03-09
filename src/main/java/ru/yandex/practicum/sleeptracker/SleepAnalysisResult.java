package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    String description;
    Object result;

    public SleepAnalysisResult(String description, Object result) {
        this.description = description;
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public Object getResult() {
        return result;
    }

    @Override
    public String toString() {
        return description + result;
    }
}
