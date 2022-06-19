package ru.lastenko.studenttest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lastenko.studenttest.service.Examiner;

//@Configuration - не нужен здесь?
@ComponentScan
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        var examiner = context.getBean(Examiner.class);
        examiner.showAllChecks();

    }
}
