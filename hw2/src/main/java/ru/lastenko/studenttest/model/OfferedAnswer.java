package ru.lastenko.studenttest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OfferedAnswer {
    private final String text;
    private final boolean correct;
}
