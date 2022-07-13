package ru.lastenko.studenttest.service;

import ru.lastenko.studenttest.model.Check;

import java.util.Collection;
import java.util.List;

public interface CheckParser<T> {

    Check parseCheckFrom(T  rawCheck);

    List<Check> parseChecksFrom(Collection<T> rawChecks);

}
