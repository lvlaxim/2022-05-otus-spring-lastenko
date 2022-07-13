package ru.lastenko.studenttest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Student {
    private final String name;
    private final String surname;
    private int score;

    public void addScore() {
        score++;
    }
}
