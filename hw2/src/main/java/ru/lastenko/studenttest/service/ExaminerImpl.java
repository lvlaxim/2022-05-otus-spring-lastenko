package ru.lastenko.studenttest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.lastenko.studenttest.model.Check;
import ru.lastenko.studenttest.model.OfferedAnswer;
import ru.lastenko.studenttest.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminerImpl implements Examiner {

    private final CheckService checkService;
    private final Assistant assistant;
    @Value("${threshold:2}")
    private final Integer threshold;


    @Override
    public void showAllChecks() {
        List<Check> checks = checkService.getAll();
        assistant.showChecks(checks);
    }

    @Override
    public void makeTest() {
        Student student = assistant.getStudent();
        Collection<Check> checks = checkService.getAll();
        checks.forEach(check -> {
            boolean checkPassed = makeCheck(check);
            if (checkPassed) {
                student.addScore();
            }
        });
        assistant.showStudentResult(student);
        boolean testPassed = student.getScore() > threshold;
        if (testPassed) {
            assistant.showGoodNews();
        } else {
            assistant.showBadNews();
        }
    }

    private boolean makeCheck(Check check) {
        assistant.showCheck(check);
        List<String> studentAnswers = assistant.getStudentAnswers();
        return areStudentAnswersRight(studentAnswers, check);
    }

    private boolean areStudentAnswersRight(List<String> studentAnswers, Check check) {
        List<String> rightAnswers = check.getRightAnswers().stream()
                .map(OfferedAnswer::getText)
                .collect(Collectors.toList());
        boolean correctAnswersCount = rightAnswers.size() == studentAnswers.size();
        boolean answersRight = studentAnswers.containsAll(rightAnswers);
        return correctAnswersCount && answersRight;
    }

}
