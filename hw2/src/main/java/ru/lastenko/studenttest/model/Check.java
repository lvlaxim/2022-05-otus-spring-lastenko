package ru.lastenko.studenttest.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class Check {
    private final Question question;
    private final List<OfferedAnswer> offeredAnswers;

    public boolean hasAnswerOptions() {
        return offeredAnswers.size() > 1;
    }

    public List<OfferedAnswer> getRightAnswers() {
        return offeredAnswers.stream()
                .filter(OfferedAnswer::isCorrect)
                .collect(Collectors.toList());
    }
}
