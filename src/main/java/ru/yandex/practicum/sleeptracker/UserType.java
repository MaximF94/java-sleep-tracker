package ru.yandex.practicum.sleeptracker;

public enum UserType {

    OWL("Сова"),
    LARK("Жаворонок"),
    PIGEON("Голубь");

    final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }
}
