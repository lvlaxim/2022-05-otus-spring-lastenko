package ru.lastenko.studenttest.service;

import lombok.RequiredArgsConstructor;
import ru.lastenko.studenttest.model.Check;
import ru.lastenko.studenttest.model.OfferedAnswer;
import ru.lastenko.studenttest.model.Question;
import ru.lastenko.studenttest.model.Student;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleAssistant implements Assistant {

    private final Scanner studentInput;

    public void showChecks(List<Check> checks) {
        checks.forEach(this::showCheck);
    }

    public void showCheck(Check check) {
        showQuestion(check.getQuestion());
        if (check.hasAnswerOptions()) {
            showAnswerOptions(check.getOfferedAnswers());
        } else {
            System.out.println("There are no options");
        }
        System.out.println();
    }

    @Override
    public void showStudentResult(Student student) {
        var result = String.format("%s, your score is %d", student.getName(), student.getScore());
        System.out.println(result);
    }

    @Override
    public void showGoodNews() {
        System.out.println("Congratulations! Test passed.");
    }

    @Override
    public void showBadNews() {
        System.out.println("Sorry, you didn't pass the test.");
    }

    @Override
    public Student getStudent() {
        String name;
        String surname;
        System.out.println("Hi! please introduce yourself");
        System.out.println("your name?");
        name = studentInput.nextLine();
        System.out.println("your surname?");
        surname = studentInput.nextLine();
        return new Student(name, surname);
    }

    @Override
    public List<String> getStudentAnswers() {
        String studentAnswer;
        System.out.println("Please enter your answers separated by commas");
        studentAnswer = studentInput.nextLine();
        System.out.println();
        String[] studentAnswerAsArray = studentAnswer.split("\\s*,\\s*");
        return List.of(studentAnswerAsArray);
    }

    private void showQuestion(Question question) {
        System.out.println("Question:");
        System.out.println("\t" + question.getText());
    }

    private void showAnswerOptions(List<OfferedAnswer> offeredAnswers) {
        System.out.println("Options:");
        for (OfferedAnswer offeredAnswer : offeredAnswers) {
            System.out.println("\t" + offeredAnswer.getText());
        }
    }
}
