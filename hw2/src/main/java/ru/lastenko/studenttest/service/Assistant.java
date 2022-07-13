package ru.lastenko.studenttest.service;

import ru.lastenko.studenttest.model.Check;
import ru.lastenko.studenttest.model.Student;

import java.util.List;

public interface Assistant {

    void showChecks(List<Check> checks);

    void showCheck(Check check);

    void showStudentResult(Student student);

    void showGoodNews();

    void showBadNews();

    Student getStudent();

    List<String> getStudentAnswers();
}
